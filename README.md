# Minpro-4-PBO-Sistem Inventaris Laboratorium Kimia

Naisyla Auliya Rahmi Putri  
Kelas: B  
NIM: 2509116078

## 1. Deskripsi Singkat Program   
Sistem Inventaris Laboratorium adalah program sederhana berbasis Java yang digunakan untuk mengelola daftar alat dan pencatatan peminjaman serta pengembalian alat di laboratorium, seperti mikroskop, tabung reaksi, dan perangkat praktikum lainnya. Setiap data alat memiliki tiga informasi utama, yaitu ID alat, nama alat, dan jumlah alat. Program ini dirancang dengan menerapkan pola modular menggunakan *package* terpisah untuk mengatur struktur kode.
Program ini memiliki enam pilihan menu utama, yaitu Tambah Data Alat, Lihat Data Alat, Ubah Data Alat, Hapus Data Alat, Peminjaman dan Pengembalian, serta Keluar. Pengguna dapat mengubah atau menghapus data alat, serta mencatat peminjaman berdasarkan ID alat yang telah terdaftar di dalam sistem.
Program menerapkan konsep Pemrograman Berorientasi Objek (PBO) dengan menggunakan *ArrayList untuk menyimpan data selama program berjalan. Validasi input juga diterapkan untuk menjaga kualitas data, seperti menolak jumlah alat yang bernilai negatif atau nama alat yang kosong. Data inventaris dan peminjaman yang dimasukkan bersifat sementara dan akan diatur ulang ketika program ditutup.  

---
## 2. Tujuan Program
   Program ini dibuat untuk memenuhi tugas mini project Pemrograman Berorientasi Objek dengan menerapkan operasi CRUD (Create, Read, Update, Delete) serta fitur peminjaman pada data inventaris alat laboratorium. Tujuan dari program ini adalah:
   - Memudahkan pencatatan data alat laboratorium beserta jumlahnya.
   - Menampilkan daftar alat yang telah ditambahkan ke dalam sistem.
   - Memungkinkan perubahan informasi nama dan jumlah alat.
   - Menghapus data alat yang sudah tidak diperlukan dari inventaris.
   - Mengelola pencatatan peminjaman dan pengembalian alat secara terstruktur berdasarkan ID alat yang terdaftar.
---
## Struktur Program
Program ini terdiri dari beberapa class, interface, dan file konfigurasi yang terorganisasi secara modular untuk menjalankan sistem manajemen inventaris laboratorium berbasis Java MVC dan database MySQL:

| No. | Class / Interface / File | Fungsi |
|---|---|---|
| 1 | `TampilkanInventarisLab.java` <br>*(Package `view`)* | Kelas utama antarmuka (View) berbasis CLI yang menampilkan menu interaktif, menangani input, serta memvalidasi masukan pengguna[cite: 3]. |
| 2 | `LabControll.java` <br>*(Package `control`)* | Kelas pengendali (Controller) yang mengatur logika bisnis dan menjembatani komunikasi langsung dengan database melalui layer DAO, serta menerapkan **Overloading** pada fungsi pencarian alat[cite: 13]. |
| 3 | `AlatLab.java` <br>*(Package `model`)* | Kelas model objek alat laboratorium (subclass dari `KeperluanLab`) yang mengimplementasikan interface `InformasiLab` dan menangani atribut kondisi alat[cite: 4, 6]. |
| 4 | `BahanLab.java` <br>*(Package `model`)* | Kelas model objek bahan laboratorium (subclass dari `KeperluanLab`) dengan atribut tambahan berupa satuan bahan[cite: 5, 6]. |
| 5 | `KeperluanLab.java` <br>*(Package `model`)* | Kelas abstrak (*superclass*) yang mewarisi atribut dasar (id, nama, jumlah) beserta validasi encapsulation kepada subclass alat dan bahan[cite: 6]. |
| 6 | `InformasiLab.java` <br>*(Package `model`)* | *Interface* murni yang berisi kontrak method `void cetakLabelKategori();` untuk menampilkan kategori peralatan[cite: 5]. |
| 7 | `Peminjaman.java` <br>*(Package `model`)* | Kelas model yang menyimpan data transaksi peminjaman alat (ID peminjaman, nama peminjam, ID alat, jumlah pinjam) lengkap dengan validasinya[cite: 7]. |
| 8 | `AlatLabDAO.java` <br>*(Package `dao`)* | Kelas Data Access Object (DAO) untuk mengeksekusi operasi CRUD SQL (`INSERT`, `UPDATE`, `DELETE`, `SELECT`) tabel `alat_lab`[cite: 9]. |
| 9 | `BahanLabDAO.java` <br>*(Package `dao`)* | Kelas DAO yang menangani query database untuk manipulasi data tabel `bahan_lab`[cite: 10]. |
| 10 | `PeminjamanDAO.java` <br>*(Package `dao`)* | Kelas DAO yang mengelola data transaksi peminjaman dan pengembalian alat pada database[cite: 11]. |
| 11 | `DAO.java` <br>*(Package `dao`)* | *Generic Interface* standar yang mendefinisikan kontrak method CRUD (`insert`, `update`, `delete`, `getAll`, `getById`) untuk semua DAO[cite: 10]. |
| 12 | `Koneksi.java` <br>*(Package `config`)* | Kelas utilitas untuk menghubungkan aplikasi Java ke database MySQL menggunakan JDBC (`DriverManager`)[cite: 14]. |
| 13 | `persistence.xml` <br>*(Folder `resources/META-INF`)* | File konfigurasi XML standar JPA/Hibernate untuk mendefinisikan *persistence-unit* dan pemetaan entitas database[cite: 1, 17]. |
| 14 | `InventarisLab.java` <br>*(Package `com.mycompany.inventarislab`)* | Kelas utama (*entry point* / `main`) yang mengeksekusi dan menginisialisasi sistem aplikasi inventaris[cite: 16]. |

---

#### 4. Hubungan antarclass

