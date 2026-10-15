package control;

import dao.AlatLabDAO;
import dao.BahanLabDAO;
import dao.PeminjamanDAO;
import model.AlatLab;
import model.BahanLab;
import model.Peminjaman;
import java.util.List;

public class LabControll {
    // Inisialisasi objek DAO untuk komunikasi langsung dengan database
    private final AlatLabDAO alatDao = new AlatLabDAO();
    private final BahanLabDAO bahanDao = new BahanLabDAO();
    private final PeminjamanDAO peminjamanDao = new PeminjamanDAO();

    // Konstruktor kosong karena data dummy sudah dihapus & bersumber dari database
    public LabControll() {
    }
    
    public int generateIdAlat() {
        List<AlatLab> listAlat = alatDao.getAll();
        if (listAlat.isEmpty()) {
            return 101; 
        }
        int maxId = listAlat.get(0).getId();
        for (AlatLab alat : listAlat) {
            if (alat.getId() > maxId) {
                maxId = alat.getId();
            }
        }
        return maxId + 1;
    }

    public int generateIdBahan() {
        List<BahanLab> listBahan = bahanDao.getAll();
        if (listBahan.isEmpty()) {
            return 201; 
        }
        int maxId = listBahan.get(0).getId();
        for (BahanLab bahan : listBahan) {
            if (bahan.getId() > maxId) {
                maxId = bahan.getId();
            }
        }
        return maxId + 1;
    }

    public void tambahAlat(AlatLab alat) {
        alatDao.insert(alat);
    }

    public void tampilkanAlat() {
        List<AlatLab> daftarAlat = alatDao.getAll();
        if (daftarAlat.isEmpty()) {
            System.out.println("\nBelum ada data alat di inventaris.");
            return;
        }
        System.out.println("\n======== DAFTAR ALAT LABORATORIUM ========");
        
        daftarAlat.get(0).cetakLabelKategori();
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < daftarAlat.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarAlat.get(i).tampilkanInfo(); 
        }
    }  
    
    public void cariAlat(int idAlat) {
        AlatLab alat = alatDao.getById(idAlat);
        if (alat != null) {
            System.out.println("Alat ditemukan: ");
            alat.tampilkanInfo();
        } else {
            System.out.println("Alat dengan ID " + idAlat + " tidak ditemukan.");
        }
    }

    public void cariAlat(String namaAlat) {
        if (namaAlat == null || namaAlat.trim().isEmpty() || !namaAlat.matches(".*[a-zA-Z].*")) {
            System.out.println("Gagal: Nama alat tidak boleh kosong, hanya spasi, atau hanya simbol!");
            return;
        }
        
        boolean ditemukan = false;
        System.out.println("\n====== HASIL PENCARIAN ALAT ======");
        List<AlatLab> daftarAlat = alatDao.getAll();
        for (AlatLab alat : daftarAlat) {
            if (alat.getNama().toLowerCase().contains(namaAlat.toLowerCase())) {
                alat.tampilkanInfo();
                ditemukan = true;
            }
        }
        
        if (!ditemukan) {
            System.out.println("Alat dengan nama '" + namaAlat + "' tidak ditemukan.");
        }
    }

    // Diperbarui menjadi 4 parameter untuk menangani kondisi baru
    public void ubahAlat(int idUbah, String namaBaru, int jumlahBaru, String kondisiBaru) {
        AlatLab alat = alatDao.getById(idUbah);
        if (alat != null) {
            if (!namaBaru.isEmpty()) {
                alat.setNama(namaBaru);
            }
            alat.setJumlah(jumlahBaru);
            if (!kondisiBaru.isEmpty()) {
                alat.setKondisi(kondisiBaru);
            }
            alatDao.update(alat);
        } else {
            System.out.println("Alat dengan ID tersebut tidak ditemukan.");
        }
    }

    public void hapusAlat(int idHapus) {
        AlatLab alat = alatDao.getById(idHapus);
        if (alat != null) {
            alatDao.delete(idHapus);
        } else {
            System.out.println("Alat dengan ID tersebut tidak ditemukan.");
        }
    }

    public void tambahBahan(BahanLab bahan) {
        bahanDao.insert(bahan);
    }

    public void tampilkanBahan() {
        List<BahanLab> daftarBahan = bahanDao.getAll();
        if (daftarBahan.isEmpty()) {
            System.out.println("\nBelum ada data bahan di inventaris.");
            return;
        }
        System.out.println("\n======== DAFTAR BAHAN LABORATORIUM ========");
        for (int i = 0; i < daftarBahan.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarBahan.get(i).tampilkanInfo(); 
        }
    }

    public void ubahBahan(int idUbah, String namaBaru, int jumlahBaru, String satuanBaru) {
        BahanLab bahan = bahanDao.getById(idUbah);
        if (bahan != null) {
            if (!namaBaru.isEmpty()) {
                bahan.setNama(namaBaru);
            }
            bahan.setJumlah(jumlahBaru);
            if (!satuanBaru.isEmpty()) {
                bahan.setSatuan(satuanBaru);
            }
            bahanDao.update(bahan);
        } else {
            System.out.println("Bahan dengan ID tersebut tidak ditemukan.");
        }
    }

    public void hapusBahan(int idHapus) {
        BahanLab bahan = bahanDao.getById(idHapus);
        if (bahan != null) {
            bahanDao.delete(idHapus);
        } else {
            System.out.println("Bahan dengan ID tersebut tidak ditemukan.");
        }
    }

    public void tambahPeminjaman(Peminjaman peminjaman) {
        AlatLab alatDipinjam = alatDao.getById(peminjaman.getIdAlatPinjam());

        if (alatDipinjam == null) {
            System.out.println("Gagal: ID Alat yang ingin dipinjam tidak terdaftar di database.");
        } else if (alatDipinjam.getJumlah() < peminjaman.getJumlahPinjam()) {
            System.out.println("Gagal: Stok alat tidak mencukupi. Sisa stok saat ini: " + alatDipinjam.getJumlah());
        } else {
            // Kurangi stok alat di database
            alatDipinjam.setJumlah(alatDipinjam.getJumlah() - peminjaman.getJumlahPinjam());
            alatDao.update(alatDipinjam);
            
            // Simpan data peminjaman ke database
            peminjamanDao.insert(peminjaman);
        }
    }

    public void tampilkanPeminjaman() {
        List<Peminjaman> daftarPeminjaman = peminjamanDao.getAll();
        if (daftarPeminjaman.isEmpty()) {
            System.out.println("\nBelum ada data peminjaman aktif.");
            return;
        }
        System.out.println("\n======== DAFTAR PEMINJAMAN ALAT ========");
        for (int i = 0; i < daftarPeminjaman.size(); i++) {
            System.out.println("----- Peminjaman ke-" + (i + 1) + " -----");
            daftarPeminjaman.get(i).tampilkanInfoPeminjaman();
        }
    }

    public void pengembalian(int idKembali) {
        Peminjaman peminjamanDitemukan = peminjamanDao.getById(idKembali);

        if (peminjamanDitemukan != null) {
            AlatLab alat = alatDao.getById(peminjamanDitemukan.getIdAlatPinjam());
            if (alat != null) {
                alat.setJumlah(alat.getJumlah() + peminjamanDitemukan.getJumlahPinjam());
                alatDao.update(alat);
            }
            peminjamanDao.delete(idKembali);
            System.out.println("Pengembalian berhasil dicatat. Stok alat telah dikembalikan ke database ^-^");
        } else {
            System.out.println("ID Peminjaman tidak ditemukan.");
        }
    }
}