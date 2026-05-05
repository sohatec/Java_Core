package com.sohatec;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class JuniorStaff {
    private String juniorId;
    private String juniorName;
    private String juniorEmail;
    private String juniorLanguage;
    private Scanner sc = new Scanner(System.in);

    public JuniorStaff() {
    }

    public JuniorStaff(String juniorId, String juniorName, String juniorEmail, String juniorLanguage) {
        this.juniorId = juniorId;
        this.juniorName = juniorName;
        this.juniorEmail = juniorEmail;
        this.juniorLanguage = juniorLanguage;
    }

    public String getJuniorId() {
        return juniorId;
    }
    public void setJuniorId(String juniorId) {
        this.juniorId = juniorId;
    }
    public String getJuniorName() {
        return juniorName;
    }
    public void setJuniorName(String juniorName) {
        this.juniorName = juniorName;
    }
    public String getJuniorEmail() {
        return juniorEmail;
    }
    public void setJuniorEmail(String juniorEmail) {
        this.juniorEmail = juniorEmail;
    }
    public String getJuniorLanguage() {
        return juniorLanguage;
    }
    public void setJuniorLanguage(String juniorLanguage) {
        this.juniorLanguage = juniorLanguage;
    }

    public void createNewOneJunior(){
        System.out.println("=== Input Number Of Junior Staff ===");
        int numberOf = Integer.parseInt(sc.nextLine());
        String id = "JUN000";
        String sql = "SELECT TOP 1 ID FROM Junior ORDER BY ID DESC";
        for(int i = 0; i < numberOf; i++){
            try(Connection conn = SQLServerProvider.getDatabaseJuniorStaff();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String lastID = rs.getString("ID");
                    String numberPart = lastID.substring(3);
                    int number = Integer.parseInt(numberPart);
                    number++;
                    id = String.format("JUN%03d", number);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.printf("\nAdding Junior Staff At ==" + (i + 1) + "==");
            System.out.printf("\n|%-10s| ", "Name");
            String name = sc.nextLine();
            int flag;
            String email;
            do{
                flag = 0;
                System.out.printf("|%-10s| ", "Email");
                email = sc.nextLine();
                if(email.contains("@gmail.com")) flag = 1;
            }while(flag == 0);
            System.out.printf("|%-10s| ", "Language");
            String language = sc.nextLine();
            JuniorStaff newJunior = new JuniorStaff(id, name, email, language);
            System.out.printf("\n=== INPUT CONTENT OF JUNIOR STAFF ===");
            System.out.printf("\n|%-12s| ", "Content");
            String newContent = sc.nextLine();
            String generatedIdStr = SQLServerProvider.saveJuniorStaffToSQL(newJunior);
            if (generatedIdStr != null) {
                MongoDBProvider.saveJuniorStaffToMongo(generatedIdStr, newContent);
                System.out.println("\n=== Successfully ===");
            } else {
                System.out.println("\n=== Failed ===");
            }
        }
    }
    public void searchIdJunior(){
        System.out.print("=== Input ID OF Junior Staff To Search: ");
        String idToSearch = sc.nextLine();
        JuniorStaff junior = SQLServerProvider.getJuniorById(idToSearch);
        if (junior != null) {
            System.out.println("\n=== INFORMATION JUNIOR STAFF ===");
            System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n", "ID", "Name", "Email", "Language");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n",
                    junior.getJuniorId(),
                    junior.getJuniorName(),
                    junior.getJuniorEmail(),
                    junior.getJuniorLanguage());
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("=> Not Found Junior Staff With ID: " + idToSearch);
        }
    }
    public void updateNameJunior(){
        System.out.print("Input ID To Update Name: ");
        String id = sc.nextLine();
        System.out.print("Input New Name: ");
        String newName = sc.nextLine();

        if (SQLServerProvider.updateJuniorField(id, "Name", newName)) {
            System.out.println("=== Update Name Successfully ===");
        } else {
            System.out.println("=== Updating Failed. Please Check ID Again ===");
        }
    }

    public void updateEmailJunior(){
        System.out.print("Input ID To Update Email: ");
        String id = sc.nextLine();
        String newEmail;
        int flag;
        do {
            flag = 0;
            System.out.print("Input New Email: ");
            newEmail = sc.nextLine();
            if (newEmail.contains("@gmail.com")) flag = 1;
        } while (flag == 0);
        if (SQLServerProvider.updateJuniorField(id, "Email", newEmail)) {
            System.out.println("=== Update Email Successfully ===");
        } else {
            System.out.println("=== Updating Failed. Please Check ID Again ===");
        }
    }

    public void updateLanguageJunior(){
        System.out.print("Input ID To Update Language: ");
        String id = sc.nextLine();
        System.out.print("Input New Language: ");
        String newLang = sc.nextLine();

        if (SQLServerProvider.updateJuniorField(id, "Language", newLang)) {
            System.out.println("=== Update Language Successfully ===");
        } else {
            System.out.println("=== Updating Failed. Please Check ID Again ===");
        }
    }

    public void deleteOneJunior(){
        System.out.print("Input ID To Delete Junior: ");
        String id = sc.nextLine();
        System.out.print("Are You Sure To Delete " + id + " Y/N: ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            if (SQLServerProvider.deleteJuniorFromSQL(id)) {
                System.out.println("=== Delete Junior Successfully: " + id + " ===");
            } else {
                System.out.println("=== Not Found ID ===");
            }
        }
    }

    public void showListJunior(){
        System.out.println("\n========================== LIST OF JUNIOR STAFF ==========================");
        System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n", "ID", "Name", "Email", "Language");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        List<JuniorStaff> list = SQLServerProvider.getAllJuniorStaff();
        if (list == null || list.isEmpty()) {
            System.out.println("|                      ( List Is Empty )                                     |");
        } else {
            for (JuniorStaff staff : list) {
                System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n",
                        staff.getJuniorId(),
                        staff.getJuniorName(),
                        staff.getJuniorEmail(),
                        staff.getJuniorLanguage());
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void addingReportJunior() {
        System.out.print("=== Input Junior's ID To Add Report: ");
        String id = sc.nextLine();
        JuniorStaff junior = SQLServerProvider.getJuniorById(id);
        if (junior != null) {
            System.out.print("=== Input Content: ");
            String content = sc.nextLine();
            MongoDBProvider.saveJuniorStaffToMongo(id, content);
            System.out.println("=== Added Report For Junior ID: " + id + " ===");
        } else {
            System.out.println("=== Error: ID " + id + " Is Not Exist In SQL Server ===");
        }
    }

    public void searchReportJunior() {
        System.out.print("=== Input Junior's ID To Search Report: ");
        String id = sc.nextLine();
        if (SQLServerProvider.getJuniorById(id) != null) {
            MongoCollection<Document> collection = MongoDBProvider.getDatabaseJuniorStaff().getCollection("Content");
            FindIterable<Document> docs = collection.find(Filters.eq("juniorId", id));
            boolean found = false;
            System.out.println("\n=== Information Report for ID: " + id + " ===");
            for (Document doc : docs) {
                found = true;
                System.out.println("-------------------------------------------");
                System.out.println("Content  : " + doc.getString("content"));
                System.out.println("Timestamp: " + doc.get("timestamp"));
            }
            if (!found) {
                System.out.println("=== Not Found Content In MongoDB For This ID ===");
            }
            System.out.println("-------------------------------------------");
        } else {
            System.out.println("=== Error: ID Does Not Exist In SQL Server.");
        }
    }

    public void deleteReportJunior() {
        System.out.print("=== Input Junior's ID To Delete Report: ");
        String id = sc.nextLine();
        if (SQLServerProvider.getJuniorById(id) == null) {
            MongoCollection<Document> collection = MongoDBProvider.getDatabaseJuniorStaff().getCollection("Content");
            long deletedCount = collection.deleteMany(Filters.eq("juniorId", id)).getDeletedCount();
            if (deletedCount > 0) {
                System.out.println("=== Delete Report Of ID " + id + " Successfully ===");
            } else {
                System.out.println("=== This ID Does Not Have Report In Database To Delete ===");
            }
        } else {
            System.out.println("=== Error: ID Is Not Still Exist In SQL Server ===");
        }
    }
}
