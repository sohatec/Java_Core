package com.sohatec;
import java.util.Scanner;

public class Manager implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String managerName;
    private int managerDay;
    private int managerMonth;
    private int managerYear;
    private String managerPhone;
    private String managerEmail;
    private String managerPassword;
    private transient Scanner sc;

    public Manager() {
    }

    private void checkScanner() {
        if (this.sc == null) this.sc = new Scanner(System.in);}

    public void createAccount () {
        checkScanner();
        System.out.printf("\n=== CREATE ACCOUNT ===\n");
        System.out.printf("|%-10s| ", "Name");
        this.managerName = sc.nextLine();
        int max, flag;
        do{
            max = 31; flag = 0;
            System.out.printf("|%-10s| ", "Birthday");
            this.managerDay = sc.nextInt();
            this.managerMonth = sc.nextInt();
            this.managerYear = sc.nextInt();
            sc.nextLine();
            if(this.managerDay < 1 ||
               this.managerDay > 31 ||
               this.managerMonth < 1 ||
               this.managerMonth > 12 ||
               this.managerYear < 1950 ||
               this.managerYear > 2026){
               continue;
            }
            if(this.managerMonth == 4 ||
               this.managerMonth == 6 ||
               this.managerMonth == 9 ||
               this.managerMonth == 11){
               max = 30;
            }else if(this.managerMonth == 2){
                if(this.managerYear % 400 == 0 || (this.managerYear % 4 == 0 && this.managerYear % 100 != 0)){
                    max = 29;
                }else{
                    max = 28;
                }
            }
            if(this.managerDay >= 1 && this.managerDay <= max) flag = 1;
        }while(flag == 0);
        do{
            flag = 0;
            System.out.printf("|%-10s| ", "Phone");
            this.managerPhone = sc.nextLine();
            if(this.managerPhone.length() != 10){
                flag = 0;
            }else if(this.managerPhone.charAt(0) != '0'){
                flag = 0;
            }else{
                for(int i = 0; i < 10; i++){
                    if(Character.isDigit(this.managerPhone.charAt(i))) flag += 1;
                }
                if(flag == 10){
                    flag = 1;
                }else{
                    flag = 0;
                }
            }
        }while(flag == 0);
        do{
            flag = 0;
            System.out.printf("|%-10s| ", "Email");
            this.managerEmail = sc.nextLine();
            if(this.managerEmail.contains("@gmail.com")) flag = 1;
        }while(flag == 0);
        System.out.printf("|%-10s| ", "Password");
        this.managerPassword = sc.nextLine();
        System.out.println("=== CREATE SUCCESSFULLY ===");
    }

    public void managerLogin(){
        checkScanner();
        String email;
        String password;
        System.out.printf("\n---LOGIN YOUR ACCOUNT---");
        while(true){
            System.out.printf("\n|%-15s| ", "Enter Email");
            email = sc.nextLine();
            System.out.printf("|%-15s| ", "Enter Pass");
            password = sc.nextLine();
            if(this.managerEmail.compareTo(email) == 0 &&
                    this.managerPassword.compareTo(password) == 0){
                break;
            }else{
                System.out.println("== Email Or Password Is Not Correct ==");
            }
        }
    }


    public void updateName () {
        checkScanner();
        System.out.print("\nInput new name of manager");
        System.out.printf("\n|%-10s| ", "Name");
        this.managerName = sc.nextLine();
        System.out.println("=== UPDATING SUCCESSFULLY ===");
    }

    public void updateBirthday () {
        checkScanner();
        int max = 31, flag = 0;
        do{
            System.out.printf("|%-10s| ", "Birthday");
            this.managerDay = Integer.parseInt(sc.nextLine());
            this.managerMonth = Integer.parseInt(sc.nextLine());
            this.managerYear = Integer.parseInt(sc.nextLine());
            sc.nextLine();
            if(this.managerDay < 1 ||
                    this.managerDay > 31 ||
                    this.managerMonth < 1 ||
                    this.managerMonth > 12 ||
                    this.managerYear < 1950 ||
                    this.managerYear > 2026){
                continue;
            }
            if(this.managerMonth == 4 ||
                    this.managerMonth == 6 ||
                    this.managerMonth == 9 ||
                    this.managerMonth == 11){
                max = 30;
            }else if(this.managerMonth == 2){
                if(this.managerYear % 400 == 0 || (this.managerYear % 4 == 0 && this.managerYear % 100 != 0)){
                    max = 29;
                }else{
                    max = 28;
                }
            }
            if(this.managerDay >= 1 && this.managerDay <= max){
                System.out.println("=== UPDATING SUCCESSFULLY ===");
                flag = 1;
            };
        }while(flag == 0);
    }

    public void updatePhone () {
        checkScanner();
        int flag = 0;
        do{
            System.out.printf("|%-10s| ", "Phone");
            this.managerPhone = sc.nextLine();
            if(this.managerPhone.length() != 10){
                flag = 0;
            }else if(this.managerPhone.charAt(0) != '0'){
                flag = 0;
            }else{
                for(int i = 0; i < 10; i++){
                    if(Character.isDigit(this.managerPhone.charAt(i))) flag += 1;
                }
                if(flag == 10){
                    System.out.println("=== UPDATING SUCCESSFULLY ===");
                    flag = 1;
                }else{
                    flag = 0;
                }
            }
        }while(flag == 0);
    }

    public void updateEmail () {
        checkScanner();
        int flag = 0;
        do{
            System.out.printf("|%-10s| ", "Email");
            this.managerEmail = sc.nextLine();
            if(this.managerEmail.contains("@gmail.com")){
                System.out.println("=== UPDATING SUCCESSFULLY ===");
                flag = 1;
            }
        }while(flag == 0);
    }

    public void updatePassword () {
        checkScanner();
        System.out.printf("|%-10s| ", "Password");
        this.managerPassword = sc.nextLine();
        System.out.println("=== UPDATING SUCCESSFULLY ===");
    }

    public void showAccount () {
        checkScanner();
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format(
                "\n|%-10s|%30s|\n" +
                        "|%-10s|\t\t\t\t    %2d/%2d/%4d|\n" +
                        "|%-10s|%30s|\n" +
                        "|%-10s|%30s|\n" +
                        "|%-10s|%30s|\n", "Name", managerName, "Birthday", managerDay, managerMonth, managerYear, "Phone", managerPhone, "Email", managerEmail, "Password", managerPassword);
    }
}