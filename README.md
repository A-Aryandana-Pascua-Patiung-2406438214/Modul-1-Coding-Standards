Nama : Aryandana Pascua Patiung NPM : 2406438214

Prinsip Clean Code yang Diterapkan
Saya telah menerapkan beberapa prinsip Clean Code dalam latihan ini untuk memastikan kode mudah dibaca, dirawat, dan dikembangkan:

Penamaan yang Jelas (Meaningful Names): Saya menggunakan nama yang deskriptif untuk kelas, metode, dan variabel. Contohnya: ProductRepository, create, findAll, findById, update, dan delete. Nama-nama ini secara jelas menunjukkan tujuannya tanpa memerlukan komentar yang berlebihan .

Single Responsibility Principle (SRP): Saya memisahkan tanggung jawab ke dalam tiga lapisan terpisah:

ProductController: Menangani request HTTP dan tampilan HTML.

ProductService: Menangani logika bisnis dan bertindak sebagai jembatan.

ProductRepository: Mengelola penyimpanan data (list in-memory). Pemisahan ini memastikan bahwa setiap kelas hanya memiliki satu alasan untuk berubah.

Fungsi yang Fokus: Metode-metode dalam service dan repository saya dibuat kecil dan fokus melakukan satu hal saja dengan baik (contoh: delete hanya menghapus produk, findById hanya mencari produk).

Praktik Secure Coding yang Diterapkan
Saya juga mempertimbangkan beberapa praktik keamanan (secure coding) selama implementasi:

Pembuatan ID yang Aman: Alih-alih menggunakan integer berurutan (1, 2, 3...) untuk ID Produk, saya menggunakan UUID.randomUUID(). Ini membuat ID sulit ditebak dan membantu mencegah serangan enumerasi (Insecure Direct Object References).

Output Data Encoding: Dengan menggunakan Thymeleaf (th:text), aplikasi secara otomatis menangani encoding output. Ini membantu melindungi aplikasi dari serangan Cross-Site Scripting (XSS) dengan memperlakukan input pengguna sebagai teks biasa, bukan skrip yang bisa dieksekusi .

Metode HTTP yang Tepat: Saya menggunakan metode HTTP yang benar untuk operasi yang mengubah status data. Contohnya, pembuatan dan pengeditan produk menggunakan request POST, yang lebih aman dan sesuai secara semantik dibandingkan menggunakan GET untuk modifikasi data.

Kesalahan dan Area Peningkatan
Saat meninjau kembali kode saya, saya menemukan beberapa area yang masih bisa diperbaiki:

Kurangnya Validasi Input: Saat ini, model Product saya menerima input apa saja. Pengguna bisa saja mengirimkan produk dengan nama kosong atau jumlah kuantitas negatif.

Perbaikan: Saya seharusnya menerapkan anotasi validasi input (seperti @NotNull, @Size, @Min) pada field model Product dan memvalidasi statusnya di Controller sebelum memproses data. Ini mengikuti praktik secure coding "Validasi Data Input" untuk memastikan input mentah telah disanitasi .

Penanganan Error untuk "Produk Tidak Ditemukan": Pada fitur edit dan delete, jika pengguna mencoba mengakses ID produk yang tidak ada, aplikasi saat ini mungkin mengembalikan objek null atau halaman error bawaan.

Perbaikan: Saya harus mengimplementasikan penanganan error yang tepat. Misalnya, jika findById mengembalikan null, controller harus mengarahkan ke halaman kustom "404 Not Found" atau menampilkan pesan error yang ramah kepada pengguna, alih-alih membiarkan aplikasi crash atau menampilkan stack trace .