- **`InventarisLab.java`** bertindak sebagai kelas utama (*entry point*) yang memanggil dan menjalankan menu dari kelas View, serta mengatur konfigurasi logger konsol[cite: 16].
- **`TampilkanInventarisLab.java`** *(package `view`)* bertugas menerima input interaktif dari pengguna, menjalankan antarmuka menu CLI, memvalidasi masukan, dan memanggil method pada Controller[cite: 3].
- **`LabControll.java`** *(package `control`)* mengelola seluruh logika bisnis dan memicu operasi CRUD ke database melalui objek DAO (`AlatLabDAO`, `BahanLabDAO`, `PeminjamanDAO`)[cite: 9, 10, 11, 13], serta menyajikan fungsi *Overloading* (`cariAlat`)[cite: 13].
- **`DAO.java`** *(package `dao`)* adalah *generic interface* yang menjadi kontrak dasar operasi CRUD (`insert`, `update`, `delete`, `getAll`, `getById`)[cite: 10] untuk diimplementasikan oleh **`AlatLabDAO`**, **`BahanLabDAO`**, dan **`PeminjamanDAO`**[cite: 9, 10, 11].
- **`Koneksi.java`** *(package `config`)* bertugas membuka koneksi JDBC ke database MySQL (`inventaris_laboratorium`)[cite: 14] yang dimanfaatkan oleh seluruh kelas DAO.
- **`KeperluanLab.java`** *(package `model`)* adalah *superclass* abstrak yang mewarikan atribut dasar (`id`, `nama`, `jumlah`) beserta kapsulasi validasi kepada *subclass* **`AlatLab.java`** dan **`BahanLab.java`**[cite: 5, 6].
- **`InformasiLab.java`** *(package `model`)* bertindak sebagai *interface* kontrak murni yang diimplementasikan oleh **`AlatLab.java`** untuk mencetak label kategori peralatan[cite: 4, 5].
- **`Peminjaman.java`** *(package `model`)* menyimpan data transaksi peminjaman alat yang tervalidasi dan terhubung dengan data stok alat di database[cite: 7, 13].

```text
InventarisLab
├── Source Packages
│   ├── com.mycompany.inventarislab
│   │   └── InventarisLab.java
│   ├── config
│   │   ├── Koneksi.java
│   │  
│   ├── control
│   │   └── LabControll.java
│   ├── dao
│   │   ├── AlatLabDAO.java
│   │   ├── BahanLabDAO.java
│   │   ├── DAO.java (Interface)
│   │   └── PeminjamanDAO.java
│   ├── model
│   │   ├── AlatLab.java
│   │   ├── BahanLab.java
│   │   ├── InformasiLab.java (Interface)
│   │   ├── KeperluanLab.java (Abstract Class)
│   │   └── Peminjaman.java
│   └── view
│       └── TampilkanInventarisLab.java
├── Other Sources
│   └── src/main/resources
│       └── META-INF
│           └── persistence.xml
├── Test Packages
├── Dependencies
└── Project Files
```
<p align="center"> 
<img width="313" height="582" alt="image" src="https://github.com/user-attachments/assets/4e004721-0fef-4a28-bf11-ef2123f78331" />
</p>
Struktur package program dengan penambahan package config dan dao.

---

## 5. Menu Program
Program memiliki enam menu utama:

```text
^-^ ============================================ ^-^
        SISTEM INVENTARIS LABORATORIUM
^-^ ============================================ ^-^
1. Tambah Data
2. Lihat Data & Cari Alat
3. Ubah Data
4. Hapus Data
5. Peminjaman dan Pengembalian
6. Keluar
==================================================
Pilih menu:
```

### 1. Tambah Data
- Tambah Alat: Digunakan untuk memasukkan data alat baru dengan sistem auto-increment ID otomatis, dilengkapi validasi input nama (tidak boleh kosong atau hanya simbol/spasi) dan jumlah (harus angka positif > 0).
- Tambah Bahan: Digunakan untuk memasukkan data bahan baru dengan ID otomatis, serta input tambahan berupa satuan bahan (seperti ml, gram, pcs) sebagai contoh, pengguna bisa memasukkan satuan bahan lain diluar contoh (sesuai kebutuhan yang memang diperlukan).
### 2. Lihat Data & Cari Alat
- Lihat Data Alat: Menampilkan daftar seluruh alat laboratorium yang tersimpan di dalam ArrayList lengkap dengan format stok dan informasi ketersediaannya.
- Lihat Data Bahan: Menampilkan daftar seluruh bahan laboratorium beserta jumlah stok dan satuannya.
- Cari Alat (Demonstrasi Overloading): Fitur pencarian fleksibel yang menerapkan konsep Method Overloading, di mana pengguna dapat mencari data alat secara spesifik berdasarkan ID (angka) maupun berdasarkan Nama (teks) menggunakan nama method yang sama.  
### 3. Ubah Data
Ubah Data Alat / Bahan: Memperbarui data berdasarkan ID yang dipilih. Dilengkapi fitur fleksibel di mana pengguna dapat menekan tombol Enter untuk melewati (mempertahankan) nama lama jika tidak ingin diubah, serta validasi stok baru.
### 4. Hapus Data
Hapus Data Alat / Bahan: Menghapus data dari inventaris berdasarkan ID yang diinputkan oleh pengguna.
### 5. Peminjaman dan Pengembalian
- Pinjam Alat: Mencatat transaksi peminjaman (menginput nama peminjam, ID alat, dan jumlah pinjam) dengan validasi otomatis terhadap ketersediaan stok di inventaris agar tidak melebihi batas.
- Lihat Peminjaman: Menampilkan daftar riwayat dan status peminjaman alat yang sedang aktif.
- Pengembalian: Memproses pengembalian alat berdasarkan ID peminjaman, yang secara otomatis akan mengembalikan jumlah stok alat ke inventaris semula.
### 6. Keluar
Digunakan untuk menghentikan program.

---

## 6. Demo Program
<img width="397" height="193" alt="image" src="https://github.com/user-attachments/assets/bba12745-4552-41bb-b432-58dd8eec376c" />

Gambar ini menunjukkan tampilan awal (menu utama) dari aplikasi Sistem Inventaris Laboratorium saat pertama kali dijalankan. Di sini terdapat 6 pilihan menu utama mulai dari mengelola data (tambah, lihat, ubah, hapus), menu peminjaman dan pengembalian, hingga opsi untuk keluar dari program.

