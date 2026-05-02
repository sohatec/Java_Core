import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private String projectId;
    private String projectName;
    private int projectDuration;
    private int projectStartDay;
    private int projectStartMonth;
    private int projectStartYear;
    private String developerId;
    private transient Scanner sc = new Scanner(System.in);
    private static List<Project> listOfProject = new ArrayList<>();

    public Project() {
    }

    public Project(String projectId,String projectName, int projectDuration, int projectStartDay, int projectStartMonth, int projectStartYear, String developerId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDuration = projectDuration;
        this.projectStartDay = projectStartDay;
        this.projectStartMonth = projectStartMonth;
        this.projectStartYear = projectStartYear;
        this.developerId = developerId;
    }

    public String getProjectId() {
        return projectId;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public int getProjectDuration() {
        return projectDuration;
    }
    public void setProjectDuration(int projectDuration) {
        this.projectDuration = projectDuration;
    }
    public int getProjectStartDay() {
        return projectStartDay;
    }
    public void setProjectStartDay(int projectStartDay) {
        this.projectStartDay = projectStartDay;
    }
    public int getProjectStartMonth() {
        return projectStartMonth;
    }
    public void setProjectStartMonth(int projectStartMonth) {
        this.projectStartMonth = projectStartMonth;
    }
    public int getProjectStartYear() {
        return projectStartYear;
    }
    public void setProjectStartYear(int projectStartYear) {
        this.projectStartYear = projectStartYear;
    }
    public String getDeveloperId() {
        return developerId;
    }

    private void checkScanner() {
        if (this.sc == null) this.sc = new Scanner(System.in);
    }

    public void createProject(){
        checkScanner();
        System.out.printf("\nInput number of project: ");
        int number = Integer.parseInt(sc.nextLine());
        int numberOfDev = Developer.getListOfDeveloper().size();
        int added = 0, timesFindId = 1;

        while(added < number){
            System.out.printf("\nInput ID of developer to receive project: ");
            String devId = sc.nextLine();
            int found = 0;
            for(int i = 0; i < numberOfDev; i++){
                if(Developer.getListOfDeveloper().get(i).getDeveloperId().compareTo(devId) == 0){
                    found = 1;
                    timesFindId = 1;
                    System.out.printf("Adding project at --" + (i + 1) + "--");
                    String id = String.format("PRO%03d", Project.listOfProject.size());
                    System.out.printf("\n|%-10s| ", "Name");
                    String name = sc.nextLine();
                    System.out.printf("|%-10s| ", "Duration");
                    int duration = Integer.parseInt(sc.nextLine());
                    int max = 31, flag = 0, day, month, year;
                    do{
                        System.out.printf("|%-10s| ", "Time");
                        day = sc.nextInt();
                        month = sc.nextInt();
                        year = sc.nextInt();
                        sc.nextLine();
                        if(day < 1 ||
                                day > 31 ||
                                month < 1 ||
                                month > 12 ||
                                year < 2026){
                            continue;
                        }
                        if(month == 4 ||
                                month == 6 ||
                                month == 9 ||
                                month == 11){
                                max = 30;
                        }else if(month == 2){
                            if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)){
                                max = 29;
                            }else {
                                max = 28;
                            }
                        }
                        if(day >= 1 && day <= max) flag = 1;
                    }while(flag == 0);
                    listOfProject.add(new Project(id, name, duration, day, month, year, devId));
                    int numPro = Developer.getListOfDeveloper().get(i).getNumberProject();
                    Developer.getListOfDeveloper().get(i).setNumberProject(numPro + 1);
                    added += 1;
                }
            }
            if(found == 0) System.out.println("*** NOT FOUND THIS DEVELOPER'S ID ***");
            if(timesFindId == 3) break;
            timesFindId += 1;
        }
    }

    public void searchProjectId(){
        checkScanner();
        System.out.printf("\nInput ID of project to search: ");
        String id = sc.nextLine();
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-10s|%-15s|%-10s|\n", "PRO ID", "Name", "Duration", "Start Time", "DEV Id");
        for(int i = 0; i < Project.listOfProject.size(); i++){
            if(listOfProject.get(i).getProjectId().compareTo(id) == 0){
                System.out.printf(listOfProject.get(i).toString());
                flag = 1;
                return;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void searchProjectDuration(){
        checkScanner();
        System.out.printf("\nInput duration of project to search: ");
        int duration = Integer.parseInt(sc.nextLine());
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-10s|%-15s|%-10s|\n", "PRO ID", "Name", "Duration", "Start Time", "DEV Id");
        for(int i = 0; i < Project.listOfProject.size(); i++){
            if(listOfProject.get(i).getProjectDuration() == duration){
                System.out.printf(listOfProject.get(i).toString());
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Duration is not exist !!!");
    }

    public void searchProjectStartTime(){
        checkScanner();
        System.out.printf("Input start time of project to search: ");
        int day = sc.nextInt();
        int month = sc.nextInt();
        int year = sc.nextInt();
        sc.nextLine();
        int flag = 0;
        System.out.printf("\n|%-10s|%-30s|%-10s|%-15s|%-10s|\n", "PRO ID", "Name", "Duration", "Start Time", "DEV Id");
        for(int i = 0; i < Project.listOfProject.size(); i++){
            if(listOfProject.get(i).getProjectStartDay() == day &&
               listOfProject.get(i).getProjectStartMonth() == month &&
               listOfProject.get(i).getProjectStartYear() == year){
                System.out.printf(listOfProject.get(i).toString());
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Start time is not exist !!!");
    }

    public void updateProjectName(){
        checkScanner();
        System.out.printf("\nInput ID of project to update name: ");
        String id = sc.nextLine();
        String newName;
        int flag = 0;
        for(int i = 0; i < Project.listOfProject.size(); i++){
            if(listOfProject.get(i).getProjectId().compareTo(id) == 0){
                System.out.println("Input new name of project");
                newName = sc.nextLine();
                listOfProject.get(i).setProjectName(newName);
                System.out.println("Update Successfully !!!");
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void updateProjectDuration(){
        checkScanner();
        System.out.printf("\nInput ID of project to update duartion: ");
        String id = sc.nextLine();
        int newDuration;
        int flag = 0;
        for(int i = 0; i < Project.listOfProject.size(); i++){
            if(listOfProject.get(i).getProjectId().compareTo(id) == 0){
                System.out.println("Input new duration of project");
                newDuration = Integer.parseInt(sc.nextLine());
                listOfProject.get(i).setProjectDuration(newDuration);
                System.out.println("Update Successfully !!!");
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void updateProjectStartTime(){
        checkScanner();
        System.out.printf("\nInput ID of project to update start time: ");
        String id = sc.nextLine();
        int flag = 0, day, month, year;
        for(int i = 0; i < Project.listOfProject.size(); i++){
            if(listOfProject.get(i).getProjectId().compareTo(id) == 0){
                System.out.println("Input new start time of project");
                day = sc.nextInt();
                month = sc.nextInt();
                year = sc.nextInt();
                sc.nextLine();
                listOfProject.get(i).setProjectStartDay(day);
                listOfProject.get(i).setProjectStartMonth(month);
                listOfProject.get(i).setProjectStartYear(year);
                System.out.println("Update Successfully !!!");
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("ID is not exist !!!");
    }

    public void totalExperienceTime() {
        checkScanner();
        System.out.printf("\nInput ID of developer to calculator experience time: ");
        String devId = sc.nextLine();
        int sum = 0, flag = 0;
        for (int i = 0; i < Project.listOfProject.size(); i++) {
            if(listOfProject.get(i).getDeveloperId().compareTo(devId) == 0){
                sum = sum + listOfProject.get(i).getProjectDuration();
                flag = 1;
            }
        }
        if(flag == 0){
            System.out.println("ID is not exist !!!");
        }else if(flag == 1){
            System.out.println("Total experience time: " + sum + " moths");
        }
    }

    public static List<Project> getListOfProject(){
        return listOfProject;
    }

    public void showProjectProfile(){
        checkScanner();
        System.out.printf("\n|%-10s|%-30s|%-10s|%-15s|%-10s|\n", "PRO ID", "Name", "Duration", "Start Time", "DEV Id");
        for(int i = 0; i < Project.listOfProject.size(); i++){
            System.out.print(listOfProject.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return String.format("|%-10s|%-30s|%10d|     %2d/%2d/%4d|%-10s|\n", projectId, projectName, projectDuration, projectStartDay, projectStartMonth, projectStartYear, developerId);
    }
}