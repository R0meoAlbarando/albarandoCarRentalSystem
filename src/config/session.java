/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author ultabook
 */
public class session {
    private static session instance;
    private int uid;
    private String fname;
    private String lname;
    private String email;
    private String contact;
    private String username;
    private String type;
    private String Status;
    
    private session (){
        //private cons. prevents instance
    }

    public static synchronized session getInstance() {
        if(instance == null){
            instance = new session();
        }
        return instance;
    }

    public static boolean isInstanceEmpty(session instance) {
        return instance == null;
    }
    
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    private int cid;
    private String carBrand;
    private String carModel;
    private String carType;
    private String carColor;
    private String carCapacity;
    private String carRate;
    private String carPlateNo;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(String carCapacity) {
        this.carCapacity = carCapacity;
    }

    public String getCarRate() {
        return carRate;
    }

    public void setCarRate(String carRate) {
        this.carRate = carRate;
    }

    public String getCarPlateNo() {
        return carPlateNo;
    }

    public void setCarPlateNo(String carPlateNo) {
        this.carPlateNo = carPlateNo;
    }
    
}

