/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apps;

import Config.KoneksiDatabase;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Apps.Obat;
import static Apps.SessionManager.nama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.DefaultListModel;



/**
 *
 * @author Acer
 */
public class Resep extends javax.swing.JFrame {
    private Connection conn = KoneksiDatabase.getConnection();
    private DefaultTableModel tabmode;
    private boolean modeTambahResep = false;
    private java.util.Date date;  
    public String jenisvar = "";
    public String hargavar = "";
    private Object totalbiaya;
    public Resep resep;
    
    public String id;
    public String nama;
    public String idpas;
    public String namapas;
    
    // Method untuk dipanggil setelah dokter dipilih
    public void dokterTerpilih() {
        // Update text field di form resep
        iddokter.setText(this.id);  // Sesuaikan nama text field
        namadokter.setText(this.nama);
        // txtNamaDokter.setText(this.nama); // Jika perlu nama juga
        
        System.out.println("ID Dokter terpilih di Resep: " + this.id);
    }
    
        public void pasienTerpilih() {
        // Update text field di form resep
        idpasien.setText(this.idpas);  // Sesuaikan nama text field
        namapasien.setText(this.namapas);
        // txtNamaDokter.setText(this.nama); // Jika perlu nama juga
        
        System.out.println("ID Dokter terpilih di Resep: " + this.id);
    }
    

    private void handleFormClosed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void resepTerpilih() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private static class totalbiaya {

        private static void setText(String valueOf) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated ethods, choose Tools | Templates.
        }

