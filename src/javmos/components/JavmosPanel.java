package javmos.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JPanel;
import javmos.JavmosGUI;
import javmos.components.functions.Function;
import javmos.listeners.PointClickListener;

public class JavmosPanel extends JPanel {

    private LinkedList<JavmosComponent> components = new LinkedList<>();
    private final JavmosGUI gui;

    public JavmosPanel(JavmosGUI gui) {
        this.gui = gui;
    }

    public Function getFunction() {
        for (JavmosComponent c : components) {
            if (c instanceof Function) {
                return (Function) c;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Function function = getFunction();
        components.clear();
        CartesianPlane plane = new CartesianPlane(gui);
        setPlane(plane);

        if (function != null) {
            components.add(function);
            HashSet<Point> points = function.getXIntercepts();

            if (function.getFirstDerivative().contains("x")) {
                points.addAll(function.getCriticalPoints());
                if (function.getSecondDerivative().contains("x")) {
                    points.addAll(function.getInflectionPoints());
                }
            }

            points.stream().forEach((point) -> {
                components.add(point);
            });
            PointClickListener p = (PointClickListener) getMouseListeners()[0];
            p.setPoints(components);
        }

        components.stream().forEach((c) -> {
            c.draw((Graphics2D) graphics);
        });
    }

    public void setFunction(Function function) {
        components.remove(getFunction());
        components.add(function);
    }

    public void setPlane(CartesianPlane plane) {
        components.add(0, plane);
    }
}
