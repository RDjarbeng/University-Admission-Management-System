/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.control.Button;

import java.sql.Blob;

/**
 * @author Djarbeng
 * @author kezia
 */
public class StudentInfo {
    String receiptID;
    String lname;
    String fname;
    String mname;
    String dob;
    String gender;
    String resAddress;
    String email;
    String nationality;
    String postalAddress;
    String course;
    String hall;



    String status;
    Blob results;
    Button button;

    public StudentInfo(String string, String string1, String string2) {
        this.receiptID = string;
        this.lname = string1;
        this.fname = string2;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }




    public StudentInfo(String receiptID, String lname, String fname, String mname, String dob, String gender,String nationality, String email, String resAddress, String posAddress) {
        this.receiptID = receiptID;
        this.lname = lname;
        this.fname = fname;
        this.mname = mname;
        this.dob = dob;
        this.gender = gender;
        this.nationality = nationality;
        this.resAddress = resAddress;
        this.email = email;
        this.postalAddress = posAddress;

        this.button = new Button("View");
        
    }

    //Getters and setters

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public Blob getResults() {
        return results;
    }

    public void setResults(Blob results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
