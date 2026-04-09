/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PLP
 */
public class AddStudentForm extends javax.swing.JFrame {

    String currentStudentID = "";

    public AddStudentForm() {
        initComponents();

        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.getRootPane().setDefaultButton(btnGenerate);
        this.setLocationRelativeTo(null);
        
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));
    }

    private void loadExistingData() {
        try (Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM tblVoters WHERE student_number = ?")) {
            pst.setString(1, this.currentStudentID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String studentID = rs.getString("student_number");
                String course = rs.getString("course");
                String year = rs.getString("year_level");
                String section = rs.getString("section");
                String passcode = rs.getString("passcode");

                txtStudentID.setText(studentID);
                txtCourse.setText(course);
                txtYear.setText(year);
                txtSection.setText(section);
                lblPasscodeDisplay.setText(passcode);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }
    }

    public AddStudentForm(String studentID) {
        initComponents();

        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.currentStudentID = studentID;

        lblTitle.setText("Edit Voter Information");
        btnGenerate.setText("Update Voter and Register");
        txtStudentID.setEnabled(false);

        loadExistingData();
    }

    private String generatePasscode() {
        String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789@#$%^&*()!";
        String passCode = "";
        int charsLength = chars.length();
        for (int i = 0; i < 8; i++) {
            int charsIndexSelected = (int) (Math.random() * charsLength);
            char c = chars.charAt(charsIndexSelected);
            passCode += c;
        }
        return passCode;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStudentID = new javax.swing.JLabel();
        txtStudentID = new javax.swing.JTextField();
        btnGenerate = new plpvotingsystem.RoundedButton();
        lblPasscodeDisplay = new javax.swing.JLabel();
        txtCourse = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        txtSection = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblStudentID1 = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        lblSection = new javax.swing.JLabel();
        lblGeneratedPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register Voter");
        setResizable(false);

        lblStudentID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStudentID.setText("Student ID");

        txtStudentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentIDActionPerformed(evt);
            }
        });

        btnGenerate.setBackground(new java.awt.Color(0, 255, 102));
        btnGenerate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGenerate.setForeground(new java.awt.Color(0, 0, 0));
        btnGenerate.setText("Generate Passcode & Register");
        btnGenerate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        lblPasscodeDisplay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPasscodeDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPasscodeDisplay.setText("----");
        lblPasscodeDisplay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCourseActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Register Voter Information");

        lblStudentID1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblStudentID1.setText("Course");

        lblYear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblYear.setText("Year");

        lblSection.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSection.setText("Section");

        lblGeneratedPass.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblGeneratedPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGeneratedPass.setText("Generated Passcode");
        lblGeneratedPass.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(txtSection)
                    .addComponent(txtYear, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCourse)
                    .addComponent(txtStudentID)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStudentID1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSection, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPasscodeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGeneratedPass, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(lblStudentID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStudentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStudentID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGeneratedPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasscodeDisplay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnGenerate)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        String studentID = txtStudentID.getText();
        String course = txtCourse.getText();
        String year = txtYear.getText();
        String section = txtSection.getText();

        if (studentID.isEmpty() || course.isEmpty() || year.isEmpty() || section.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (!studentID.matches("\\d{2}-\\d{5}")) {
            JOptionPane.showMessageDialog(this, "Student ID invalid. Must be exactly 8 characters including the dash (e.g. 25-00722)");
            return;
        }

        if (currentStudentID.isEmpty()) {
            try (
                    Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("INSERT INTO tblVoters (student_number, passcode, has_voted, course, year_level, section)VALUES(?, ?, ?, ?, ?, ?)"); PreparedStatement pst2 = conn.prepareStatement("SELECT student_number FROM tblVoters WHERE student_number = ?")) {

                pst2.setString(1, studentID);
                ResultSet rs = pst2.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Registration Failed: This Student ID is already registered in the system.", "Duplicate ID", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String newPasscode = generatePasscode();
                lblPasscodeDisplay.setText(newPasscode);

                pst.setString(1, studentID);
                pst.setString(2, newPasscode);
                pst.setInt(3, 0);
                pst.setString(4, course);
                pst.setString(5, year);
                pst.setString(6, section);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Successful enrollment");

                txtStudentID.setText("");
                txtCourse.setText("");
                txtYear.setText("");
                txtSection.setText("");
                // lblPasscodeDisplay.setText(""); -> to prevent losing passcode

            } catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
                return;
            }
        } else {
            try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("UPDATE tblVoters SET course = ?, year_level = ?, section = ? WHERE student_number = ?")){
                pst.setString(1, txtCourse.getText());
                pst.setString(2, txtYear.getText());
                pst.setString(3, txtSection.getText());
                pst.setString(4, txtStudentID.getText());
                
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Student Successfully Updated!");
                this.dispose();
            }catch (java.sql.SQLException e){
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void txtStudentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIDActionPerformed

    private void txtCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCourseActionPerformed

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
            java.util.logging.Logger.getLogger(AddStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStudentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddStudentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.JLabel lblGeneratedPass;
    private javax.swing.JLabel lblPasscodeDisplay;
    private javax.swing.JLabel lblSection;
    private javax.swing.JLabel lblStudentID;
    private javax.swing.JLabel lblStudentID1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTextField txtCourse;
    private javax.swing.JTextField txtSection;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables
}