### **1. Tambah Data**
<img width="396" height="120" alt="image" src="https://github.com/user-attachments/assets/76d745f2-92ae-495c-84a7-cc34143e45e4" />

Gambar ini menampilkan sub-menu saat pengguna memilih opsi nomor 1 (Tambah Data) dari menu utama. Pengguna dapat memilih apakah ingin menambahkan data alat laboratorium (pilihan 1) atau data bahan laboratorium (pilihan 2).  

#### **a. Tambah Alat**

<img width="398" height="346" alt="image" src="https://github.com/user-attachments/assets/5bf8fe71-2174-48fd-b3f3-9fad82b9c0ae" />

Gambar ini memperlihatkan proses pada Menu Tambah Alat. Di sini terlihat program sudah menggunakan fitur validasi input (defensive programming), di mana sistem secara otomatis akan menolak jika pengguna memasukkan nama alat yang hanya berupa simbol atau spasi, membiarkannya kosong, ataupun menginput jumlah dengan angka nol dan minus. Program baru akan menyimpan data dan menampilkan pesan berhasil setelah semua input diisi dengan benar (contohnya nama "Sekop" dengan jumlah "10").

<img width="491" height="93" alt="image" src="https://github.com/user-attachments/assets/0b4e89b3-0fee-4ff3-9ebd-98f1641a8ae3" />

Gambar tersebut memperlihatkan bahwa data alat baru sudah berhasil ditambahkan.


#### **b. Tambah Bahan**

<img width="401" height="364" alt="image" src="https://github.com/user-attachments/assets/4c7bdaf7-1403-4d37-97a5-9e0cfc37c823" />

Gambar ini memperlihatkan proses pada Menu Tambah Bahan. Sama seperti sebelumnya, menu ini juga sudah menerapkan validasi input, di mana program akan otomatis menolak jika nama bahan hanya berupa simbol, dikosongkan, jumlahnya bernilai nol atau minus, maupun saat satuan bahan dikosongkan. Data baru akan berhasil disimpan setelah semua input diisi dengan benar (seperti nama "H2O", jumlah "3", dan satuan "gram").   

<img width="460" height="66" alt="image" src="https://github.com/user-attachments/assets/a4bbf43e-45c9-42f5-9b9c-f729b54ca3f9" />

Gambar tersebut memperlihatkan bahwa data bahan baru sudah berhasil ditambahkan.

---

### **2. Lihat Data**
<img width="369" height="130" alt="image" src="https://github.com/user-attachments/assets/23b49bc0-2841-4c35-9a26-6bceedbae81d" />


Gambar ini menunjukkan sub-menu saat pengguna memilih opsi nomor 2, yaitu Lihat Data & Pencarian. Pada menu ini, pengguna dapat memilih untuk melihat daftar data alat laboratorium, daftar data bahan laboratorium, atau menggunakan fitur Cari Alat yang menerapkan konsep Method Overloading dalam sistem.

#### **a. Lihat Data Alat**
<img width="574" height="203" alt="image" src="https://github.com/user-attachments/assets/683764c8-bc0d-4650-bc74-3b30ad62e748" />


Gambar ini menunjukkan hasil saat pengguna memilih untuk melihat daftar alat laboratorium (pilihan 1 pada menu lihat data). Sistem menampilkan seluruh data alat yang tersimpan secara rapi, lengkap dengan informasi ID alat, nama alat, serta jumlah stok yang tersedia.

#### **b. Lihat Data Bahan**
<img width="490" height="160" alt="image" src="https://github.com/user-attachments/assets/21c9e50a-aa32-45c4-bbc5-ee5582faa533" />


Gambar ini menunjukkan hasil saat pengguna memilih untuk melihat daftar bahan laboratorium (pilihan 2 pada menu lihat data). Sistem menampilkan seluruh data bahan yang tersimpan, lengkap dengan informasi ID bahan, nama bahan, jumlah stok, beserta satuannya.

#### **c. Cari Alat (Demonstrasi Overloading)**

<img width="319" height="181" alt="image" src="https://github.com/user-attachments/assets/a59efa1d-c430-46ec-9c81-434a89e16c0d" />


Gambar ini menunjukkan proses saat pengguna memilih opsi nomor 3, yaitu Cari Alat, untuk menerapkan konsep Method Overloading. Pengguna dapat memilih metode pencarian berdasarkan ID alat berupa angka (pilihan 1) atau nama alat berupa teks (pilihan 2). Pilihan tersebut akan memanggil method cariAlat yang sesuai pada controller.

<img width="552" height="204" alt="image" src="https://github.com/user-attachments/assets/7c5e640b-1ec5-4168-9e86-5e72de3e83d7" />

Gambar ini menunjukkan validasi input pada fitur pencarian alat berdasarkan ID. Sistem menolak input yang tidak valid, seperti angka minus (-999999) dan teks (Mikroskop). Setelah pengguna memasukkan ID yang benar, yaitu 102, sistem berhasil menjalankan method cariAlat(int idAlat) dan menampilkan informasi alat secara lengkap.

<img width="630" height="220" alt="image" src="https://github.com/user-attachments/assets/21b10fa1-35d5-4716-a871-7f718e83326d" />

Gambar ini menunjukkan validasi input teks pada fitur pencarian alat. Sistem menolak input yang kosong atau hanya berisi angka, spasi, dan simbol, lalu meminta pengguna memasukkan data yang benar. Setelah pengguna memasukkan nama alat "Mikroskop", sistem menjalankan method cariAlat(String namaAlat) sebagai penerapan Method Overloading dan menampilkan informasi alat tersebut.

---

### **3. Ubah Data**
<img width="237" height="119" alt="image" src="https://github.com/user-attachments/assets/c254bab7-85a5-4420-ba5b-5da64710f166" />

Gambar ini menunjukkan sub-menu saat pengguna memilih opsi nomor 3 (Ubah Data) dari menu utama. Di sini pengguna dapat memilih apakah ingin memperbarui atau mengubah data alat laboratorium (pilihan 1) atau data bahan laboratorium (pilihan 2) yang sudah tersimpan sebelumnya di dalam sistem.

