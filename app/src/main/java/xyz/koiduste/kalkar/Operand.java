package xyz.koiduste.kalkar;

/**
 * Created by marko on 4/16/16.
 */
public class Operand implements Entity{
    private int id;
    private String operand;
    private int lifetimeCounter;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public int getLifetimeCounter() {
        return lifetimeCounter;
    }

    public void setLifetimeCounter(int lifetimeCounter) {
        this.lifetimeCounter = lifetimeCounter;
    }

    @Override
    public String toString() {
        return "Tehe:" + getOperand() + " | Kogus: "+getLifetimeCounter();
    }
}
