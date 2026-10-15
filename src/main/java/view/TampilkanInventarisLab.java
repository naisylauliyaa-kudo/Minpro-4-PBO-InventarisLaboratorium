package view;

import control.LabControll;
import model.AlatLab;
import model.BahanLab;
import model.Peminjaman;
import java.util.Scanner;

public class TampilkanInventarisLab {
    private Scanner input = new Scanner(System.in);
    private LabControll crud = new LabControll();

    private String inputValidasiNama(String pesan) {
        String nama = "";
        while (true) {
            System.out.print(pesan);
            nama = input.nextLine();
            if (nama.isEmpty()) { 
                System.out.println("Nama tidak boleh kosong!"); 
                continue; 
            }
            if (!nama.matches(".*[a-zA-Z0-9].*")) {
                System.out.println("Nama tidak boleh hanya berisi simbol atau spasi!");
                continue;
            }
            break;
        }
        return nama;
    }

    private String inputValidasiNamaPeminjam(String pesan) {
        String nama = "";
        while (true) {
            System.out.print(pesan);
            nama = input.nextLine();
            
            if (nama.isEmpty()) { 
                System.out.println("Nama peminjam tidak boleh kosong!"); 
                continue; 
            }
            
            if (!nama.matches(".*[a-zA-Z].*")) {
                System.out.println("Nama peminjam harus mengandung huruf (tidak boleh hanya angka atau simbol)!");
                continue;
            }
            
            break;
        }
        return nama;
    }

    private int inputValidasiAngkaPositif(String pesan) {
        int hasil = 0;
        while (true) {
            System.out.print(pesan);
            String inputJml = input.nextLine();

            if (inputJml.isEmpty()) { 
                System.out.println("Input tidak boleh kosong!"); 
                continue; 
            }
            boolean valid = true;
            for (int i = 0; i < inputJml.length(); i++) {
                char c = inputJml.charAt(i);
                if (i == 0 && c == '-') { continue; } 
                if (!Character.isDigit(c)) { 
                    valid = false; 
                    break; 
                }
            }
            if (!valid) { 
                System.out.println("Harus berupa angka!"); 
                continue; 
            }
            hasil = Integer.parseInt(inputJml);
            if (hasil <= 0) { 
                System.out.println("Nilai tidak boleh 0 atau minus!"); 
                continue; 
            }
            break;
        }
        return hasil;
    }