#### **a. Ubah Data Alat**
<img width="526" height="295" alt="image" src="https://github.com/user-attachments/assets/1a1c8f51-af91-4853-bcae-f342499d32b3" />

Gambar ini memperlihatkan proses pada Menu Ubah Data Alat. Sistem akan menampilkan daftar alat yang tersedia terlebih dahulu, lalu pengguna diminta memasukkan ID alat yang ingin diubah (contohnya ID 101). Pengguna juga memiliki fleksibilitas untuk mengisi nama baru (atau menekan Enter jika tidak ingin mengubah nama) serta memperbarui jumlah stok yang baru, dan program akan menampilkan pesan berhasil setelah data diperbarui.

<img width="502" height="293" alt="image" src="https://github.com/user-attachments/assets/a14856e6-7018-448e-9dc0-15c6569725c4" />

Gambar ini memperlihatkan contoh penggunaan fitur fleksibel pada Menu Ubah Data Alat. Ketika pengguna memilih ID alat (contohnya ID 101) dan langsung menekan tombol Enter pada bagian "Nama Baru", sistem akan mengabaikan perubahan nama sehingga nama alat tetap seperti sebelumnya, lalu hanya memperbarui jumlah stok yang baru

<img width="493" height="300" alt="image" src="https://github.com/user-attachments/assets/7842097e-0d55-4262-98fc-7b65e85ea0f8" />

Gambar ini memperlihatkan penanganan kesalahan saat pengguna memasukkan ID alat yang tidak terdaftar di sistem (contohnya memasukkan ID 1 padahal daftar ID yang ada adalah 101, 102, dan 103). Sistem akan mendeteksi bahwa ID tersebut tidak ada dan langsung menampilkan pesan peringatan "Alat dengan ID tersebut tidak ditemukan."

<img width="503" height="362" alt="image" src="https://github.com/user-attachments/assets/933f54d8-e0b2-4159-a3e2-81b3699009ce" />

Gambar ini memperlihatkan fitur validasi input saat pengguna mengubah jumlah stok pada data alat. Sistem secara otomatis akan menolak jika pengguna memasukkan angka nol atau nilai minus (seperti -0 atau -99), dan baru akan berhasil menyimpan perubahan setelah memasukkan angka positif yang valid.

<img width="493" height="69" alt="image" src="https://github.com/user-attachments/assets/00b7724e-510f-438c-a76d-4dd204d42e92" />

Gambar menunjukkan bahwa data alat sudah berhasil di ubah (stok = 100) sesuai yang di input pada sistem.


#### **b. Ubah Data Bahan**
<img width="467" height="294" alt="image" src="https://github.com/user-attachments/assets/2a76d5e5-dd81-4e30-8cc8-92bb2eab152d" />

Gambar ini menunjukkan proses pada Menu Ubah Data Bahan. Pengguna memasukkan ID bahan yang ingin diperbarui (contohnya ID 201), lalu mengisi nama baru (Retinol 90%), jumlah stok baru (20), serta satuan baru (100 (ml)), hingga akhirnya program menampilkan pesan bahwa data bahan berhasil diubah.

<img width="478" height="71" alt="image" src="https://github.com/user-attachments/assets/27b1fdbd-82b9-41a1-8894-537f48a24b80" />

Gambar setelah Data Bahan diubah sesuai yang sudah dimasukkan di sistem.


<img width="493" height="293" alt="image" src="https://github.com/user-attachments/assets/28fb4d86-eff4-42b3-8456-2be57068057a" />

Gambar ini memperlihatkan penanganan kesalahan saat pengguna memasukkan ID bahan yang tidak terdaftar di sistem (contohnya memasukkan ID 10 padahal daftar ID yang tersedia adalah 201 dan 202). Sistem langsung mendeteksi bahwa ID tersebut tidak ada dan menampilkan pesan peringatan "Bahan dengan ID tersebut tidak ditemukan."

<img width="484" height="293" alt="image" src="https://github.com/user-attachments/assets/75162808-9e6c-4144-9a85-fd10a4643470" />

Gambar ini memperlihatkan contoh penggunaan fitur fleksibel pada Menu Ubah Data Bahan. Ketika pengguna memasukkan ID bahan (contohnya 201) dan langsung menekan tombol Enter pada bagian "Nama Baru", sistem akan mempertahankan nama bahan yang lama tanpa mengubahnya, lalu memperbarui jumlah stok baru (10) beserta satuan barunya ((200 ml)), hingga akhirnya data berhasil diubah.

<img width="479" height="424" alt="image" src="https://github.com/user-attachments/assets/57404878-c139-4e98-8baa-7324d7e1bd3b" />

Gambar ini memperlihatkan fitur validasi input yang ketat pada Menu Ubah Data Bahan. Sistem secara otomatis akan menolak jika pengguna memasukkan jumlah stok baru bernilai nol atau minus (-0, 0, -99), serta menolak jika bagian satuan baru dikosongkan. Perubahan data baru akan berhasil disimpan setelah seluruh input diisi dengan valid (seperti jumlah 10 dan satuan ml).

---

### **4. Hapus Data**
<img width="236" height="111" alt="image" src="https://github.com/user-attachments/assets/1ea1f13e-7907-4d28-8870-d3c2f119f714" />

Gambar ini menunjukkan sub-menu saat pengguna memilih opsi nomor 4 (Hapus Data) dari menu utama. Di sini pengguna dapat memilih apakah ingin menghapus data alat laboratorium (pilihan 1) atau data bahan laboratorium (pilihan 2).

#### **a. Hapus Data Alat**
<img width="502" height="226" alt="image" src="https://github.com/user-attachments/assets/313186d7-ab8f-40b5-9ee7-f54dc010431e" />

Gambar ini memperlihatkan proses penghapusan data pada Menu Hapus Data Alat. Pengguna memasukkan ID alat yang ingin dihapus (contohnya ID 103), dan sistem langsung memprosesnya serta menampilkan pesan konfirmasi "Data alat berhasil dihapus".

<img width="518" height="83" alt="image" src="https://github.com/user-attachments/assets/b92ebb5d-e741-496d-ab7d-7637211ce23b" />

Gambar setelah Data Alat dengan ID tersebut dihapus.

