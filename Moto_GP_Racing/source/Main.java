import java.io.IOException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static List<MotoGP> listOfMotoGp = new ArrayList<>();
    public static List<MainRacingResult> listOfEveluate = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        greet();
        menu();
        addRacer();
        System.out.printf("\n\n");
        Countdown1();
        showResultFormationLap();
        System.out.printf("\n\n");
        Countdown2();
        sortSpeedOpeningLap();
        champion();
    }

    public static void greet(){
        System.out.println("_-^-_-^-_-^-_-^-_-^-_-^-_-^-_-^-_-^-_-^-_\n");
        System.out.println("\t\tWELCOME TO MOTOR GP\n" + "\t\t 🏆CHAMPION 2025🏆");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dinhDang = new SimpleDateFormat("\t  dd/MM/yyyy hh:mm:ss a");
        Date d = cal.getTime();
        String s = dinhDang.format(d);
        System.out.println(s);
    }

    public static void addRacer(){
        int number;
        while (true) {
            try {
                System.out.printf("Input number and information of racer: ");
                number = Integer.parseInt(sc.nextLine());
                if (number >= 2) {
                    break;
                } else {
                    System.out.println("<<< Information Not Correct >>>");
                }
            } catch (NumberFormatException e) {
                System.out.println("<<< Information Not Correct >>>");
            }
        }
        int added = 0;
        while(added < number){
            System.out.printf("\nInput racer >>-" + (added + 1) + "-<<");
            System.out.printf("\nInput your brand: ");
            String b = sc.nextLine();
            if(b.compareTo("Ducati") == 0 || b.compareTo("Yamaha") == 0 || b.compareTo("Honda") == 0 ||
               b.compareTo("Aprilia") == 0 || b.compareTo("BMW") == 0){
                System.out.printf("Input name of racer: ");
                String n = sc.nextLine();
                System.out.printf("Input No of racer: ");
                int no = Integer.parseInt(sc.nextLine());
                switch (b) {
                    case "Ducati":
                        listOfMotoGp.add(new Ducati(b, n, no));
                        break;
                    case "Yamaha":
                        listOfMotoGp.add(new Yamaha(b, n, no));
                        break;
                    case "Honda":
                        listOfMotoGp.add(new Honda(b, n, no));
                        break;
                    case "Aprilia":
                        listOfMotoGp.add(new Aprilia(b, n, no));
                        break;
                    case "BMW":
                        listOfMotoGp.add(new BMW(b, n, no));
                        break;
                }
                added += 1;
            }else{
                System.out.println("<<<< Information Is Not Correct >>>>");
            }
        }
    }

    public static void showResultFormationLap(){
        System.out.println("\n<<<<<-----Formation lap----->>>>>");
        System.out.println("|Brand     |Racer     |No   |Speed   |");
        for(MotoGP m : listOfMotoGp){
            m.showResultFormationLap();
        }
    }

    public static void sortSpeedOpeningLap(){
        for(MotoGP m : listOfMotoGp){
            listOfEveluate.add(new MainRacingResult(m, m.racingOpeningLap()));
        }
        listOfEveluate.sort((a, b) -> Double.compare(b.speed, a.speed));
        System.out.println("\n<<<<<-----Opening lap----->>>>>");
        System.out.println("|Brand     |Racer     |No   |Speed   |");
        for(MainRacingResult rws : listOfEveluate){
            rws.motor.showResultOpeningLap(rws.speed);
        }
    }

    public static void champion(){
        if (listOfEveluate.isEmpty()) {
            System.out.println("No racers are evaluated yet!");
            return;
        }

        MainRacingResult champ = listOfEveluate.getFirst();
        System.out.println(" ");
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      🏆 The Champion of 2026 🏆      ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.printf(" Brand : %-10s\n", champ.motor.getMotoBrand());
        System.out.printf(" Racer : %-10s\n", champ.motor.getMotoRacer());
        System.out.printf(" No    : %-5d\n", champ.motor.getMotoNumber());
        System.out.printf(" Speed : %-8.2f km/h\n", champ.speed);
    }

    public static void Countdown1 (){
        int seconds = 10;
        for (int i = seconds; i >= 0; i--) {
            System.out.print("\rCOUNT DOWN FOR FORMATION LAP:::>>> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Countdown2 (){
        int seconds = 10;
        for (int i = seconds; i >= 0; i--) {
            System.out.print("\rCOUNT DOWN FOR OPENING LAP:::>>> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void menu(){
        System.out.println("\t\t==================");
        System.out.println("\t\t|  List Of Moto  |");
        System.out.println("\t\t==================");
        System.out.println("\t\t|     Ducati    |");
        System.out.println("\t\t|     Yamaha     |");
        System.out.println("\t\t|      Honda     |");
        System.out.println("\t\t|     Aprilia    |");
        System.out.println("\t\t|       BMW      |");
        System.out.println("\t\t=================");
    }
}