    public void tampilkanMenu() {
        int pilihan;

        do {
            System.out.println("\n^-^ ============================================ ^-^");
            System.out.println("            SISTEM INVENTARIS LABORATORIUM");
            System.out.println("^-^ ============================================ ^-^");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data & Cari Alat");
            System.out.println("3. Ubah Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Peminjaman dan Pengembalian");
            System.out.println("6. Keluar");
            System.out.println("==================================================");
            System.out.print("Pilih menu: ");

            String inputPilihan = input.nextLine();
            boolean isMenuNumeric = true;
            for (int i = 0; i < inputPilihan.length(); i++) {
                if (!Character.isDigit(inputPilihan.charAt(i))) {
                    isMenuNumeric = false;
                    break;
                }
            }
            if (!isMenuNumeric || inputPilihan.isEmpty()) {
                System.out.println("Pilihan tidak valid! Masukkan angka menu (1-6).");
                pilihan = 0;
                continue;
            }

            pilihan = Integer.parseInt(inputPilihan);

            switch (pilihan) {
                case 1: 
                    System.out.println("\n------ MENU TAMBAH DATA ------");
                    System.out.println("1. Tambah Alat");
                    System.out.println("2. Tambah Bahan");
                    System.out.print("Pilih jenis: ");
                    String subTambah = input.nextLine();

                    if (subTambah.equals("1")) {
                        System.out.println("\n======= TAMBAH ALAT =======");
                        int idAlat = crud.generateIdAlat();
                        String namaAlat = inputValidasiNama("Nama Alat: ");
                        int jumlahAlat = inputValidasiAngkaPositif("Jumlah: ");
                        
                        String kondisiAlat = "";
                        while (true) {
                            System.out.print("Kondisi Alat (Baik/Cukup/Rusak): ");
                            kondisiAlat = input.nextLine();
                            if (kondisiAlat.isEmpty()) {
                                System.out.println("Kondisi tidak boleh kosong!");
                                continue;
                            }
                            break;
                        }

                        crud.tambahAlat(new AlatLab(idAlat, namaAlat, jumlahAlat, kondisiAlat));

                    } else if (subTambah.equals("2")) {
                        System.out.println("\n======= TAMBAH BAHAN =======");
                        int idBahan = crud.generateIdBahan();
                        String namaBahan = inputValidasiNama("Nama Bahan: ");
                        int jumlahBahan = inputValidasiAngkaPositif("Jumlah    : ");

                        String satuan = "";
                        while (true) {
                            System.out.print("Satuan (ml/gram/pcs): ");
                            satuan = input.nextLine();
                            if (satuan.isEmpty()) { System.out.println("Satuan tidak boleh kosong!"); continue; }
                            break;
                        }

                        crud.tambahBahan(new BahanLab(idBahan, namaBahan, jumlahBahan, satuan));
                    } else {
                        System.out.println("Pilihan submenu tidak valid.");
                    }
                    break;

                case 2: 
                    System.out.println("\n------ MENU LIHAT & PENCARIAN DATA ------");
                    System.out.println("1. Lihat Data Alat");
                    System.out.println("2. Lihat Data Bahan");
                    System.out.println("3. Cari Alat (Demonstrasi Overloading)");
                    System.out.print("Pilih jenis: ");
                    String subLihat = input.nextLine();

                    if (subLihat.equals("1")) {
                        crud.tampilkanAlat();
                    } else if (subLihat.equals("2")) {
                        crud.tampilkanBahan(); 
                    } else if (subLihat.equals("3")) {
                        System.out.println("\n======== PENCARIAN ALAT ========");
                        System.out.println("1. Cari berdasarkan ID (Angka)");
                        System.out.println("2. Cari berdasarkan Nama (Teks)");
                        System.out.print("Pilih metode pencarian: ");
                        String metodeCari = input.nextLine();

                        if (metodeCari.equals("1")) {
                            int cariId = inputValidasiAngkaPositif("Masukkan ID Alat: ");
                            crud.cariAlat(cariId); 
                        } else if (metodeCari.equals("2")) {
                            String cariNama = "";
                            while (true) {
                                System.out.print("Masukkan Nama Alat yang dicari: ");
                                cariNama = input.nextLine();

                                if (cariNama.isEmpty()) {
                                    System.out.println("Nama tidak boleh kosong!");
                                    continue;
                                }
                                if (!cariNama.matches(".*[a-zA-Z].*")) {
                                    System.out.println("Gagal: Nama alat harus mengandung huruf (tidak boleh hanya simbol, spasi, atau angka)!");
                                    continue;
                                }
                                break;
                            }
                            crud.cariAlat(cariNama); 
                        } else {
                            System.out.println("Metode pencarian tidak valid.");
                        }
                    } else {
                        System.out.println("Pilihan submenu tidak valid.");
                    }
                    break;

                case 3: 
                    System.out.println("\n------ MENU UBAH DATA ------");
                    System.out.println("1. Ubah Data Alat");
                    System.out.println("2. Ubah Data Bahan");
                    System.out.print("Pilih jenis: ");
                    String subUbah = input.nextLine();

                    if (subUbah.equals("1")) {
                        crud.tampilkanAlat();
                        System.out.println("----------------------------------------------------------");
                        int idUbah = inputValidasiAngkaPositif("ID Alat yang akan diubah: ");

                        System.out.print("Nama Baru (tekan Enter jika tidak ingin mengubah): ");
                        String namaBaru = input.nextLine();
                        if (!namaBaru.isEmpty() && !namaBaru.matches(".*[a-zA-Z0-9].*")) {
                            System.out.println("Nama tidak valid, perubahan dibatalkan.");
                            break;
                        }

                        int jmlBaru = inputValidasiAngkaPositif("Jumlah Baru: ");

                        System.out.print("Kondisi Baru (Baik/Cukup/Rusak): ");
                        String kondisiBaru = input.nextLine();
                        if (kondisiBaru.isEmpty()) {
                            kondisiBaru = "Baik"; // Default jika dikosongkan
                        }

                        crud.ubahAlat(idUbah, namaBaru, jmlBaru, kondisiBaru);

                    } else if (subUbah.equals("2")) {
                        crud.tampilkanBahan();
                        System.out.println("----------------------------------------------------------");
                        int idUbahBahan = inputValidasiAngkaPositif("ID Bahan yang akan diubah: ");

                        System.out.print("Nama Baru (tekan Enter jika tidak ingin mengubah): ");
                        String namaBaruBahan = input.nextLine();
                        if (!namaBaruBahan.isEmpty() && !namaBaruBahan.matches(".*[a-zA-Z0-9].*")) {
                            System.out.println("Nama tidak valid, perubahan dibatalkan.");
                            break;
                        }

                        int jmlBaruBahan = inputValidasiAngkaPositif("Jumlah Baru: ");

                        String satuanBaru = "";
                        while (true) {
                            System.out.print("Satuan Baru (ml/gram/pcs): ");
                            satuanBaru = input.nextLine();
                            if (satuanBaru.isEmpty()) { System.out.println("Satuan tidak boleh kosong!"); continue; }
                            break;
                        }

                        crud.ubahBahan(idUbahBahan, namaBaruBahan, jmlBaruBahan, satuanBaru); 
                    } else {
                        System.out.println("Pilihan submenu tidak valid.");
                    }
                    break;

                case 4: 
                    System.out.println("\n------ MENU HAPUS DATA ------");
                    System.out.println("1. Hapus Data Alat");
                    System.out.println("2. Hapus Data Bahan");
                    System.out.print("Pilih jenis: ");
                    String subHapus = input.nextLine();

                    if (subHapus.equals("1")) {
                        crud.tampilkanAlat(); 
                        System.out.println("----------------------------------------------------------");
                        int idHapus = inputValidasiAngkaPositif("ID Alat yang akan dihapus: ");
                        crud.hapusAlat(idHapus);

                    } else if (subHapus.equals("2")) {
                        crud.tampilkanBahan(); 
                        System.out.println("----------------------------------------------------------");
                        int idHapusBahan = inputValidasiAngkaPositif("ID Bahan yang akan dihapus: ");
                        crud.hapusBahan(idHapusBahan); 
                    } else {
                        System.out.println("Pilihan submenu tidak valid.");
                    }
                    break;

                case 5: 
                    System.out.println("\n======== PEMINJAMAN ========");
                    System.out.println("1. Pinjam Alat");
                    System.out.println("2. Lihat Peminjaman");
                    System.out.println("3. Pengembalian");

                    int pilihanPinjam = inputValidasiAngkaPositif("Pilih menu peminjaman (1-3): ");

                    if (pilihanPinjam == 1) {
                        crud.tampilkanAlat(); 
                        System.out.println("----------------------------------------------------------");
                        
                        int idPeminjaman = inputValidasiAngkaPositif("ID Peminjaman : ");
                        String namaPeminjam = inputValidasiNamaPeminjam("Nama Peminjam : "); 
                        int idAlatPinjam = inputValidasiAngkaPositif("ID Alat       : ");
                        int jumlahPinjam = inputValidasiAngkaPositif("Jumlah Pinjam : ");

                        crud.tambahPeminjaman(new Peminjaman(idPeminjaman, namaPeminjam, idAlatPinjam, jumlahPinjam));

                    } else if (pilihanPinjam == 2) {
                        crud.tampilkanPeminjaman();

                    } else if (pilihanPinjam == 3) {
                        int idKembali = inputValidasiAngkaPositif("ID Peminjaman yang dikembalikan: ");
                        crud.pengembalian(idKembali);
                    }
                    break;

                case 6:
                    System.out.println("Program selesai. Terima kasih ^^!");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 6);

        input.close();
    }
}