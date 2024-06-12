package login;

import admin.adminDashboard;
import config.dbConnector;
import static config.passwordHasher.hashPassword;
import config.session;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import user.userDashboard;




public class loginForm extends javax.swing.JFrame {


    public loginForm() {
        initComponents();
    }
    
    static String status;
    static String type;
    
    public static boolean loginAcc(String username, String pass){
        
        dbConnector connector = new dbConnector();
        
        try{
            String hashedPassword = hashPassword(pass);
            String sql = "SELECT * FROM tbl_user WHERE u_username = '" + username + "' AND u_password = '" + hashedPassword + "' ";
            ResultSet rs= connector.getData(sql);
            if(rs.next()){
                    status = rs.getString("u_status");
                    type = rs.getString("u_account");
                    session sess =  session.getInstance();
                    sess.setUid(rs.getInt("u_id"));
                    sess.setFname(rs.getString("u_fname"));
                    sess.setLname(rs.getString("u_lname"));
                    sess.setEmail(rs.getString("u_email"));
                    sess.setUsername(rs.getString("u_username"));
                    sess.setType(rs.getString("u_account"));
                    sess.setStatus(rs.getString("u_status"));
                    return true;
                }else{
                    return false;

            }
        }catch(SQLException | NoSuchAlgorithmException ex){
            return false;
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        showPass = new javax.swing.JLabel();
        hidePass = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        Exit = new javax.swing.JButton();
        Login = new javax.swing.JButton();
        CreateNewAccount = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(204, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(null);

        username.setForeground(new java.awt.Color(0, 0, 204));
        username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        username.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel4.add(username);
        username.setBounds(50, 80, 290, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Username");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(50, 110, 100, 20);

        showPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-eye-30.png"))); // NOI18N
        jPanel4.add(showPass);
        showPass.setBounds(310, 150, 30, 30);

        hidePass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hide-Pass.png"))); // NOI18N
        hidePass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hidePassMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hidePassMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hidePassMouseReleased(evt);
            }
        });
        jPanel4.add(hidePass);
        hidePass.setBounds(310, 150, 30, 30);

        password.setForeground(new java.awt.Color(0, 0, 204));
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel4.add(password);
        password.setBounds(50, 150, 290, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Password");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(50, 180, 100, 20);

        Exit.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Exit.setText("Exit");
        Exit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jPanel4.add(Exit);
        Exit.setBounds(50, 230, 70, 30);

        Login.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        Login.setText("Login");
        Login.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        jPanel4.add(Login);
        Login.setBounds(270, 230, 70, 30);

        CreateNewAccount.setBackground(new java.awt.Color(0, 0, 204));
        CreateNewAccount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CreateNewAccount.setForeground(new java.awt.Color(255, 255, 0));
        CreateNewAccount.setText("Create New Account ?");
        CreateNewAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreateNewAccountMouseClicked(evt);
            }
        });
        jPanel4.add(CreateNewAccount);
        CreateNewAccount.setBounds(50, 270, 160, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LOGIN");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(50, 20, 290, 50);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(40, 290, 380, 310);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(-160, 0, 610, 590);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CreateNewAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateNewAccountMouseClicked
        // TODO add your handling code here:
        signupForm sign = new signupForm();
        sign.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CreateNewAccountMouseClicked

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        
        if(loginAcc(username.getText(),password.getText())){
            if(!status.equals("Active")){
                JOptionPane.showMessageDialog(null,"In-Active Account, Contact the Manager","Warning", JOptionPane.WARNING_MESSAGE);
            }else{
                if(type.equals("Admin")){
                    JOptionPane.showMessageDialog(null,"Admin Login Success!");
                    adminDashboard ad = new adminDashboard();
                    ad.setVisible(true);
                    this.dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"User Login Success!");
                    userDashboard ad = new userDashboard();
                    ad.setVisible(true);
                    this.dispose();
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Invalid Account","Login Active Account", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_LoginActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Select an option...",
            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_ExitActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void hidePassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePassMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_hidePassMouseClicked

    private void hidePassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePassMousePressed
        // TODO add your handling code here:
        showPass.setVisible(true);
        hidePass.setVisible(false);
        password.setEchoChar((char)0);
    }//GEN-LAST:event_hidePassMousePressed

    private void hidePassMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hidePassMouseReleased
        // TODO add your handling code here:
        showPass.setVisible(false);
        hidePass.setVisible(true);
        password.setEchoChar('*');
    }//GEN-LAST:event_hidePassMouseReleased

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
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CreateNewAccount;
    private javax.swing.JButton Exit;
    private javax.swing.JButton Login;
    private javax.swing.JLabel hidePass;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel showPass;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