        public totalbiaya() {
        }
    }


    public class Obat {
    private String nama;
    private String harga;
    private String jenis;

    public Obat( String nama, String harga, String jenis) {
        
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
    }

    public String getNama() { return nama; }
    public String getHarga() { return harga; }
    public String getJenis() { return jenis; }

@Override
public String toString() {
    return nama;
}

}

    public void loadComboBox() {
    String query = "SELECT  nama_obat, harga, jenis FROM obat";

    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        comboobatrsp.removeAllItems();  // kosongkan dulu

        while (rs.next()) {
            Obat obat = new Obat(
                rs.getString("nama_obat"),
                rs.getString("harga"),
                rs.getString("jenis")
            );

            comboobatrsp.addItem(obat);  // masukkan objek Obat ke ComboBox
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

 // Tambahkan inner class ini di dalam class Resep
     protected void aktif (){
        jpembuatan.setEnabled(true);

    }
    
    protected void kosong (){
     jpembuatan.setDate(null);
    iddokter.setText("");
    namadokter.setText("");
    idpasien.setText("");
    idresep.setText("");
    namapasien.setText("");
    jumlahobat.setText("");
    jenisobat.setText("");
    hargaobat.setText("");
    cat.setText("");
    }
    
    private void resetAllFields() {
    jpembuatan.setDate(null);
    idresep.setText("");
    iddokter.setText("");
    namadokter.setText("");
    idpasien.setText("");
    namapasien.setText("");
    jumlahobat.setText("");
    jenisobat.setText("");
    hargaobat.setText("");
    cat.setText("");
    tabelresep.setModel(new DefaultTableModel(
        new Object[]{"Tanggal Resep","ID Resep","Id Dokter","Nama Dokter","ID Pasien","Nama Pasien","Nama Obat","Jenis Obat","Jumlah Obat","Harga","Subtotal","Total Bayar","Catatan"}, 0
    ));
    comboobatrsp.setSelectedIndex(-1); // kosongkan combo obat
    modeTambahResep = false;
}

   
private void tambahObatKeResep() throws SQLException {
    Obat obat = (Obat) comboobatrsp.getSelectedItem();
    if (obat == null || jumlahobat.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Lengkapi data obat terlebih dahulu.");
        return;
    }

    int jumlah = Integer.parseInt(jumlahobat.getText());
    int harga = Integer.parseInt(obat.getHarga());
    int subtotal = jumlah * harga;

    // Ambil data resep lainnya
    java.util.Date date = jpembuatan.getDate();
    if (date == null) {
        JOptionPane.showMessageDialog(this, "Pilih tanggal resep terlebih dahulu.");
        return;
    }
    String tanggal = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
    String idResep = idresep.getText().trim();
    String idDokter = iddokter.getText().trim();
    String namaDokter = namadokter.getText().trim();
    String idPasien = idpasien.getText().trim();
    String namaPasien = namapasien.getText().trim();
    String catatan = cat.getText().trim(); // sesuaikan kalau ada field catatan

    DefaultTableModel model = (DefaultTableModel) tabelresep.getModel();

    // Jika belum dalam mode tambah resep, aktifkan mode dan kosongkan tabel
    if (!modeTambahResep) {
        modeTambahResep = true;
        model.setRowCount(0); // kosongkan tabel lama
    }

    // Tambahkan data lengkap ke tabel
    model.addRow(new Object[]{
        tanggal,
        idResep,
        idDokter,
        namaDokter,
        idPasien,
        namaPasien,
        obat.getNama(),
        obat.getJenis(),
        jumlah,
        harga,
        subtotal,
        catatan
    });

    resetInputFields();
}




private void simpanResepBaru() throws SQLException {
    int rowCount = tabelresep.getRowCount();
    if (rowCount == 0) {
        JOptionPane.showMessageDialog(this, "Belum ada data untuk disimpan.");
        return;
    }

    // Hitung totalBayar dulu
    int totalBayar = 0;
    for (int i = 0; i < rowCount; i++) {
        int subtotal = Integer.parseInt(tabelresep.getValueAt(i, 10).toString());
        totalBayar += subtotal;
    }

    // Simpan semua baris
    for (int i = 0; i < rowCount; i++) {
        String tanggal = tabelresep.getValueAt(i, 0).toString();
        String idResep = tabelresep.getValueAt(i, 1).toString();
        String idDokter = tabelresep.getValueAt(i, 2).toString();
        String namaDokter = tabelresep.getValueAt(i, 3).toString();
        String idPasien = tabelresep.getValueAt(i, 4).toString();
        String namaPasien = tabelresep.getValueAt(i, 5).toString();
        String namaObat = tabelresep.getValueAt(i, 6).toString();
        String jenisObat = tabelresep.getValueAt(i, 7).toString();
        int jumlah = Integer.parseInt(tabelresep.getValueAt(i, 8).toString());
        int harga = Integer.parseInt(tabelresep.getValueAt(i, 9).toString());
        int subtotal = Integer.parseInt(tabelresep.getValueAt(i, 10).toString());
        String catatan = tabelresep.getValueAt(i, 11) != null ? tabelresep.getValueAt(i, 11).toString() : "";

        String sql = "INSERT INTO resep (tanggal_resep, id_resep1, id_dokter, nama_dokter, id_pasien, nama_pasien, nama_obat, jenis, jumlah_obat, harga, subtotal, catatan, total_bayar) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tanggal);
            pst.setString(2, idResep);
            pst.setString(3, idDokter);
            pst.setString(4, namaDokter);
            pst.setString(5, idPasien);
            pst.setString(6, namaPasien);
            pst.setString(7, namaObat);
            pst.setString(8, jenisObat);
            pst.setInt(9, jumlah);
            pst.setInt(10, harga);
            pst.setInt(11, subtotal);
            pst.setString(12, catatan);
            pst.setInt(13, totalBayar); // simpan totalBayar di setiap baris
            pst.executeUpdate();
        }
    }

    JOptionPane.showMessageDialog(this, "Semua data berhasil disimpan!\nTotal Bayar: Rp " + String.format("%,d", totalBayar));
    modeTambahResep = false;
    resetAllFields();
    datatable();
}




        protected void datatable() throws SQLException{
        Object[] Baris ={"Tanggal Resep","ID Resep","Id Dokter", "Nama Dokter", "ID Pasien", "Nama Pasien","Nama Obat", "Jenis Obat", "Jumlah Obat","harga","Subtotal","Total Bayar","catatan"};
        tabmode = new DefaultTableModel(null,  Baris);
        tabelresep.setModel (tabmode);
        String sql = "select * from resep";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("tanggal_resep");
                String b = hasil.getString("id_resep1");
                String c = hasil.getString("id_dokter");
                String d = hasil.getString("nama_dokter");
                String e= hasil.getString("id_pasien");
                String f= hasil.getString("nama_pasien");
                String g= hasil.getString("nama_obat");
                String h = hasil.getString("jenis");
                String i= hasil.getString("jumlah_obat");
                String j = hasil.getString("harga");
                String k = hasil.getString("subtotal");
                String l = hasil.getString("total_bayar");
                String m = hasil.getString("catatan");
                
                String[] data={a,b,c,d,e,f,g,h,i,j,k,l,m};
                tabmode.addRow(data);
            }
            


        } catch (Exception e){
          JOptionPane.showMessageDialog(null, "Gagal load data: " + e.getMessage());
          e.printStackTrace();
        }
        }
        



        private void resetInputFields() {
    comboobatrsp.setSelectedIndex(0);
    jumlahobat.setText("");
    cat.setText("");
}
        
        
      

        
        public Resep() throws SQLException {
        setUndecorated(true); 
        comboobatrsp = new javax.swing.JComboBox<>();

        initComponents(); 
        setFullScreen();
       
        datatable();
     loadComboBox();
        }
     
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        buttoninput = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelresep = new javax.swing.JTable();
        CloseButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblnamapasien = new javax.swing.JLabel();
        jpembuatan = new com.toedter.calendar.JDateChooser();
        idresep = new javax.swing.JTextField();
        iddokter = new javax.swing.JTextField();
        bcaridokter = new javax.swing.JButton();
        namadokter = new javax.swing.JTextField();
        idpasien = new javax.swing.JTextField();
        namapasien = new javax.swing.JTextField();
        bcarirekam = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        try {
            comboobatrsp =(javax.swing.JComboBox)java.beans.Beans.instantiate(getClass().getClassLoader(), "Apps.Resep_comboobatrsp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        jumlahobat = new javax.swing.JTextField();
        jenisobat = new javax.swing.JTextField();
        hargaobat = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        cat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pendataan Resep Obat Pasien");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(33, 232, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        buttoninput.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buttoninput.setText("Input Resep");
        buttoninput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoninputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.insets = new java.awt.Insets(13, 234, 0, 0);
        getContentPane().add(buttoninput, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Tambah Obat");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 12, 0, 0);
        getContentPane().add(jButton4, gridBagConstraints);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabelresep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelresep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelresepMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelresep);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1141;
        gridBagConstraints.ipady = 240;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 0);
        getContentPane().add(jScrollPane3, gridBagConstraints);

        CloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/CloseButton.png"))); // NOI18N
        CloseButton.setBorder(null);
        CloseButton.setBorderPainted(false);
        CloseButton.setContentAreaFilled(false);
        CloseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 0);
        getContentPane().add(CloseButton, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Lihat Resep");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 7, 0, 0);
        getContentPane().add(jButton2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Tanggal Pembuatan Resep");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 12, 0, 0);
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("ID Resep");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 12, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("ID Dokter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Nama Dokter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("ID Pasien");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 12, 0, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        lblnamapasien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblnamapasien.setText("Nama Pasien");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 12, 0, 0);
        jPanel1.add(lblnamapasien, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 275;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 30, 0, 0);
        jPanel1.add(jpembuatan, gridBagConstraints);

        idresep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idresepActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 265;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 30, 0, 0);
        jPanel1.add(idresep, gridBagConstraints);

        iddokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iddokterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 265;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 30, 0, 0);
        jPanel1.add(iddokter, gridBagConstraints);

        bcaridokter.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        bcaridokter.setText("Cari");
        bcaridokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcaridokterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 0, 0);
        jPanel1.add(bcaridokter, gridBagConstraints);

        namadokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namadokterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.ipadx = 265;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 30, 0, 0);
        jPanel1.add(namadokter, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 265;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        jPanel1.add(idpasien, gridBagConstraints);

        namapasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namapasienActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.ipadx = 265;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 30, 0, 0);
        jPanel1.add(namapasien, gridBagConstraints);

        bcarirekam.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        bcarirekam.setText("Cari");
        bcarirekam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcarirekamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 12, 0, 0);
        jPanel1.add(bcarirekam, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Nama Obat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 73, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Jumlah Obat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 73, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Jenis Obat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 73, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Harga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 76, 0, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Catatan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 73, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        comboobatrsp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboobatrspItemStateChanged(evt);
            }
        });
        comboobatrsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboobatrspMouseClicked(evt);
            }
        });
        comboobatrsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboobatrspActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 259;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 70, 0, 0);
        jPanel1.add(comboobatrsp, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 284;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 70, 0, 0);
        jPanel1.add(jumlahobat, gridBagConstraints);

        jenisobat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisobatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 284;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 70, 0, 0);
        jPanel1.add(jenisobat, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 284;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 70, 0, 0);
        jPanel1.add(hargaobat, gridBagConstraints);

        cat.setColumns(20);
        cat.setRows(5);
        jScrollPane1.setViewportView(cat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 333;
        gridBagConstraints.ipady = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 70, 0, 0);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(40, 12, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idresepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idresepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idresepActionPerformed

    private void bcarirekamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcarirekamActionPerformed
        // TODO add your handling code here:
                     try {
        // Disable hanya field iddokter, bukan seluruh window
        idpasien.setEnabled(false);
        namapasien.setEnabled(false);
        
        cariPasien cp = new cariPasien();
        cp.resep = this;
        
        // Karena cariDokter1 adalah JFrame, gunakan pengaturan ini:
        cp.setAlwaysOnTop(true);
        cp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Gunakan HIDE instead of DISPOSE
        
        // Set posisi di tengah parent window
        cp.setLocationRelativeTo(this);
        
        // JANGAN disable parent window - ini yang menyebabkan masalah
        // this.setEnabled(false); // <-- Hapus baris ini
        
        cp.setVisible(true);
        cp.setResizable(false);
        
        // Tambahkan window listener untuk handle ketika form ditutup
        cp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                handleFormClosed();
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                handleFormClosed();
            }

            private void handleFormClosed() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error membuka form pencarian dokter: " + e.getMessage());
        handleFormClosed();
    }
    }//GEN-LAST:event_bcarirekamActionPerformed

    private void buttoninputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoninputActionPerformed

        try {
            simpanResepBaru() ;
        } catch (SQLException ex) {
            Logger.getLogger(Resep.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttoninputActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        new Dashboard();
        this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void tabelresepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelresepMouseClicked
if (evt.getClickCount() == 2) { // Double click
            //pilihobatActionPerformed(null);
        }
        int selectedRow = tabelresep.getSelectedRow();

// Format tanggal dari String ke java.util.Date
try {
    String tgl = tabmode.getValueAt(selectedRow, 0).toString(); // Kolom tanggal resep (bukan 1!)
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date tanggal = sdf.parse(tgl);
    jpembuatan.setDate(tanggal);
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Gagal memproses tanggal: " + e.getMessage());
}

// Set data ke input form berdasarkan urutan kolom yang benar
idresep.setText(tabmode.getValueAt(selectedRow, 1).toString());        // ID Resep
namapasien.setText(tabmode.getValueAt(selectedRow, 2).toString());
iddokter.setText(tabmode.getValueAt(selectedRow, 3).toString()); // ID Obat
String namaObat = tabmode.getValueAt(selectedRow, 4).toString();
DefaultListModel<String> model = new DefaultListModel<>();
model.addElement(namaObat);
//listnamaobat.setModel(model);
jumlahobat.setText(tabmode.getValueAt(selectedRow, 6).toString());   
jenisobat.setText(tabmode.getValueAt(selectedRow, 7).toString());// Jumlah Obat
cat.setText(tabmode.getValueAt(selectedRow, 8).toString());        // Catatan

    }//GEN-LAST:event_tabelresepMouseClicked

    private void bcaridokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcaridokterActionPerformed
        // TODO add your handling code here:
      try {
        // Disable hanya field iddokter, bukan seluruh window
        iddokter.setEnabled(false);
        namadokter.setEnabled(false);
        
        cariDokter1 cd = new cariDokter1();
        cd.resep = this;
        
        // Karena cariDokter1 adalah JFrame, gunakan pengaturan ini:
        cd.setAlwaysOnTop(true);
        cd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Gunakan HIDE instead of DISPOSE
        
        // Set posisi di tengah parent window
        cd.setLocationRelativeTo(this);
        
        // JANGAN disable parent window - ini yang menyebabkan masalah
        // this.setEnabled(false); // <-- Hapus baris ini
        
        cd.setVisible(true);
        cd.setResizable(false);
        
        // Tambahkan window listener untuk handle ketika form ditutup
        cd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                handleFormClosed();
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                handleFormClosed();
            }
        });
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error membuka form pencarian dokter: " + e.getMessage());
        handleFormClosed();
    }

    }//GEN-LAST:event_bcaridokterActionPerformed

    private void comboobatrspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboobatrspActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_comboobatrspActionPerformed

    private void comboobatrspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboobatrspMouseClicked
    // TODO add your handling code here:
    }//GEN-LAST:event_comboobatrspMouseClicked

    private void comboobatrspItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboobatrspItemStateChanged
