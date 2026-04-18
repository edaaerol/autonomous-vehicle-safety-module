package security.ui;

import security.model.Point;
import security.service.SVMManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * UI Katmanı: Hesaplama mantığından izole edilmiştir.
 */
public class Visualizer extends JPanel {
    private final List<Point> points;
    private final SVMManager svm;
    private final int SCALE = 50;
    private final int OFFSET = 250;

    public Visualizer(List<Point> points, SVMManager svm) {
        this.points = points;
        this.svm = svm;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Izgara Sistemi (Grid)
        g2.setColor(new Color(240, 240, 240));
        for(int i=0; i<getWidth(); i+=SCALE) g2.drawLine(i, 0, i, getHeight());
        for(int i=0; i<getHeight(); i+=SCALE) g2.drawLine(0, i, getWidth(), i);

        // Engeller
        for (Point p : points) {
            g2.setColor(p.label() == 1 ? Color.RED : Color.BLUE);
            g2.fillOval((int)(p.x() * SCALE) + OFFSET - 6, (int)(-p.y() * SCALE) + OFFSET - 6, 12, 12);
        }

        // Optimum Karar Sınırı (Siyah)
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        drawLine(g2, svm::getY);

        // Güvenlik Koridoru (Kesikli Çizgiler)
        g2.setColor(Color.GRAY);
        float[] dash = {10.0f};
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        drawLine(g2, svm::getUpperMarginY);
        drawLine(g2, svm::getLowerMarginY);

        g2.setColor(Color.DARK_GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("KIRMIZI/MAVİ: Engeller | SİYAH: Optimum Yol | KESİKLİ: Güvenlik Koridoru", 20, 30);
    }

    private void drawLine(Graphics2D g2, java.util.function.Function<Double, Double> func) {
        try {
            int x1 = -10, x2 = 10;
            int px1 = x1 * SCALE + OFFSET;
            int py1 = (int)(-func.apply((double)x1) * SCALE) + OFFSET;
            int px2 = x2 * SCALE + OFFSET;
            int py2 = (int)(-func.apply((double)x2) * SCALE) + OFFSET;
            g2.drawLine(px1, py1, px2, py2);
        } catch (Exception ignored) {}
    }
}
