 package javmos.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import javmos.JavmosGUI;

public class CartesianPlane extends JavmosComponent {

    public CartesianPlane(JavmosGUI gui) {
        super(gui);
    }
    
    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.setFont(new Font("default", Font.BOLD, 18 * (int) gui.getZoom() / 50));
        DecimalFormat df;
        
        if (gui.getDomainStep() % 1 == 0) {
            df = new DecimalFormat("#");
        } else {
            df = new DecimalFormat();
        }
        for (int i = 1; i * gui.getZoom() <= gui.getPlaneWidth() / 2; i++) {
            graphics2D.drawString("" + df.format((-i * gui.getDomainStep())), gui.getPlaneWidth() / 2 - i * (int) gui.getZoom() + 2, gui.getPlaneHeight() / 2 - 2);
            graphics2D.drawString(df.format(i * gui.getDomainStep()), gui.getPlaneWidth() / 2 + i * (int) gui.getZoom() + 2, gui.getPlaneHeight() / 2 - 2);
            graphics2D.drawString("" + df.format((-i * gui.getRangeStep())), gui.getPlaneWidth() / 2 + 2, gui.getPlaneHeight() / 2 + i * (int) gui.getZoom() - 2);
            graphics2D.drawString(df.format(i * gui.getRangeStep()), gui.getPlaneWidth() / 2 + 2, gui.getPlaneHeight() / 2 - i * (int) gui.getZoom() - 2);
            graphics2D.drawLine(gui.getPlaneWidth() / 2 + i * (int) gui.getZoom(), 0, gui.getPlaneWidth() / 2 + i * (int) gui.getZoom(), gui.getPlaneHeight());
            graphics2D.drawLine(gui.getPlaneWidth() / 2 - i * (int) gui.getZoom(), 0, gui.getPlaneWidth() / 2 - i * (int) gui.getZoom(), gui.getPlaneHeight());
            graphics2D.drawLine(0, gui.getPlaneHeight() / 2 + i * (int) gui.getZoom(), gui.getPlaneWidth(), gui.getPlaneHeight() / 2 + i * (int) gui.getZoom());
            graphics2D.drawLine(0, gui.getPlaneHeight() / 2 - i * (int) gui.getZoom(), gui.getPlaneWidth(), gui.getPlaneHeight() / 2 - i * (int) gui.getZoom());
        }
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawString("0", gui.getPlaneWidth() / 2 + 2, gui.getPlaneHeight() / 2 - 2);
        graphics2D.drawLine(gui.getPlaneWidth() / 2, 0, gui.getPlaneWidth() / 2, gui.getPlaneHeight());
        graphics2D.drawLine(0, gui.getPlaneHeight() / 2, gui.getPlaneWidth(), gui.getPlaneHeight() / 2);
    }
}