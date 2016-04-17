package xyz.koiduste.kalkar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marko on 4/16/16.
 */
public class Stats implements Entity {

    private int id;
    private int daystamp;
    private int operandId;
    private int dayCounter;


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getDaystamp() {
        return daystamp;
    }

    public void setDaystamp(int daystamp) {
        this.daystamp = daystamp;
    }

    public int getOperandId() {
        return operandId;
    }

    public void setOperandId(int operandId) {
        this.operandId = operandId;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    private String intToDateString(int i) {
        Date date = new Date(i);
        SimpleDateFormat format = new SimpleDateFormat("d-MMM-yyyy");
        return format.format(date);
    }

    @Override
    public String toString() {
        return String.format("[%s] Tehe: %s | Kogus: %d");
    }
}
