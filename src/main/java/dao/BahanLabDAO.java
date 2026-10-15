package dao;

import config.Koneksi;
import model.BahanLab;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BahanLabDAO implements DAO<BahanLab> {

    @Override
    public void insert(BahanLab bahan) {
        String query = "INSERT INTO bahan_lab (id, nama, jumlah, satuan) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, bahan.getId());
            ps.setString(2, bahan.getNama());
            ps.setInt(3, bahan.getJumlah());
            ps.setString(4, bahan.getSatuan());
            
            ps.executeUpdate();
            System.out.println("Data bahan berhasil ditambahkan ke database! ^-^");
        } catch (SQLException e) {
            System.out.println("Gagal menambah bahan: " + e.getMessage());
        }
    }

    @Override
    public void update(BahanLab bahan) {
        String query = "UPDATE bahan_lab SET nama = ?, jumlah = ?, satuan = ? WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, bahan.getNama());
            ps.setInt(2, bahan.getJumlah());
            ps.setString(3, bahan.getSatuan());
            ps.setInt(4, bahan.getId());
            
            ps.executeUpdate();
            System.out.println("Data bahan berhasil diubah di database! ^-^");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah bahan: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM bahan_lab WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Data bahan berhasil dihapus dari database!");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus bahan: " + e.getMessage());
        }
    }

    @Override
    public List<BahanLab> getAll() {
        List<BahanLab> listBahan = new ArrayList<>();
        String query = "SELECT * FROM bahan_lab";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                int jumlah = rs.getInt("jumlah");
                String satuan = rs.getString("satuan");
                
                BahanLab bahan = new BahanLab(id, nama, jumlah, satuan);
                listBahan.add(bahan);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data bahan: " + e.getMessage());
        }
        return listBahan;
    }

    @Override
    public BahanLab getById(int id) {
        BahanLab bahan = null;
        String query = "SELECT * FROM bahan_lab WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nama = rs.getString("nama");
                    int jumlah = rs.getInt("jumlah");
                    String satuan = rs.getString("satuan");
                    bahan = new BahanLab(id, nama, jumlah, satuan);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari bahan: " + e.getMessage());
        }
        return bahan;
    }
}