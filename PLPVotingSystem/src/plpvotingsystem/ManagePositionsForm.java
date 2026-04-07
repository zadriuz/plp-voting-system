/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package plpvotingsystem;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ManagePositionsForm extends javax.swing.JFrame {

    String currentAdmin = "";

    java.awt.Image plpIcon = java.awt.Toolkit.getDefaultToolkit().getImage("SystemIcons\\plpIcon1000.png");

    public ManagePositionsForm(String adminName) {
        initComponents();
        this.currentAdmin = adminName;
        this.setLocationRelativeTo(null);
        
        this.setIconImage(plpIcon);
        
        loadPositionsTable();
    }

    private void loadPositionsTable(){
        javax.swing.table.DefaultTableModel model = (DefaultTableModel) tblPositionsData.getModel();
        model.setRowCount(0);
        
        try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT * FROM tblPositions ORDER BY position_id ASC")){
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                int positionID = rs.getInt("position_id");
                String position = rs.getString("position_name");
                int maxPos = rs.getInt("max_votes");
                
                Object[] posInformation = new Object[3];
                
                posInformation[0] = positionID;
                posInformation[1] = position;
                posInformation[2] = maxPos;
                
                model.addRow(posInformation);
            }
        }catch(java.sql.SQLException e){
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
            return;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalHeader2 = new plpvotingsystem.GlobalHeader();
        jLabel2 = new javax.swing.JLabel();
        btnAddPosition = new plpvotingsystem.RoundedButton();
        btnEditPosition = new plpvotingsystem.RoundedButton();
        btnRemovePosition = new plpvotingsystem.RoundedButton();
        btnBack2Dashboard = new plpvotingsystem.RoundedButton();
        btnRefresh = new plpvotingsystem.RoundedButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPositionsData = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 245, 245));

        globalHeader2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 60, 20, 60));
        globalHeader2.setPreferredSize(new java.awt.Dimension(1000, 110));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actions");

        btnAddPosition.setBackground(new java.awt.Color(0, 255, 102));
        btnAddPosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddPosition.setForeground(new java.awt.Color(0, 0, 0));
        btnAddPosition.setText("Register Position");
        btnAddPosition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPositionActionPerformed(evt);
            }
        });

        btnEditPosition.setBackground(new java.awt.Color(250, 192, 45));
        btnEditPosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditPosition.setForeground(new java.awt.Color(0, 0, 0));
        btnEditPosition.setText("Edit Position");
        btnEditPosition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPositionActionPerformed(evt);
            }
        });

        btnRemovePosition.setBackground(new java.awt.Color(255, 102, 102));
        btnRemovePosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemovePosition.setForeground(new java.awt.Color(0, 0, 0));
        btnRemovePosition.setText("Remove Position");
        btnRemovePosition.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemovePosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemovePositionActionPerformed(evt);
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

        tblPositionsData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Position ID", "Position Name", "Max Positions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPositionsData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalHeader2, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemovePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack2Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalHeader2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddPosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditPosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemovePosition)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btnBack2Dashboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPositionActionPerformed
        plpvotingsystem.AddPositionForm addForm = new plpvotingsystem.AddPositionForm();

        addForm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // 3. Refresh the grid the second they finish adding the candidate
                loadPositionsTable();
            }
        });

        addForm.setVisible(true);
    }//GEN-LAST:event_btnAddPositionActionPerformed

    private void btnEditPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPositionActionPerformed

        int selectedRow = tblPositionsData.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first to edit by clicking on their cell.", "Edit Candidate", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            int posID = Integer.parseInt(tblPositionsData.getValueAt(selectedRow, 0).toString());
            String posName = tblPositionsData.getValueAt(selectedRow, 1).toString();
            int maxPos = Integer.parseInt(tblPositionsData.getValueAt(selectedRow, 2).toString());

            // 1. Create the form but don't show it yet
            plpvotingsystem.AddPositionForm editForm = new plpvotingsystem.AddPositionForm(posID, posName, maxPos);
            editForm.setLocationRelativeTo(null);
            editForm.setIconImage(plpIcon);

            // 2. Attach a spy to the form that waits for it to die
            editForm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // 3. ONLY refresh the table once the popup is officially closed!
                    loadPositionsTable();
                }
            });

            // 4. Now show the form
            editForm.setVisible(true);
        }
    }//GEN-LAST:event_btnEditPositionActionPerformed

    private void btnRemovePositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemovePositionActionPerformed
        int selectedRow = tblPositionsData.getSelectedRow();
        
        if(selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Please select a position to remove", "Select a position", JOptionPane.ERROR_MESSAGE);
            return;
        } else{
            String selectedID = tblPositionsData.getValueAt(selectedRow, 0).toString();
            int selectID = Integer.parseInt(selectedID);
            
            int adminChoice = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this position?", "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (adminChoice == JOptionPane.YES_OPTION){
                try(Connection conn = database.DBConnection.DBConnect(); PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM tblCandidates WHERE position_id = ?")){
                    pst.setInt(1, selectID);
                    ResultSet rs = pst.executeQuery();
                    
                    while(rs.next()){  
                        int count = rs.getInt("COUNT(*)");
                        
                        if(count > 0){
                            JOptionPane.showMessageDialog(this, "Cannot remove a position when candidates are currently running for it", "Remove Position Disabled", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else{
                            int adminChoice2 = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this position?", "Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            
                            if(adminChoice2 == JOptionPane.YES_OPTION){
                                try(Connection conn2 = database.DBConnection.DBConnect(); PreparedStatement pst2 = conn2.prepareStatement("DELETE FROM tblPositions WHERE position_id = ?")){
                                    pst2.setInt(1, selectID);
                                    pst2.executeUpdate();
                                    
                                    JOptionPane.showMessageDialog(this, "Successfully removed position");
                                    
                                    loadPositionsTable();
                                    
                                }catch(java.sql.SQLException e){
                                    JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
                                    return;
                                }
                            }
                        }
                    }
                }catch(java.sql.SQLException e){
                    JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
                    return;
                }
            }
        }
    }//GEN-LAST:event_btnRemovePositionActionPerformed

    private void btnBack2DashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2DashboardActionPerformed
        this.dispose();
        new plpvotingsystem.AdminDashboard(this.currentAdmin).setVisible(true);
    }//GEN-LAST:event_btnBack2DashboardActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadPositionsTable();
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
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePositionsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagePositionsForm("TestAdmin").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPosition;
    private javax.swing.JButton btnBack2Dashboard;
    private javax.swing.JButton btnEditPosition;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemovePosition;
    private plpvotingsystem.GlobalHeader globalHeader2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPositionsData;
    // End of variables declaration//GEN-END:variables
}
