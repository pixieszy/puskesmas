/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apps;
import static Apps.EncryptDecrypt.decrypt;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Config.KoneksiDatabase;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javafx.css.StyleOrigin.USER;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author USER
 */
public class rekammedis extends javax.swing.JFrame {
    private Connection conn = KoneksiDatabase.getConnection();
    private DefaultTableModel tabmode;
    public rekammedis rekam;
    
    public String id;
    public String npoli;
    public String idpas;
    
    
    // Method untuk dipanggil setelah dokter dipilih
    public void dokterTerpilih() {
        // Update text field di form resep
        jiddok.setText(this.id);  // Sesuaikan nama text field
        jnamapoli.setText(this.npoli);
        // txtNamaDokter.setText(this.nama); // Jika perlu nama juga
        
        System.out.println("ID Dokter terpilih di Resep: " + this.id);
    }
    
        public void pasienTerpilih(){
        cariPasien cp = new cariPasien();
        cp.rekam = this;
        jidpas.setText(idpas);
        rekam.setVisible(true);
    }
    
     protected void aktif (){
     java.util.Date date = null;
        jidrekam.setEnabled(true);
        jidpas.setEnabled(true);
        jiddok.setEnabled(true);
        jnamapoli.setEnabled(true);
        jkeluhan.setEnabled(true);
        jdiag.setEnabled(true);
        jtindakan.setEnabled(true);
        jcat.setEnabled(true);
        jresep.setEnabled(true);
    }
    
protected void kosong () {
        jidrekam.setText("");
        jidpas.setText("");
        jiddok.setText("");
        jnamapoli.setText("");
        jkeluhan.setText("");
        jdiag.setText("");
        jtindakan.setText("");
        jcat.setText("");
        jresep.setText("");
        jpemeriksaan.setDate(null);
}
    

        protected void datatable() throws SQLException{
        Object[] Baris ={"id_rekam","id_pasien","id_dokter","id_poli","keluhan","diagnosa","tindakan","catatan_dokter","resep","tanggal_kunjungan"};
        tabmode = new DefaultTableModel(null,  Baris);
        tabelrekam.setModel (tabmode);
        String sql = "select * from rekam_medis";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_rekam");
                String b = hasil.getString("id_pasien");
                String c = hasil.getString("id_dokter");
                String d = hasil.getString("id_poli");
                String e = hasil.getString("keluhan");
                String f = hasil.getString("diagnosa");
                String g = hasil.getString("tindakan");
                String h = hasil.getString("catatan_dokter");
                String i = hasil.getString("resep");
                String j = hasil.getString("tanggal_kunjungan");
                
                
                String[] data={a,b,c,d,e, f, g, h,i,j};
                tabmode.addRow(data);
            }
        } catch (Exception e){
          JOptionPane.showMessageDialog(null, "Gagal load data: " + e.getMessage());
          e.printStackTrace();
        }
    }

   
    
    /**
     * Creates new form rekammedis
     */
    public rekammedis() throws SQLException {
        initComponents();
        datatable();
        setFullScreen();
        generateDanTampilkanId(); 
        setLocationRelativeTo(null);
    }
    
