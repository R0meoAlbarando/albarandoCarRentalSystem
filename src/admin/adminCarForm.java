/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;


import config.dbConnector;
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author sheila mae albarando
 */
public class adminCarForm extends javax.swing.JFrame {

    /**
     * Creates new form carOwnerRegistrationCar
     */
    public adminCarForm() {
        initComponents();
        dt();
        times();
        displayData();
    }

    
    boolean checkadd = true;
    
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
        
        Path filePath = Paths.get("src/images", fileName);
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

    
    private void clearFields() {
        // Clear all the text fields and reset selected items to default
        carBrand.setText("");
        carModel.setText("");
        carType.setText("");
        carColor.setText("");
        carCapacity.setText("");
        carRate.setText("");
        carPlateNo.setText("");
    }
    
    private void refreshData() {
        dbConnector dbc = new dbConnector();
        ResultSet resultSet = dbc.refreshData();
    
        // Process the ResultSet, update UI or data model as needed
        // For example, you can update a table model with the fresh data
        // or populate UI components with the fetched data.
    }
    
    public void displayData(){
        try{
            dbConnector dbc = new dbConnector();
            ResultSet rs = dbc.getData("SELECT * FROM tbl_car");
            carsTable.setModel(DbUtils.resultSetToTableModel(rs));
             rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
        
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        carColor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        carCapacity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        carRate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        carPlateNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        carBrand = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        carType = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        carModel = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        remove = new javax.swing.JButton();
        select = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        carsTable = new javax.swing.JTable();
        Add = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();
        carStatus = new javax.swing.JComboBox<>();
        print = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(null);

        carColor.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carColor.setForeground(new java.awt.Color(0, 0, 204));
        carColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carColor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        carColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carColorActionPerformed(evt);
            }
        });
        jPanel3.add(carColor);
        carColor.setBounds(30, 660, 230, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Car Color       :");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(30, 700, 80, 30);

        carCapacity.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carCapacity.setForeground(new java.awt.Color(0, 0, 204));
        carCapacity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carCapacity.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(carCapacity);
        carCapacity.setBounds(290, 380, 230, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Car Capacity :");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(290, 420, 80, 30);

        carRate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carRate.setForeground(new java.awt.Color(0, 0, 204));
        carRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carRate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(carRate);
        carRate.setBounds(290, 450, 230, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Car Plate #   :");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(290, 560, 80, 30);

        carPlateNo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carPlateNo.setForeground(new java.awt.Color(0, 0, 204));
        carPlateNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carPlateNo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(carPlateNo);
        carPlateNo.setBounds(290, 520, 230, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Car Status");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(290, 630, 80, 30);

        cid.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cid.setForeground(new java.awt.Color(0, 0, 204));
        cid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cid.setEnabled(false);
        jPanel3.add(cid);
        cid.setBounds(30, 380, 230, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Car  ID          :");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(30, 420, 80, 30);

        carBrand.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carBrand.setForeground(new java.awt.Color(0, 0, 204));
        carBrand.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carBrand.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        carBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carBrandActionPerformed(evt);
            }
        });
        jPanel3.add(carBrand);
        carBrand.setBounds(30, 450, 230, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Car Brand     :");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(30, 490, 80, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Car Model     :");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(30, 560, 80, 30);

        carType.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carType.setForeground(new java.awt.Color(0, 0, 204));
        carType.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carType.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(carType);
        carType.setBounds(30, 590, 230, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Car Type       :");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(30, 630, 80, 30);

        carModel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carModel.setForeground(new java.awt.Color(0, 0, 204));
        carModel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carModel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(carModel);
        carModel.setBounds(30, 520, 230, 40);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Admin Car Form");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jLabel10);
        jLabel10.setBounds(30, 10, 910, 50);

        date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(date);
        date.setBounds(340, 60, 110, 30);

        time.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(time);
        time.setBounds(480, 60, 110, 30);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(null);

        image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(image);
        image.setBounds(10, 10, 360, 290);

        remove.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        remove.setText("Remove Image");
        remove.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel4.add(remove);
        remove.setBounds(250, 310, 119, 40);

        select.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        select.setText("Select Image");
        select.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel4.add(select);
        select.setBounds(10, 310, 120, 40);

        jPanel3.add(jPanel4);
        jPanel4.setBounds(560, 370, 380, 360);

        carsTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        carsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(carsTable);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(30, 90, 910, 260);

        Add.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Add.setText("Add");
        Add.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        jPanel3.add(Add);
        Add.setBounds(30, 750, 130, 30);

        Back.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Back.setText("Back");
        Back.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        jPanel3.add(Back);
        Back.setBounds(820, 750, 120, 30);

        clear.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        clear.setText("Clear");
        clear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel3.add(clear);
        clear.setBounds(650, 750, 120, 30);

        delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        delete.setText("Delete Data");
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel3.add(delete);
        delete.setBounds(340, 750, 120, 30);

        update.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        update.setText("Upadate");
        update.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        update.setEnabled(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel3.add(update);
        update.setBounds(190, 750, 120, 30);

        carStatus.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        carStatus.setForeground(new java.awt.Color(0, 0, 204));
        carStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Rented" }));
        carStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        carStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carStatusActionPerformed(evt);
            }
        });
        jPanel3.add(carStatus);
        carStatus.setBounds(290, 590, 230, 40);

        print.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        print.setText("Print");
        print.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        jPanel3.add(print);
        print.setBounds(500, 750, 110, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Car Rate        :");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(290, 490, 80, 30);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(30, 30, 960, 800);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void carColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carColorActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void carBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carBrandActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // TODO add your handling code here:
        remove.setEnabled(false);
        select.setEnabled(true);
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_removeActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/carImage/" + selectedFile.getName();
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

    private void carsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carsTableMouseClicked
        // TODO add your handling code here:
        int rowIndex = carsTable.getSelectedRow();

        if(rowIndex < 0){
            JOptionPane.showMessageDialog(null,"Please Select an Item!");
        }else{
            try{
                dbConnector dbc = new dbConnector();
                TableModel tbl = carsTable.getModel();
                String carId = tbl.getValueAt(rowIndex, 0).toString();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_car WHERE c_id = '"+carId+"'");
                if(rs.next()){
                    cid.setText(""+rs.getInt("c_id"));
                    carBrand.setText(""+rs.getString("c_brand"));
                    carModel.setText(""+rs.getString("c_model"));
                    carType.setText(""+rs.getString("c_type"));
                    carColor.setText(""+rs.getString("c_color"));
                    carCapacity.setText(""+rs.getString("c_capacity"));
                    carRate.setText(""+rs.getString("c_rate"));
                    carPlateNo.setText(""+rs.getString("c_plateNo"));
                    carStatus.setSelectedItem(""+rs.getString("c_status"));
                    image.setIcon(ResizeImage(rs.getString("c_image"),null,image));
                    oldpath = rs.getString("c_image");
                    path = rs.getString("c_image");
                    destination = rs.getString("c_image");
                    Add.setEnabled(false);
                    update.setEnabled(true);
                }
            }catch(SQLException ex){
                System.out.println(""+ex);
            }
        }
    }//GEN-LAST:event_carsTableMouseClicked

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        
        if(checkadd){
            if(carModel.getText().isEmpty() || carType.getText().isEmpty() || carColor.getText().isEmpty() || 
                carCapacity.getText().isEmpty() || carRate.getText().isEmpty() || carPlateNo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in all required Data.");
            }else{
                dbConnector dbc = new dbConnector();

                String query = "INSERT INTO tbl_car (c_brand, c_model, c_type, c_color, c_capacity, c_rate, c_plateNo, c_status, c_image) " +
                "VALUES ('" + carBrand.getText() + "', '" + carModel.getText() + "', '" + carType.getText() + "', '" +
                carColor.getText() + "', '" + carCapacity.getText() + "', '" + carRate.getText() + "', '" +
                carPlateNo.getText() + "', '" + carStatus.getSelectedItem() + "', '" + destination + "')";

                if(dbc.insertData(query)) {
                    JOptionPane.showMessageDialog(null, "Inserted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Connection Error!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                displayData();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Clear The Field First", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        adminDashboard ad = new adminDashboard();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        carBrand.setText("");
        carModel.setText("");
        carType.setText("");
        carColor.setText("");
        carCapacity.setText("");
        carRate.setText("");
        carPlateNo.setText("");
        carStatus.setSelectedItem("");
        image.setIcon(null);
        Add.setEnabled(true);
        update.setEnabled(false);
    }//GEN-LAST:event_clearActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this car?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            if (cid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a Car to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int cId = Integer.parseInt(cid.getText());
                    dbConnector dbc = new dbConnector();
                    dbc.deleteCar(cId);
                    // After deletion, you might want to clear the form fields or refresh the user interface
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Car ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        displayData();
    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        if (carBrand.getText().isEmpty() || carModel.getText().isEmpty() || carType.getText().isEmpty()
            || carColor.getText().isEmpty() || carCapacity.getText().isEmpty()
            || carRate.getText().isEmpty() || carPlateNo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required data.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            dbConnector dbc = new dbConnector();
            dbc.updateData("UPDATE tbl_car SET "
                + "c_brand = '" + carBrand.getText() + "', "
                + "c_model = '" + carModel.getText() + "', "
                + "c_type = '" + carType.getText() + "', "
                + "c_color = '" + carColor.getText() + "', "
                + "c_capacity = '" + carCapacity.getText() + "', "
                + "c_rate = '" + carRate.getText() + "', "
                + "c_plateNo = '" + carPlateNo.getText() + "', "
                + "c_status = '" + carStatus.getSelectedItem() + "', "
                + "c_image = '" + destination + "' "
                + "WHERE c_id = '" + Integer.valueOf(cid.getText()) + "'");

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
            displayData();
        }
    }//GEN-LAST:event_updateActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
        int rowIndex = carsTable.getSelectedRow();

        if(rowIndex < 0){
            JOptionPane.showMessageDialog(null,"Please Select an Item!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                dbConnector dbc = new dbConnector();
                TableModel tbl = carsTable.getModel();
                ResultSet rs = dbc.getData("SELECT * FROM tbl_car WHERE c_id = '"+tbl.getValueAt(rowIndex, 0)+"'");
                if(rs.next()){
                    adminIndividualCarPrinting aip = new adminIndividualCarPrinting();
                    aip.cid.setText(""+rs.getInt("c_id"));
                    aip.cBrand.setText(""+rs.getString("c_brand"));
                    aip.cModel.setText(""+rs.getString("c_model"));
                    aip.cType.setText(""+rs.getString("c_type"));
                    aip.cColor.setText(""+rs.getString("c_color"));
                    aip.cCapacity.setText(""+rs.getString("c_capacity"));
                    aip.cRate.setText(""+rs.getString("c_rate"));
                    aip.cStatus.setText(""+rs.getString("c_status"));

                    aip.image.setIcon(aip.ResizeImage(rs.getString("c_image"),null,aip.image));
                    aip.setVisible(true);
                    this.dispose();
                }
            }catch(SQLException ex){
                System.out.println(""+ex);
            }
        }
    }//GEN-LAST:event_printActionPerformed

    private void carStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carStatusActionPerformed

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
            java.util.logging.Logger.getLogger(adminCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminCarForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminCarForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Back;
    public javax.swing.JTextField carBrand;
    public javax.swing.JTextField carCapacity;
    public javax.swing.JTextField carColor;
    public javax.swing.JTextField carModel;
    public javax.swing.JTextField carPlateNo;
    public javax.swing.JTextField carRate;
    public javax.swing.JComboBox<String> carStatus;
    public javax.swing.JTextField carType;
    private javax.swing.JTable carsTable;
    public javax.swing.JTextField cid;
    private javax.swing.JButton clear;
    private javax.swing.JLabel date;
    private javax.swing.JButton delete;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton print;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    private javax.swing.JLabel time;
    public javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
