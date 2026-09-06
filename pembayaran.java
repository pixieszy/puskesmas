/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Apps;

import Config.KoneksiDatabase;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class pembayaran extends javax.swing.JFrame {
    private Connection conn = KoneksiDatabase.getConnection();
    private DefaultTableModel tabmode;
    private java.util.Date date;
    public pembayaran bayar = null;
    public Resep resep= null;
    
    
    
    public String id;
    public String getid(){
        return id;
    }
     public String getidres(String idres){
     return idres;
     }
     
    
         public void dokterTerpilih(){
        cariDokter1 cd = new cariDokter1();
        cd.bayar = this;
        iddokter.setText(id);
    }
         
                  public void resepTerpilih(){
        cariresep cr = new cariresep();
        cr.bayar= this;
        idresep.setText(id);
    }
    
    
    
 protected void aktif (){
        jtanggal.setEnabled(true);
        idpembayaran.setEnabled(true);
        idresep.setEnabled(true);
        idtotal.setEnabled(true);
        cmetode.setEnabled(true);
    }
    
protected void kosong () {
    idpembayaran.setText("");
    idresep.setText("");
    idtotal.setText("");
    cmetode.setSelectedIndex(0); // kembali ke pilihan pertama
    jtanggal.setDate(null); // reset tanggal
}
    
        protected void datatable() throws SQLException{
        Object[] Baris ={"ID Pembayaran","ID Resep","Total Biaya","Metode Pembayaran","Tanggal Bayar"};
        tabmode = new DefaultTableModel(null,  Baris);
        tabelpembayaran.setModel (tabmode);
        String sql = "select * from pembayaran";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("id_pembayaran");
                String b = hasil.getString("id_resep");
                String c = hasil.getString("total_biaya");
                String d = hasil.getString("metode_pembayaran");
                String e = hasil.getString("tanggal_bayar");
                
                String[] data={a,b,c,d,e};
                tabmode.addRow(data);
            }
        } catch (Exception e){
          JOptionPane.showMessageDialog(null, "Gagal load data: " + e.getMessage());
          e.printStackTrace();
        }
    }
    public pembayaran() throws SQLException {
        initComponents();
       datatable();
       setFullScreen();
       
    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        closeButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        idpembayaran = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        idresep = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        iddokter = new javax.swing.JTextField();
        cariresep = new javax.swing.JButton();
        caridok = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        idtotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmetode = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jtanggal = new com.toedter.calendar.JDateChooser();
        btnsimpan = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        ttable = new javax.swing.JScrollPane();
        tabelpembayaran = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.insets = new java.awt.Insets(13, 82, 0, 12);
        getContentPane().add(closeButton, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PEMBAYARAN");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("ID Pembayaran");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("ID Resep");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("ID DOKTER");

        cariresep.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cariresep.setText("Cari");
        cariresep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariresepActionPerformed(evt);
            }
        });

        caridok.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        caridok.setText("Cari");
        caridok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caridokActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Status Perawatan");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Biaya Konsultasi");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Total Bayar");

        idtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtotalActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Metode Pembayaran");

        cmetode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunai", "Transfer", "QRIS" }));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Tanggal Pembayaran");

        btnsimpan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnsimpan.setText("SIMPAN");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnbatal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnbatal.setText("BATAL");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btncetak.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btncetak.setText("CETAK KWITANSI");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(240, 240, 240))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idpembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                            .addComponent(idresep)
                            .addComponent(iddokter))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cariresep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(caridok)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField2)
                                .addGap(102, 102, 102))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btncetak)
                                    .addComponent(idtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(105, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cmetode, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(btnsimpan)
                .addGap(47, 47, 47)
                .addComponent(btnbatal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idpembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idresep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariresep))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(iddokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caridok))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmetode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnbatal)
                    .addComponent(btncetak))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(57, 182, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        ttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ttableMouseClicked(evt);
            }
        });

        tabelpembayaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpembayaranMouseClicked(evt);
            }
        });
        ttable.setViewportView(tabelpembayaran);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 775;
        gridBagConstraints.ipady = 114;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(29, 150, 36, 0);
        getContentPane().add(ttable, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
        StringBuilder isi = new StringBuilder();
        isi.append("ID Bayar    : ").append(idpembayaran.getText()).append("\n");
        isi.append("ID Resep   : ").append(idresep.getText()).append("\n");
        isi.append("Total Bayar : ").append(idtotal.getText()).append("\n");
        isi.append("Metode      : ").append(cmetode.getSelectedItem()).append("\n");
        isi.append("Tanggal     : ").append(
            new java.text.SimpleDateFormat("yyyy-MM-dd").format(jtanggal.getDate())
        );
        JTextArea area = new JTextArea(isi.toString());
        JOptionPane.showMessageDialog(this, new JScrollPane(area),
            "Kwitansi", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_btncetakActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
        kosong();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String sql = "INSERT INTO pembayaran "
        + "(id_pembayaran, id_resep, total_biaya, metode_pembayaran, tanggal_bayar) "
        + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, idpembayaran.getText());
            stat.setString(2, idresep.getText());
            stat.setString(3, idtotal.getText());
            stat.setString(4, cmetode.getSelectedItem().toString());
            java.sql.Date tgl = new java.sql.Date(jtanggal.getDate().getTime());
            stat.setDate(5, tgl);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            kosong();
            datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void idtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtotalActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void caridokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caridokActionPerformed
        // TODO add your handling code here:
        try {
            // Disable hanya field iddokter, bukan seluruh window
            iddokter.setEnabled(false);

            cariDokter1 cd = new cariDokter1();
            cd.bayar = this;

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
    }//GEN-LAST:event_caridokActionPerformed

    private void cariresepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariresepActionPerformed
        // TODO add your handling code here:
        try {
            // Disable hanya field iddokter, bukan seluruh window
            idresep.setEnabled(false);

            cariresep cd = new cariresep();
            cd.bayar = this;

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
    }//GEN-LAST:event_cariresepActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered

    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked

    }//GEN-LAST:event_closeButtonMouseClicked

    private void closeButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonMouseMoved

    private void ttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ttableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_ttableMouseClicked

    private void tabelpembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpembayaranMouseClicked
        // TODO add your handling code here:
        int baris = tabelpembayaran.getSelectedRow();

        if (baris >= 0) {
            idpembayaran.setText(tabelpembayaran.getValueAt(baris, 0).toString());
            idresep.setText(tabelpembayaran.getValueAt(baris, 1).toString());
            idtotal.setText(tabelpembayaran.getValueAt(baris, 2).toString());
            cmetode.setSelectedItem(tabelpembayaran.getValueAt(baris, 3).toString());

            try {
                String tanggalString = tabelpembayaran.getValueAt(baris, 4).toString();
                java.util.Date tanggal = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tanggalString);
                jtanggal.setDate(tanggal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tabelpembayaranMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (intxtidpembayaran Java SE 6) is not available, stay with the default look and feel.
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
            java.util.logging.Logger.getLogger(pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new pembayaran().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(pembayaran.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton caridok;
    private javax.swing.JButton cariresep;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox<String> cmetode;
    private javax.swing.JTextField iddokter;
    private javax.swing.JTextField idpembayaran;
    private javax.swing.JTextField idresep;
    private javax.swing.JTextField idtotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private com.toedter.calendar.JDateChooser jtanggal;
    private javax.swing.JTable tabelpembayaran;
    private javax.swing.JScrollPane ttable;
    // End of variables declaration//GEN-END:variables

    private void handleFormClosed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

