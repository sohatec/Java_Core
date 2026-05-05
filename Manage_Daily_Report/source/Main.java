package com.sohatec;
import java.util.Scanner;
public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Menu menu = new Menu();
        JuniorStaff juniorStaff = new JuniorStaff();
        SeniorStaff seniorStaff = new SeniorStaff();
        FileData fileData = new FileData();

        clearScreen();
        menu.welcome();
        Manager manager = downloadDatabase();
        if(manager == null){
            manager = new Manager();
            manager.createAccount();
            manager.managerLogin();
        }else{
            manager.managerLogin();
        }
        fileData.saveDataManagerBIN(manager);
        int choice, choice1, choice2, choice3;
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
                                juniorStaff.createNewOneJunior();
                                pressEnter();
                                break;
                            case 2:
                                clearScreen();
                                juniorStaff.searchIdJunior();
                                pressEnter();
                                break;
                            case 3:
                                clearScreen();
                                juniorStaff.updateNameJunior();
                                pressEnter();
                                break;
                            case 4:
                                clearScreen();
                                juniorStaff.updateEmailJunior();
                                pressEnter();
                                break;
                            case 5:
                                clearScreen();
                                juniorStaff.updateLanguageJunior();
                                pressEnter();
                                break;
                            case 6:
                                clearScreen();
                                juniorStaff.deleteOneJunior();
                                pressEnter();
                                break;
                            case 7:
                                clearScreen();
                                juniorStaff.showListJunior();
                                pressEnter();
                                break;
                            case 8:
                                clearScreen();
                                juniorStaff.addingReportJunior();
                                pressEnter();
                                break;
                            case 9:
                                clearScreen();
                                juniorStaff.searchReportJunior();
                                pressEnter();
                                break;
                            case 10:
                                clearScreen();
                                juniorStaff.deleteReportJunior();
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
                                seniorStaff.createNewOneSenior();
                                pressEnter();
                                break;
                            case 2:
                                clearScreen();
                                seniorStaff.searchIdSenior();
                                pressEnter();
                                break;
                            case 3:
                                clearScreen();
                                seniorStaff.updateNameSenior();
                                pressEnter();
                                break;
                            case 4:
                                clearScreen();
                                seniorStaff.updateEmailSenior();
                                pressEnter();
                                break;
                            case 5:
                                clearScreen();
                                seniorStaff.updateLanguageSenior();
                                pressEnter();
                                break;
                            case 6:
                                clearScreen();
                                seniorStaff.deleteOneSenior();
                                pressEnter();
                                break;
                            case 7:
                                clearScreen();
                                seniorStaff.showListSenior();
                                pressEnter();
                                break;
                            case 8:
                                clearScreen();
                                seniorStaff.addingReportSenior();
                                pressEnter();
                                break;
                            case 9:
                                clearScreen();
                                seniorStaff.searchReportSenior();
                                pressEnter();
                                break;
                            case 10:
                                clearScreen();
                                seniorStaff.deleteReportSenior();
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
                        switch (choice3) {
                            case 1:
                                clearScreen();
                                manager.updateName();
                                pressEnter();
                                break;
                            case 2:
                                clearScreen();
                                manager.updateBirthday();
                                pressEnter();
                                break;
                            case 3:
                                clearScreen();
                                manager.updateEmail();
                                pressEnter();
                                break;
                            case 4:
                                clearScreen();
                                manager.updatePhone();
                                pressEnter();
                                break;
                            case 5:
                                clearScreen();
                                manager.updatePassword();
                                pressEnter();
                                break;
                            case 6:
                                clearScreen();
                                manager.showAccount();
                                pressEnter();
                                break;
                        }
                    }while(choice3 != 0);
                    break;
            }
        }while(choice != 0);
        menu.goodBye();
    }

    public static Manager downloadDatabase(){
        Manager manager = new Manager();
        FileData fileData = new FileData();
        manager = fileData.readDataManagerBIN();
        return manager;
    }


    public static void pressEnter(){
        System.out.printf("=== Press ENTER To Continue ===");
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