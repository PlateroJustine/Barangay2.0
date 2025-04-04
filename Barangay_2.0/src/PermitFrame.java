import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PermitFrame extends javax.swing.JFrame {

    public PermitFrame() {
        initComponents();
        setTitle("PERMIT");
        loadPermitTypes();
        loadTableData();
        loadResidentNames();
        setLocationRelativeTo(null);
    }
    private void loadPermitTypes() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT permit_name FROM permit_types");
             ResultSet rs = pst.executeQuery()) {

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("permit_name"));
            }
            PermitBox.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading permit types: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        String sql = "SELECT CONCAT(r.first_name, ' ', r.last_name) AS resident_name, " +
                     "pt.permit_name, p.date_issued " +
                     "FROM residents r " +
                     "JOIN permits p ON r.resident_id = p.resident_id " +
                     "JOIN permit_types pt ON p.permit_type = pt.permit_type_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("resident_name"),
                    rs.getString("permit_name"),
                    rs.getDate("date_issued")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading permit data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadResidentNames() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT CONCAT(first_name, ' ', last_name) AS full_name FROM residents");
             ResultSet rs = pst.executeQuery()) {

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("full_name"));
            }
            ResidentNameBox.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading resident names: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadIssuedPermits() {
    String sql = "SELECT CONCAT(r.first_name, ' ', r.last_name) AS resident_name, " +
                 "pt.permit_name, p.date_issued, r.status " +
                 "FROM residents r " +
                 "JOIN permits p ON r.resident_id = p.resident_id " +
                 "JOIN permit_types pt ON p.permit_type = pt.permit_type_id";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("resident_name"),
                rs.getString("permit_name"),
                rs.getDate("date_issued"),
                rs.getString("status")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading permits: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private int getResidentIdByName(Connection conn, String fullName) throws SQLException {
        String sql = "SELECT resident_id FROM residents WHERE CONCAT(first_name, ' ', last_name) = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("resident_id");
            }
        }
        return -1;
    }

    private int getPermitTypeIdByName(Connection conn, String permitName) throws SQLException {
        String sql = "SELECT permit_type_id FROM permit_types WHERE permit_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, permitName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("permit_type_id");
            }
        }
        return -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        PermitBox = new javax.swing.JComboBox<>();
        IssueButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        ResidentNameBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setText("BARANGAY MANAGEMENT SYSTEM");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jTable1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Resident Name", "Permit Type", "Issue Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 460, 230));

        PermitBox.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        PermitBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PermitBoxActionPerformed(evt);
            }
        });
        jPanel1.add(PermitBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 150, 30));

        IssueButton.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        IssueButton.setText("Issue Permit");
        IssueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueButtonActionPerformed(evt);
            }
        });
        jPanel1.add(IssueButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 100, 30));

        DeleteButton.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DeleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 100, 30));

        BackButton.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 100, 30));

        ResidentNameBox.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        ResidentNameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResidentNameBoxActionPerformed(evt);
            }
        });
        jPanel1.add(ResidentNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IssueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueButtonActionPerformed
        String selectedResident = ResidentNameBox.getSelectedItem().toString();
        String selectedPermitType = PermitBox.getSelectedItem().toString();

        // Validate selection
        if (selectedResident.isEmpty() || selectedPermitType.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a resident and permit type.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDateTime.format(formatter);

        try (Connection conn = DatabaseConnection.getConnection()) {
            int residentId = getResidentIdByName(conn, selectedResident);
            int permitTypeId = getPermitTypeIdByName(conn, selectedPermitType);
            if (residentId == -1 || permitTypeId == -1) {
                JOptionPane.showMessageDialog(this, "Error: Resident or Permit Type not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String sql = "INSERT INTO permits (resident_id, permit_type, date_issued) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, residentId); // Resident ID
                pstmt.setInt(2, permitTypeId); // Permit Type ID
                pstmt.setString(3, formattedDate); // Current date

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Permit issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(this, "Error issuing permit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_IssueButtonActionPerformed

    private void PermitBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PermitBoxActionPerformed
        String selectedPermit = (String) PermitBox.getSelectedItem();
        System.out.println("Selected Permit: " + selectedPermit);
    }//GEN-LAST:event_PermitBoxActionPerformed

    private void ResidentNameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResidentNameBoxActionPerformed
        String selectedResident = (String) ResidentNameBox.getSelectedItem();
        System.out.println("Selected Resident: " + selectedResident);
    }//GEN-LAST:event_ResidentNameBoxActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        int selectedRow = jTable1.getSelectedRow();
    
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String residentName = (String) jTable1.getValueAt(selectedRow, 0);
        String permitName = (String) jTable1.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this permit?", 
                                                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            int residentId = getResidentIdByName(conn, residentName);
            int permitTypeId = getPermitTypeIdByName(conn, permitName);

            if (residentId == -1 || permitTypeId == -1) {
                JOptionPane.showMessageDialog(this, "Error: Resident or Permit Type not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "DELETE FROM permits WHERE resident_id = ? AND permit_type = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, residentId);
                pstmt.setInt(2, permitTypeId);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Permit deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadTableData(); // Refresh the table
                } else {
                    JOptionPane.showMessageDialog(this, "Error deleting permit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        this.dispose();
        MainDashboard maindashboard = new MainDashboard();
        maindashboard.setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PermitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PermitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PermitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PermitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PermitFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton IssueButton;
    private javax.swing.JComboBox<String> PermitBox;
    private javax.swing.JComboBox<String> ResidentNameBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
