package swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Button extends JButton {

    private Animator animator;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float alpha;
    private Color effectColor = new Color(255, 255, 255);
    private Color gradientStart = Color.decode("#000000"); // Default Gradient Start (Steel Blue)
    private Color gradientEnd = Color.decode("#36454f");   // Default Gradient End (Cornflower Blue)

    public Button() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;
                pressedPoint = me.getPoint();
                alpha = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };

        animator = new Animator(700, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
    }

    public void setGradientStart(Color gradientStart) {
        this.gradientStart = gradientStart;
        repaint();
    }

    public void setGradientEnd(Color gradientEnd) {
        this.gradientEnd = gradientEnd;
        repaint();
    }

    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    @Override
protected void paintComponent(Graphics grphcs) {
    int width = getWidth();
    int height = getHeight();

    // Create a high-resolution buffered image
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = img.createGraphics();

    // Enable high-quality rendering
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

    // Gradient background (top to bottom gradient)
    GradientPaint gradientPaint = new GradientPaint(0, 0, gradientStart, 0, height, gradientEnd);
    g2.setPaint(gradientPaint);

    // Draw a rounded rectangle with a radius equal to the height (capsule shape)
    g2.fillRoundRect(0, 0, width, height, height, height);

    // Ripple effect (if applicable)
    if (pressedPoint != null) {
        g2.setColor(effectColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        g2.fillOval(
            (int) (pressedPoint.x - animatSize / 2),
            (int) (pressedPoint.y - animatSize / 2),
            (int) animatSize,
            (int) animatSize
        );
    }

    g2.dispose();
    grphcs.drawImage(img, 0, 0, null);

    super.paintComponent(grphcs);
}
}
