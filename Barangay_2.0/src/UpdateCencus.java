import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateCencus extends javax.swing.JFrame {

    /**
     * Creates new form UpdateCencus
     */
    public UpdateCencus() {
        initComponents();
        populateYearComboBox();
        setLocationRelativeTo(null);
        setTitle("Update");
    }

    
    private void populateYearComboBox() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barangay_db2", "root", "");
            String sql = "SELECT DISTINCT year FROM census ORDER BY year DESC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            YearComboBox.removeAllItems();
            while (rs.next()) {
                YearComboBox.addItem(rs.getString("year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading years.");
        }
    }
    
    private void loadExistingData(String selectedYear) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barangay_db2", "root", "");
            String sql = "SELECT * FROM census WHERE year = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, selectedYear);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Date dateRecorded = rs.getDate("date_recorded");
                if (dateRecorded != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    DateTextField1.setText(sdf.format(dateRecorded));
                } else {
                    DateTextField1.setText("");
                }

                MaleTextField1.setText(rs.getString("male_population") != null ? rs.getString("male_population") : "0");
                FemaleTextField1.setText(rs.getString("female_population") != null ? rs.getString("female_population") : "0");
            } else {
                JOptionPane.showMessageDialog(this, "No data found for the selected year.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading census data.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MaleTextField = new javax.swing.JTextField();
        FemaleTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        YearTextField = new javax.swing.JTextField();
        DateTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        MaleTextField1 = new javax.swing.JTextField();
        FemaleTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        DateTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        UpdateButton = new javax.swing.JButton();
        CancelButton2 = new javax.swing.JButton();
        YearComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setText("BARANGAY MANAGEMENT SYSTEM");

        jLabel3.setText("MALE COUNT:");

        MaleTextField.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N

        FemaleTextField.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N

        jLabel4.setText("FEMALE COUNT:");

        jLabel5.setText("YEAR:");

        YearTextField.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N

        DateTextField.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N

        jLabel6.setText("DATE RECORDED:");

        CancelButton.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        CancelButton.setText("CANCEL");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel7.setText("BARANGAY MANAGEMENT SYSTEM");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 15, -1, -1));

        jLabel8.setText("MALE COUNT:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        MaleTextField1.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jPanel1.add(MaleTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 120, 30));

        FemaleTextField1.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jPanel1.add(FemaleTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 120, 30));

        jLabel9.setText("FEMALE COUNT:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        DateTextField1.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jPanel1.add(DateTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 120, 30));

        jLabel11.setText("YEAR:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        UpdateButton.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        UpdateButton.setText("UPDATE");
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });
        jPanel1.add(UpdateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        CancelButton2.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        CancelButton2.setText("CANCEL");
        CancelButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(CancelButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, -1));

        YearComboBox.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        YearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(YearComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 120, 30));

        jLabel12.setText("DATE RECORDED:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        this.dispose();
        CencusFrame cencusFrame = new CencusFrame();
        cencusFrame.setVisible(true);
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barangay_db2", "root", "");
        String selectedYear = (String) YearComboBox.getSelectedItem();

        if (selectedYear == null) {
            JOptionPane.showMessageDialog(this, "Please select a year.");
            return;
        }

        int malePopulation = Integer.parseInt(MaleTextField1.getText());
        int femalePopulation = Integer.parseInt(FemaleTextField1.getText());
        int totalPopulation = malePopulation + femalePopulation;

        String sql = "UPDATE census SET date_recorded = ?, male_population = ?, female_population = ?, total_population = ? WHERE year = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, DateTextField1.getText());
        pst.setInt(2, malePopulation);
        pst.setInt(3, femalePopulation);
        pst.setInt(4, totalPopulation);
        pst.setString(5, selectedYear);

        int updated = pst.executeUpdate();
        if (updated > 0) {
            JOptionPane.showMessageDialog(this, "Census record updated successfully.");
            this.dispose(); // Close the current window

            // Open CensusFrame
            CencusFrame cencusFrame = new CencusFrame();
            cencusFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update record.");
        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating data.");
    }
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void CancelButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButton2ActionPerformed
        this.dispose();
        CencusFrame cencusFrame = new CencusFrame();
        cencusFrame.setVisible(true);
    }//GEN-LAST:event_CancelButton2ActionPerformed

    private void YearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearComboBoxActionPerformed
        String selectedYear = (String) YearComboBox.getSelectedItem();
        if (selectedYear != null) {
            loadExistingData(selectedYear);
        }
    }//GEN-LAST:event_YearComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateCencus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateCencus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateCencus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateCencus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateCencus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton CancelButton2;
    private javax.swing.JTextField DateTextField;
    private javax.swing.JTextField DateTextField1;
    private javax.swing.JTextField FemaleTextField;
    private javax.swing.JTextField FemaleTextField1;
    private javax.swing.JTextField MaleTextField;
    private javax.swing.JTextField MaleTextField1;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JComboBox<String> YearComboBox;
    private javax.swing.JTextField YearTextField;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
