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
public class Student extends Person{
    
    private long identification;
    private School school;
    public String[] course = new String[4];
    private String password;
    public ArrayList <Mark> marks = new ArrayList<Mark>();
    public ArrayList<String> assignments = new ArrayList<String>();
    Student(String lnam, String fnam, String add, String dob, long id,String pass){
        super(lnam, fnam, add, dob);
        identification=id;
        password=pass;
    }
    public void courseRequest(Course course){
        //writes to a file where admin can see the request 
    }
    
    

    public long getIdentification() {
        return identification;
    }

    public School getSchool() {
        return school;
    }

    public String[] getCourse() {
        return course;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

   
}
