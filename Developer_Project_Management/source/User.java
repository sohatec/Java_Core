import java.util.Scanner;

public class User implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private String userName;
    private int userDay;
    private int userMonth;
    private int userYear;
    private String userPhone;
    private String userEmail;
    private String userPassword;
    private transient Scanner sc;

    public User() {
    }

    private void checkScanner() {
        if (this.sc == null) this.sc = new Scanner(System.in);
    }

    public void createAccount(){
        checkScanner();
        System.out.printf("---CREATE NEW ACCOUNT---");
        System.out.printf("\n|%-10s| ", "Name");
        this.userName = sc.nextLine();
        int max, flag;
        do{
            max = 31; flag = 0;
            System.out.printf("|%-10s| ", "Birthday");
            this.userDay = sc.nextInt();
            this.userMonth = sc.nextInt();
            this.userYear = sc.nextInt();
            sc.nextLine();
            if(this.userDay < 1 ||
                    this.userDay > 31 ||
                    this.userMonth < 1 ||
                    this.userMonth > 12 ||
                    this.userYear < 1950 ||
                    this.userYear > 2026){
                continue;
            }
            if(this.userMonth == 4 ||
                    this.userMonth == 6 ||
                    this.userMonth == 9 ||
                    this.userMonth == 11){
                max = 30;
            }else if(this.userMonth == 2){
                if(this.userYear % 400 == 0 || (this.userYear % 4 == 0 && this.userYear % 100 != 0)){
                    max = 29;
                }else {
                    max = 28;
                }
            }
            if(this.userDay >= 1 && this.userDay <= max) flag = 1;
        }while(flag == 0);
        do{
            flag = 0;
            System.out.printf("|%-10s| ", "Phone");
            this.userPhone = sc.nextLine();
            if(this.userPhone.length() != 10){
                flag = 0;
            }else if(this.userPhone.charAt(0) != '0'){
                flag = 0;
            }else{
                for(int i = 0; i < 10; i++){
                    if(Character.isDigit(this.userPhone.charAt(i))) flag = flag + 1;
                }
                if(flag == 10){
                    flag = 1;
                }else {
                    flag = 0;
                }
            }
        }while(flag == 0);
        do{
            flag = 0;
            System.out.printf("|%-10s| ", "Email");
            this.userEmail = sc.nextLine();
            if(this.userEmail.contains("@gmail.com")) flag = 1;
        }while(flag == 0);
        System.out.printf("|%-10s| ", "Password");
        this.userPassword = sc.nextLine();
        System.out.println("CREATE SUCCESSFULLY !!!");
    }

    public void userLogin(){
        checkScanner();
        String email;
        String password;
        System.out.printf("\n---LOGIN YOUR ACCOUNT---");
        while(true){
            System.out.printf("\n|%-15s| ", "Enter Email");
            email = sc.nextLine();
            System.out.printf("|%-15s| ", "Enter Pass");
            password = sc.nextLine();
            if(this.userEmail.compareTo(email) == 0 &&
               this.userPassword.compareTo(password) == 0){
                break;
            }else{
                System.out.println("Email Or Password Is Not Correct");
            }
        }
    }

    public void setUserName(){
        checkScanner();
        System.out.print("\nInput new name of user");
        System.out.printf("\n|%-10s| ", "Name");
        this.userName = sc.nextLine();
        System.out.println("UPDATING SUCCESSFULLY !!!");
    }

    public void setUserBirthday(){
        checkScanner();
        int max = 31, flag = 0;
        do{
            System.out.printf("|%-10s| ", "Birthday");
            this.userDay = sc.nextInt();
            this.userMonth = sc.nextInt();
            this.userYear = sc.nextInt();
            sc.nextLine();
            if(this.userDay < 1 ||
                    this.userDay > 31 ||
                    this.userMonth < 1 ||
                    this.userMonth > 12 ||
                    this.userYear < 1950 ||
                    this.userYear > 2026){
                continue;
            }
            if(this.userMonth == 4 ||
                    this.userMonth == 6 ||
                    this.userMonth == 9 ||
                    this.userMonth == 11){
                max = 30;
            }else if(this.userMonth == 2){
                if(this.userYear % 400 == 0 || (this.userYear % 4 == 0 && this.userYear % 100 != 0)){
                    max = 29;
                }else {
                    max = 28;
                }
            }
            if(this.userDay >= 1 && this.userDay <= max){
                System.out.println("UPDATING SUCCESSFULLY !!!");
                flag = 1;
            }
        }while(flag == 0);
    }

    public void setUserEmail(){
        checkScanner();
        int flag = 0;
        do{
            System.out.printf("|%-10s| ", "Email");
            this.userEmail = sc.nextLine();
            if(this.userEmail.contains("@gmail.com")){
                System.out.println("UPDATING SUCCESSFULLY !!!");
                flag = 1;
            }
        }while(flag == 0);
    }

    public void setUserPhone(){
        checkScanner();
        int flag =0;
        do{
            flag = 0;
            System.out.printf("|%-10s| ", "Phone");
            this.userPhone = sc.nextLine();
            if(this.userPhone.length() != 10){
                flag = 0;
            }else if(this.userPhone.charAt(0) != '0'){
                flag = 0;
            }else{
                for(int i = 0; i < 10; i++){
                    if(Character.isDigit(this.userPhone.charAt(i))) flag = flag + 1;
                }
                if(flag == 10){
                    System.out.println("UPDATING SUCCESSFULLY !!!");
                    flag = 1;
                }
            }
        }while(flag == 0);
    }

    public void setUserPassword(){
        checkScanner();
        System.out.printf("|%-10s| ", "Password");
        this.userPassword = sc.nextLine();
        System.out.println("UPDATING SUCCESSFULLY !!!");
    }

    public void showUserProfile(){
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
                "|%-10s|%30s|\n", "Name", userName, "Birthday", userDay, userMonth, userYear, "Phone", userPhone, "Email", userEmail, "Password", userPassword);
    }
}