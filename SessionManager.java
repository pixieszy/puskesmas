/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apps;
/**
 *
 * @author Acer
 */
public class SessionManager {

    public static String nip;
    public static String nama;
    public static String jenkel;
    public static String jabatan;

    public static void setNip(String nipValue) {

        nip = EncryptDecrypt.encrypt(nipValue);
    }
   public static void setNama(String namaValue) {

        nama = EncryptDecrypt.encrypt(namaValue);
    }
   public static void setjenkel(String jenkelValue) {
        jenkel = EncryptDecrypt.encrypt(jenkelValue);
    }
      public static void setjabatan(String jabatanValue) {
        jabatan = EncryptDecrypt.encrypt(jabatanValue);
    }
    public static String getNip() {

        return nip;
    
}
        public static String getNama() {

        return nama;
    
}
                public static String getjenkel() {

        return jenkel;
    
}
                public static String getjabatan() {

        return jabatan;
    
}
}