public String generateIdRekam(Connection conn) throws SQLException {
    String prefix = "RM" + new SimpleDateFormat("yyyyMMdd").format(new Date());
    String query = "SELECT id_rekam FROM rekam_medis WHERE id_rekam LIKE '" + prefix + "%' ORDER BY id_rekam DESC LIMIT 1";

    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);

    int nomor = 1;
    if (rs.next()) {
        String lastId = rs.getString("id_rekam");
        nomor = Integer.parseInt(lastId.substring(prefix.length())) + 1;
    }

    return String.format(prefix + "%03d", nomor);
}
private void generateDanTampilkanId() {
    try {
        String id = generateIdRekam(conn);
        jidrekam.setText(id); // tampilkan hasil di textfield
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal generate ID: " + e.getMessage());
    }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelrekam = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jidrekam = new javax.swing.JTextField();
        jidpas = new javax.swing.JTextField();
        jiddok = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jkeluhan = new javax.swing.JTextField();
        jdiag = new javax.swing.JTextField();
        jcat = new javax.swing.JTextField();
        jtindakan = new javax.swing.JTextField();
        jpemeriksaan = new com.toedter.calendar.JDateChooser();
        cariidpasien = new javax.swing.JButton();
        jresep = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jnamapoli = new javax.swing.JTextField();
        cariiddokter = new javax.swing.JButton();
        btsimpan = new javax.swing.JButton();
        btubah = new javax.swing.JButton();
        bthapus = new javax.swing.JButton();
        btbatal = new javax.swing.JButton();
        bprint = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tabelrekam.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelrekam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelrekamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelrekam);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1161;
        gridBagConstraints.ipady = 101;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(39, 154, 24, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/CloseButton.png"))); // NOI18N
        closeButton.setBorder(null);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                closeButtonMouseMoved(evt);
            }
        });
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
        });
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 140, 0, 0);
        getContentPane().add(closeButton, gridBagConstraints);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("ID REKAM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("ID PASIEN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("ID DOKTER");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jidrekam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jidrekamActionPerformed(evt);
            }
        });
        jPanel1.add(jidrekam, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 260, -1));

        jidpas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jidpasActionPerformed(evt);
            }
        });
        jPanel1.add(jidpas, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 260, -1));

        jiddok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jiddokActionPerformed(evt);
            }
        });
        jPanel1.add(jiddok, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 260, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("KELUHAN");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("DIAGNOSA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("CATATAN DOKTER");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 170, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Tanggal Kunjungan");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 150, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("TINDAKAN");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, -1, -1));

        jkeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluhanActionPerformed(evt);
            }
        });
        jPanel1.add(jkeluhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 80, 260, -1));

        jdiag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdiagActionPerformed(evt);
            }
        });
        jPanel1.add(jdiag, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, 259, -1));

        jcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcatActionPerformed(evt);
            }
        });
        jPanel1.add(jcat, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 258, -1));

        jtindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtindakanActionPerformed(evt);
            }
        });
        jPanel1.add(jtindakan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 259, -1));
        jPanel1.add(jpemeriksaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 162, -1));

        cariidpasien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cariidpasien.setText("Cari");
        cariidpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariidpasienActionPerformed(evt);
            }
        });
        jPanel1.add(cariidpasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, -1));
        jPanel1.add(jresep, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 260, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("RESEP");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, -1, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("POLI");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));
        jPanel1.add(jnamapoli, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 260, -1));

        cariiddokter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cariiddokter.setText("Cari");
        cariiddokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariiddokterActionPerformed(evt);
            }
        });
        jPanel1.add(cariiddokter, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        btsimpan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btsimpan.setText("SIMPAN");
        btsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        btubah.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btubah.setText("UBAH");
        btubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btubahActionPerformed(evt);
            }
        });
        jPanel1.add(btubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, -1, -1));

        bthapus.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        bthapus.setText("HAPUS");
        bthapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthapusActionPerformed(evt);
            }
        });
        jPanel1.add(bthapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        btbatal.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btbatal.setText("BATAL");
        btbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbatalActionPerformed(evt);
            }
        });
        jPanel1.add(btbatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, -1, -1));

        bprint.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        bprint.setText("CETAK DATA REKAM MEDIS");
        bprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprintActionPerformed(evt);
            }
        });
        jPanel1.add(bprint, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("REKAM MEDIS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(55, 189, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jidrekamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jidrekamActionPerformed
jidrekam.setBounds(100, 20, 200, 25); // x, y, width, height
jidrekam.setEditable(false);
add(jidrekam);
    }//GEN-LAST:event_jidrekamActionPerformed

    private void jidpasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jidpasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jidpasActionPerformed

    private void jkeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jkeluhanActionPerformed

    private void jiddokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jiddokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jiddokActionPerformed

    private void jcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcatActionPerformed

    private void jdiagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdiagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdiagActionPerformed

    private void jtindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtindakanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtindakanActionPerformed

    private void btubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btubahActionPerformed
        // TODO add your handling code here:
try {
    String sql = "UPDATE rekam_medis SET id_pasien=?, id_dokter=?, id_poli=?, tanggal_kunjungan=?, keluhan=?, diagnosa=?, tindakan=?, resep=?, catatan_dokter=? WHERE id_rekam=?";
    PreparedStatement stat = conn.prepareStatement(sql);

    stat.setString(1, jidpas.getText().trim());
    stat.setString(2, jiddok.getText().trim());
    stat.setString(3, jnamapoli.getText().trim());

    java.util.Date tgl = jpemeriksaan.getDate();      
    if (tgl != null) {
        stat.setDate(4, new java.sql.Date(tgl.getTime()));
    } else {
        stat.setNull(4, java.sql.Types.DATE);
    }

    stat.setString(5, jkeluhan.getText().trim());
    stat.setString(6, jdiag.getText().trim());
    stat.setString(7, jtindakan.getText().trim());
    stat.setString(8, jresep.getText().trim());
    stat.setString(9, jcat.getText().trim());

    stat.setString(10, jidrekam.getText().trim());

    int hasil = stat.executeUpdate();
    if (hasil > 0) {
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        kosong();
        jidrekam.requestFocus();
    } else {
        JOptionPane.showMessageDialog(null, "Data gagal diubah: ID tidak ditemukan");
    }

} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Data gagal diubah\n" + e);
}

