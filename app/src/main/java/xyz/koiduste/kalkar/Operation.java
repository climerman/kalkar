package xyz.koiduste.kalkar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marko on 4/16/16.
 */
public class Operation implements Entity{

    private int id;
    private int operandId;
    private String operand;
    private float num1;
    private float num2;
    private float result;
    private long timestamp;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    public int getOperandId() {
        return operandId;
    }

    public void setOperandId(int operandId) {
        this.operandId = operandId;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public float getNum1() {
        return num1;
    }

    public void setNum1(float num1) {
        this.num1 = num1;
    }

    public float getNum2() {
        return num2;
    }

    public void setNum2(float num2) {
        this.num2 = num2;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private String intToDateString(long i) {
        Date date = new Date(i);
        SimpleDateFormat format = new SimpleDateFormat("d-MMM-yyyy hh:mm:ss");
        return format.format(date);
    }

    @Override
    public String toString() {
        return String.format("[%s] Tehe: %f %s %f = %f", intToDateString(getTimestamp()), getNum1(), getOperand(), getNum2(), getResult());
    }

}
