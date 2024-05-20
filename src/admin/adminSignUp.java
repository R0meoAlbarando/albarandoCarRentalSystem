/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.dbConnector;
import config.passwordHasher;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author sheila mae albarando
 */
public class adminSignUp extends javax.swing.JFrame {

    /**
     * Creates new form carOwnerRegistrationCar
     */
    public adminSignUp() {
        initComponents();
        dt();
        times();
    }
    
    public void dt(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy");
        
        String dd = sdf.format(d);
        date.setText(dd);
    }
    
    Timer t;
    SimpleDateFormat st;
    public void times(){
        
        
        t = new Timer(0, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //thow new UnsupportedOperationException("Not supported yet");
            Date dt = new Date();
            st = new SimpleDateFormat("hh:mm:ss a");
            String tt =st.format(dt);
            time.setText(tt);
            }
        });
        t.start();
    }
    
    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
    
    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/image", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
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
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
    }
    
    public static String email,usname;
    public boolean duplicateCheck(){
        
        dbConnector dbc = new dbConnector();
        
        try{
            String sql = "SELECT * FROM tbl_user WHERE u_username = '"+username.getText()+"'OR u_email = '"+em.getText()+"'";
            ResultSet rs = dbc.getData(sql);
            
            if(rs.next()){
                email = rs.getString("u_email");
                if(email.equals(em.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used.");
                    em.setText("");
                }
                usname = rs.getString("u_username");
                if(usname.equals(username.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used.");
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
    
    public boolean updateCheck(){
        
        dbConnector dbc = new dbConnector();
        
        try{
            String sql = "SELECT * FROM tbl_user WHERE (u_username = '"+username.getText()+"'OR u_email = '"+em.getText()+"')AND u_id!='"+uid.getText()+"'";
            ResultSet rs = dbc.getData(sql);
            
            if(rs.next()){
                email = rs.getString("u_email");
                if(email.equals(em.getText())){
                    JOptionPane.showMessageDialog(null, "Email is Already Used.");
                    em.setText("");
                }
                usname = rs.getString("u_username");
                if(usname.equals(username.getText())){
                    JOptionPane.showMessageDialog(null, "Username is Already Used.");
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
    
    private void clearFields() {
        // Clear all the text fields and reset selected items to default
        fname.setText("");
        lname.setText("");
        em.setText("");
        username.setText("");
        password.setText("");
        // Clear any selection in userType and status dropdowns
        userType.setSelectedIndex(0);
        status.setSelectedIndex(0);
    }
    
    private void refreshData() {
        dbConnector dbc = new dbConnector();
        ResultSet resultSet = dbc.refreshData();
    
        // Process the ResultSet, update UI or data model as needed
        // For example, you can update a table model with the fresh data
        // or populate UI components with the fetched data.
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
        username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        back = new javax.swing.JButton();
        uid = new javax.swing.JTextField();
        fname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addData = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        contact = new javax.swing.JTextField();
        userType = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        username.setForeground(new java.awt.Color(0, 0, 204));
        username.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username);
        username.setBounds(20, 310, 230, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Username");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 340, 80, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText(" Password");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 400, 80, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Contact");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 460, 80, 30);

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        status.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(status);
        status.setBounds(20, 550, 230, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("User Type");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 520, 80, 30);

        update.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        update.setText("Upadate Data");
        update.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        update.setEnabled(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update);
        update.setBounds(150, 610, 90, 30);

        back.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        back.setText("Back");
        back.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel1.add(back);
        back.setBounds(520, 610, 90, 30);

        uid.setForeground(new java.awt.Color(0, 0, 204));
        uid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        uid.setEnabled(false);
        jPanel1.add(uid);
        uid.setBounds(20, 70, 230, 30);

        fname.setForeground(new java.awt.Color(0, 0, 204));
        fname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel1.add(fname);
        fname.setBounds(20, 130, 230, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(" ID");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 100, 80, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText(" First Name");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 160, 80, 30);

        lname.setForeground(new java.awt.Color(0, 0, 204));
        lname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(lname);
        lname.setBounds(20, 190, 230, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText(" Last Name");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 220, 80, 30);

        em.setForeground(new java.awt.Color(0, 0, 204));
        em.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(em);
        em.setBounds(20, 250, 230, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(" Email");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 280, 80, 30);

        addData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addData.setText("Add Data");
        addData.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDataActionPerformed(evt);
            }
        });
        jPanel1.add(addData);
        addData.setBounds(20, 610, 90, 30);

        delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        delete.setText("Delete Data");
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete);
        delete.setBounds(270, 610, 90, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Admin Sign Up");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 10, 610, 50);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(null);
        jPanel3.add(image);
        image.setBounds(10, 10, 340, 410);

        select.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        select.setText("Select");
        select.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel3.add(select);
        select.setBounds(10, 430, 90, 30);

        remove.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        remove.setText("Remove");
        remove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel3.add(remove);
        remove.setBounds(260, 430, 90, 30);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(270, 110, 360, 470);

        clear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        clear.setText("Clear ");
        clear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear);
        clear.setBounds(400, 610, 90, 30);

        date.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(date);
        date.setBounds(310, 70, 110, 30);

        time.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(time);
        time.setBounds(450, 70, 110, 30);

        contact.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(contact);
        contact.setBounds(20, 430, 230, 30);

        userType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        userType.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTypeActionPerformed(evt);
            }
        });
        jPanel1.add(userType);
        userType.setBounds(20, 490, 230, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("User Status");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 580, 80, 30);

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
        hide.setBounds(220, 370, 30, 30);

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-eye-30.png"))); // NOI18N
        jPanel1.add(show);
        show.setBounds(220, 370, 30, 30);

        password.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(password);
        password.setBounds(20, 370, 230, 30);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(30, 30, 650, 660);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDataActionPerformed
        // TODO add your handling code here:
        if(fname.getText().isEmpty() || lname.getText().isEmpty() || em.getText().isEmpty() || contact.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill in all required Data.");
        }else if (password.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "Password should have at least 8 characters or Above.");
            password.setText("");
        }else if (!contact.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Contact must contain only numbers.");
        }else if(duplicateCheck()){    
            System.out.println("Duplicated Exist");
        }else{
            dbConnector dbc = new dbConnector();
    
            try {    
                String pass = passwordHasher.hashPassword(password.getText());
                if (dbc.insertData("INSERT INTO tbl_user (u_fname, u_lname, u_email, u_username, u_password, u_contact,u_account, u_status, u_image) "
                    + "VALUES ('"+fname.getText()+"', '"+lname.getText()+"', '"+em.getText()+"', '"+username.getText()+"', '"
                    + pass+"', '"+contact.getText()+"', '"+status.getSelectedItem()+"', 'Pending', '"+destination+ "')")) {
            
                    JOptionPane.showMessageDialog(null, "Registration Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    adminUsersForm ad = new adminUsersForm();
                    ad.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Connection Error", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("" + ex);
            }
        }
    }//GEN-LAST:event_addDataActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        if(fname.getText().isEmpty() || lname.getText().isEmpty() || em.getText().isEmpty() 
            || username.getText().isEmpty() || password.getText().isEmpty() || contact.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill in all required Data.");
        }else if (password.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "Password should have at least 8 characters or Above.");
            password.setText("");
        }else if (contact.getText().length() != 11) {
            JOptionPane.showMessageDialog(null, "Contact Number must contain 11 digit numbers.");
        }else if (!contact.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Contact Number must contain only numbers.");
            contact.setText("");
        }else if(updateCheck()){    
            System.out.println("Duplicated Exist");
        }else{
            dbConnector dbc =new dbConnector();
            dbc.updateData("UPDATE tbl_user SET "
            + "u_fname = '"+fname.getText()+"', "
            + "u_lname = '"+lname.getText()+"', "
            + "u_email = '"+em.getText()+"', "
            + "u_username ='"+username.getText()+ "', "
            + "u_password = '"+password.getText()+"', "
            + "u_contact ='"+contact.getText()+"', "
            + "u_account = '"+userType.getSelectedItem()+"', "
            + "u_status = '"+status.getSelectedItem()+"', " 
            + "u_image = '"+destination+"' "
            + "WHERE u_id='"+Integer.valueOf(uid.getText())+"'");

            if(destination.isEmpty()){
                File existingFile = new File(oldpath);
                if(existingFile.exists()){
                    existingFile.delete();
                }
            }else{
                if(!(oldpath.equals(path))){
                    imageUpdater(oldpath, path);
                }
            }
            adminUsersForm auf = new adminUsersForm();
            auf.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            if (uid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a user to delete.");
            } else {
                try {
                    int userId = Integer.parseInt(uid.getText());
                    dbConnector dbc = new dbConnector();
                    dbc.deleteUser(userId);
                    // After deletion, you might want to clear the form fields or refresh the user interface
                    clearFields();
                    adminUsersForm auf = new adminUsersForm();
                    auf.setVisible(true);
                    this.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid user ID.");
                }
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // TODO add your handling code here:
        remove.setEnabled(false);
        select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_removeActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        adminUsersForm auf = new adminUsersForm();
        auf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_clearActionPerformed

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

    private void userTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTypeActionPerformed

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
            java.util.logging.Logger.getLogger(adminSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminSignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminSignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addData;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    public javax.swing.JTextField contact;
    private javax.swing.JLabel date;
    private javax.swing.JButton delete;
    public javax.swing.JTextField em;
    public javax.swing.JTextField fname;
    private javax.swing.JLabel hide;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JTextField lname;
    public javax.swing.JPasswordField password;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    private javax.swing.JLabel show;
    public javax.swing.JComboBox<String> status;
    private javax.swing.JLabel time;
    public javax.swing.JTextField uid;
    public javax.swing.JButton update;
    public javax.swing.JComboBox<String> userType;
    public javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
