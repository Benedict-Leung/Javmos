package javmos.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.LinkedList;
import javmos.JavmosGUI;
import javmos.components.JavmosComponent;
import javmos.components.Point;

public class PointClickListener extends Object implements MouseListener {

    private final JavmosGUI gui;
    private final HashSet<Point> points;

    public PointClickListener(JavmosGUI gui) {
        this.gui = gui;
        points = new HashSet<>();
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        if (!points.isEmpty()) {
            points.stream().filter((point) -> (point.getPoint().contains(event.getX(), event.getY()))).forEach((point) -> {
                gui.setPointLabel(point.toString(), point.getRootType());
            });
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Not needed!
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Not needed!
    }

    public void setPoints(LinkedList<JavmosComponent> points) {
        this.points.clear();
        points.stream().filter((c) -> (c instanceof Point)).forEach((c) -> {
            this.points.add((Point) c);
        });
    }
}
