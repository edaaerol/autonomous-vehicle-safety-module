package security;

import security.model.Point;
import security.service.SVMManager;
import security.ui.Visualizer;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("[SİSTEM] Otonom Navigasyon Modülü Başlatılıyor...");

        List<Point> dataset = new ArrayList<>();

        // Sınıf 1 (Kırmızı Engeller)
        dataset.add(new Point(1, 4, 1));
        dataset.add(new Point(2, 5, 1));
        dataset.add(new Point(3, 4.5, 1));

        // Sınıf -1 (Mavi Engeller)
        dataset.add(new Point(5, 1.5, -1));
        dataset.add(new Point(6, 2.5, -1));
        dataset.add(new Point(7, 2, -1));

        // Algoritma Eğitimi ve Analiz
        SVMManager svm = new SVMManager();
        System.out.println("[İŞLEM] SVM Eğitimi Başlatıldı...");
        svm.train(dataset);

        // Terminale matematiksel sonuçları yazdır
        svm.printResults();

        // Görselleştirme
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Otonom Araç Güvenlik Navigasyon Sistemi");
            frame.add(new Visualizer(dataset, svm));
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            System.out.println("[UI] Canlı Görselleştirme Ekranı Açıldı.");
        });
    }
}
