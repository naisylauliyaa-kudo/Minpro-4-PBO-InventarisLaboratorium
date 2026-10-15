package dao;

import config.Koneksi;
import model.AlatLab;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlatLabDAO implements DAO<AlatLab> {

    @Override
    public void insert(AlatLab alat) {
        String query = "INSERT INTO alat_lab (id, nama, jumlah, kondisi) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, alat.getId());
            ps.setString(2, alat.getNama());
            ps.setInt(3, alat.getJumlah());
            ps.setString(4, alat.getKondisi());
            
            ps.executeUpdate();
            System.out.println("Data alat berhasil ditambahkan ke database! ^-^");
        } catch (SQLException e) {
            System.out.println("Gagal menambah alat: " + e.getMessage());
        }
    }

    @Override
    public void update(AlatLab alat) {
        String query = "UPDATE alat_lab SET nama = ?, jumlah = ?, kondisi = ? WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, alat.getNama());
            ps.setInt(2, alat.getJumlah());
            ps.setString(3, alat.getKondisi());
            ps.setInt(4, alat.getId());
            
            ps.executeUpdate();
            System.out.println("Data alat berhasil diubah di database! ^-^");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah alat: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM alat_lab WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Data alat berhasil dihapus dari database!");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus alat: " + e.getMessage());
        }
    }

    @Override
    public List<AlatLab> getAll() {
        List<AlatLab> listAlat = new ArrayList<>();
        String query = "SELECT * FROM alat_lab";
        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                int jumlah = rs.getInt("jumlah");
                String kondisi = rs.getString("kondisi");
                
                AlatLab alat = new AlatLab(id, nama, jumlah, kondisi);
                listAlat.add(alat);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data alat: " + e.getMessage());
        }
        return listAlat;
    }

    @Override
    public AlatLab getById(int id) {
        AlatLab alat = null;
        String query = "SELECT * FROM alat_lab WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nama = rs.getString("nama");
                    int jumlah = rs.getInt("jumlah");
                    String kondisi = rs.getString("kondisi");
                    alat = new AlatLab(id, nama, jumlah, kondisi);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari alat: " + e.getMessage());
        }
        return alat;
    }
}