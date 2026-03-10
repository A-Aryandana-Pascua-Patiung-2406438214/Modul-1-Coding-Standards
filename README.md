Nama : Aryandana Pascua Patiung
NPM : 2406438214
Link Deployment: https://zeroth-eugenia-nanda-pascua-e7ecbd38.koyeb.app

---

**1. Reflect based on Percival (2017) proposed self-reflective questions, whether this TDD flow is useful enough for you or not.**
Alur Test-Driven Development (TDD) yang saya terapkan di modul ini terbukti sangat berguna. Dengan menulis tes terlebih dahulu (fase RED), saya dituntut untuk merancang *interface* dan ekspektasi *behavior* dari kode sebelum terjebak dalam kerumitan detail implementasi. Contohnya, saat mengerjakan `OrderService`, menulis skenario tes terlebih dahulu membuat saya lebih terarah dalam menentukan parameter dan *expected return* dari fungsi-fungsinya.

Selain itu, *safety net* dari tes yang sudah berstatus GREEN membuat saya merasa jauh lebih percaya diri saat melakukan *refactoring*. Ketika saya melakukan perubahan *hardcoded string* menjadi `OrderStatus` Enum pada model `Order`, saya bisa langsung memastikan fungsionalitasnya tidak rusak hanya dengan menjalankan tes ulang. Untuk ke depannya, hal yang perlu saya perbaiki adalah lebih membiasakan diri memikirkan skenario *unhappy path* (seperti *invalid ID* atau *invalid status*) di awal pembuatan tes, agar cakupan tes menjadi lebih komprehensif sejak awal siklus TDD.

**2. Reflect whether your tests have successfully followed F.I.R.S.T. principle or not.**
Secara keseluruhan, unit test yang telah saya buat di sesi tutorial ini sudah mengikuti prinsip F.I.R.S.T.:
- **Fast:** Tes berjalan sangat cepat (hanya dalam hitungan milidetik) karena terisolasi dengan baik. Pada *service layer* (`OrderServiceImplTest`), saya menggunakan *mocking* dengan Mockito sehingga tes tidak perlu benar-benar mengakses database atau layanan eksternal yang lambat.
- **Independent:** Penggunaan anotasi `@BeforeEach` pada file *test* memastikan bahwa *setup* data (seperti list of `Product` dan `Order`) selalu di-reset sebelum pengujian baru dimulai. Ini menjamin bahwa satu *test method* tidak akan saling memengaruhi *test method* lainnya.
- **Repeatable:** Tes memberikan hasil yang konsisten (selalu hijau jika kode benar) tidak peduli berapa kali dijalankan atau di *environment* mana tes tersebut dieksekusi.
- **Self-Validating:** Pengujian sepenuhnya mengandalkan *assertions* bawaan JUnit (seperti `assertEquals`, `assertNull`, `assertThrows`). Output akhirnya langsung memberikan konfirmasi lulus (hijau) atau gagal (merah) secara otomatis, tanpa memerlukan inspeksi manual pada *console log*.
- **Timely:** Tes ditulis tepat waktu, yaitu persis sebelum kode *production* diimplementasikan, murni mengikuti siklus TDD (Red-Green-Refactor).

Hal yang perlu saya perhatikan untuk pembuatan tes selanjutnya adalah menjaga agar satu metode tes sebisa mungkin hanya melakukan *assert* terhadap satu kelakuan (*single behavior*) spesifik, guna meningkatkan aspek *Independent* secara maksimal.