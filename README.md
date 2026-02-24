Nama : Aryandana Pascua Patiung
NPM : 2406438214
Link Deployment: https://zeroth-eugenia-nanda-pascua-e7ecbd38.koyeb.app

---

**1. Masalah Kualitas Kode yang Diperbaiki dan Strateginya:**
Selama pengerjaan evaluasi ini, saya memperbaiki beberapa masalah untuk memastikan *pipeline* berjalan lancar dan aman. 
- Pertama, saya memperbaiki kerentanan `Token-Permissions (High)` yang dideteksi oleh OSSF Scorecard di *workflow* GitHub Actions. Strategi saya adalah menerapkan *Principle of Least Privilege* dengan menambahkan `permissions: read-all` secara eksplisit di file `ci.yml` dan `pmd.yml`. Hal ini membatasi akses tulis (*write access*) bawaan robot yang terlalu luas, sehingga mengamankan *environment* CI dari modifikasi yang tidak sah. Kedua, saya mengatasi kegagalan *build* CI yang disebabkan oleh resolusi *template* HTML Thymeleaf. Sistem operasi lokal saya (macOS) bersifat *case-insensitive*, tetapi *runner* GitHub Actions (Ubuntu Linux) sangat *case-sensitive*. Strategi saya adalah mengubah penamaan file *template* (contoh: `CreateProduct.html` menjadi `createProduct.html`) agar sama persis dengan *string* yang dikembalikan oleh `ProductController`, guna memastikan kompatibilitas lintas *platform*.

**2. Evaluasi Definisi CI/CD:**
- Ya, saya yakin implementasi saat ini telah memenuhi definisi *Continuous Integration* (CI) dan *Continuous Deployment* (CD).
*Continuous Integration* (CI) tercapai karena setiap kali ada kode yang di-*push* atau *pull request* dibuat, GitHub Actions secara otomatis menjalankan *workflow* yang melakukan kompilasi kode, menjalankan *automated test* menggunakan Gradle, serta melakukan pemindaian kualitas dan keamanan kode (melalui PMD dan OSSF Scorecard). Hal ini memastikan bahwa perubahan baru terintegrasi dengan aman tanpa merusak fitur yang ada atau memunculkan kerentanan.
Sementara itu, *Continuous Deployment* (CD) tercapai dengan mengintegrasikan *repository* GitHub secara langsung dengan Koyeb (PaaS). Setiap kali ada kode baru yang berhasil di-*merge* ke *branch* `main` dan lolos *pipeline* CI, Koyeb secara otomatis mendeteksi perubahan tersebut, melakukan *build* *image* Docker menggunakan `Dockerfile` yang tersedia, dan men-*deploy* aplikasi yang diperbarui ke internet publik tanpa memerlukan intervensi manual sama sekali.