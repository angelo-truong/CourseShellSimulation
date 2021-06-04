/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transcriptprogram;

import java.util.ArrayList;

/**
 *
 * @author 324786953
 */
public class Course {
    //course code 
    private String code;
    //course name
    private String name;
    //course level
    private String level;
    //capacity of course 
    private final int capacity = 20;
    //list of students in the course 
    public ArrayList <Student> student = new ArrayList <Student>();
    public Course(String c, String nam, String lvl){
	code = c;
	name = nam;
	level = lvl;
    }
    //checks if course is full
    public boolean isFull(int size){
        if(size>capacity){
            return true;
        }
        else{
            return false;
        }
        
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    /**
     * @return the student
     */
    public ArrayList <Student> getStudent() {
        return student;
    }
}
