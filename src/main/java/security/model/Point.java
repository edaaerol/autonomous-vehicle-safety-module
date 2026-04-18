package security.model;

/**
 * Record kullanımı immutability (değişmezlik) sağlar.
 * Yan etkileri önler ve bellek sızıntısı riskini ortadan kaldırır.
 */
public record Point(double x, double y, int label) {
    // label: 1 (Sınıf A), -1 (Sınıf B)
}
