/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Peminjaman {
    private int idPeminjaman;
    private String namaPeminjam;
    private int idAlatPinjam;
    private int jumlahPinjam;
   
    public Peminjaman(int idPeminjaman, String namaPeminjam, int idAlatPinjam, int jumlahPinjam){
        setIdPeminjaman(idPeminjaman);
        setNamaPeminjam(namaPeminjam);
        setIdAlatPinjam(idAlatPinjam);
        setJumlahPinjam(jumlahPinjam);
    }
   
    public int getIdPeminjaman(){
        return idPeminjaman;
    }
    
    public String getNamaPeminjam() {
        return namaPeminjam;
    }
   
    public int getIdAlatPinjam(){
        return idAlatPinjam;
    }

    public int getJumlahPinjam(){
        return jumlahPinjam;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        if (idPeminjaman <= 0) {
            System.out.println("Gagal: ID Peminjaman tidak boleh minus!");
        } else {
            this.idPeminjaman = idPeminjaman;
        }
    }

    public void setNamaPeminjam(String namaPeminjam) {
        if (namaPeminjam == null || namaPeminjam.trim().isEmpty()) {
            System.out.println("Gagal: Nama peminjam tidak boleh kosong!");
        } else {
            this.namaPeminjam = namaPeminjam;
        }
    }

    public void setIdAlatPinjam(int idAlatPinjam) {
        if (idAlatPinjam <= 0) {
            System.out.println("Gagal: ID Alat pinjam tidak boleh minus!");
        } else {
            this.idAlatPinjam = idAlatPinjam;
        }
    }

    public void setJumlahPinjam(int jumlahPinjam) {
        if (jumlahPinjam <= 0) {
            System.out.println("Gagal: Jumlah pinjam tidak boleh 0 atau minus!");
        } else {
            this.jumlahPinjam = jumlahPinjam;
        }
    }
   
    public void tampilkanInfoPeminjaman(){
        System.out.println("ID Peminjaman  : " + idPeminjaman);
        System.out.println("Nama Peminjam  : " + namaPeminjam);
        System.out.println("ID Alat Pinjam : " + idAlatPinjam);
        System.out.println("Jumlah Dipinjam: " + jumlahPinjam);
    }
}