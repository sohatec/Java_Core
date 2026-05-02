import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Developer implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private String developerId;
    private String developerName;
    private int developerYear;
    private int numberProject;
    private String developerLanguage;
    private double developerSalary;
    private transient Scanner sc = new Scanner(System.in);
    private static List<Developer> listOfDeveloper = new ArrayList<>();

    public Developer() {
    }

    public Developer(String developerId, String developerName, int developerYear, String developerLanguage, double developerSalary) {
        this.developerId = developerId;
        this.developerName = developerName;
        this.developerYear = developerYear;
        this.developerLanguage = developerLanguage;
        this.developerSalary = developerSalary;
    }

    public String getDeveloperId() {
        return developerId;
    }
    public String getDeveloperName() {
        return developerName;
    }
    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }
    public int getDeveloperYear() {
        return developerYear;
    }
    public void setDeveloperYear(int developerYear) {
        this.developerYear = developerYear;
    }
    public String getDeveloperLanguage() {
        return developerLanguage;
    }
    public void setDeveloperLanguage(String developerLanguage) {
        this.developerLanguage = developerLanguage;
    }
    public double getDeveloperSalary() {
        return developerSalary;
    }
    public void setDeveloperSalary(double developerSalary) {
        this.developerSalary = developerSalary;
    }
    public int getNumberProject() {
        return numberProject;
    }
    public void setNumberProject(int numberProject) {
        this.numberProject = numberProject;
    }
    private void checkScanner() {
        if (this.sc == null) this.sc = new Scanner(System.in);
    }

    public void createDeveloper(){
        checkScanner();
        System.out.printf("\n|%-27s| ", "Input number of developer");
        int number = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < number; i++){
            String id;
            if(listOfDeveloper.isEmpty()){
                id = "DEV000";
            }else{
                Developer lastDev = listOfDeveloper.get(listOfDeveloper.size() - 1);
                int lastNumberId = Integer.parseInt(lastDev.getDeveloperId().substring(3));
                id = String.format("DEV%03d", lastNumberId + 1);
            }
            System.out.printf("\nAdding developer at --" + (i + 1) + "--");
            System.out.printf("\n|%-10s| ", "Name");
            String name = sc.nextLine();
            int year;
            while(true){
                System.out.printf("|%-10s| ", "Year");
                year = Integer.parseInt(sc.nextLine());
                if(year < 1950 || year > 2026){
                    continue;
                }else{
                    break;
                }
            }
            System.out.printf("|%-10s| ", "Language");
            String language = sc.nextLine();
            System.out.printf("|%-10s| ", "Salary");
            double salary = Double.parseDouble(sc.nextLine());
            listOfDeveloper.add(new Developer(id, name, year, language, salary));
        }
    }

    public void searchDeveloperId(){
        checkScanner();
        System.out.printf("\nInput ID of developer to search");
        System.out.printf("\n|%-10s| ", "ID");
        String id = sc.nextLine();
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperId().compareTo(id) == 0){
                System.out.printf(listOfDeveloper.get(i).toString());
                flag = 1;
                return;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void searchDeveloperName(){
        checkScanner();
        System.out.println("\nInput name of developer to search");
        System.out.printf("|%-10s| ", "Name");
        String name = sc.nextLine();
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperName().compareTo(name) == 0){
                System.out.printf(listOfDeveloper.get(i).toString());
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Name is not exist !!!");
    }

    public void searchDeveloperYear(){
        checkScanner();
        System.out.println("\nInput year of developer to search");
        System.out.printf("|%-10s| ", "Year");
        int year = Integer.parseInt(sc.nextLine());
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperYear() == year){
                System.out.printf(listOfDeveloper.get(i).toString());
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Year is not exist !!!");
    }

    public void searchDeveloperLanguage(){
        checkScanner();
        System.out.println("\nInput language of developer to search");
        System.out.printf("|%-10s| ", "Language");
        String language = sc.nextLine();
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperLanguage().compareTo(language) == 0){
                System.out.printf(listOfDeveloper.get(i).toString());
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Language is not exist !!!");
    }

    public void searchDeveloperSalary(){
        checkScanner();
        System.out.println("\nInput salary of developer to search");
        System.out.printf("|%-10s| ", "Salary");
        double salary = Double.parseDouble(sc.nextLine());
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperSalary() == salary){
                System.out.printf(listOfDeveloper.get(i).toString());
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Salary is not exist !!!");
    }

    public void searchNumberProject(){
        checkScanner();
        System.out.println("\nInput number of project of developer to search");
        System.out.printf("|%-10s| ", "Number");
        int number = Integer.parseInt(sc.nextLine());
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getNumberProject() == number){
                System.out.printf(listOfDeveloper.get(i).toString());
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Number is not exist !!!");
    }

    public void sortDeveloperName(){
        checkScanner();
        List<Developer> sortName = listOfDeveloper.stream()
                .sorted(Comparator.comparing(content -> content.getDeveloperName()))
                .collect(Collectors.toList());
        sortName.forEach(content -> System.out.printf(content.toString()));
    }

    public void sortDeveloperYear(){
        checkScanner();
        List<Developer> sortYear = listOfDeveloper.stream()
                .sorted(Comparator.comparing(content -> content.getDeveloperYear()))
                .collect(Collectors.toList());
        sortYear.forEach(content -> System.out.printf(content.toString()));
    }

    public void sortDeveloperSalary(){
        checkScanner();
        List<Developer> sortSalary = listOfDeveloper.stream()
                .sorted(Comparator.comparing(content -> content.getDeveloperSalary()))
                .collect(Collectors.toList());
        sortSalary.forEach(content -> System.out.printf(content.toString()));
    }

    public void updateDeveloperName(){
        checkScanner();
        System.out.printf("\nInput ID of developer to update: ");
        String id = sc.nextLine();
        System.out.printf("Input new name of developer to update");
        System.out.printf("\n|%-10s| ", "Name");
        String newName = sc.nextLine();
        int flag = 0;
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperId().compareTo(id) == 0){
                listOfDeveloper.get(i).setDeveloperName(newName);
                System.out.println("Update successfully !!!");
                flag = 1;
                return;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void updateDeveloperYear(){
        checkScanner();
        System.out.printf("\nInput ID of developer to update: ");
        String id = sc.nextLine();
        System.out.printf("Input new year of developer to update\n");
        int newYear;
        while(true){
            System.out.printf("|%-10s| ", "Year");
            newYear = Integer.parseInt(sc.nextLine());
            if(newYear < 1950 || newYear > 2026){
                continue;
            }else{
                break;
            }
        }
        int flag = 0;
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperId().compareTo(id) == 0){
                listOfDeveloper.get(i).setDeveloperYear(newYear);
                System.out.println("Update successfully !!!");
                flag = 1;
                return;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void updateDeveloperLanguage(){
        checkScanner();
        System.out.printf("\nInput ID of developer to update: ");
        String id = sc.nextLine();
        System.out.printf("Input new language of developer to update");
        System.out.printf("\n|%-10s| ", "Language");
        String newLanguage = sc.nextLine();
        int flag = 0;
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperId().compareTo(id) == 0){
                listOfDeveloper.get(i).setDeveloperLanguage(newLanguage);
                System.out.println("Update successfully !!!");
                flag = 1;
                return;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void updateDeveloperSalary(){
        checkScanner();
        System.out.printf("\nInput ID of developer to update: ");
        String id = sc.nextLine();
        System.out.printf("Input new salary of developer to update");
        System.out.printf("\n|%-10s| ", "Salary");
        double newSalary = Double.parseDouble(sc.nextLine());
        int flag = 0;
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperId().compareTo(id) == 0){
                listOfDeveloper.get(i).setDeveloperSalary(newSalary);
                System.out.println("Update successfully !!!");
                flag = 1;
                return;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void deleteDeveloper(){
        checkScanner();
        System.out.println("\nInput ID of developer to delete");
        System.out.printf("|%-10s| ", "ID");
        String id = sc.nextLine();
        int flag = 0;
        for(int i = 0; i < Developer.listOfDeveloper.size(); i++){
            if(listOfDeveloper.get(i).getDeveloperId().compareTo(id) == 0 &&
               listOfDeveloper.get(i).getNumberProject() == 0){
                listOfDeveloper.remove(i);
                System.out.println("Delete successfully !!!");
                flag = 1;
                break;
            }else if(listOfDeveloper.get(i).getDeveloperId().compareTo(id) == 0 &&
                    listOfDeveloper.get(i).getNumberProject() >= 1){
                System.out.println("This developer is building " + listOfDeveloper.get(i).getNumberProject() + " projects");
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public static List<Developer> getListOfDeveloper(){
        return listOfDeveloper;
    }

    public void showDeveloperProfile(){
        checkScanner();
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
        for(int i = 0; i < Developer.getListOfDeveloper().size(); i++){
            System.out.print(listOfDeveloper.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return String.format("|%-10s|%-30s|%7d|%15s|%25.2f|%12d|\n", developerId, developerName, developerYear, developerLanguage, developerSalary, numberProject);
    }
}