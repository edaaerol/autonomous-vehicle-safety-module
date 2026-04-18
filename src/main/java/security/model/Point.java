package security.model;

/**
 * [KRİTER: Bellek Yönetimi]
 * Record kullanımı, verilerin değiştirilemez (immutable) olmasını sağlar.
 * Bu, gereksiz kopya oluşturulmasını önler ve bellek sızıntısı riskini sıfıra indirir.
 */
public record Point(double x, double y, int label) {
    // label: 1 (Sınıf A - Kırmızı), -1 (Sınıf B - Mavi)
}