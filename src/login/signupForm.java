/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import config.dbConnector;
import config.passwordHasher;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author sheila mae albarando
 */
public class signupForm extends javax.swing.JFrame {

    /**
     * Creates new form signupForm
     */
    public signupForm() {
        initComponents();
    }
    
    public static String emails,usname;
    
    public boolean DuplicateCheck(){
        dbConnector dbc = new dbConnector();
        
        try{
            String query = "SELECT * FROM tbl_user  WHERE u_username = '" + username.getText() + "' AND u_email = '" + em.getText() + "'";
            ResultSet resultSet = dbc.getData(query);
            
            if(resultSet.next()){
                emails=resultSet.getString("u_email");
                System.out.println(""+emails);
                if(emails.equals(em.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used","Enter Another Email", JOptionPane.WARNING_MESSAGE);
                    em.setText("");
                }
                usname=resultSet.getString("u_username");
                if(usname.equals(username.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used","Enter Another Username", JOptionPane.WARNING_MESSAGE);
                    username.setText("");
                }
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
            return false;
        }
    }  
    
    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
    
    public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: ");
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
    
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
    try {
        // Read the image file
        File imageFile = new File(imagePath);
        BufferedImage image = ImageIO.read(imageFile);
            
        // Get the original width and height of the image
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
            
        // Calculate the new height based on the desired width and the aspect ratio
        int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
        return newHeight;
    } catch (IOException ex) {
        System.out.println("No image found!");
    }       
        return -1;
    }
    
    
    public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
        ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
    int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
    }

    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        fname = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        em = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        Register = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        remove = new javax.swing.JButton();
        select = new javax.swing.JButton();
        status = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        fname.setForeground(new java.awt.Color(0, 0, 204));
        fname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(fname);
        fname.setBounds(20, 70, 220, 30);

        lname.setForeground(new java.awt.Color(0, 0, 204));
        lname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(lname);
        lname.setBounds(20, 130, 220, 30);

        em.setForeground(new java.awt.Color(0, 0, 204));
        em.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        em.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        em.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emActionPerformed(evt);
            }
        });
        jPanel1.add(em);
        em.setBounds(20, 190, 220, 30);

        username.setForeground(new java.awt.Color(0, 0, 204));
        username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        username.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username);
        username.setBounds(260, 130, 220, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("First Name ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 100, 70, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Last Name ");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 160, 70, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Email       ");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 220, 70, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Status");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(260, 100, 70, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Password ");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(260, 220, 70, 30);

        cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cancel.setText("Cancel");
        cancel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel1.add(cancel);
        cancel.setBounds(300, 460, 180, 30);

        Register.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Register.setText("Register");
        Register.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });
        jPanel1.add(Register);
        Register.setBounds(300, 310, 180, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sign up");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 10, 460, 50);

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-eye-30.png"))); // NOI18N
        jPanel1.add(show);
        show.setBounds(448, 190, 30, 30);

        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hide-Pass.png"))); // NOI18N
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hideMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hideMouseReleased(evt);
            }
        });
        jPanel1.add(hide);
        hide.setBounds(450, 190, 30, 30);

        password.setForeground(new java.awt.Color(0, 0, 204));
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(password);
        password.setBounds(260, 190, 220, 30);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(null);
        jPanel3.add(image);
        image.setBounds(10, 10, 240, 200);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(20, 270, 260, 220);

        remove.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        remove.setText("Remove Image");
        jPanel1.add(remove);
        remove.setBounds(300, 410, 180, 30);

        select.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        select.setText("Select Image");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel1.add(select);
        select.setBounds(300, 360, 180, 30);

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        status.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(status);
        status.setBounds(260, 70, 220, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Username  ");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(260, 160, 70, 30);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(30, 350, 500, 520);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1.png"))); // NOI18N
        jPanel2.add(jLabel8);
        jLabel8.setBounds(-120, 10, 540, 590);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        // TODO add your handling code here:
        if(fname.getText().isEmpty() || lname.getText().isEmpty() || em.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill in all required Data.");
        } else if (password.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "Password should have at least 8 characters or Above.");
            password.setText("");
        }else if(DuplicateCheck()){    
            System.out.println("Duplicate Exists");
        } else {
            dbConnector dbc = new dbConnector();
    
            try {    
                String pass = passwordHasher.hashPassword(password.getText());
                if (dbc.insertData("INSERT INTO tbl_user (u_fname, u_lname, u_email, u_username, u_password, u_account, u_status, u_image) "
                    + "VALUES ('"+fname.getText()+"', '"+lname.getText()+"', '"+em.getText()+"', '"+username.getText()+"', '"
                    + pass+"', '"+status.getSelectedItem()+"', 'Pending', '"+destination+ "')")) {
            
                    JOptionPane.showMessageDialog(null, "Registration Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loginForm ad = new loginForm();
                    ad.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Connection Error", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("" + ex);
            }
        }
    }//GEN-LAST:event_RegisterActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        loginForm log = new loginForm();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void emActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/userImages/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();
                        
                        
                if(FileExistenceChecker(path) == 1){
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path="";
                }else{
                    image.setIcon(ResizeImage(path,null,image));
                    select.setEnabled(false);
                    remove.setEnabled(true);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_selectActionPerformed

    private void hideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMousePressed
        // TODO add your handling code here:
        show.setVisible(true);
        hide.setVisible(false);
        password.setEchoChar((char)0);
    }//GEN-LAST:event_hideMousePressed

    private void hideMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMouseReleased
        // TODO add your handling code here:
        show.setVisible(false);
        hide.setVisible(true);
        password.setEchoChar('*');
    }//GEN-LAST:event_hideMouseReleased

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
            java.util.logging.Logger.getLogger(signupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signupForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signupForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Register;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField em;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel hide;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lname;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton remove;
    private javax.swing.JButton select;
    private javax.swing.JLabel show;
    public javax.swing.JComboBox<String> status;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
