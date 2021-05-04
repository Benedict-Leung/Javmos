package javmos.components.functions;

import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.enums.FunctionType;

public class Tangent extends Trignometric {
    
    public Tangent(JavmosGUI gui, String function) {
        super(gui, function, "tan");
    }

    @Override
    public String getFirstDerivative() {
        return (float) (a * k) + "sec^2(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return (float) (2 * a * Math.pow(k, 2)) + "sec^2(" + k + "x)tan(" + k + "x)";
    }

    @Override
    public HashSet<Point> getCriticalPoints() {
        return new HashSet<>();
    }

    @Override
    public HashSet<Point> getInflectionPoints() {
        return new HashSet<>();
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        return (functionType == FunctionType.FIRST_DERIVATIVE) ? a * k * Math.pow(1 / Math.cos(k * x), 2) : a * Math.tan(k * x);
    }
}
