package javmos.enums;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.components.functions.Function;

public enum RootType {

    X_INTERCEPT(Color.decode("#C2BCFF"), "X-INT", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.decode("#6330DB"), "CP", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.decode("#D331E2"), "IP", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    private final Color color;
    private final String name;
    private final int ATTEMPTS = 100;
    private final FunctionType functionOne;
    private final FunctionType functionTwo;

    RootType(Color color, String name, FunctionType functionOne, FunctionType functionTwo) {
        this.color = color;
        this.name = name;
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
    }

    public Color getPointColor() {
        return color;
    }

    public String getPointName() {
        return name;
    }

    public HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        DecimalFormat format = new DecimalFormat("0.000");
        format.setRoundingMode(RoundingMode.HALF_EVEN);
        HashSet<Point> addPoint = new HashSet<>();

        for (double i = minDomain - 0.01; i < maxDomain + 0.01; i += 0.01) {
            double x = newtonsMethod(function, i, ATTEMPTS);
            
            if (!Double.isNaN(x) && Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))) <= gui.getMaxRange() && Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))) >= gui.getMinRange() && Double.parseDouble(format.format(x)) <= maxDomain && Double.parseDouble(format.format(x)) >= minDomain) {
                Point point = new Point(gui, this, Double.parseDouble(format.format(x)), Double.parseDouble(format.format(function.getValueAt(x, FunctionType.ORIGINAL))));
                addPoint.add(point);
            }
        }
        return addPoint;
    }

    protected double newtonsMethod(Function function, double guess, int attempts) {
        if (Math.abs(function.getValueAt(guess, functionOne)) < 0.00000001) {
            return guess;
        } else if (attempts == 0) {
            return Double.NaN;
        } else {
            return newtonsMethod(function, guess - function.getValueAt(guess, functionOne) / function.getValueAt(guess, functionTwo), attempts - 1);
        }
    }
}
