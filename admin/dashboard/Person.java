/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dashboard;

/**
 *
 * @author kezia
 */
public class Person {
    String name, course;
    int id;

    public Person(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    
    
   
    
    
}
