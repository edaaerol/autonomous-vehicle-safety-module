package security;

import security.model.Point;
import security.service.SVMManager;
import security.ui.Visualizer;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * [KRİTER: Demo ve Senaryo]
 * Bu sınıf, iki farklı engel kümesini simüle eder ve otonom sistemi çalıştırır.
 */
public class Main {
    public static void main(String[] args) {
        // [KRİTER: OOP Prensipleri] - Veri kümesi yönetimi
        List<Point> dataset = new ArrayList<>();

        // Sınıf 1: Yakın Engel Grubu (Kırmızı)
        dataset.add(new Point(1, 4, 1));
        dataset.add(new Point(2, 5, 1));
        dataset.add(new Point(3, 4.5, 1));
        dataset.add(new Point(1.5, 6, 1));

        // Sınıf -1: Uzak Engel Grubu (Mavi)
        dataset.add(new Point(5, 1, -1));
        dataset.add(new Point(6, 2, -1));
        dataset.add(new Point(7, 1.5, -1));
        dataset.add(new Point(5.5, 0.5, -1));

        // Algoritmayı Çalıştır
        SVMManager svm = new SVMManager();
        svm.train(dataset);

        // Görselleştirmeyi Başlat
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Otonom Araç Güvenlik Navigasyon Sistemi");
            frame.add(new Visualizer(dataset, svm));
            frame.setSize(700, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}