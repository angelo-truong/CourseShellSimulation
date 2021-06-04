/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transcriptprogram;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat;
/**
 *
 * @author 324786953
 */
public class Teacher extends Person{
    //username for teacher
    private long identification;
    //password for teacher
    private String password;
    //array to store the number of courses the teacher is teaching max of 4
    public Course course[] = new Course[3];
    //array list of students marks
    public ArrayList <String> assignName = new ArrayList<String>();
   // public ArrayList <Student> student = new ArrayList <Student>();
    
    //constructor for teacher 
    public Teacher(String lnam, String fnam, String add, String dob, long id,String pass){
        super(lnam, fnam, add, dob);
        identification = id;
        password = pass;
    }
    //takes name of assignment, entry number and total of the assignment and creates the appropriate number of mark objects for students 
    public void addAssessment(String name, int num, int total,Course c){
        //creates a new markC array so each markC list in the mark array is unique and doesnt reference the same list 
        //markC=new ArrayList<Mark>();
        assignName.add(name);
        //loop to create and fill markC with mark objects
        for(int i=0;i<c.getStudent().size();i++){
              c.student.get(i).assignments.add(name);
            long studentNum = c.getStudent().get(i).getIdentification();
            double given = 0;
            Mark mark1 = new Mark(name, num, given, total,studentNum);
            c.student.get(i).marks.add(mark1);
           // getMarkC().add(mark1);
        }
 
    }
    
    //takes a student number , the mark they earned, their mark object and the entry of the mark to give the student the mark they had earned for that assignment
    public void addMark(long studentNum, double markEarned, Mark m,int entry,Course c){
            //calculates assignment percentage 
            double percent = markEarned/m.getMaxMark()*100;
            //searches for the index of the student with the matching student number
            int index = search(studentNum,this.sortBy(1,c),0,c.getStudent().size()-1);
            //if couldn't find student, outputs a message, else adds the mark to the mark object
            if(index == -1){
                System.out.println("Student does not exist");
            }
            else{
                DecimalFormat df = new DecimalFormat("##.0");
                
               
                    c.student.get(index).marks.get(entry).setGivenMark(percent);
                    String num =studentNum+"";
                    String school = "";
                    if(num.charAt(1)=='2'){
                        school="Downsview/";
                    }
                    else{
                        school = "Northview/";
                    }   String fileName = school+studentNum+".txt";
                    String marks = c.getCode()+" "+ m.getAssignmentName()+" "+df.format(percent)+" "+m.getMaxMark();
                try {
                    FileWriter fr = new FileWriter(fileName,true);
                   BufferedWriter br= new BufferedWriter(fr);
                    br.write(marks);
                    br.newLine();
                    br.close();
                } catch (IOException ex) {
                    System.out.println("Error");
                }
                   
                
               
            }
            
            
            
    }
    //searching algorithm for student using student numbers,returns the index of the student, if couldn't find student, returns -1
    public int search(long studentNum,Student[] student,int start,int end){
        
        if(end>=start){
            int mid = (end + start)/2;
            if(studentNum>student[mid].getIdentification()){
                return search(studentNum,student,mid+1,end);
            }else if(studentNum<student[mid].getIdentification()){
                return search(studentNum,student,start,mid-1);
            }
            else{
                return mid;
            }
        
        }
        return -1;
    }
    //searches for student with the matching student number and resets their password to their date of birth
    public void passwordResetStudent(long studentNum,Course c){
        int index = search(studentNum,this.sortBy(1,c),0,c.getStudent().size()-1);
        if(index == -1){
                System.out.println("Student does not exist");
            }
        else{
            c.getStudent().get(index).setPassword(c.getStudent().get(index).getDateOfBirth());
        }
    }
    //sort by method used to sort the student array in various ways depending on the user request
    public Student[] sortBy(int num,Course c){
        int size = c.getStudent().size();
        Student studentArray[] = new Student[size];
        for(int counter = 0; counter < c.getStudent().size(); counter++){
            studentArray[counter] = c.getStudent().get(counter);
        }
        //sort by student number using insertion sort
        if(num == 1){
            for(int i =1; i<studentArray.length;i++){
                long value = studentArray[i].getIdentification();
                Student temp = studentArray[i];
                int j = i-1;
                while(j>=0 && studentArray[j].getIdentification()>value){
                    studentArray[j+1] = studentArray[j];
                    j--;
                }
                studentArray[j+1]=temp;
            }  
        }
        //sort by alphabetical order by last name 
        else if(num==2){
            for(int i=0; i<studentArray.length;i++){
                for(int j=0;j<studentArray.length-1;j++){
                    if(studentArray[j].getLname().charAt(0)>studentArray[j+1].getLname().charAt(0)){
                        Student temp = studentArray[j];
                        studentArray[j]= studentArray[j+1];
                        studentArray[j+1]=temp;
                    }
                }
            }
        }
        else{
            
        }
        return studentArray;
    }
    
    public long getIdentification(){
        return identification;
    }

    public Course[] getCourse() {
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
