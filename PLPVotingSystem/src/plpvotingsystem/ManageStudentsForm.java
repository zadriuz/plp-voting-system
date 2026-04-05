/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageStudentsForm extends javax.swing.JFrame {

    java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");

    String currentAdmin = "";
    public ManageStudentsForm(String adminName) {
        initComponents();
        
        this.currentAdmin = adminName;
        this.setLocationRelativeTo(null);
        this.setIconImage(plpIcon);
        
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Voters Information");
        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(1018, 437));
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
        btnAddStudent.setText("Register Student");
        btnAddStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        btnEditStudent.setBackground(new java.awt.Color(250, 192, 45));
        btnEditStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditStudent.setForeground(new java.awt.Color(0, 0, 0));
        btnEditStudent.setText("Edit Student");
        btnEditStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStudentActionPerformed(evt);
            }
        });

        btnDeleteStudent.setBackground(new java.awt.Color(255, 102, 102));
        btnDeleteStudent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteStudent.setForeground(new java.awt.Color(0, 0, 0));
        btnDeleteStudent.setText("Remove Student");
        btnDeleteStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack2Dashboard))
                .addGap(14, 14, 14))
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
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
        plpvotingsystem.AdminGeneratorForm addForm = new plpvotingsystem.AdminGeneratorForm();

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
            JOptionPane.showMessageDialog(this, "Select a student first to edit by clicking on their cell.", "Edit Candidate", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            String selectedID = tblStudentData.getValueAt(selectedRow, 0).toString();

            // 1. Create the form but don't show it yet
            plpvotingsystem.AdminGeneratorForm editForm = new plpvotingsystem.AdminGeneratorForm(selectedID);
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
        int selectedRow = tblStudentData.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first to delete by clicking on their cell.", "Delete Student", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            String data = tblStudentData.getValueAt(selectedRow, 0).toString();

            int adminChoice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (adminChoice == JOptionPane.YES_OPTION) {
                try (
                        Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("DELETE FROM tblVoters WHERE student_number = ?")) {
                    pst.setString(1, data);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Successfully deleted student.");

                    loadStudentTable();
                } catch (java.sql.SQLException e) {
                    JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
                }
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
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStudentData;
    // End of variables declaration//GEN-END:variables
}
