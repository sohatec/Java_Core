public abstract class MotoGP {

    protected String motoBrand;
    protected String motoRacer;
    protected int motoNumber;

    public MotoGP(String motoBrand, String motoRacer, int motoNumber) {
        this.motoBrand = motoBrand;
        this.motoRacer = motoRacer;
        this.motoNumber = motoNumber;
    }

    public String getMotoBrand() {
        return motoBrand;
    }
    public void setMotoBrand(String motoBrand) {
        this.motoBrand = motoBrand;
    }
    public String getMotoRacer() {
        return motoRacer;
    }
    public void setMotoRacer(String motoRacer) {
        this.motoRacer = motoRacer;
    }
    public int getMotoNumber() {
        return motoNumber;
    }
    public void setMotoNumber(int motoNumber) {
        this.motoNumber = motoNumber;
    }

    public abstract double racingOpeningLap();
    public abstract void showResultFormationLap();
    public abstract void showResultOpeningLap(double speed);
}
