package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Cosine extends Trignometric {
    
    public Cosine(JavmosGUI gui, String function) {
        super(gui, function, "cos");
    }

    @Override
    public String getFirstDerivative() {
        return (float) (-a * k) + "sin(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return (float) (-a * Math.pow(k, 2)) + "cos(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch (functionType) {
            case FIRST_DERIVATIVE:
                return -a * k * Math.sin(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k, 2) * Math.cos(k * x);
            case THIRD_DERIVATIVE:
                return a * Math.pow(k, 3) * Math.sin(k * x);
            default:
                return a * Math.cos(k * x);
        }
    }
}
