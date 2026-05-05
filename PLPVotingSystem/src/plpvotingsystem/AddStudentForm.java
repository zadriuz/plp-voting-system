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
    private void showCustomMessage(String title, String message, java.awt.Color themeColor) {
        javax.swing.JDialog msgDialog = new javax.swing.JDialog(this, title, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        msgDialog.setSize(400, 200);
        msgDialog.setLocationRelativeTo(this);
        msgDialog.setUndecorated(true);

        plpvotingsystem.RoundedPanel pnlContent = new plpvotingsystem.RoundedPanel();
        pnlContent.setBackground(java.awt.Color.WHITE);
        pnlContent.setBorder(javax.swing.BorderFactory.createLineBorder(themeColor, 3));
        
        pnlContent.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();

        javax.swing.JLabel lblMessage = new javax.swing.JLabel("<html><center style='width: 280px;'>" + message.replace("\n", "<br>") + "</center></html>");
        lblMessage.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.JPanel btnPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        
        plpvotingsystem.RoundedButton btnOk = new plpvotingsystem.RoundedButton();
        btnOk.setText("OK");
        btnOk.setBackground(themeColor);
        btnOk.setForeground(java.awt.Color.WHITE);
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.setPreferredSize(new java.awt.Dimension(120, 40));
        btnOk.setDialogButton(true); 
        btnOk.addActionListener(e -> msgDialog.dispose());

        btnPanel.add(btnOk);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(0, 0, 20, 0); 
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        pnlContent.add(lblMessage, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        pnlContent.add(btnPanel, gbc);

        msgDialog.add(pnlContent);

        msgDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent e) {
                btnOk.requestFocusInWindow();
            }
        });

        msgDialog.setVisible(true);
    }

    String currentStudentID = "";
    private javax.swing.JTextField txtCopyablePass;
    
    private void setupCopyablePasscode() {
        // 1. Hollow out the existing NetBeans label
        lblPasscodeDisplay.setLayout(new java.awt.BorderLayout());
        lblPasscodeDisplay.setText(""); 

        // THE GOLDILOCKS FIX: Exactly 35 pixels. 
        // Large enough to exist, small enough to keep the layout tight!
        lblPasscodeDisplay.setPreferredSize(new java.awt.Dimension(200, 35));
        lblPasscodeDisplay.setMinimumSize(new java.awt.Dimension(200, 35));

        // 2. Create the copyable text field 
        txtCopyablePass = new javax.swing.JTextField("----");
        txtCopyablePass.setEditable(false); 
        txtCopyablePass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        
        // Font bumped to 22 so it fits perfectly inside the 35px height without getting chopped
        txtCopyablePass.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 22)); 
        txtCopyablePass.setForeground(new java.awt.Color(55, 140, 59)); // PLP Green
        txtCopyablePass.setBackground(new java.awt.Color(245, 245, 245)); 
        txtCopyablePass.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        // 3. Keep the HTML trick for the title and hint!
        lblGeneratedPass.setText("<html><center>Generated Passcode<br><span style='font-size:11px; color:gray; font-weight:normal;'>(Highlight code to copy)</span></center></html>");

        // 4. Inject ONLY the text field into the hijacked label
        lblPasscodeDisplay.add(txtCopyablePass, java.awt.BorderLayout.CENTER);
        
        lblPasscodeDisplay.revalidate();
        lblPasscodeDisplay.repaint();
    }

    public AddStudentForm() {
        initComponents();
        setupCopyablePasscode();

        // THE FIX: Tell the window to re-wrap itself NOW that the big label is inside!
        this.pack();

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
                txtCopyablePass.setText(passcode);
            }
        } catch (java.sql.SQLException e) {
            showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
        }
    }

    public AddStudentForm(String studentID) {
        initComponents();
        setupCopyablePasscode();

        // THE FIX: Tell the window to re-wrap itself NOW that the big label is inside!
        this.pack();

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
        lblYear.setText("Year Level");

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
            showCustomMessage("Error", "Please fill in all fields.", new java.awt.Color(250, 192, 45));
            return;
        }

        if (!studentID.matches("\\d{2}-\\d{5}")) {
            showCustomMessage("Error", "Student ID invalid. Must be exactly 8 characters including the dash (e.g. 25-00722)", new java.awt.Color(250, 192, 45));
            
            
            return;
        }

        if (currentStudentID.isEmpty()) {
            try (
                    Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("INSERT INTO tblVoters (student_number, passcode, has_voted, course, year_level, section)VALUES(?, ?, ?, ?, ?, ?)"); PreparedStatement pst2 = conn.prepareStatement("SELECT student_number FROM tblVoters WHERE student_number = ?")) {

                pst2.setString(1, studentID);
                ResultSet rs = pst2.executeQuery();

                if (rs.next()) {
                    showCustomMessage("Duplicate Entry", "Registration Failed: This Student ID is already registered in the system.", new java.awt.Color(220, 53, 69));
                    return;
                }

                String newPasscode = generatePasscode();
                txtCopyablePass.setText(newPasscode);

                pst.setString(1, studentID);
                pst.setString(2, newPasscode);
                pst.setInt(3, 0);
                pst.setString(4, course);
                pst.setString(5, year);
                pst.setString(6, section);

                pst.executeUpdate();

                showCustomMessage("Success", "Successful enrollment!", new java.awt.Color(55, 140, 59));

                txtStudentID.setText("");
                txtCourse.setText("");
                txtYear.setText("");
                txtSection.setText("");
                // lblPasscodeDisplay.setText(""); -> to prevent losing passcode

            } catch (java.sql.SQLException e) {
                showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
                return;
            }
        } else {
            try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("UPDATE tblVoters SET course = ?, year_level = ?, section = ? WHERE student_number = ?")){
                pst.setString(1, txtCourse.getText());
                pst.setString(2, txtYear.getText());
                pst.setString(3, txtSection.getText());
                pst.setString(4, txtStudentID.getText());
                
                pst.executeUpdate();
                
                showCustomMessage("Success", "Successfully registered student!", new java.awt.Color(55, 140, 59));
                this.dispose();
            }catch (java.sql.SQLException e){
                showCustomMessage("DB Error", "Error: " + e.getMessage(), new java.awt.Color(220, 53, 69));
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
