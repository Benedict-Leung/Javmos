package javmos.components;

import java.awt.Graphics2D;
import javmos.JavmosGUI;

public abstract class JavmosComponent extends Object {

    protected JavmosGUI gui;
    
    public JavmosComponent(JavmosGUI gui) {
        this.gui = gui;
    }
    
    public abstract void draw(Graphics2D graphics2D);
}
