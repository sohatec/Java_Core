import java.util.Random;

public class Honda extends MotoGP{

    public static final double MAX_SPEED = 350;

    public Honda(String motoBrand, String motoRacer, int motoNumber) {
        super(motoBrand, motoRacer, motoNumber);
    }

    @Override
    public double racingOpeningLap(){
        return 300 + (new Random().nextDouble() * (MAX_SPEED - 300));
    }

    @Override
    public void showResultFormationLap(){
        System.out.printf("|%-10s|%-10s|%5d|%8.2f|\n", motoBrand, motoRacer, motoNumber, racingOpeningLap());
    }

    @Override
    public void showResultOpeningLap(double speed){
        System.out.printf("|%-10s|%-10s|%5d|%8.2f|\n", motoBrand, motoRacer, motoNumber, speed);
    }
}
