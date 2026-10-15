package dao;

import config.Koneksi;
import model.Peminjaman;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeminjamanDAO implements DAO<Peminjaman> {

    @Override
    public void insert(Peminjaman peminjaman) {
        String query = "INSERT INTO peminjaman (id_peminjaman, nama_peminjam, id_alat_pinjam, jumlah_pinjam) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, peminjaman.getIdPeminjaman());
            ps.setString(2, peminjaman.getNamaPeminjam());
            ps.setInt(3, peminjaman.getIdAlatPinjam());
            ps.setInt(4, peminjaman.getJumlahPinjam());
            
            ps.executeUpdate();
            System.out.println("Data peminjaman berhasil ditambahkan ke database! ^-^");
        } catch (SQLException e) {
            System.out.println("Gagal menambah peminjaman: " + e.getMessage());
        }
    }

    @Override
    public void update(Peminjaman peminjaman) {
        String query = "UPDATE peminjaman SET nama_peminjam = ?, id_alat_pinjam = ?, jumlah_pinjam = ? WHERE id_peminjaman = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, peminjaman.getNamaPeminjam());
            ps.setInt(2, peminjaman.getIdAlatPinjam());
            ps.setInt(3, peminjaman.getJumlahPinjam());
            ps.setInt(4, peminjaman.getIdPeminjaman());
            
            ps.executeUpdate();
            System.out.println("Data peminjaman berhasil diubah di database! ^-^");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah peminjaman: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM peminjaman WHERE id_peminjaman = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Data peminjaman berhasil dihapus dari database!");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus peminjaman: " + e.getMessage());
        }
    }

    @Override
    public List<Peminjaman> getAll() {
        List<Peminjaman> listPeminjaman = new ArrayList<>();
        String query = "SELECT * FROM peminjaman";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int idPeminjaman = rs.getInt("id_peminjaman");
                String namaPeminjam = rs.getString("nama_peminjam");
                int idAlatPinjam = rs.getInt("id_alat_pinjam");
                int jumlahPinjam = rs.getInt("jumlah_pinjam");
                
                Peminjaman peminjaman = new Peminjaman(idPeminjaman, namaPeminjam, idAlatPinjam, jumlahPinjam);
                listPeminjaman.add(peminjaman);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data peminjaman: " + e.getMessage());
        }
        return listPeminjaman;
    }

    @Override
    public Peminjaman getById(int id) {
        Peminjaman peminjaman = null;
        String query = "SELECT * FROM peminjaman WHERE id_peminjaman = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String namaPeminjam = rs.getString("nama_peminjam");
                    int idAlatPinjam = rs.getInt("id_alat_pinjam");
                    int jumlahPinjam = rs.getInt("jumlah_pinjam");
                    peminjaman = new Peminjaman(id, namaPeminjam, idAlatPinjam, jumlahPinjam);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari peminjaman: " + e.getMessage());
        }
        return peminjaman;
    }
}