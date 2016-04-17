package xyz.koiduste.kalkar;

/**
 * Created by marko on 4/5/16.
 */
public class KalkarEngineImpl implements KalkarEngine {
    private MathService service = new MathServiceImpl();
    @Override
    public double getResult(double num1, double num2, String op) {
        switch (op) {
            case "+":
                return service.add(num1, num2);

            case "-":
                return service.subtract(num1, num2);

            case "*":
                return service.multiply(num1, num2);

            case "/":
                return service.divide(num1, num2);
            default:
                throw new RuntimeException("Unknown operator: "+ op);
        }
    }

    @Override
    public double getResultFromStringInput(String result, String operand, String op) {
        double num1, num2;
        if (result.trim().isEmpty()) {
            num1 = 0;
        } else {
            num1 = Double.parseDouble(result);
        }
        if (operand.trim().isEmpty()) {
            num2 = 0;
        } else {
            num2 = Double.parseDouble(operand);
        }
        if (op.isEmpty()) {
            return 0;
        }
        return getResult(num1, num2, op);
    }
}
