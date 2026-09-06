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
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static javafx.css.StyleOrigin.USER;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
/**
 *
 * @author Acer
 */
public class Obat extends javax.swing.JFrame {
    private Connection conn = KoneksiDatabase.getConnection();
    private DefaultTableModel tabmode;

    static Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static Object getNama() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static Object getJenis() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static Object getHarga() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Creates new form Obat
     */

    public Obat() {
        initComponents();
        setFullScreen();
         loadData();
    }

    Obat(int id, String nama, double harga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Obat(String nama, String jenis, double harga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        public void loadData() {
        // Query untuk mengambil data dari database
       String query = "SELECT obat.*, user.nama as namaorang FROM obat LEFT JOIN user ON obat.nip = user.nip";


        // Kolom yang akan ditampilkan di JTable
        String[] columnNames = {"ID Obat", "Nama Obat", "Kategori","Jenis Obat", "Stok", "Harga", "Brand", "BPJS Status", "NIP", "Input Date", "Edit Date"};

        // Array untuk menampung data yang diambil dari database
        Object[][] data = new Object[0][columnNames.length];
        
        // Dapatkan koneksi dari KoneksiDatabase
        Connection conn = KoneksiDatabase.getConnection();
        
        if (conn != null) {
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                // Hitung jumlah baris
                int rowCount = 0;
                while (rs.next()) {
                    rowCount++;
                }
                
                // Mengisi data array dengan data dari ResultSet
                data = new Object[rowCount][columnNames.length];
                int rowIndex = 0;
                rs.beforeFirst();  // Set ResultSet ke awal
                while (rs.next()) {
                    data[rowIndex][0] = rs.getInt("id_obat");
                    data[rowIndex][1] = rs.getString("nama_obat");
                    data[rowIndex][2] = rs.getString("kategori");
                    data[rowIndex][3] = rs.getString("jenis");
                    data[rowIndex][4] = rs.getInt("stok");
                    data[rowIndex][5] = rs.getString("harga");
                    data[rowIndex][6] = rs.getString("brand");
                    data[rowIndex][7] = rs.getString("bpjs_status");
                    data[rowIndex][8] = rs.getString("nip");
                    data[rowIndex][9] = rs.getTimestamp("input_date");
                    data[rowIndex][10] = rs.getTimestamp("edit_date");
                    rowIndex++;
                }

                // Buat DefaultTableModel dengan data yang telah diambil
                DefaultTableModel model = new DefaultTableModel(data, columnNames);

                // Set model ke tabel_obat
                tabel_obat.setModel(model);
                
            } catch (SQLException e) {
                System.out.println("Error mengambil data: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memuat data.");
            } finally {
                // Pastikan koneksi ditutup setelah selesai
                KoneksiDatabase.tutupKoneksi(conn);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Koneksi ke database gagal.");
        }
    }
private void InputDataObat() {
    // Validasi input
    if (nama_obat.getText().isEmpty() || harga_obat.getText().isEmpty() || stok_obat.getText().isEmpty() || brand_obat.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Harap lengkapi semua data terlebih dahulu.");
        return;
    }

    String namaObat = nama_obat.getText();
    String kategori = obat_kategori.getSelectedItem().toString();
    String jenis = jenisobat.getSelectedItem().toString();
    String harga = harga_obat.getText();
    String brand = brand_obat.getText();
    String bpjsStatus = BPJS_status.getSelectedItem().toString();
    String nip = decrypt(SessionManager.getNip());

    int stok;
    try {
        stok = Integer.parseInt(stok_obat.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Stok harus berupa angka!");
        return;
    }

    String query = "INSERT INTO obat (nama_obat, kategori, jenis, stok, harga, brand, bpjs_status, nip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    Connection conn = KoneksiDatabase.getConnection();

    if (conn != null) {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, namaObat);
            stmt.setString(2, kategori);
            stmt.setString(3, jenis);
            stmt.setInt(4, stok);
            stmt.setString(5, harga);
            stmt.setString(6, brand);
            stmt.setString(7, bpjsStatus);
            stmt.setString(8, nip);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                nama_obat.setText("");
                obat_kategori.setSelectedIndex(0);
                jenisobat.setSelectedIndex(0);
                stok_obat.setText("");
                harga_obat.setText("");
                brand_obat.setText("");
                BPJS_status.setSelectedIndex(0);
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal disimpan.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan data:\n" + e.getMessage());
            e.printStackTrace();
        } finally {
            KoneksiDatabase.tutupKoneksi(conn);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Koneksi ke database gagal.");
    }

    loadData(); // refresh tabel
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

        backgroundobat1 = new Apps.backgroundmenu();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        obat_kategori = new javax.swing.JComboBox<>();
        nama_obat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        stok_obat = new javax.swing.JTextField();
        harga_obat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        brand_obat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BPJS_status = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        bcetak = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jenisobat = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_obat = new javax.swing.JTable();
        CloseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        backgroundobat1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inventory Obat Puskesmas Kemuning");
        jLabel1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(8, 159, 0, 0);
        backgroundobat1.add(jLabel1, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Obat"), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Nama Obat");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Kategori");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 64, -1));

        obat_kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Obat Ringan", "Obat Keras", "Obat Sangat Keras" }));
        jPanel1.add(obat_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 380, -1));
        jPanel1.add(nama_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 380, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Stok");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 44, -1));

        stok_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stok_obatActionPerformed(evt);
            }
        });
        jPanel1.add(stok_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 380, -1));
        jPanel1.add(harga_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 380, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Harga");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 44, -1));

        brand_obat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brand_obatActionPerformed(evt);
            }
        });
        jPanel1.add(brand_obat, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 310, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Brand");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 101, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("BPJS Status");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 101, -1));

        BPJS_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tercover", "Tidak Dicover" }));
        BPJS_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPJS_statusActionPerformed(evt);
            }
        });
        jPanel1.add(BPJS_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 310, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Input Obat");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 200, 42));

        bcetak.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bcetak.setText("Print Data Obat");
        bcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcetakActionPerformed(evt);
            }
        });
        jPanel1.add(bcetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, 180, 42));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Jenis Obat");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jenisobat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~Pilih Jenis Obat~", "Kapsul", "Tablet", "Sirup ", "Salep", "Injeksi", " " }));
        jPanel1.add(jenisobat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 380, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 56;
        gridBagConstraints.ipady = 42;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(57, 12, 0, 0);
        backgroundobat1.add(jPanel1, gridBagConstraints);

        tabel_obat.setBackground(new java.awt.Color(204, 204, 204));
        tabel_obat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Obat", "Nama Obat", "Kategori", "Stok", "Harga", "Tercover BPJS", "Petugas Input", "Input Date", "Edit Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_obat.setOpaque(false);
        tabel_obat.getTableHeader().setReorderingAllowed(false);
        tabel_obat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_obatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_obat);
        if (tabel_obat.getColumnModel().getColumnCount() > 0) {
            tabel_obat.getColumnModel().getColumn(0).setResizable(false);
            tabel_obat.getColumnModel().getColumn(1).setResizable(false);
            tabel_obat.getColumnModel().getColumn(2).setResizable(false);
            tabel_obat.getColumnModel().getColumn(4).setResizable(false);
            tabel_obat.getColumnModel().getColumn(5).setResizable(false);
            tabel_obat.getColumnModel().getColumn(6).setResizable(false);
            tabel_obat.getColumnModel().getColumn(7).setResizable(false);
            tabel_obat.getColumnModel().getColumn(8).setResizable(false);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1029;
        gridBagConstraints.ipady = 226;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(46, 12, 238, 0);
        backgroundobat1.add(jScrollPane1, gridBagConstraints);

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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 14, 0, 0);
        backgroundobat1.add(CloseButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backgroundobat1, javax.swing.GroupLayout.DEFAULT_SIZE, 1163, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(backgroundobat1, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        new Dashboard();
        this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void tabel_obatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_obatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabel_obatMouseClicked

    private void bcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcetakActionPerformed
        // TODO add your handling code here:
        try {
            String reportPath = "src/laporan/laporan_obat.jrxml";
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
    }//GEN-LAST:event_bcetakActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InputDataObat();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BPJS_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPJS_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BPJS_statusActionPerformed

    private void brand_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brand_obatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_brand_obatActionPerformed

    private void stok_obatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stok_obatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stok_obatActionPerformed

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
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Obat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        // Muat data ke dalam tabel
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Obat().setVisible(true);
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
    private javax.swing.JComboBox<String> BPJS_status;
    private javax.swing.JButton CloseButton;
    private Apps.backgroundmenu backgroundobat1;
    private javax.swing.JButton bcetak;
    private javax.swing.JTextField brand_obat;
    private javax.swing.JTextField harga_obat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jenisobat;
    private javax.swing.JTextField nama_obat;
    private javax.swing.JComboBox<String> obat_kategori;
    private javax.swing.JTextField stok_obat;
    private javax.swing.JTable tabel_obat;
    // End of variables declaration//GEN-END:variables
}