<img width="494" height="223" alt="image" src="https://github.com/user-attachments/assets/7d4bbd2c-176c-416b-8e8e-0dde4d7a2443" />

Gambar ini memperlihatkan penanganan kesalahan saat pengguna memasukkan ID alat yang tidak terdaftar di sistem pada Menu Hapus Data (contohnya memasukkan ID 0 padahal daftar ID yang tersedia adalah 101, 102, dan 103). Sistem secara otomatis mendeteksi bahwa ID tersebut tidak ditemukan dan langsung menampilkan pesan peringatan "Alat dengan ID tersebut tidak ditemukan."

#### **b. Hapus Data Bahan**
<img width="489" height="197" alt="image" src="https://github.com/user-attachments/assets/789f10a3-88e4-492b-bd66-7887322971dd" />

Gambar ini memperlihatkan proses penghapusan data pada Menu Hapus Data Bahan. Pengguna memasukkan ID bahan yang ingin dihapus (contohnya ID 202), dan sistem langsung memprosesnya serta menampilkan pesan konfirmasi "Data bahan berhasil dihapus".

<img width="488" height="62" alt="image" src="https://github.com/user-attachments/assets/0af7a26e-ddd5-49a2-ad46-d2de49cf98cc" />

Gambar setelah menghapus Data Bahan sesuai ID yang di input.

<img width="495" height="201" alt="image" src="https://github.com/user-attachments/assets/24227bdd-9ceb-4b22-9ccf-0f7c2b24dd25" />

Gambar ini memperlihatkan penanganan kesalahan saat pengguna memasukkan ID bahan yang tidak terdaftar di sistem pada Menu Hapus Data (contohnya memasukkan ID 10 padahal daftar ID bahan yang tersedia adalah 201 dan 202). Sistem secara otomatis mendeteksi bahwa ID tersebut tidak ditemukan dan langsung menampilkan pesan peringatan "Bahan dengan ID tersebut tidak ditemukan."

---

### **5. Peminjaman dan Pengembalian**
<img width="303" height="128" alt="image" src="https://github.com/user-attachments/assets/96e33a06-9fce-4fc0-af4d-6d9a68e8a959" />

Gambar ini menunjukkan sub-menu saat pengguna memilih opsi nomor 5 (Peminjaman) dari menu utama. Di sini terdapat beberapa pilihan fitur terkait transaksi peminjaman, yaitu:  
- Pinjam Alat (pilihan 1) untuk mencatat peminjaman alat laboratorium.
- Lihat Peminjaman (pilihan 2) untuk melihat daftar atau riwayat alat yang sedang dipinjam.
- Pengembalian (pilihan 3) untuk memproses pengembalian alat laboratorium yang telah dipinjam.

#### **a. Pinjam Alat**
<img width="487" height="305" alt="image" src="https://github.com/user-attachments/assets/d6958773-4973-4029-8cc4-070754acc8e6" />

Gambar ini memperlihatkan proses transaksi peminjaman pada Menu Pinjam Alat. Sistem menampilkan daftar alat yang tersedia, lalu pengguna memasukkan informasi peminjaman seperti ID peminjaman, nama peminjam (Nai), ID alat yang dipinjam (101), dan jumlah pinjam (10). Setelah itu, sistem berhasil mencatat transaksi dan memperbarui stok alat secara otomatis.

<img width="496" height="77" alt="image" src="https://github.com/user-attachments/assets/f2e1fe4a-4ef1-4258-9af1-9b1ae1098c31" />

Update stok sesuai berapa banyak yang dipinjam.

<img width="494" height="303" alt="image" src="https://github.com/user-attachments/assets/39266ccc-0ecf-4953-9c8b-acd1e7b74a5e" />

Gambar ini memperlihatkan fitur validasi ketika pengguna mencoba meminjam alat dengan jumlah yang melebihi stok yang tersedia. Saat peminjam mencoba meminjam 200 buah alat dengan ID 101 (padahal sisa stok saat itu hanya 90), sistem secara otomatis menolak transaksi dan menampilkan pesan peringatan "Gagal: Stok alat tidak mencukupi. Sisa stok saat ini: 90".

<img width="493" height="313" alt="image" src="https://github.com/user-attachments/assets/2eaaa356-a184-4fe8-905c-7d132dd39fc3" />

Gambar ini memperlihatkan fitur validasi ketika pengguna memasukkan ID alat yang tidak terdaftar di dalam inventaris saat ingin melakukan peminjaman. Saat peminjam mencoba memasukkan ID alat 10 (yang tidak ada dalam daftar), sistem secara otomatis menggagalkan transaksi dan menampilkan pesan peringatan "Gagal: ID Alat yang ingin dipinjam tidak terdaftar di inventaris."

#### **b. Lihat Peminjam**
<img width="333" height="257" alt="image" src="https://github.com/user-attachments/assets/1e493db3-be74-4fcb-97c3-d859b7dfc96e" />

Gambar ini menunjukkan hasil saat pengguna memilih opsi nomor 2 (Lihat Peminjaman) di dalam sub-menu Peminjaman. Sistem menampilkan daftar rinci data peminjaman alat yang sedang aktif, yang mencakup informasi ID Peminjaman (misalnya 1), Nama Peminjam (Nai), ID Alat yang dipinjam (101), serta Jumlah Dipinjam (10).

#### **c. Pengembalian**
<img width="514" height="177" alt="image" src="https://github.com/user-attachments/assets/b7ab21a0-3e64-46a5-b510-f600f3605fcf" />

Gambar ini memperlihatkan proses pengembalian alat pada Menu Pengembalian (pilihan 3 dari sub-menu Peminjaman). Pengguna memasukkan ID peminjaman yang dikembalikan (contohnya ID 1), lalu sistem mencatat pengembalian tersebut, memperbarui status, dan mengembalikan stok alat ke inventaris dengan menampilkan pesan sukses "Pengembalian berhasil dicatat. Stok alat telah dikembalikan ^-^".

<img width="497" height="76" alt="image" src="https://github.com/user-attachments/assets/f147aece-fe23-4fd5-bfec-bbd5ed8ca1d1" />

Gambar menampilkan bahwa stok akan kembali seperti semula setelah dilakukan pengembalian alat.

---

