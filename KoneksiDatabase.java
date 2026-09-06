package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDatabase {
    // Konstanta untuk koneksi database
    private static final String URL = "jdbc:mysql://localhost:3306/puskesmas"; // Ganti dengan nama database Anda
    private static final String USER = "root"; // Ganti dengan username database
    private static final String PASSWORD = ""; // Ganti dengan password database

    public static void main(String[] args) {

        // Panggil metode untuk koneksi ke database
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Koneksi ke database berhasil!");

            // Contoh query: Ambil data dari tabel users
            tampilkanData(conn);

            // Tutup koneksi setelah selesai
            tutupKoneksi(conn);
        } else {
            System.out.println("Koneksi ke database gagal.");
        }
    }

    // Metode untuk mendapatkan koneksi database
    public static Connection getConnection() {
        try {
            
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error koneksi: " + e.getMessage());
            return null;
        }
    }

    // Metode untuk menampilkan data dari tabel
    public static void tampilkanData(Connection conn) {
        String query = "SELECT * FROM pegawai"; // Ganti dengan nama tabel yang sesuai
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Data dari tabel users:");
            while (rs.next()) {
                int id = rs.getInt("id"); // Sesuaikan dengan kolom di tabel Anda
                String nama = rs.getString("nama"); // Sesuaikan dengan kolom di tabel Anda
                System.out.println("ID: " + id + ", Nama: " + nama);
            }
        } catch (SQLException e) {
            System.out.println("Error mengambil data: " + e.getMessage());
        }
    }

    // Metode untuk menutup koneksi
    public static void tutupKoneksi(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Koneksi ditutup.");
            }
        } catch (SQLException e) {
            System.out.println("Error saat menutup koneksi: " + e.getMessage());
        }
    }

    public Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
