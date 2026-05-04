import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Developer developer = new Developer();
        Project project = new Project();
        DataFile dataFile = new DataFile();
        Scanner sc = new Scanner(System.in);

        clearScreen();
        User user = downloadDatabase();
        menu.welcome();
        if(user == null){
            user = new User();
            user.createAccount();
            user.userLogin();
        }else{
             user.userLogin();
        }

        int choice, choice1, choice12, choice13, choice14, choice2, choice22,
            choice23,  choice3, choice4;
        do{
            clearScreen();
            menu.menu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    do{
                        clearScreen();
                        menu.menu1();
                        choice1 = Integer.parseInt(sc.nextLine());
                        switch (choice1){
                            case 1:
                                clearScreen();
                                developer.createDeveloper();
                                pressEnter();
                                break;
                            case 2:
                                do{
                                    clearScreen();
                                    menu.menu12();
                                    choice12 = Integer.parseInt(sc.nextLine());
                                    switch (choice12){
                                        case 1:
                                            clearScreen();
                                            developer.searchDeveloperId();
                                            pressEnter();
                                            break;
                                        case 2:
                                            clearScreen();
                                            developer.searchDeveloperName();
                                            pressEnter();
                                            break;
                                        case 3:
                                            clearScreen();
                                            developer.searchDeveloperYear();
                                            pressEnter();
                                            break;
                                        case 4:
                                            clearScreen();
                                            developer.searchDeveloperLanguage();
                                            pressEnter();
                                            break;
                                        case 5:
                                            clearScreen();
                                            developer.searchDeveloperSalary();
                                            pressEnter();
                                            break;
                                        case 6:
                                            clearScreen();
                                            developer.searchNumberProject();
                                            pressEnter();
                                            break;
                                    }
                                }while(choice12 != 0);
                                break;
                            case 3:
                                do{
                                    clearScreen();
                                    menu.menu13();
                                    choice13 = Integer.parseInt(sc.nextLine());
                                    switch (choice13){
                                        case 1:
                                            clearScreen();
                                            developer.sortDeveloperName();
                                            pressEnter();
                                            break;
                                        case 2:
                                            clearScreen();
                                            developer.sortDeveloperYear();
                                            pressEnter();
                                            break;
                                        case 3:
                                            clearScreen();
                                            developer.sortDeveloperSalary();
                                            pressEnter();
                                            break;
                                    }
                                }while(choice13 != 0);
                                break;
                            case 4:
                                do{
                                    clearScreen();
                                    menu.menu14();
                                    choice14 = Integer.parseInt(sc.nextLine());
                                    switch (choice14){
                                        case 1:
                                            clearScreen();
                                            developer.updateDeveloperName();
                                            pressEnter();
                                            break;
                                        case 2:
                                            clearScreen();
                                            developer.updateDeveloperYear();
                                            pressEnter();
                                            break;
                                        case 3:
                                            clearScreen();
                                            developer.updateDeveloperLanguage();
                                            pressEnter();
                                            break;
                                        case 4:
                                            clearScreen();
                                            developer.updateDeveloperSalary();
                                            pressEnter();
                                            break;
                                    }
                                }while(choice14 != 0);
                                break;
                            case 5:
                                clearScreen();
                                developer.deleteDeveloper();
                                pressEnter();
                                break;
                            case 6:
                                clearScreen();
                                developer.showDeveloperProfile();
                                pressEnter();
                                break;
                        }
                    }while(choice1 != 0);
                    break;
                case 2:
                    do{
                        clearScreen();
                        menu.menu2();
                        choice2 = Integer.parseInt(sc.nextLine());
                        switch (choice2){
                            case 1:
                                clearScreen();
                                project.createProject();
                                pressEnter();
                                break;
                            case 2:
                                do{
                                    clearScreen();
                                    menu.menu22();
                                    choice22 = Integer.parseInt(sc.nextLine());
                                    switch (choice22){
                                        case 1:
                                            clearScreen();
                                            project.searchProjectId();
                                            pressEnter();
                                            break;
                                        case 2:
                                            clearScreen();
                                            project.searchProjectDuration();
                                            pressEnter();
                                            break;
                                        case 3:
                                            clearScreen();
                                            project.searchProjectStartTime();
                                            pressEnter();
                                            break;
                                    }
                                }while(choice22 != 0);
                                break;
                            case 3:
                                do{
                                    clearScreen();
                                    menu.menu23();
                                    choice23 = Integer.parseInt(sc.nextLine());
                                    switch (choice23){
                                        case 1:
                                            clearScreen();
                                            project.updateProjectName();
                                            pressEnter();
                                            break;
                                        case 2:
                                            clearScreen();
                                            project.updateProjectDuration();
                                            pressEnter();
                                            break;
                                        case 3:
                                            clearScreen();
                                            project.updateProjectStartTime();
                                            pressEnter();
                                            break;
                                    }
                                }while(choice23 != 0);
                                break;
                            case 4:
                                clearScreen();
                                project.totalExperienceTime();
                                pressEnter();
                                break;
                            case 5:
                                clearScreen();
                                project.showProjectProfile();
                                pressEnter();
                                break;
                        }
                    }while(choice2 != 0);
                    break;
                case 3:
                    do{
                        clearScreen();
                        menu.menu3();
                        choice3 = Integer.parseInt(sc.nextLine());
                        switch (choice3){
                            case 1:
                                clearScreen();
                                user.setUserName();
                                pressEnter();
                                break;
                            case 2:
                                clearScreen();
                                user.setUserBirthday();
                                pressEnter();
                                break;
                            case 3:
                                clearScreen();
                                user.setUserPhone();
                                pressEnter();
                                break;
                            case 4:
                                clearScreen();
                                user.setUserEmail();
                                pressEnter();
                                break;
                            case 5:
                                clearScreen();
                                user.setUserPassword();
                                pressEnter();
                                break;
                            case 6:
                                clearScreen();
                                user.showUserProfile();
                                pressEnter();
                                break;
                        }
                    }while(choice3 != 0);
                    break;
                case 4:
                    do{
                        clearScreen();
                        menu.menu4();
                        choice4 = Integer.parseInt(sc.nextLine());
                        switch (choice4){
                            case 1:
                                clearScreen();
                                dataFile.saveDataDeveloperTXT();
                                pressEnter();
                                break;
                            case 2:
                                clearScreen();
                                dataFile.saveDataProjectTXT();
                                pressEnter();
                                break;
                            case 3:
                                clearScreen();
                                dataFile.headerDeveloperTable();
                                dataFile.readDataDeveloperTXT();
                                pressEnter();
                                break;
                            case 4:
                                clearScreen();
                                dataFile.headerProjectTable();
                                dataFile.readDataProjectTXT();
                                pressEnter();
                                break;
                        }
                    }while(choice4 != 0);
                    break;
            }
        }while(choice != 0);
        dataFile.saveDataUserBIN(user);
        dataFile.saveDataDeveloperBIN();
        dataFile.saveDataProjectBIN();
        menu.goodBye();
    }

    public static User downloadDatabase(){
        User user = new User();
        Developer developer = new Developer();
        Project project = new Project();
        DataFile dataFile = new DataFile();

        user = dataFile.readDataUserBIN();
        List<Developer> tempDev = dataFile.readDataDeveloperBIN();
        if(tempDev != null && !tempDev.isEmpty()){
            Developer.getListOfDeveloper().addAll(tempDev);
        }
        List<Project> tempPro = dataFile.readDataProjectBIN();
        if(tempPro != null && !tempPro.isEmpty()){
            Project.getListOfProject().addAll(tempPro);
        }
        return user;
    }

    public static void pressEnter(){
        Scanner sc = new Scanner(System.in);
        System.out.printf("!!! Press ENTER to continue !!!");
        sc.nextLine();
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}