### **6. Keluar**
<img width="542" height="158" alt="image" src="https://github.com/user-attachments/assets/1ee7dfad-d046-4091-a381-24155958fd30" />

Gambar ini memperlihatkan tampilan saat pengguna memilih opsi nomor 6 dari menu utama untuk keluar dari program. Sistem menampilkan pesan "Program selesai. Terima kasih!" diikuti dengan status eksekusi program yang sukses (BUILD SUCCESS) beserta informasi durasi dan waktu penyelesaian.

---

## 7. Penerapan Inheritance dan Encapsulation
### a. Enhericate
<img width="472" height="101" alt="image" src="https://github.com/user-attachments/assets/30a8a604-5bfa-4ada-9bfe-5128215a97e9" />    



<img width="410" height="59" alt="image" src="https://github.com/user-attachments/assets/a923b6e7-d927-4120-8a2a-89f711235253" />    



<img width="575" height="95" alt="image" src="https://github.com/user-attachments/assets/b0154eee-dd65-45ff-b4f9-1ca78f587a85" />    

Potongan kode di atas menunjukkan implementasi dari Inheritance (Pewarisan) 
- Class KeperluanLab bertindak sebagai superclass (kelas induk) yang mendeklarasikan atribut umum seperti id, nama, dan jumlah.
- Class AlatLab dan BahanLab bertindak sebagai subclass (kelas anak) yang mewarisi atribut dari KeperluanLab menggunakan keyword extends.
- Di dalam subclass AlatLab, terdapat pemanggilan super(idAlat, namaAlat, jumlahAlat) di dalam konstruktornya, yang berfungsi untuk mengirimkan dan menginisialisasi nilai data ke superclass (KeperluanLab).   

### b. Encapsulation
<img width="334" height="91" alt="image" src="https://github.com/user-attachments/assets/f4b158db-a85a-4be9-8442-2440059f8cfc" />    

<img width="420" height="55" alt="image" src="https://github.com/user-attachments/assets/75fa12e7-2ebb-43f6-8c4f-4b17fb264b5d" />    

Penggunaan Modifier protected dan private:
- Pada class KeperluanLab, atribut-atribut dasar seperti id, nama, dan jumlah dideklarasikan menggunakan modifier protected. Hal ini berarti atribut tersebut hanya dapat diakses secara langsung oleh class itu sendiri, package yang sama, serta subclass (kelas turunannya).
- Pada class BahanLab yang merupakan turunan (subclass) dari KeperluanLab, atribut khusus satuan dideklarasikan menggunakan modifier private. Dengan modifier private, atribut satuan disegel dan disembunyikan agar tidak dapat diakses atau diubah secara langsung dari luar class BahanLab guna menjaga keamanan serta integritas data.  
  
Fungsi utama:
- Penerapan enkapsulasi ini bertujuan untuk membungkus data dan melindungi variabel agar tidak mengalami perubahan nilai secara ilegal atau tidak disengaja dari bagian program lain, sehingga struktur data inventaris laboratorium menjadi lebih aman.

---

## 8. Penerapan Wajib
### a. Menerapkan Struktur MVC
<img width="310" height="256" alt="image" src="https://github.com/user-attachments/assets/4090c272-1abe-4778-a9ff-7c0bfb97613b" />


Gambar di atas menunjukkan struktur direktori proyek **InventarisLab** di NetBeans yang menerapkan arsitektur MVC (Model-View-Controller) untuk memisahkan logika data, antarmuka pengguna, dan alur kontrol program, sehingga kode lebih terstruktur, mudah dikelola, dan bersifat modular. Berikut adalah pembagian package di dalam program:
1. **com.mycompany.inventarislab (Main Package)**
Berisi class utama `InventarisLab.java` yang berfungsi sebagai titik masuk (*entry point*) atau class utama untuk menjalankan program aplikasi secara keseluruhan.
2. **model (Package Model)**
Package ini bertanggung jawab untuk merepresentasikan struktur data, atribut, serta logika objek yang ada di dalam sistem inventaris laboratorium. Terdiri dari:
* `KeperluanLab.java`: Berfungsi sebagai superclass (kelas induk) utama yang mendefinisikan atribut dasar item laboratorium.
* `AlatLab.java`: Subclass yang mewarisi sifat dari `KeperluanLab` untuk mengelola data khusus alat laboratorium (seperti stok tersedia).
* `BahanLab.java`: Subclass yang mewarisi sifat dari `KeperluanLab` untuk mengelola data khusus bahan laboratorium (seperti jumlah dan satuan ukuran).
* `InformasiLab.java`: Interface tambahan yang mendefinisikan kontrak method informasi standar bagi objek laboratorium.
* `Peminjaman.java`: Class yang menangani struktur data transaksi peminjaman dan pengembalian alat.
3. **control (Package Controller)**
Berisi class `LabControll.java` yang bertindak sebagai penghubung (jembatan) antara Model dan View. Class ini memproses logika bisnis, seperti validasi input stok, pengaturan ID, proses peminjaman, serta pembaruan data inventaris.
4. **view (Package View)**
Berisi class `TampilkanInventarisLab.java` yang bertanggung jawab penuh terhadap tampilan antarmuka (*user interface*) program kepada pengguna, termasuk pengelolaan menu teks, daftar inventaris, dan pesan interaktif.

### b. Polymorphism
 **Overriding**   
<img width="882" height="95" alt="image" src="https://github.com/user-attachments/assets/7a90da65-7421-4d16-8078-6b5e973c4cd0" />    

<img width="868" height="99" alt="image" src="https://github.com/user-attachments/assets/77843cc0-609d-45bb-a4c5-74f6f1376506" />    

Potongan kode di atas menunjukkan penerapan Polymorphism melalui teknik Method Overriding, di mana subclass (kelas turunan) memodifikasi atau menulis ulang kembali implementasi method yang diwarisi dari superclass-nya.  

Penjelasan Implementasi
- @Override digunakan untuk mendeklarasikan bahwa method tampilkanInfo() didefinisikan ulang secara khusus di dalam masing-masing subclass (seperti pada class BahanLab dan AlatLab).
- Meskipun menggunakan nama method yang sama (tampilkanInfo()), perilaku dan format teks yang dicetak berbeda. Class BahanLab menampilkan informasi spesifik berupa ID bahan, nama, jumlah, dan satuan (satuan), sedangkan class AlatLab menampilkan informasi ID alat, nama, serta stok alat yang tersedia.

