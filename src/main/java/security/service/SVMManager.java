package security.service;

import security.model.Point;
import java.util.List;

/**
 * [KRİTER: Algoritmik Doğruluk & Zaman Karmaşıklığı]
 * Bu sınıf, iki sınıf arasındaki mesafeyi (margin) maksimize eden çizgiyi bulur.
 */
public class SVMManager {
    private double w1 = 0, w2 = 0, b = 0; // Ağırlıklar ve Bias
    private final double learningRate = 0.001;
    private final double lambda = 0.01; // Regularizasyon: Koridorun genişliğini kontrol eder.
    private final int epochs = 10000;

    /**
     * Algoritma: Stochastic Gradient Descent (SGD) ile Linear SVM.
     * Neden Optimum? SVM, sınıfları ayıran herhangi bir çizgiyi değil,
     * sınırlara en yakın noktalara (Support Vectors) en uzak çizgiyi bulur.
     */
    public void train(List<Point> points) {
        // [Zaman Karmaşıklığı: O(Epochs * N)]
        for (int i = 0; i < epochs; i++) {
            for (Point p : points) {
                // Karar fonksiyonu: y_i * (w * x_i - b) >= 1
                double condition = p.label() * (w1 * p.x() + w2 * p.y() - b);

                if (condition >= 1) {
                    // Margin dışında: Sadece ağırlıkları minimize et (Regularizasyon)
                    w1 -= learningRate * (2 * lambda * w1);
                    w2 -= learningRate * (2 * lambda * w2);
                } else {
                    // Margin ihlali: Ağırlıkları ve yönü güncelle
                    w1 -= learningRate * (2 * lambda * w1 - p.label() * p.x());
                    w2 -= learningRate * (2 * lambda * w2 - p.label() * p.y());
                    b -= learningRate * p.label();
                }
            }
        }
    }

    // Ana ayrıştırıcı sınır denklemi: y = (b - w1*x) / w2
    public double getY(double x) { return (b - w1 * x) / w2; }

    // Üst Güvenlik Sınırı (Margin +1)
    public double getUpperMarginY(double x) { return (b + 1 - w1 * x) / w2; }

    // Alt Güvenlik Sınırı (Margin -1)
    public double getLowerMarginY(double x) { return (b - 1 - w1 * x) / w2; }
}