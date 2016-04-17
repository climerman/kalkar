package xyz.koiduste.kalkar;

/**
 * Created by marko on 4/5/16.
 */
public interface KalkarEngine {
    double getResult(double num1, double num2, String op);
    double getResultFromStringInput(String result, String operand, String op);
}