Fungsi
- Teknik ini memungkinkan pemanggilan method dengan nama yang seragam pada objek yang berbeda, namun tetap menghasilkan keluaran yang dinamis dan spesifik sesuai dengan bentuk objek kelas turunannya.

 **Overloading**

<img width="683" height="259" alt="image" src="https://github.com/user-attachments/assets/af67c1ef-bebe-435c-a218-a3a1b56a2a10" />
   

<img width="811" height="351" alt="image" src="https://github.com/user-attachments/assets/164faf35-6f9c-4dd8-b7af-33b8842e2296" />


Potongan kode di atas menunjukkan penerapan konsep Polymorphism melalui teknik Method Overloading, di mana sebuah class (seperti pada class LabControll) mendefinisikan beberapa method dengan nama yang sama namun memiliki tipe atau jumlah parameter yang berbeda.

Penjelasan Implementasi:
- Kedua method di atas sama-sama menggunakan nama cariAlat, tetapi dibedakan berdasarkan parameter yang diterimanya: method pertama menggunakan parameter bertipe data integer (int idAlat), sedangkan method kedua menggunakan parameter bertipe data teks (String namaAlat).
- Meskipun memiliki nama yang identik, mekanisme dan logika pencariannya disesuaikan dengan parameter yang masuk. Method cariAlat(int idAlat) melakukan pencarian data secara spesifik berdasarkan kesesuaian ID angka, sementara method cariAlat(String namaAlat) melakukan pencarian berdasarkan kecocokan teks nama alat (lengkap dengan validasi input).

Fungsi:
- Teknik ini memungkinkan pemanggilan fungsi dengan penamaan yang konsisten dan intuitif untuk tujuan yang serupa (yaitu mencari data alat), tetapi tetap memberikan fleksibilitas kepada programmer untuk memilih cara pencarian yang diinginkan berdasarkan ID ataupun berdasarkan nama teks.

### c. Abstraction
 **Penerapan di Superclass**  
 
<img width="396" height="94" alt="image" src="https://github.com/user-attachments/assets/b27e787e-1f88-49b9-82d6-e56d4108c74a" />


KeperluanLab dideklarasikan menggunakan keyword abstract, yang menandakan bahwa kelas ini berfungsi sebagai cetak biru (blueprint) umum bagi seluruh jenis item di laboratorium.

<img width="361" height="79" alt="image" src="https://github.com/user-attachments/assets/e1d9713e-4bfe-4488-967d-6c0f1daeddd9" />


Method public abstract void tampilkanInfo(); didefinisikan tanpa memiliki isi atau blok tubuh (body method), dan ditandai dengan keyword abstract.
Keberadaan abstract method ini berfungsi sebagai kontrak wajib yang harus diimplementasikan atau ditulis ulang (override) oleh setiap subclass (kelas turunan) di bawahnya, seperti AlatLab dan BahanLab.

**Penerapan di Subclass**

<img width="970" height="89" alt="image" src="https://github.com/user-attachments/assets/94b5d69f-f8a7-4678-94c6-cae0555233db" />


Potongan kode di atas menunjukkan penerapan Abstraction, yaitu class AlatLab mewarisi abstract class KeperluanLab dan harus mengisi method abstrak yang sudah ditentukan oleh class induknya.
Penggunaan anotasi @Override menunjukkan bahwa method tampilkanInfo() dari class induk harus dibuat kembali di class AlatLab. Dengan begitu, method yang sebelumnya masih berupa kerangka kosong dapat memiliki fungsi yang jelas sesuai kebutuhan class tersebut.


---
## 9. Interface

<img width="307" height="91" alt="image" src="https://github.com/user-attachments/assets/f297b20a-8277-43a8-8af2-7ba380497e75" />


Class dideklarasikan sebagai public interface InformasiLab, yang berfungsi sebagai kontrak atau aturan yang harus diikuti oleh class yang menggunakannya. Di dalamnya terdapat method void cetakLabelKategori(); yang wajib diimplementasikan oleh class tersebut.

Fungsi:
Interface digunakan untuk menentukan standar perilaku tambahan bagi objek laboratorium. Dengan begitu, setiap class yang mengimplementasikan InformasiLab harus menyediakan fungsi cetakLabelKategori() sesuai dengan aturan yang telah ditentukan.

---

## 10. Penerapan JDBC

Program terhubung ke database MySQL inventaris_laboratorium menggunakan JDBC dengan PreparedStatement, dan akses databasenya dipisahkan dengan pola DAO (Data Access Object). Dengan begitu data alat, bahan, dan peminjaman tersimpan permanen di database dan tidak lagi menggunakan ArrayList di memori.

### a. Database dan Data Awal
<p> 
  
  <img width="1063" height="401" alt="image" src="https://github.com/user-attachments/assets/ffdd5f9d-77f0-410c-8cb9-4e1b524ade43" />

</p> 

Database dibuat dengan nama inventaris_laboratorium yang mencakup empat tabel utama, yaitu alat_lab, bahan_lab, keperluan_lab, dan peminjaman. Tabel-tabel tersebut menampung data inventaris alat, bahan kimia/laboratorium, serta catatan transaksi peminjaman secara terstruktur. Data awal dimasukkan langsung ke dalam database sehingga saat fitur Lihat Data Alat, Bahan, atau Peminjaman dijalankan melalui menu CLI, data dapat langsung ditampilkan secara real-time dari database tanpa menggunakan data dummy di memori aplikasi.

### b. Koneksi Database

Class Koneksi (package config) berfungsi untuk mengatur konfigurasi koneksi ke database MySQL. Di dalam kelas ini didefinisikan variabel konstanta URL yang mengarah ke database inventaris_laboratorium, beserta USER dan PASSWORD akses database. Method getConnection() menggunakan Class.forName("com.mysql.cj.jdbc.Driver") untuk memuat driver MySQL, lalu membuka koneksi JDBC melalui DriverManager.getConnection(URL, USER, PASSWORD). Jika koneksi berhasil atau gagal, program akan mencetak pesan status ke konsol.
<p>  

  
  <img width="1026" height="506" alt="image" src="https://github.com/user-attachments/assets/28701c0b-52c9-457b-a4ac-f05e1a8e139b" />

