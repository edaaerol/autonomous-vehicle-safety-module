# Otonom Araç Güvenlik Navigasyon Sistemi

Bu proje, bir otonom aracın iki farklı engel sınıfı arasından en güvenli şekilde geçebilmesi için **Maksimum Güvenlik Koridoru** hesaplayan bir yazılım modülüdür.

## 🚀 Projenin Amacı
Sistem, doğrusal olarak ayrılabilen iki engel kümesini birbirinden ayıran herhangi bir çizgi bulmakla kalmaz; her iki sınıftaki en yakın koordinatlara olan uzaklığı **maksimum (optimum)** yapan sınır çizgisini hesaplar.

## 🧠 Matematiksel Model: SVM
Projede **Support Vector Machines (SVM)** algoritması kullanılmıştır. 
- **Karar Sınırı:** $w \cdot x - b = 0$
- **Optimizasyon:** $2/\|w\|$ (Margin) değerini maksimize etmek için Hinge Loss ve L2 Regularization kullanılarak Gradient Descent uygulanmıştır.

## 🏗️ Yazılım Mimarisi
Proje, OOP prensiplerine uygun olarak 3 ana katmanda izole edilmiştir:
1. **Model:** `Point` record yapısı ile immutable veri yönetimi.
2. **Service:** `SVMManager` ile matematiksel hesaplama ve eğitim motoru.
3. **UI:** `Visualizer` ile Swing tabanlı grafiksel gösterim.

## 📊 Analiz
- **Zaman Karmaşıklığı (Big-O):** Eğitim aşaması $O(E \times N)$, Karar aşaması $O(1)$.
- **Bellek Yönetimi:** Java record yapısı ve primitive veri tipleri kullanılarak bellek sızıntıları önlenmiş ve RAM kullanımı optimize edilmiştir.

## 🛠️ Kurulum ve Çalıştırma
1. Projeyi bilgisayarınıza indirin: `git clone [LINK]`
2. IntelliJ IDEA veya herhangi bir Java IDE'si ile açın.
3. Maven bağımlılıklarının yüklenmesini bekleyin.
4. `security.Main` sınıfını çalıştırın.
