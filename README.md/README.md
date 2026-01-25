# TO-DO LIST APP
# Deskripsi Singkat
To-Do List App adalah aplikasi berbasis Java Swing untuk mencatat dan mengelola daftar kegiatan. Aplikasi ini terintegrasi dengan MySQL database sehingga data tersimpan secara permanen.
Fitur utama meliputi CRUD tugas (Create, Read, Update, Delete), checkbox untuk menandai status selesai, integrasi database, dan antarmuka grafis sederhana.

# Daftar Anggota
1. Ida Bagus Gde Putra Gautama (240030167)
2. I Komang Agus Adi Merta (240030159)
3. Ni Made Gita Ulandari ( 240030077)
4. Gusti Ayu Made Bintang Widya Putri (240030333)

# Lingkungan Pengembangan
Backend: Java 17

Database: MySQL 8.0

Tools:

1. Laragon / MySQL Server
2. Visual Studio Code 
3. Git & GitHub

# Struktur Project
Code
PBO-TO-DO-LIST/
│
├── database/                  
│   ├── Koneksi.java           
│   └── TaskDatabase.java      
│
├── model/                     
│   └── Task.java              
│
├── ui/                        
│   ├── TaskForm.java          
│   ├── TaskTableModel.java    
│   └── ToDoListApp.java       
│
├── program/                   
│   └── Main.java              
│
├── lib/                       
│   └── mysql-connector-j-9.5.0.jar
│
└── README.md                  

# Konfigurasi Koneksi
Edit Koneksi.java:

java
private static final String URL = "jdbc:mysql://localhost:3306/todo_list?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";   
private static final String PASS = ""; 
      
# Cara Menjalankan Aplikasi
1. Aktifkan server MYSQL di Laragon, lalu buat database todo_list.
2. Buatkan table tasks pada database, berisikan id sebagai primary key [datatype:int, Default:auto_increment], tittle [datatype:varchar], is_done [datatype:tinyint], created_at [datatype:timestamp]
3. Jalankan
bash
java -cp ".;lib/mysql-connector-j-8.0.33.jar" program.Main


# Tampilan Aplikasi
1. Antarmuka Utama: tabel daftar tugas dengan kolom ID, Judul, dan Selesai (checkbox).Form Input: kolom “Masukan list kegiatan” dengan tombol Tambah, Simpan Perubahan, dan Delete.
2. Checkbox Status: kolom “Selesai” untuk menandai apakah tugas sudah selesai atau belum.

# Contoh Skenario Uji
1. Tambah tugas baru → isi judul kegiatan, klik Tambah.
2. Tandai tugas selesai → centang checkbox, klik Simpan Perubahan.
3. Hapus tugas → pilih baris, klik Delete.
4. Lihat daftar → semua tugas tampil di tabel dengan status masing-masing.