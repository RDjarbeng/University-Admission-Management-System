/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.tableview;

import java.sql.Blob;
import javafx.scene.control.Button;

/**
 *
 * @author kezia
 */
public class StudentInfo {
    String payment, lname, fname, mname, exten, dob, gender, father, mother, contact, email, address, course, hall;
    Blob results; 
    Button button;

    public StudentInfo(String payment, String lname, String fname, String mname, String exten, String dob, String gender, String father, String mother, String contact, String email, String address, String course, String hall, Blob results) {
        this.payment = payment;
        this.lname = lname;
        this.fname = fname;
        this.mname = mname;
        this.exten = exten;
        this.dob = dob;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.course = course;
        this.hall = hall;
        this.results = results;
        this.button = new Button("View");
        
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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

    public String getExten() {
        return exten;
    }

    public void setExten(String exten) {
        this.exten = exten;
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

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    
  
    
}
