package javmos.components.functions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.components.Point;
import javmos.enums.FunctionType;
import javmos.enums.RootType;

public abstract class Function extends JavmosComponent {
    
    
    protected Function(JavmosGUI gui) {
        super(gui);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        double minDomain = (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 > gui.getMinDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 : gui.getMinDomain();
        double maxDomain = (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 < gui.getMaxDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 : gui.getMaxDomain();

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.decode("#E37222"));
        graphics2D.setStroke(new BasicStroke(3));
        DecimalFormat df = new DecimalFormat(".");

        for (double x = minDomain + 0.02; x < maxDomain + 0.02; x += 0.02) {
            if (!Double.isNaN(getValueAt(x - 0.02, FunctionType.ORIGINAL)) && getValueAt(x, FunctionType.ORIGINAL) > gui.getMinRange() && getValueAt(x, FunctionType.ORIGINAL) < gui.getMaxRange()) {
                graphics2D.draw(new Line2D.Double(Double.parseDouble(df.format((x - 0.02) * gui.getZoom() / gui.getDomainStep() + gui.getPlaneWidth() / 2)), Double.parseDouble(df.format(gui.getZoom() * -getValueAt(x - 0.02, FunctionType.ORIGINAL) / gui.getRangeStep() + gui.getPlaneHeight() / 2)), Double.parseDouble(df.format(x * gui.getZoom() / gui.getDomainStep() + gui.getPlaneWidth() / 2)), Double.parseDouble(df.format((gui.getZoom() * -getValueAt(x, FunctionType.ORIGINAL) / gui.getRangeStep() + gui.getPlaneHeight() / 2)))));
            }
        }
    }

    public HashSet<Point> getXIntercepts() {
        return RootType.X_INTERCEPT.getRoots(gui, this, (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 > gui.getMinDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 : gui.getMinDomain(), (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 < gui.getMaxDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 : gui.getMaxDomain());
    }

    public HashSet<Point> getCriticalPoints() {
        return RootType.CRITICAL_POINT.getRoots(gui, this, (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 > gui.getMinDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 : gui.getMinDomain(), (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 < gui.getMaxDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 : gui.getMaxDomain());
    }

    public HashSet<Point> getInflectionPoints() {
        return RootType.INFLECTION_POINT.getRoots(gui, this, (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 > gui.getMinDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / -2 : gui.getMinDomain(), (gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 < gui.getMaxDomain()) ? gui.getPlaneWidth() / gui.getZoom() * gui.getDomainStep() / 2 : gui.getMaxDomain());
    }

    public abstract String getFirstDerivative();

    public abstract String getSecondDerivative();

    public abstract double getValueAt(double x, FunctionType functionType);
}