comboobatrsp.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        Obat selected = (Obat) comboobatrsp.getSelectedItem();
        if (selected != null) {
            hargaobat.setText(selected.getHarga());
            jenisobat.setText(selected.getJenis());
        }
    }
});
    // TODO add your handling code here:
    }//GEN-LAST:event_comboobatrspItemStateChanged

    private void namadokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namadokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namadokterActionPerformed

    private void namapasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namapasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namapasienActionPerformed

    private void iddokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iddokterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iddokterActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            tambahObatKeResep();
        } catch (SQLException ex) {
            Logger.getLogger(Resep.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
try {
    String idResep = idresep.getText(); // Ambil ID dari TextField
    String sql = "SELECT nama_obat, jenis, jumlah_obat, harga, catatan FROM resep WHERE id_resep1 = ?";
    PreparedStatement stat = conn.prepareStatement(sql);
    stat.setString(1, idResep);
    ResultSet rs = stat.executeQuery();

    DefaultTableModel model = (DefaultTableModel) tabelresep.getModel();
    model.setRowCount(0); // Bersihkan isi tabel

    int totalBiaya = 0;

    while (rs.next()) {
        String nama = rs.getString("nama_obat");
        String jenis = rs.getString("jenis");
        String catatan = rs.getString("catatan");

        // Ambil sebagai string biar aman
        String jumlahStr = rs.getString("jumlah_obat");
        String hargaStr = rs.getString("harga");

        int jumlah = 0;
        int harga = 0;
        int subtotal = 0;

        try {
            jumlah = Integer.parseInt(jumlahStr != null ? jumlahStr.trim() : "0");
        } catch (NumberFormatException e) {
            jumlah = 0;
        }

        try {
            harga = Integer.parseInt(hargaStr != null ? hargaStr.trim() : "0");
        } catch (NumberFormatException e) {
            harga = 0;
        }

        subtotal = jumlah * harga;
        totalBiaya += subtotal;

        model.addRow(new Object[]{
            nama, jenis, jumlah, harga, subtotal, catatan
        });
    }


} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Gagal menampilkan resep: " + e.getMessage());
}

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jenisobatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisobatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenisobatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Resep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Resep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Resep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Resep.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Resep().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Resep.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    private void setFullScreen() {
    try {
        // Gunakan maximized state daripada full screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Alternative: gunakan full screen hanya jika benar-benar diperlukan
        /*
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            // Simpan referensi untuk bisa keluar dari full screen
            gd.setFullScreenWindow(this);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize.width, screenSize.height);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        */
    } catch (Exception e) {
        // Fallback ke maximized window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JButton bcaridokter;
    private javax.swing.JButton bcarirekam;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton buttoninput;
    private javax.swing.JTextArea cat;
    private javax.swing.JComboBox<String> comboobatrsp;
    private javax.swing.JTextField hargaobat;
    private javax.swing.JTextField iddokter;
    private javax.swing.JTextField idpasien;
    private javax.swing.JTextField idresep;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jenisobat;
    private com.toedter.calendar.JDateChooser jpembuatan;
    private javax.swing.JTextField jumlahobat;
    private javax.swing.JLabel lblnamapasien;
    private javax.swing.JTextField namadokter;
    private javax.swing.JTextField namapasien;
    private javax.swing.JTable tabelresep;
    // End of variables declaration//GEN-END:variables


}