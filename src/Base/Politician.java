package Base;

public class Politician {
    boolean isMalicious;
    int numberCitizen=0;

    public Politician(boolean isMalicious){
        this.isMalicious=isMalicious;
    }

    public boolean isMalicious() {
        return isMalicious;
    }

    public boolean transaction() {
        return !isMalicious;
    }
}