</p>

### c. PreparedStatement

<img width="822" height="376" alt="image" src="https://github.com/user-attachments/assets/19569322-a18b-4f71-a735-767b1f035695" />

PreparedStatement digunakan untuk menjalankan perintah SQL yang memiliki parameter secara dinamis dan aman. Penggunaan tanda tanya (?) sebagai placeholder pada query membantu mengurangi risiko keamanan seperti SQL injection serta mempermudah pengiriman nilai objek ke dalam perintah SQL. Pada penerapannya di dalam method, objek PreparedStatement diinisialisasi melalui koneksi database, lalu setiap parameter nilai diisi menggunakan method yang sesuai dengan tipe datanya, seperti setInt() untuk data angka dan setString() untuk data teks. Setelah seluruh parameter terikat dengan aman, perintah SQL dieksekusi menggunakan method seperti executeUpdate(), yang kemudian dilindungi oleh blok penanganan kesalahan (try-catch) untuk mengantisipasi kegagalan eksekusi atau gangguan pada koneksi database.

### d. Data Access Object (DAO)

<img width="203" height="94" alt="image" src="https://github.com/user-attachments/assets/3bb03759-4d86-4360-a4a3-5532bea29bcd" />  

Pola Data Access Object (DAO) diterapkan dengan memisahkan operasi database dari bagian lain program, sehingga Controller dan View tidak perlu menangani query SQL secara langsung. Setiap kelas DAO bertanggung jawab penuh dalam menangani operasi database untuk entitas atau tabel tertentu secara modular. Kelas DAO yang digunakan dalam sistem ini meliputi:

- AlatLabDAO: Menangani operasi database untuk pengelolaan data alat laboratorium pada tabel alat_lab.
- BahanLabDAO: Menangani operasi database untuk pengelolaan data bahan laboratorium pada tabel bahan_lab.
- PeminjamanDAO: Menangani operasi database untuk pengelolaan data transaksi peminjaman dan pengembalian alat.
- DAO: Berbentuk interface generik yang menjadi kontrak dasar standar untuk metode CRUD (insert, update, delete, getAll, getById) yang diterapkan pada seluruh kelas DAO.
Interface DAO<T> (package dao) bertindak sebagai kontrak akses data generik yang mendefinisikan method CRUD standar. Kelas implementasinya seperti AlatLabDAO, BahanLabDAO, dan PeminjamanDAO mengimplementasikan kontrak tersebut dengan mengeksekusi query SQL menggunakan PreparedStatement.   Nilai parameter pada query dimasukkan menggunakan tanda tanya (?) melalui method seperti ps.setInt() dan ps.setString(), bukan dengan menggabungkan string secara langsung, sehingga lebih aman dari risiko SQL injection. Saat melakukan pembacaan data (SELECT), hasil query ditampung di dalam ResultSet lalu dikonversi menjadi objek model Java.   Class Controller (LabControll) tidak lagi memuat query SQL maupun menyimpan data dalam ArrayList secara lokal, melainkan memanggil method dari kelas DAO untuk mengelola data langsung di database MySQL.

<img width="1030" height="630" alt="image" src="https://github.com/user-attachments/assets/9e22ad32-7d0f-4c5a-99ab-814b7ebae03c" />  

<img width="856" height="422" alt="image" src="https://github.com/user-attachments/assets/6e85b2d2-b960-472f-8cd6-558e402fc1ba" />

Pada gambar di atas ditunjukkan implementasi kelas AlatLabDAO yang bertugas mengelola penyimpanan dan pengambilan data inventaris alat dari database MySQL. Untuk proses penambahan data, method insert(AlatLab alat) menggunakan query SQL INSERT INTO dengan parameter tanda tanya (?) yang diisi melalui PreparedStatement guna mencegah celah SQL injection. Sementara itu, untuk proses pembacaan data, method getAll() menggunakan koneksi database untuk menjalankan perintah SQL SELECT * FROM alat_lab. Hasil query tersebut dibaca menggunakan ResultSet, di mana setiap baris data dipetakan kembali menjadi objek AlatLab dan ditampung ke dalam sebuah List agar dapat digunakan secara langsung oleh program.

### e. Operasi CRUD
Operasi CRUD digunakan untuk mengelola data yang tersimpan di database, yaitu:

- Create: Menambahkan data baru.
- Read: Mengambil dan menampilkan data.
- Update: Memperbarui data yang sudah ada.
- Delete: Menghapus data sesuai pilihan pengguna.

Sebelum Ditambah: 

<img width="537" height="321" alt="image" src="https://github.com/user-attachments/assets/aa8ffafa-c6ea-4608-ab95-8a63aeb3d286" />

Proses Penambahan:  

<img width="453" height="263" alt="image" src="https://github.com/user-attachments/assets/75fbef60-138b-4a38-9183-db3e9ce60ad6" />

Setelah Ditambah:

<img width="530" height="313" alt="image" src="https://github.com/user-attachments/assets/8948fed5-028f-4eba-b486-700271842565" />

Bisa dilihat bahwasanya penambahan dari Apache sudah dapat tersambung ke dalam database.

---

## 11. Kesimpulan
Berdasarkan perancangan, pengembangan, dan pengujian yang telah dilakukan, program InventarisLab berhasil dibangun dengan menerapkan arsitektur Model-View-Controller (MVC) yang memisahkan pengelolaan data, tampilan antarmuka, dan logika kontrol secara terstruktur. Program ini juga telah menerapkan konsep Pemrograman Berorientasi Objek (OOP), seperti Inheritance dan Encapsulation pada class model serta Polymorphism melalui method overriding. Selain itu, program dilengkapi dengan fitur pengelolaan data inventaris, peminjaman, pengembalian alat, dan validasi input untuk memastikan data yang dimasukkan sesuai dengan ketentuan. Berdasarkan hasil pengujian, fitur-fitur tersebut dapat berjalan sesuai dengan skenario yang telah ditentukan.






