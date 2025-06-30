package util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {

    private int arcWidth = 20;
    private int arcHeight = 20;
    private Color borderColor = Color.BLACK;
    private float borderThickness = 2.0f;
    private boolean drawBorder = true;
    
    public RoundedPanel() {
        setOpaque(false);
    }
    
    public RoundedPanel(Color backgroundColor) {
        this();
        setBackground(backgroundColor);
    }

    public RoundedPanel(Color backgroundColor, Color borderColor, int arcWidth, int arcHeight, float borderThickness) {
        this();
        setBackground(backgroundColor);
        this.borderColor = borderColor;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.borderThickness = borderThickness;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fill background jika background tidak transparan
        if (getBackground() != null) {
            g2d.setColor(getBackground());
            g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight));
        }
        
        // Draw border jika diperlukan
        if (drawBorder) {
            g2d.setColor(borderColor);
            g2d.setStroke(new BasicStroke(borderThickness));
            g2d.draw(new RoundRectangle2D.Float(
                    borderThickness / 2, borderThickness / 2, 
                    getWidth() - 1 - borderThickness, getHeight() - 1 - borderThickness, 
                    arcWidth, arcHeight));
        }
        
        g2d.dispose();
    }

    public void setArc(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderThickness(float thickness) {
        this.borderThickness = thickness;
        repaint();
    }

    public void setDrawBorder(boolean draw) {
        this.drawBorder = draw;
        repaint();
    }
}