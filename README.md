# TP6DPBO2025C1

# Janji
Saya Naeya Adeani Putri dengan NIM 2304017 mengerjakan Tugas Praktikum Latihan 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program

Program ini merupakan game **Flappy Bird** sederhana yang dibangun menggunakan **Java Swing**, dengan fitur-fitur berikut:

- **Game over** saat player menabrak pipa atau jatuh ke bawah `JFrame`.
- **Restart game** dengan tombol keyboard `R` setelah game over.
- **Menampilkan skor** pada layar game menggunakan `JLabel`.
- **Menambahkan skor** (`+1`) setiap kali player berhasil melewati pipa.
- **Main Menu** berupa GUI Form awal (`StartForm`) dengan tombol **"Start Game"** untuk masuk ke `JFrame` game.

### Struktur Class

| Class            | Deskripsi                                               |
|------------------|----------------------------------------------------------|
| `StartForm.java` | GUI awal dengan tombol *Start Game*.                     |
| `FlappyBird.java`| `JFrame` utama tempat game berjalan.                     |
| `Player.java`    | Representasi burung dan logika gerakan.                  |
| `Pipe.java`      | Representasi pipa sebagai obstacle.                      |
| `App.java`       | Kelas `main` untuk menjalankan `StartForm`.              |

# Alur Program

1. Start Menu (`StartForm.java`)
    - Saat program dijalankan, GUI awal berupa `JFrame` akan muncul.
    - User menekan tombol **"Start Game"**, maka form ini akan tertutup dan membuka window game (`FlappyBird`).

2. Game Dimulai (`FlappyBird.java`)
    - `JFrame` game dibuka dengan komponen utama: player, pipa, dan background.
    - `Timer` digunakan untuk mengatur *refresh* game loop (gerakan burung dan pipa).

3. Player Bergerak (`Player.java`)
    - Player akan jatuh karena efek gravitasi dan bisa melompat saat menekan tombol **Spasi**.
    - Posisi `y` dan kecepatan akan diatur pada setiap tick dari `Timer`.
    - Jika player menyentuh bagian bawah `JFrame` atau menabrak pipa → **Game Over**.

4. Obstacle - Pipa (`Pipe.java`)
    - Pipa akan bergerak dari kanan ke kiri layar.
    - Setiap pipa memiliki celah acak di antara bagian atas dan bawah.
    - Jika player berhasil melewati sepasang pipa → **skor bertambah 1**.

5. Game Over & Restart
    - Jika terjadi Game Over, akan muncul teks **"Game Over"** di layar.
    - Tekan tombol **R** untuk me-*restart* game:
      - Semua elemen (player, pipa, skor) akan di-*reset*.

6. Skor
    - `JLabel` di sudut kiri atas akan menampilkan skor saat ini.
    - Skor akan bertambah setiap kali player berhasil melewati sepasang pipa.

# Dokumentasi


https://github.com/user-attachments/assets/5d7c6170-1c9b-42ad-8dcc-48bd6b85b724

