package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.*;

public class DecoratedPanel extends JPanel{
    byte position;
    Graphics2D graphics;
    public DecoratedPanel(byte position) {
        this.position = position;
    }
    public DecoratedPanel (byte position, LayoutManager lm) {
        super (lm);
        this.position = position;
    }
    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D graphics2D = (Graphics2D) g;
        graphics = graphics2D;
        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHints(qualityHints);
        GeneralPath path = new GeneralPath();
        Color color = Color.white;
        switch (position) {
            case (byte)1:
                color = new Color(100, 100, 170);
                paintForTop(path);
                break;
            case (byte)2:
                paintForMiddle(path);
                break;
        }
        graphics2D.setPaint(color);
        graphics2D.fill(path);
    }
    void paintForTop (GeneralPath path) {
        int width = getWidth();
        int height = getHeight();
        path.moveTo(width - 5, 0);
        path.curveTo(width - 5, 0, width, 0, width, 5);
        path.lineTo(width, height - 5);
        path.curveTo(width, height - 5, width, height, width - 5, height);
        path.lineTo(5, height);
        path.curveTo(5, height, 0, height, 0, height - 5);
        path.lineTo(0, 5);
        path.curveTo(0, 5, 0, 0, 5, 0);
        path.lineTo(width - 5, 0);
        path.closePath();
    }
    void paintForMiddle (GeneralPath path) {
        int width = getWidth();
        int height = getHeight();
        path.moveTo(width - 38, 0);
        path.curveTo(width - 38, 0, width - 30, 0, width - 30, 8);
        path.lineTo(width - 30, height - 8);
        path.curveTo(width - 30, height - 8, width - 30, height, width - 38, height);
        path.lineTo(38, height);
        path.curveTo(38, height, 30, height, 30, height - 8);
        path.lineTo(30, 8);
        path.curveTo(30, 8, 30, 0, 38, 0);
        path.lineTo(width - 38, 0);
        path.closePath();
    }
}
