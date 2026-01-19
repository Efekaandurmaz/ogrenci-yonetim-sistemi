import java.util.Scanner;

public class Algo1UltraProje {

    // GLOBAL DEĞİŞKENLER
    static Scanner sc = new Scanner(System.in);

    static final int MAX = 100;

    static String[] isimler = new String[MAX];
    static int[] vizeler = new int[MAX];
    static int[] finaller = new int[MAX];
    static double[] ortalamalar = new double[MAX];
    static boolean[] gectiMi = new boolean[MAX];

    static int ogrenciSayisi = 0;

    // ANA METOT
    public static void main(String[] args) {

        int secim;

        do {
            menu();
            secim = sc.nextInt();

            switch (secim) {
                case 1:
                    ogrenciEkle();
                    break;
                case 2:
                    notGir();
                    break;
                case 3:
                    ortalamaHesapla();
                    break;
                case 4:
                    harfNotlariGoster();
                    break;
                case 5:
                    gecenKalanListe();
                    break;
                case 6:
                    enYuksekOrtalama();
                    break;
                case 7:
                    enDusukOrtalama();
                    break;
                case 8:
                    sinifOrtalamasi();
                    break;
                case 9:
                    ogrenciAra();
                    break;
                case 0:
                    System.out.println("Program sonlandırıldı");
                    break;
                default:
                    System.out.println("Hatalı seçim yaptınız!");
            }

        } while (secim != 0);
    }

    // MENÜ
    static void menu() {
        System.out.println("\n--- ÖĞRENCİ YÖNETİM SİSTEMİ ---");
        System.out.println("1- Öğrenci Ekle");
        System.out.println("2- Not Gir");
        System.out.println("3- Ortalama Hesapla");
        System.out.println("4- Harf Notları");
        System.out.println("5- Geçen / Kalan Liste");
        System.out.println("6- En Yüksek Ortalama");
        System.out.println("7- En Düşük Ortalama");
        System.out.println("8- Sınıf Ortalaması");
        System.out.println("9- Öğrenci Ara");
        System.out.println("0- Çıkış");
        System.out.print("Seçim: ");
    }

    // ÖĞRENCİ EKLEME
    static void ogrenciEkle() {
        if (ogrenciSayisi >= MAX) {
            System.out.println("Kontenjan dolu!");
            return;
        }

        sc.nextLine(); // tampon temizleme
        System.out.print("Öğrenci adı: ");
        isimler[ogrenciSayisi] = sc.nextLine();

        vizeler[ogrenciSayisi] = 0;
        finaller[ogrenciSayisi] = 0;
        ortalamalar[ogrenciSayisi] = 0;
        gectiMi[ogrenciSayisi] = false;

        ogrenciSayisi++;
        System.out.println("Öğrenci başarıyla eklendi.");
    }

    // NOT GİRME
    static void notGir() {
        if (ogrenciSayisi == 0) {
            System.out.println("Önce öğrenci ekleyin!");
            return;
        }

        for (int i = 0; i < ogrenciSayisi; i++) {
            System.out.println("\n" + isimler[i]);

            do {
                System.out.print("Vize (0-100): ");
                vizeler[i] = sc.nextInt();
            } while (vizeler[i] < 0 || vizeler[i] > 100);

            do {
                System.out.print("Final (0-100): ");
                finaller[i] = sc.nextInt();
            } while (finaller[i] < 0 || finaller[i] > 100);
        }
    }

    // ORTALAMA HESAPLAMA
    static void ortalamaHesapla() {
        for (int i = 0; i < ogrenciSayisi; i++) {
            ortalamalar[i] = vizeler[i] * 0.4 + finaller[i] * 0.6;

            if (ortalamalar[i] >= 60)
                gectiMi[i] = true;
            else
                gectiMi[i] = false;
        }
        System.out.println("Ortalamalar hesaplandı.");
    }

    // HARF NOTLARI
    static void harfNotlariGoster() {
        for (int i = 0; i < ogrenciSayisi; i++) {
            System.out.println(isimler[i] + " → " + harfNotu(ortalamalar[i]));
        }
    }

    static String harfNotu(double o) {
        if (o >= 90) return "AA";
        else if (o >= 80) return "BA";
        else if (o >= 70) return "BB";
        else if (o >= 60) return "CB";
        else if (o >= 50) return "CC";
        else return "FF";
    }

    // GEÇEN / KALAN
    static void gecenKalanListe() {
        for (int i = 0; i < ogrenciSayisi; i++) {
            if (gectiMi[i])
                System.out.println(isimler[i] + " GEÇTİ");
            else
                System.out.println(isimler[i] + " KALDI");
        }
    }

    // EN YÜKSEK ORTALAMA
    static void enYuksekOrtalama() {
        double max = ortalamalar[0];
        int index = 0;

        for (int i = 1; i < ogrenciSayisi; i++) {
            if (ortalamalar[i] > max) {
                max = ortalamalar[i];
                index = i;
            }
        }
        System.out.println("En yüksek: " + isimler[index] + " = " + max);
    }

    // EN DÜŞÜK ORTALAMA
    static void enDusukOrtalama() {
        double min = ortalamalar[0];
        int index = 0;

        for (int i = 1; i < ogrenciSayisi; i++) {
            if (ortalamalar[i] < min) {
                min = ortalamalar[i];
                index = i;
            }
        }
        System.out.println("En düşük: " + isimler[index] + " = " + min);
    }

    // SINIF ORTALAMASI
    static void sinifOrtalamasi() {
        double toplam = 0;
        int i = 0;

        while (i < ogrenciSayisi) {
            toplam += ortalamalar[i];
            i++;
        }

        if (ogrenciSayisi > 0)
            System.out.println("Sınıf ortalaması: " + toplam / ogrenciSayisi);
    }

    // ÖĞRENCİ ARAMA
    static void ogrenciAra() {
        sc.nextLine();
        System.out.print("Aranan isim: ");
        String aranan = sc.nextLine();

        boolean bulundu = false;

        for (int i = 0; i < ogrenciSayisi; i++) {
            if (isimler[i].equalsIgnoreCase(aranan)) {
                System.out.println("Bulundu → Ortalama: " + ortalamalar[i]);
                bulundu = true;
                break;
            }
        }

        if (!bulundu)
            System.out.println("Öğrenci bulunamadı");
    }
}

