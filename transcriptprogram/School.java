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
public class School {
    //name of school
    private String name;
    //address of school
    private String address;
    //max student capacity
    private int maxSCapacity;
    //current student capacity
    private int currentSCapacity;
    //max teacher capacity
    private int maxTCapacity;
    //current teacher capacity
    private int currentTCapacity;
    //array list of teachers
    public ArrayList <Teacher> teacher = new ArrayList <Teacher>();
    //array list of students
    public ArrayList <Student> students = new ArrayList<Student>();
    //arraylist for courses avalible in school
    public ArrayList <Course> courses = new ArrayList<Course>();
    //constructor for school
    public School(String nam, String add, int msc, int csc, int mtc, int ctc){
        name=nam;
        address=add;
        maxSCapacity=msc;
        currentSCapacity=csc;
        maxTCapacity=mtc;
        currentTCapacity=ctc;
    }
    //checks if student population is full returns true or false
    public boolean isFullS(int maxSCapacity, int currentSCapacity){
        boolean full = false;
        if(maxSCapacity < currentSCapacity){
            full = true;
        }
        System.out.println(full);
        return full;
    }
    //checks if teacher population is full returns true or false
    public boolean isFullT(int maxTCapacity, int currentTCapacity){
        boolean full = false;
        if(maxTCapacity <= currentTCapacity){
            full = true;
        }
        return full;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getMaxSCapacity() {
        return maxSCapacity;
    }

    public int getCurrentSCapacity() {
        return currentSCapacity;
    }

    public int getMaxTCapacity() {
        return maxTCapacity;
    }

    public int getCurrentTCapacity() {
        return currentTCapacity;
    }

    /**
     * @param currentSCapacity the currentSCapacity to set
     */
    public void setCurrentSCapacity(int currentSCapacity) {
        this.currentSCapacity = currentSCapacity;
    }

    /**
     * @param currentTCapacity the currentTCapacity to set
     */
    public void setCurrentTCapacity(int currentTCapacity) {
        this.currentTCapacity = currentTCapacity;
    }

    /**
     * @return the teacher
     */
    public ArrayList <Teacher> getTeacher() {
        return teacher;
    }

    /**
     * @return the students
     */
    public ArrayList <Student> getStudents() {
        return students;
    }
    public ArrayList <Course> getCourses(){
        return courses;
    }
}
