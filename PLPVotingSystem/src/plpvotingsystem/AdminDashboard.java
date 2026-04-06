/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

/**
 *
 * @author PLP
 */
public class AdminDashboard extends javax.swing.JFrame {
    
    String currentAdmin = "";
    
    public AdminDashboard(String adminName) {
        initComponents();
        
        this.currentAdmin = adminName;
        
        this.setLocationRelativeTo(null);
        java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");
        this.setIconImage(plpIcon);
        
        this.getContentPane().setBackground(new java.awt.Color(245,245,245));
        
        lblGreeting.setText("Hello, " + adminName);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalHeader2 = new plpvotingsystem.GlobalHeader();
        jPanel1 = new javax.swing.JPanel();
        btnRegisCandidate = new plpvotingsystem.RoundedButton();
        btnRegisStudent = new plpvotingsystem.RoundedButton();
        jLabel2 = new javax.swing.JLabel();
        btnConcludeElect = new plpvotingsystem.RoundedButton();
        lblGreeting = new javax.swing.JLabel();
        btnRegisParty = new plpvotingsystem.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setBackground(new java.awt.Color(245, 245, 245));
        setResizable(false);

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 60, 20, 60));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 80, 1, 80));

        btnRegisCandidate.setBackground(new java.awt.Color(0, 255, 102));
        btnRegisCandidate.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnRegisCandidate.setForeground(new java.awt.Color(0, 0, 0));
        btnRegisCandidate.setText("Manage Candidate Information");
        btnRegisCandidate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegisCandidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisCandidateActionPerformed(evt);
            }
        });

        btnRegisStudent.setBackground(new java.awt.Color(250, 192, 45));
        btnRegisStudent.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnRegisStudent.setForeground(new java.awt.Color(0, 0, 0));
        btnRegisStudent.setText("Manage Voter Information");
        btnRegisStudent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegisStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisStudentActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Admin Only Actions");

        btnConcludeElect.setBackground(new java.awt.Color(255, 102, 102));
        btnConcludeElect.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnConcludeElect.setForeground(new java.awt.Color(0, 0, 0));
        btnConcludeElect.setText("Conclude Elections");
        btnConcludeElect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConcludeElect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcludeElectActionPerformed(evt);
            }
        });

        lblGreeting.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGreeting.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGreeting.setText("Hello");

        btnRegisParty.setBackground(new java.awt.Color(0, 255, 102));
        btnRegisParty.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        btnRegisParty.setForeground(new java.awt.Color(0, 0, 0));
        btnRegisParty.setText("Manage Partylist Information");
        btnRegisParty.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegisParty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisPartyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblGreeting, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 722, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRegisCandidate, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRegisStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRegisParty, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnConcludeElect, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGreeting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegisCandidate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegisStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegisParty, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConcludeElect, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConcludeElectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcludeElectActionPerformed
        this.dispose();
        new plpvotingsystem.ElectionResultsForm().setVisible(true);
    }//GEN-LAST:event_btnConcludeElectActionPerformed

    private void btnRegisCandidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisCandidateActionPerformed
        this.dispose();
        new plpvotingsystem.ManageCandidatesForm(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnRegisCandidateActionPerformed

    private void btnRegisStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisStudentActionPerformed
        this.dispose();
        new plpvotingsystem.ManageStudentsForm(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnRegisStudentActionPerformed

    private void btnRegisPartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisPartyActionPerformed
        this.dispose();
        new plpvotingsystem.ManagePartylistsForm(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnRegisPartyActionPerformed

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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConcludeElect;
    private javax.swing.JButton btnRegisCandidate;
    private javax.swing.JButton btnRegisParty;
    private javax.swing.JButton btnRegisStudent;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGreeting;
    // End of variables declaration//GEN-END:variables
}
