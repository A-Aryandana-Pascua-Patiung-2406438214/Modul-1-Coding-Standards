Nama : Aryandana Pascua Patiung
NPM : 2406438214
Link Deployment: https://zeroth-eugenia-nanda-pascua-e7ecbd38.koyeb.app

---

1) Explain what principles you apply to your project!
Pada proyek ini, saya menerapkan beberapa prinsip SOLID untuk merapikan struktur kode yang ada:

- Single Responsibility Principle (SRP): Saya memisahkan CarController yang sebelumnya berada di dalam ProductController.java menjadi filenya sendiri. Selain itu, saya memindahkan logika pembuatan UUID mobil yang awalnya ada di CarRepository ke CarServiceImpl. Hal ini karena tugas Repository seharusnya hanya untuk menyimpan dan mengambil data, sedangkan urusan pembuatan ID adalah bagian dari business logic yang menjadi tanggung jawab Service.

- Liskov Substitution Principle (LSP): Saya menghapus keyword extends ProductController pada CarController. Karena CarController tidak bisa menggantikan ProductController secara utuh. Keduanya memiliki behavior dan urusan routing yang berbeda, sehingga memaksakan inheritance di sini menyalahi prinsip LSP.

- Dependency Inversion Principle (DIP): Pada file CarController, saya mengubah injeksi dependensi yang awalnya bergantung pada concrete class (CarServiceImpl) menjadi bergantung pada interface (CarService). Hal ini membuat controller tidak terikat langsung pada detail implementasi spesifik.

2) Explain the advantages of applying SOLID principles to your project with examples.
Keuntungan utama menerapkan prinsip SOLID adalah kode menjadi jauh lebih rapi, terstruktur, dan mudah untuk dites maupun dikembangkan.

Contoh: Dengan menerapkan SRP (memisahkan CarController dan ProductController), apabila suatu saat saya ingin menambahkan fitur atau routing khusus untuk mobil, saya hanya perlu mengedit file CarController tanpa perlu khawatir akan merusak logika pada fitur produk. Selain itu, dengan menerapkan DIP, saya bisa lebih mudah membuat mocking untuk unit test di CarController karena saya hanya perlu melihat kontrak dari interface CarService, tanpa peduli bagaimana logic di dalam CarServiceImpl bekerja.

3) Explain the disadvantages of not applying SOLID principles to your project with examples.
Jika tidak menerapkan prinsip SOLID, kode akan cenderung memiliki coupling yang tinggi (saling ketergantungan), sehingga satu perubahan kecil bisa memicu error di banyak tempat yang tidak terduga.

Contoh: Saat CarController masih menjadi child dari ProductController (pelanggaran LSP), saya sempat mengalami error UnsatisfiedDependencyException saat menjalankan unit test untuk Product. Hal ini terjadi karena Spring Boot ikut mencoba me-load dependensi milik Car padahal saya hanya ingin mengetes Product. Contoh lainnya, jika logika generate UUID tetap dibiarkan di CarRepository (pelanggaran SRP), developer lain yang mencari bug terkait ID akan kesulitan, karena umumnya urusan modifikasi data sebelum disimpan adalah tugas layer Service, bukan Repository.