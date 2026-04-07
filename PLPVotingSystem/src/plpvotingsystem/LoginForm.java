package plpvotingsystem;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import java.awt.Font;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();

        this.getRootPane().setDefaultButton(btnLogin);
        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);

        this.setSize(600, 800);
        this.setMinimumSize(new java.awt.Dimension(600, 800));
        this.setLocationRelativeTo(null);

        this.getContentPane().setBackground(new java.awt.Color(55, 140, 59));

        java.awt.Font txtFieldFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16);
        java.awt.Color plpGreen = new java.awt.Color(55, 140, 59);
        java.awt.Color defaultGray = java.awt.Color.LIGHT_GRAY;

        // --- FIX 7: Restore the Missing Highlight Colors ---
        java.awt.Color modernBlue = new java.awt.Color(0, 120, 215); // Standard modern OS blue

        // Fix the Student Number field
        txtStudentNumber.setSelectionColor(modernBlue);
        txtStudentNumber.setSelectedTextColor(java.awt.Color.WHITE);

        // Fix the Password field
        pwdPassCode.setSelectionColor(modernBlue);
        pwdPassCode.setSelectedTextColor(java.awt.Color.WHITE);

        txtStudentNumber.setFont(txtFieldFont);
        pwdPassCode.setFont(txtFieldFont);

        // --- FIX 1: Strip the Nimbus Skin off the text fields ---
        txtStudentNumber.setUI(new javax.swing.plaf.basic.BasicTextFieldUI());
        pwdPassCode.setUI(new javax.swing.plaf.basic.BasicPasswordFieldUI());

        // --- FIX 2: Restore the Hand Cursor ---
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // --- FIX 3: Setup Initial Placeholders ---
        txtStudentNumber.setText("Enter your student number");
        txtStudentNumber.setForeground(java.awt.Color.GRAY);

        pwdPassCode.setEchoChar((char) 0); // Turn off password dots temporarily
        pwdPassCode.setText("Enter your password");
        pwdPassCode.setForeground(java.awt.Color.GRAY);

        // --- FIX 4: The Dynamic Focus Listeners (Borders + Placeholders) ---
        txtStudentNumber.setBorder(new plpvotingsystem.RoundedBorder(15, defaultGray));
        pwdPassCode.setBorder(new plpvotingsystem.RoundedBorder(15, defaultGray));

        txtStudentNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStudentNumber.setBorder(new plpvotingsystem.RoundedBorder(15, plpGreen));
                // Erase placeholder if it's there
                if (txtStudentNumber.getText().equals("Enter your student number")) {
                    txtStudentNumber.setText("");
                    txtStudentNumber.setForeground(java.awt.Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStudentNumber.setBorder(new plpvotingsystem.RoundedBorder(15, defaultGray));
                // Put placeholder back if they left it blank
                if (txtStudentNumber.getText().isEmpty()) {
                    txtStudentNumber.setForeground(java.awt.Color.GRAY);
                    txtStudentNumber.setText("Enter your student number");
                }
            }
        });

        pwdPassCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwdPassCode.setBorder(new plpvotingsystem.RoundedBorder(15, plpGreen));
                // Erase placeholder and turn on password dots
                String pass = new String(pwdPassCode.getPassword());
                if (pass.equals("Enter your password")) {
                    pwdPassCode.setText("");
                    pwdPassCode.setForeground(java.awt.Color.BLACK);
                    pwdPassCode.setEchoChar('•'); // Turn dots back on
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                pwdPassCode.setBorder(new plpvotingsystem.RoundedBorder(15, defaultGray));
                // Put placeholder back and turn off dots if blank
                String pass = new String(pwdPassCode.getPassword());
                if (pass.isEmpty()) {
                    pwdPassCode.setEchoChar((char) 0);
                    pwdPassCode.setForeground(java.awt.Color.GRAY);
                    pwdPassCode.setText("Enter your password");
                }
            }
        });

        // --- FIX 4.5: The Eye Icon Hack ---
        // 1. Tell the password field it is now a container
        pwdPassCode.setLayout(new java.awt.BorderLayout());

        // 2. Teleport the eye icon inside the password field and push it to the right edge
        pwdPassCode.add(lblEyeIcon, java.awt.BorderLayout.EAST);

        // 3. Add a tiny invisible margin so the icon doesn't crash into your rounded corners
        lblEyeIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10));

        // 4. Change cursor to a hand when hovering over the eye
        lblEyeIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // 5. Pre-load your two images so Java doesn't have to search for them every time you click
        // NOTE: Replace these paths with your actual image paths!
        javax.swing.ImageIcon iconClosed = new javax.swing.ImageIcon("SystemIcons\\eyeClosedd.png");
        javax.swing.ImageIcon iconOpen = new javax.swing.ImageIcon("SystemIcons\\eyeOpenedd.png");

        // 6. The Click Logic
        lblEyeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String pass = new String(pwdPassCode.getPassword());

                // Do nothing if it's currently showing the placeholder
                if (pass.equals("Enter your password") || pass.isEmpty()) {
                    return;
                }

                // Toggle the password dots and swap the image
                if (pwdPassCode.getEchoChar() == '•') {
                    pwdPassCode.setEchoChar((char) 0);      // Show password
                    lblEyeIcon.setIcon(iconOpen);           // Change to Open Eye
                } else {
                    pwdPassCode.setEchoChar('•');           // Hide password
                    lblEyeIcon.setIcon(iconClosed);         // Change to Closed Eye
                }
            }
        });

        // --- FIX 5: Click Outside to Deselect ---
        // 1. Make the background panels capable of receiving focus
        roundedPanel1.setFocusable(true);
        this.getContentPane().setFocusable(true);

        // 2. Create a mouse listener that steals focus when clicked
        java.awt.event.MouseAdapter dropFocusAdapter = new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                roundedPanel1.requestFocusInWindow();
            }
        };

        // 3. Attach the listener to the white card and the green background
        roundedPanel1.addMouseListener(dropFocusAdapter);
        this.getContentPane().addMouseListener(dropFocusAdapter);

        // --- FIX 6: Prevent Selection on Startup ---
        // We have to wait for the UI to finish building before we can steal focus
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                roundedPanel1.requestFocusInWindow();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new plpvotingsystem.RoundedPanel();
        txtStudentNumber = new javax.swing.JTextField();
        pwdPassCode = new javax.swing.JPasswordField();
        lblPLPIcon = new javax.swing.JLabel();
        lblPLP = new javax.swing.JLabel();
        lblVotingSystem = new javax.swing.JLabel();
        lblStudentNo = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnLogin = new plpvotingsystem.RoundedButton();
        lblVotingSystem1 = new javax.swing.JLabel();
        lblEyeIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(37, 110, 41));
        setIconImages(null);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setPreferredSize(new java.awt.Dimension(500, 650));

        txtStudentNumber.setBackground(new java.awt.Color(255, 255, 255));
        txtStudentNumber.setPreferredSize(new java.awt.Dimension(350, 55));
        txtStudentNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentNumberActionPerformed(evt);
            }
        });

        pwdPassCode.setBackground(new java.awt.Color(255, 255, 255));
        pwdPassCode.setPreferredSize(new java.awt.Dimension(350, 55));

        lblPLPIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPLPIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/plpIcon150x150.jpg"))); // NOI18N
        lblPLPIcon.setPreferredSize(new java.awt.Dimension(100, 100));

        lblPLP.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblPLP.setForeground(new java.awt.Color(0, 0, 0));
        lblPLP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPLP.setText("Pamantasan ng Lungsod ng Pasig");

        lblVotingSystem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblVotingSystem.setForeground(new java.awt.Color(102, 102, 102));
        lblVotingSystem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVotingSystem.setText("Voting System");

        lblStudentNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStudentNo.setForeground(new java.awt.Color(0, 0, 0));
        lblStudentNo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblStudentNo.setText("Student Number");

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPassword.setText("Password");

        btnLogin.setBackground(new java.awt.Color(250, 192, 45));
        btnLogin.setForeground(new java.awt.Color(0, 0, 0));
        btnLogin.setText("Login");
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnLogin.setPreferredSize(new java.awt.Dimension(350, 55));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblVotingSystem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblVotingSystem1.setForeground(new java.awt.Color(102, 102, 102));
        lblVotingSystem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVotingSystem1.setText("Secure and transparent student voting");

        lblEyeIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEyeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plpvotingsystem/eyeClosedd.png"))); // NOI18N

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVotingSystem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblPLP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVotingSystem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                    .addComponent(txtStudentNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pwdPassCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addComponent(lblPLPIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                        .addComponent(lblEyeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblPLPIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPLP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVotingSystem)
                .addGap(18, 18, 18)
                .addComponent(lblStudentNo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStudentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pwdPassCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblVotingSystem1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEyeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        getContentPane().add(roundedPanel1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String studentNumber = txtStudentNumber.getText();
        String passCode = new String(pwdPassCode.getPassword());

        if (studentNumber.trim().isEmpty() || passCode.trim().isEmpty() || studentNumber.equals("Enter your student number") || passCode.equals("Enter your password")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // ... Keep your existing validation check (the isEmpty() and .equals() stuff) above this ...
        try (Connection conn = database.DBConnection.DBConnect()) {

            // --- CHECK 1: THE ADMIN BACKDOOR ---
            String adminSql = "SELECT * FROM tblAdmins WHERE username = ? AND BINARY password = ?";
            try (PreparedStatement adminPst = conn.prepareStatement(adminSql)) {
                adminPst.setString(1, studentNumber); // They typed their username into the Student ID field
                adminPst.setString(2, passCode);
                ResultSet adminRs = adminPst.executeQuery();

                if (adminRs.next()) {
                    // It's an Admin! Send them to the Admin Form and stop the code.
                    String userName = adminRs.getString("username");
                    this.dispose();
                    new plpvotingsystem.AdminDashboard(userName).setVisible(true); // Change this if your main admin menu is named differently
                    return;
                }
            }

            // --- CHECK 2: THE STUDENT LOGIN ---
            try (Connection conn1 = database.DBConnection.DBConnect(); PreparedStatement pst1 = conn1.prepareStatement("SELECT current_phase FROM tblElectionStatus WHERE status_id = 1")) {
                ResultSet rs = pst1.executeQuery();

                if (rs.next()) {
                    String currentPhase = rs.getString("current_phase").trim();

                    if (currentPhase.equalsIgnoreCase("CLOSED")) {
                        JOptionPane.showMessageDialog(this, "The election has officially concluded. Voting is now closed.");
                        return;
                    } else if (currentPhase.equalsIgnoreCase("SETUP")){
                        JOptionPane.showMessageDialog(this, "System under maintenance. Please try again later!");
                        return;
                    } else {
                        String studentSql = "SELECT * FROM tblVoters WHERE student_number = ? AND BINARY passcode = ?";
                        try (PreparedStatement studentPst = conn.prepareStatement(studentSql)) {
                            studentPst.setString(1, studentNumber);
                            studentPst.setString(2, passCode);
                            ResultSet studentRs = studentPst.executeQuery();

                            if (studentRs.next()) {
                                int has_voted = studentRs.getInt("has_voted");

                                if (has_voted == 1) {
                                    JOptionPane.showMessageDialog(this, "You have already voted!");
                                } else {
                                    this.dispose();
                                    new plpvotingsystem.StudentDashboard(studentNumber).setVisible(true);
                                }
                            } else {
                                // If it's not an admin AND not a student, kick them out.
                                JOptionPane.showMessageDialog(this, "Invalid Username/Student # or Password");
                            }
                        }
                    }
                }
            } catch (java.sql.SQLException e) {
                JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
                return;
            }

        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtStudentNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentNumberActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private plpvotingsystem.RoundedButton btnLogin;
    private javax.swing.JLabel lblEyeIcon;
    private javax.swing.JLabel lblPLP;
    private javax.swing.JLabel lblPLPIcon;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblStudentNo;
    private javax.swing.JLabel lblVotingSystem;
    private javax.swing.JLabel lblVotingSystem1;
    private javax.swing.JPasswordField pwdPassCode;
    private plpvotingsystem.RoundedPanel roundedPanel1;
    private javax.swing.JTextField txtStudentNumber;
    // End of variables declaration//GEN-END:variables
}
