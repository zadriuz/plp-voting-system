/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;

public class ManageStudentsForm extends javax.swing.JFrame {

    java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");

    String currentAdmin = "";

    public ManageStudentsForm(String adminName) {
        initComponents();

        this.currentAdmin = adminName;
        this.setLocationRelativeTo(null);
        this.setIconImage(plpIcon);

        this.getContentPane().setBackground(new java.awt.Color(245, 245, 245));
        loadStudentTable();
    }

    private void loadStudentTable() {
        javax.swing.table.DefaultTableModel model = (DefaultTableModel) tblStudentData.getModel();
        model.setRowCount(0);

        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT student_number, passcode, has_voted, course, year_level, section FROM tblVoters")) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String studentNo = rs.getString("student_number");
                String passCode = rs.getString("passcode");
                int hasVoted = rs.getInt("has_voted");
                String course = rs.getString("course");
                String yearLevel = rs.getString("year_level");
                String section = rs.getString("section");

                Object[] studentData = new Object[6];

                studentData[0] = studentNo;
                studentData[1] = passCode;
                studentData[2] = hasVoted;
                studentData[3] = course;
                studentData[4] = yearLevel;
                studentData[5] = section;

                model.addRow(studentData);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalHeader2 = new plpvotingsystem.GlobalHeader();
        jLabel2 = new javax.swing.JLabel();
        btnAddStudent = new plpvotingsystem.RoundedButton();
        btnEditStudent = new plpvotingsystem.RoundedButton();
        btnDeleteStudent = new plpvotingsystem.RoundedButton();
        btnBack2Dashboard = new plpvotingsystem.RoundedButton();
        btnRefresh = new plpvotingsystem.RoundedButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudentData = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        lblFilterBy = new javax.swing.JLabel();
        cmbFilterBy = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Voters Information");
        setBackground(new java.awt.Color(245, 245, 245));
        setResizable(false);

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 60, 20, 60));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actions");

        btnAddStudent.setBackground(new java.awt.Color(0, 255, 102));
        btnAddStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddStudent.setForeground(new java.awt.Color(0, 0, 0));
        btnAddStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/addUser.png"))); // NOI18N
        btnAddStudent.setText("Register Voter");
        btnAddStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddStudent.setIconTextGap(10);
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        btnEditStudent.setBackground(new java.awt.Color(250, 192, 45));
        btnEditStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditStudent.setForeground(new java.awt.Color(0, 0, 0));
        btnEditStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/editUser.png"))); // NOI18N
        btnEditStudent.setText("Edit Voter");
        btnEditStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditStudent.setIconTextGap(10);
        btnEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStudentActionPerformed(evt);
            }
        });

        btnDeleteStudent.setBackground(new java.awt.Color(255, 102, 102));
        btnDeleteStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteStudent.setForeground(new java.awt.Color(0, 0, 0));
        btnDeleteStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/removeUser.png"))); // NOI18N
        btnDeleteStudent.setText("Remove Voter");
        btnDeleteStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteStudent.setIconTextGap(10);
        btnDeleteStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStudentActionPerformed(evt);
            }
        });

        btnBack2Dashboard.setBackground(new java.awt.Color(204, 204, 204));
        btnBack2Dashboard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack2Dashboard.setForeground(new java.awt.Color(0, 0, 0));
        btnBack2Dashboard.setText("TP to Admin Dashboard");
        btnBack2Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack2Dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2DashboardActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(204, 204, 204));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(0, 0, 0));
        btnRefresh.setText("Refresh Table");
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        tblStudentData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Passcode", "Has Voted", "Course", "Year", "Section"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblStudentData);

        txtSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch.setForeground(new java.awt.Color(0, 0, 0));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(0, 0, 0));
        lblSearch.setText("Search:");

        lblFilterBy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFilterBy.setForeground(new java.awt.Color(0, 0, 0));
        lblFilterBy.setText("Filter By:");

        cmbFilterBy.setBackground(new java.awt.Color(255, 255, 255));
        cmbFilterBy.setForeground(new java.awt.Color(0, 0, 0));
        cmbFilterBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Course", "Year" }));
        cmbFilterBy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbFilterBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterByActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lblFilterBy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbFilterBy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack2Dashboard))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch)
                    .addComponent(lblFilterBy)
                    .addComponent(cmbFilterBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(btnAddStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(btnBack2Dashboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefresh))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentActionPerformed
        // 1. Trigger the Chameleon Form in "Create" mode (using the blank constructor)
        plpvotingsystem.AddStudentForm addForm = new plpvotingsystem.AddStudentForm();

        // 2. Attach a spy to wait for the form to close
        addForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // 3. Refresh the grid the second they finish adding the candidate
                loadStudentTable();
            }
        });

        // 4. Show the form to the admin
        addForm.setVisible(true);
    }//GEN-LAST:event_btnAddStudentActionPerformed

    private void btnEditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStudentActionPerformed
        int selectedRow = tblStudentData.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first to edit by clicking on their cell.", "Edit Candidate", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            int modelRow = tblStudentData.convertRowIndexToModel(selectedRow);
            String selectedID = tblStudentData.getModel().getValueAt(modelRow, 0).toString();

            // 1. Create the form but don't show it yet
            plpvotingsystem.AddStudentForm editForm = new plpvotingsystem.AddStudentForm(selectedID);
            editForm.setLocationRelativeTo(null);
            editForm.setIconImage(plpIcon);

            // 2. Attach a spy to the form that waits for it to die
            editForm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // 3. ONLY refresh the table once the popup is officially closed!
                    loadStudentTable();
                }
            });

            // 4. Now show the form
            editForm.setVisible(true);
        }
    }//GEN-LAST:event_btnEditStudentActionPerformed

    private void btnDeleteStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStudentActionPerformed
        // 1. Get ALL selected rows
        int[] selectedRows = tblStudentData.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one row to delete.");
            return;
        }

        // 2. Ask for confirmation
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the " + selectedRows.length + " selected record(s)?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("DELETE FROM tblVoters WHERE student_number = ?")) {

                // 3. Loop through every selected row
                for (int i = 0; i < selectedRows.length; i++) {
                    // THE MAGIC TRANSLATOR: Convert visual row to data row!
                    int modelRow = tblStudentData.convertRowIndexToModel(selectedRows[i]);

                    // Grab the Student ID from Column 0 of the actual model
                    String studentIdToDelete = tblStudentData.getModel().getValueAt(modelRow, 0).toString();

                    // Add to the batch
                    pst.setString(1, studentIdToDelete);
                    pst.addBatch();
                }

                // 4. Execute the mass execution!
                pst.executeBatch();
                JOptionPane.showMessageDialog(this, "Successfully deleted " + selectedRows.length + " record(s).");

                // 5. Refresh your table here! (Call whatever method you use to load the table)
                // loadStudentTable(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeleteStudentActionPerformed

    private void btnBack2DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2DashboardActionPerformed
        this.dispose();
        new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnBack2DashboardActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadStudentTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        String searchText = txtSearch.getText();
        String filterBy = cmbFilterBy.getSelectedItem().toString();

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblStudentData.getModel();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> trs = new javax.swing.table.TableRowSorter<>(model);
        tblStudentData.setRowSorter(trs);

        if (searchText.trim().length() == 0) {
            trs.setRowFilter(null); // Show everything if search is empty
        } else {
            // Use the same direct targeting you used in Candidates!
            if (filterBy.equals("Course")) {
                trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + searchText, 3)); // Column 3
            } else if (filterBy.equals("Year")) {
                trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + searchText, 4)); // Column 4
            } else {
                // "None" selected - search everything!
                trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + searchText));
            }
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cmbFilterByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterByActionPerformed
        txtSearchKeyReleased(null);
    }//GEN-LAST:event_cmbFilterByActionPerformed

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
            java.util.logging.Logger.getLogger(ManageStudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudentsForm("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnBack2Dashboard;
    private javax.swing.JButton btnDeleteStudent;
    private javax.swing.JButton btnEditStudent;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cmbFilterBy;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFilterBy;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblStudentData;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
