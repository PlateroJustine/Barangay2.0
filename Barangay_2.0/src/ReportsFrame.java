import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.awt.Desktop;
import java.io.IOException;
import java.util.HashMap;

public class ReportsFrame extends javax.swing.JFrame {

    /**
     * Creates new form ReportsFrame
     */
    public ReportsFrame() {
        initComponents();
        setTitle("REPORTS");
        loadTableData();
        setLocationRelativeTo(null);
    }

    private void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear the table before adding new data

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barangay_db2", "root", "");
            String sql = "SELECT CONCAT(r.first_name, ' ', r.last_name) AS resident_name, " +
                         "pt.permit_name, p.date_issued " +
                         "FROM permits p " +
                         "JOIN residents r ON p.resident_id = r.resident_id " +
                         "JOIN permit_types pt ON p.permit_type = pt.permit_type_id";
                         
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("resident_name"),
                    rs.getString("permit_name"),
                    rs.getString("date_issued")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SearchReportField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        GenerateButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ResidentNameField = new javax.swing.JTextField();

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
                "Resident Name", "Permit Type", "Date Issued"
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 460, 210));

        SearchReportField.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        SearchReportField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchReportFieldActionPerformed(evt);
            }
        });
        jPanel1.add(SearchReportField, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 100, 30));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 0, 12)); // NOI18N
        jLabel1.setText("Resident Name:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 286, -1, -1));

        GenerateButton.setText("Generate Report");
        GenerateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateButtonActionPerformed(evt);
            }
        });
        jPanel1.add(GenerateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 120, 30));

        BackButton.setText("Back");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(BackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 120, 30));

        jLabel3.setText("Search Report:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 286, -1, -1));

        ResidentNameField.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        ResidentNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResidentNameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(ResidentNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 100, 30));

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

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        this.dispose();
        MainDashboard maindashboard = new MainDashboard();
        maindashboard.setVisible(true);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void GenerateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateButtonActionPerformed
        int selectedRow = jTable1.getSelectedRow(); // Get selected row index
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a permit from the table.");
        return;
    }

    String permitName = jTable1.getValueAt(selectedRow, 1).toString(); // Get permit name

    // Mapping permit names to permit_type_id
    HashMap<String, Integer> permitMap = new HashMap<>();
    permitMap.put("Barangay Clearance", 1);
    permitMap.put("Blotter or Complaint Record", 9);
    permitMap.put("Business Clearance", 5);
    permitMap.put("Certificate for Livelihood Assistance", 13);
    permitMap.put("Certificate for Lost Items", 12);
    permitMap.put("Certificate for No Pending Case", 10);
    permitMap.put("Certificate for PWD", 7);
    permitMap.put("Certificate for Senior Citizens", 8);
    permitMap.put("Certificate for Solo Parents", 6);
    permitMap.put("Certificate of Good Moral Character", 4);
    permitMap.put("Certificate of Indigency", 3);
    permitMap.put("Certificate of Ownership", 11);
    permitMap.put("Certificate of Residency", 2);
    permitMap.put("Endorsement for Events", 14);

    // Get permit_type_id from permit name
    Integer permitTypeId = permitMap.get(permitName);

    if (permitTypeId == null) {
        JOptionPane.showMessageDialog(this, "Invalid permit type.");
        return;
    }

    // File path where the document should be located
    String folderPath = "C:\\Users\\Kazu\\Documents\\Barangay\\";
    File permitFile = null;

    // Check for different possible file formats
    String[] extensions = {".pdf", ".png", ".webp", ".docx"};
    for (String ext : extensions) {
        File file = new File(folderPath + permitTypeId + ext);
        if (file.exists()) {
            permitFile = file;
            break;
        }
    }

    if (permitFile != null) {
        try {
            Desktop.getDesktop().open(permitFile); // Open the file
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error opening the file.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "No document available for this permit.");
    }
    }//GEN-LAST:event_GenerateButtonActionPerformed

    private void SearchReportFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchReportFieldActionPerformed
        String searchText = SearchReportField.getText().trim();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear the table before adding new data

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barangay_db2", "root", "");
            String sql = "SELECT CONCAT(r.first_name, ' ', r.last_name) AS resident_name, " +
                         "pt.permit_name, p.date_issued " +
                         "FROM permits p " +
                         "JOIN residents r ON p.resident_id = r.resident_id " +
                         "JOIN permit_types pt ON p.permit_type = pt.permit_type_id " +
                         "WHERE pt.permit_name LIKE ? OR pt.permit_name LIKE ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + searchText + "%"); // Matches partial words like 'Events'
            pst.setString(2, searchText); // Matches the exact term 'Endorsement for Events'

            ResultSet rs = pst.executeQuery();

            boolean hasResults = false; // Flag to check if data exists

            while (rs.next()) {
                hasResults = true;
                model.addRow(new Object[]{
                    rs.getString("resident_name"),
                    rs.getString("permit_name"),
                    rs.getString("date_issued")
                });
            }

            if (!hasResults) {
                JOptionPane.showMessageDialog(this, "No Permit Existing", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SearchReportFieldActionPerformed

    private void ResidentNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResidentNameFieldActionPerformed
        String searchText = ResidentNameField.getText().trim(); // Get input and remove extra spaces
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear table before inserting new data

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/barangay_db2", "root", "");

            String sql;
            PreparedStatement pst;

            if (searchText.isEmpty()) {
                // If the field is empty, show all records (refresh)
                sql = "SELECT CONCAT(r.first_name, ' ', r.last_name) AS resident_name, " +
                      "pt.permit_name, p.date_issued " +
                      "FROM permits p " +
                      "JOIN residents r ON p.resident_id = r.resident_id " +
                      "JOIN permit_types pt ON p.permit_type = pt.permit_type_id";
                pst = con.prepareStatement(sql);
            } else {
                // Search for resident name or permit type
                sql = "SELECT CONCAT(r.first_name, ' ', r.last_name) AS resident_name, " +
                      "pt.permit_name, p.date_issued " +
                      "FROM permits p " +
                      "JOIN residents r ON p.resident_id = r.resident_id " +
                      "JOIN permit_types pt ON p.permit_type = pt.permit_type_id " +
                      "WHERE CONCAT(r.first_name, ' ', r.last_name) LIKE ? " +
                      "OR pt.permit_name LIKE ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchText + "%"); // Partial match for name
                pst.setString(2, "%" + searchText + "%"); // Partial match for permit type
            }

            ResultSet rs = pst.executeQuery();
            boolean found = false;

            while (rs.next()) {
                found = true;
                model.addRow(new Object[]{
                    rs.getString("resident_name"),
                    rs.getString("permit_name"),
                    rs.getString("date_issued")
                });
            }

            // If no resident is found, show a pop-up message
            if (!found) {
                JOptionPane.showMessageDialog(this, "Resident does not exist", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ResidentNameField.setText(""); // Clear the text field if results are found
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ResidentNameFieldActionPerformed

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
            java.util.logging.Logger.getLogger(ReportsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton GenerateButton;
    private javax.swing.JTextField ResidentNameField;
    private javax.swing.JTextField SearchReportField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
