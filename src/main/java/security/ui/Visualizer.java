package security.ui;

import security.model.Point;
import security.service.SVMManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * [KRİTER: Yazılım Mimarisi]
 * UI Katmanı: Hesaplama mantığından (SVMManager) tamamen izoledir.
 */
public class Visualizer extends JPanel {
    private final List<Point> points;
    private final SVMManager svm;
    private final int SCALE = 40; // Ekran ölçeklendirme
    private final int OFFSET = 250; // Grafik merkezi

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

        // Kılavuz Çizgiler (Grid)
        g2.setColor(new Color(230, 230, 230));
        for(int i=0; i<800; i+=SCALE) g2.drawLine(i, 0, i, 600);
        for(int i=0; i<600; i+=SCALE) g2.drawLine(0, i, 800, i);

        // Engelleri Çiz
        for (Point p : points) {
            g2.setColor(p.label() == 1 ? Color.RED : Color.BLUE);
            g2.fillOval((int)(p.x() * SCALE) + OFFSET - 5, (int)(-p.y() * SCALE) + OFFSET - 5, 10, 10);
        }

        // Karar Sınırı (Siyah - Optimum Hat)
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        drawLine(g2, svm::getY);

        // Güvenlik Koridoru (Gri Kesikli Çizgiler)
        g2.setColor(Color.GRAY);
        float[] dash = {5.0f};
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        drawLine(g2, svm::getUpperMarginY);
        drawLine(g2, svm::getLowerMarginY);

        g2.drawString("KIRMIZI/MAVİ: Engeller | SİYAH: Optimum Yol | KESİKLİ: Güvenlik Koridoru", 20, 20);
    }

    private void drawLine(Graphics2D g2, java.util.function.Function<Double, Double> func) {
        try {
            int x1 = -20, x2 = 20;
            int px1 = x1 * SCALE + OFFSET;
            int py1 = (int)(-func.apply((double)x1) * SCALE) + OFFSET;
            int px2 = x2 * SCALE + OFFSET;
            int py2 = (int)(-func.apply((double)x2) * SCALE) + OFFSET;
            g2.drawLine(px1, py1, px2, py2);
        } catch (Exception ignored) {}
    }
}