try {
    datatable();
} catch (SQLException ex) {
    Logger.getLogger(rekammedis.class.getName()).log(Level.SEVERE, null, ex);
}

    }//GEN-LAST:event_btubahActionPerformed

    private void bthapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthapusActionPerformed
        // TODO add your handling code here:
    int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
    
    if (konfirmasi == JOptionPane.YES_OPTION) {
        try {
            String sql = "DELETE FROM rekam_medis WHERE id_rekam=?";
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, jidrekam.getText()); // Ambil ID dari field jidrekam

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong(); // fungsi untuk mengosongkan form
                jidrekam.requestFocus();
                datatable(); // refresh tabel
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan atau sudah dihapus");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus\n" + e);
        }
    }
    }//GEN-LAST:event_bthapusActionPerformed

    private void btbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbatalActionPerformed
        // TODO add your handling code here:
        kosong();
        try {
            datatable();
        } catch (SQLException ex) {
            Logger.getLogger(rekammedis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btbatalActionPerformed

    private void btsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsimpanActionPerformed
        // TODO add your handling code here:
        String sql = "INSERT INTO rekam_medis (id_rekam, id_pasien, id_dokter, id_poli, keluhan, diagnosa, tindakan, catatan_dokter, resep, tanggal_kunjungan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, jidrekam.getText());
            stat.setString(2, jidpas.getText());
            stat.setString(3, jiddok.getText());
            stat.setString(4, jnamapoli.getText());
            stat.setString(5, jkeluhan.getText());
            stat.setString(6, jdiag.getText());
            stat.setString(7, jtindakan.getText());
            stat.setString(8, jcat.getText());
            stat.setString(9, jresep.getText());
            
    
            java.util.Date tgl = jpemeriksaan.getDate();
            if (tgl != null) {
                stat.setDate(10, new java.sql.Date(tgl.getTime())); // tanggal_lahir
            } else {
                stat.setNull(10, java.sql.Types.DATE);
            }

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            kosong();
            jidrekam.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan: " + e.getMessage());
        }
       try {
           datatable();
       } catch (SQLException ex) {
           Logger.getLogger(rekammedis.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btsimpanActionPerformed

    private void bprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprintActionPerformed
        // TODO add your handling code here:
                 try {
            String reportPath = "src/laporan/laporan_rekam Medis.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperViewer.viewReport(jasperPrint, false);

            } catch (JRException e) {
                JOptionPane.showMessageDialog(null, "Error JasperReports: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bprintActionPerformed

    private void tabelrekamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelrekamMouseClicked
        // TODO add your handling code here:
try {
    int bar = tabelrekam.getSelectedRow();
    if (bar == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu dari tabel.");
        return;
    }

    jidrekam.setText(tabmode.getValueAt(bar, 0).toString());     // id_rekam
    jidpas.setText(tabmode.getValueAt(bar, 1).toString());       // id_pasien
    jiddok.setText(tabmode.getValueAt(bar, 2).toString()); 
    jnamapoli.setText(tabmode.getValueAt(bar, 3).toString());// id_dokter
    jkeluhan.setText(tabmode.getValueAt(bar, 4).toString());     // keluhan
    jdiag.setText(tabmode.getValueAt(bar, 5).toString());        // diagnosa
    jtindakan.setText(tabmode.getValueAt(bar, 6).toString());    // tindakan
    jcat.setText(tabmode.getValueAt(bar, 7).toString());         // catatan_dokter
    jresep.setText(tabmode.getValueAt(bar, 8).toString());       // resep

    // Tanggal kunjungan (kolom ke-8, index 8)
    Object objTanggal = tabmode.getValueAt(bar, 9);
    if (objTanggal != null && !objTanggal.toString().isEmpty()) {
        try {
            String tglperiksa = objTanggal.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tanggal = sdf.parse(tglperiksa);
            jpemeriksaan.setDate(tanggal);
        } catch (ParseException pe) {
            jpemeriksaan.setDate(null);
            JOptionPane.showMessageDialog(this, "Format tanggal tidak sesuai: " + objTanggal);
        }
    } else {
        jpemeriksaan.setDate(null);
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage());
}


    }//GEN-LAST:event_tabelrekamMouseClicked

    private void closeButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonMouseMoved

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked

    }//GEN-LAST:event_closeButtonMouseClicked

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered

    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void cariiddokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariiddokterActionPerformed
        // TODO add your handling code here:
              try {
        // Disable hanya field iddokter, bukan seluruh window
        jiddok.setEnabled(false);
        jnamapoli.setEnabled(false);
        
        cariDokter1 cd = new cariDokter1();
        cd.rekam = this;
        
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

            private void handleFormClosed() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error membuka form pencarian dokter: " + e.getMessage());
        handleFormClosed();
    }
    }//GEN-LAST:event_cariiddokterActionPerformed

    private void cariidpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariidpasienActionPerformed
        // TODO add your handling code here:
              try {
        // Disable hanya field iddokter, bukan seluruh window
        jidpas.setEnabled(false);
        
        cariPasien cp = new cariPasien();
        cp.rekam = this;
        
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
    }//GEN-LAST:event_cariidpasienActionPerformed

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
            java.util.logging.Logger.getLogger(rekammedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(rekammedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(rekammedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rekammedis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new rekammedis().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(rekammedis.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

               private void setFullScreen() {
// Menggunakan ukuran layar penuh tanpa mode fullscreen
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
setSize(screenSize.width, screenSize.height);
setExtendedState(JFrame.MAXIMIZED_BOTH); // Alternatif fullscreen tanpa mengubah mode penuh

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bprint;
    private javax.swing.JButton btbatal;
    private javax.swing.JButton bthapus;
    private javax.swing.JButton btsimpan;
    private javax.swing.JButton btubah;
    private javax.swing.JButton cariiddokter;
    private javax.swing.JButton cariidpasien;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jcat;
    private javax.swing.JTextField jdiag;
    private javax.swing.JTextField jiddok;
    private javax.swing.JTextField jidpas;
    private javax.swing.JTextField jidrekam;
    private javax.swing.JTextField jkeluhan;
    private javax.swing.JTextField jnamapoli;
    private com.toedter.calendar.JDateChooser jpemeriksaan;
    private javax.swing.JTextField jresep;
    private javax.swing.JTextField jtindakan;
    private javax.swing.JTable tabelrekam;
    // End of variables declaration//GEN-END:variables

    private void handleFormClosed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
