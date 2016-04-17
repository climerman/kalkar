package xyz.koiduste.kalkar;

/**
 * Created by marko on 3/9/16.
 */
public class MathServiceImpl implements MathService {
    @Override
    public double add(double num1, double num2) {
        return num1+num2;
    }

    @Override
    public double subtract(double num1, double num2) {
        return num1-num2;
    }

    @Override
    public double multiply(double num1, double num2) {
        return num1*num2;
    }

    @Override
    public double divide(double num1, double num2) {
        return num1/num2; //TODO checking logic to avoid divide by 0
    }
}
