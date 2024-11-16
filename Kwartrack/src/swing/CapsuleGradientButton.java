/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

/**
 *
 * @author Chrys Sean Sevilla
 */
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class CapsuleGradientButton extends JButton {

    private Color color1;
    private Color color2;

    public CapsuleGradientButton(String text, Color color1, Color color2) {
        super(text); // Set button text
        this.color1 = color1;
        this.color2 = color2;
        setContentAreaFilled(false); // Disable default button background
        setFocusPainted(false); // Remove focus border
        setBorderPainted(false); // Remove border for a modern look
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a gradient paint
        GradientPaint gradientPaint = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
        g2.setPaint(gradientPaint);

        // Draw a capsule shape as the button background (fully rounded left and right edges)
        int height = getHeight();
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), height, height, height));

        // Draw the button's text and other components
        super.paintComponent(g);
    }

    // Setters and getters for colors
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
        repaint();
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
        repaint();
    }
}

