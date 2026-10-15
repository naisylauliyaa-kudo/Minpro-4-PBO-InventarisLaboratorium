package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TesORM {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("pengaduanPU");
            EntityManager em = emf.createEntityManager();
            System.out.println("SUKSES: Koneksi Hibernate Entity Manager berhasil dibuat ^-^ ");
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println("GAGAL: Terjadi kesalahan pada konfigurasi ORM T~T");
            e.printStackTrace();
        }
    }
}