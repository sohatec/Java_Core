package com.sohatec;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class SeniorStaff {
    private String seniorId;
    private String seniorName;
    private String seniorEmail;
    private String seniorLanguage;
    private Scanner sc = new Scanner(System.in);

    public SeniorStaff() {
    }

    public SeniorStaff(String seniorId, String seniorName, String seniorEmail, String seniorLanguage) {
        this.seniorId = seniorId;
        this.seniorName = seniorName;
        this.seniorEmail = seniorEmail;
        this.seniorLanguage = seniorLanguage;
    }

    public String getSeniorId() {return seniorId;}
    public void setSeniorId(String seniorId) {this.seniorId = seniorId;}
    public String getSeniorName() {return seniorName;}
    public void setSeniorName(String seniorName) {this.seniorName = seniorName;}
    public String getSeniorEmail() {return seniorEmail;}
    public void setSeniorEmail(String seniorEmail) {this.seniorEmail = seniorEmail;}
    public String getSeniorLanguage() {return seniorLanguage;}
    public void setSeniorLanguage(String seniorLanguage) {this.seniorLanguage = seniorLanguage;}

    public void createNewOneSenior(){
        System.out.println("=== Input Number Of Senior Staff ===");
        int numberOf = Integer.parseInt(sc.nextLine());
        String id = "SEN000";
        String sql = "SELECT TOP 1 ID FROM Senior ORDER BY ID DESC";
        for(int i = 0; i < numberOf; i++){
            try(Connection conn = SQLServerProvider.getDatabaseSeniorStaff();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    String lastID = rs.getString("ID");
                    String numberPart = lastID.substring(3);
                    int number = Integer.parseInt(numberPart);
                    number++;
                    id = String.format("SEN%03d", number);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.printf("\nAdding Senior Staff At ==" + (i + 1) + "==");
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
            SeniorStaff newSenior = new SeniorStaff(id, name, email, language);
            System.out.printf("\n=== INPUT CONTENT OF SENIOR STAFF ===");
            System.out.printf("\n|%-12s| ", "Content");
            String newContent = sc.nextLine();
            String generatedIdStr = SQLServerProvider.saveSeniorStaffToSQL(newSenior);
            if (generatedIdStr != null) {
                MongoDBProvider.saveSeniorStaffToMongo(generatedIdStr, newContent);
                System.out.println("\n=== Successfully ===");
            } else {
                System.out.println("\n=== Failed ===");
            }
        }
    }

    public void searchIdSenior(){
        System.out.print("=== Input ID OF Senior Staff To Search: ");
        String idToSearch = sc.nextLine();
        SeniorStaff senior = SQLServerProvider.getSeniorById(idToSearch);
        if (senior != null) {
            System.out.println("\n=== INFORMATION SENIOR STAFF ===");
            System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n", "ID", "Name", "Email", "Language");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n",
                    senior.getSeniorId(),
                    senior.getSeniorName(),
                    senior.getSeniorEmail(),
                    senior.getSeniorLanguage());
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("=> Not Found Senior Staff With ID: " + idToSearch);
        }
    }

    public void updateNameSenior(){
        System.out.print("Input ID To Update Name: ");
        String id = sc.nextLine();
        System.out.print("Input New Name: ");
        String newName = sc.nextLine();

        if (SQLServerProvider.updateSeniorField(id, "Name", newName)) {
            System.out.println("=== Update Name Successfully ===");
        } else {
            System.out.println("=== Updating Failed. Please Check ID Again ===");
        }
    }

    public void updateEmailSenior(){
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

        if (SQLServerProvider.updateSeniorField(id, "Email", newEmail)) {
            System.out.println("=== Update Email Successfully ===");
        } else {
            System.out.println("=== Updating Failed. Please Check ID Again ===");
        }
    }

    public void updateLanguageSenior(){
        System.out.print("Input ID To Update Language: ");
        String id = sc.nextLine();
        System.out.print("Input New Language: ");
        String newLang = sc.nextLine();

        if (SQLServerProvider.updateSeniorField(id, "Language", newLang)) {
            System.out.println("=== Update Language Successfully ===");
        } else {
            System.out.println("=== Updating Failed. Please Check ID Again ===");
        }
    }

    public void deleteOneSenior(){
        System.out.print("Input ID To Delete Senior: ");
        String id = sc.nextLine();
        System.out.print("Are You Sure To Delete " + id + " Y/N: ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            if (SQLServerProvider.deleteSeniorFromSQL(id)) {
                System.out.println("=== Delete Senior Successfully: " + id + " ===");
            } else {
                System.out.println("=== Not Found ID ===");
            }
        }
    }

    public void showListSenior(){
        System.out.println("\n========================== LIST OF SENIOR STAFF ==========================");
        System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n", "ID", "Name", "Email", "Language");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
        List<SeniorStaff> list = SQLServerProvider.getAllSeniorStaff();
        if (list == null || list.isEmpty()) {
            System.out.println("|                      ( List Is Empty )                                     |");
        } else {
            for (SeniorStaff staff : list) {
                System.out.printf("| %-10s | %-40s | %-50s | %-40s |\n",
                        staff.getSeniorId(),
                        staff.getSeniorName(),
                        staff.getSeniorEmail(),
                        staff.getSeniorLanguage());
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void addingReportSenior() {
        System.out.print("=== Input Senior's ID To Add Report: ");
        String id = sc.nextLine();
        SeniorStaff senior = SQLServerProvider.getSeniorById(id);
        if (senior != null) {
            System.out.print("=== Input Content: ");
            String content = sc.nextLine();
            MongoDBProvider.saveSeniorStaffToMongo(id, content);
            System.out.println("=== Added Report For Senior ID: " + id + " ===");
        } else {
            System.out.println("=== Error: ID " + id + " Is Not Exist In SQL Server ===");
        }
    }

    public void searchReportSenior() {
        System.out.print("=== Input Senior's ID To Search Report: ");
        String id = sc.nextLine();
        if (SQLServerProvider.getSeniorById(id) != null) {
            MongoCollection<Document> collection = MongoDBProvider.getDatabaseSeniorStaff().getCollection("Content");
            FindIterable<Document> docs = collection.find(Filters.eq("seniorId", id));
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

    public void deleteReportSenior() {
        System.out.print("=== Input Senior's ID To Delete Report: ");
        String id = sc.nextLine();
        if (SQLServerProvider.getSeniorById(id) != null) {
            MongoCollection<Document> collection = MongoDBProvider.getDatabaseSeniorStaff().getCollection("Content");
            long deletedCount = collection.deleteOne(Filters.eq("seniorId", id)).getDeletedCount();
            if (deletedCount > 0) {
                System.out.println("=== Delete Report Of ID " + id + " Successfully ===");
            } else {
                System.out.println("=== This ID Does Not Have Report In Database To Delete ===");
            }
        } else {
            System.out.println("=== Error: ID Is Not Exist In SQL Server.");
        }
    }
}
