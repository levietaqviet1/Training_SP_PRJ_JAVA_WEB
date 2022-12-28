/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
 
public class Student {
    private int id ;
    private String name ;
    private Date date ;
    private boolean gender ;
    private ClassRoom classRoom ; 
   
    //hiển thị tên của class của từng sv 
    // select DB --> tb Student --> list<Student>

    public Student() {
    }

    public Student(int id, String name, Date date, boolean gender, ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.classRoom = classRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
    
    
    
}
