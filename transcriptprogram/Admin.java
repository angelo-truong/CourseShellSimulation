
package transcriptprogram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 324786953
 */
public class Admin extends Person{
    private long identification;
    final private long MASTER_KEY = 123456789;
    
    
    public Admin(String lnam, String fnam, String add, String dob, long id){
        super(lnam, fnam, add, dob);
        identification = id;
    }
    //method to wipe files
    public void resetAll(long key){
        File file1= new File("Northview/Students.txt");
        File file2 = new File("Downsview/Student.txt");
        File file3 = new File("Northview/Teachers.txt");
        File file4 = new File("Downsview/Teacher.txt");       
        if(file1.delete()){
            System.out.println("file deleated");
        }
        else{
            System.out.println("file cant be found");
        }
        try {
            if(file1.createNewFile()){
                System.out.println("file created");
            }
        } catch (IOException ex) {
            System.out.println("Error");
        }
        
         if(file2.delete()){
            System.out.println("file deleated");
        }
        else{
            System.out.println("file cant be found");
        }
        try {
            if(file2.createNewFile()){
                System.out.println("file created");
            }
        } catch (IOException ex) {
            System.out.println("Error");
        }
        
         if(file3.delete()){
            System.out.println("file deleated");
        }
        else{
            System.out.println("file cant be found");
        }
        try {
            if(file3.createNewFile()){
                System.out.println("file created");
            }
        } catch (IOException ex) {
            System.out.println("Error");
        }
        
         if(file4.delete()){
            System.out.println("file deleated");
        }
        else{
            System.out.println("file cant be found");
        }
        try {
            if(file4.createNewFile()){
                System.out.println("file created");
            }
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
    //method to add teacher
    public void addTeacher(String lnam, String fnam, String add, String dob, long id,String pass,School s){
       if(s.isFullT(s.getMaxTCapacity(), s.getCurrentTCapacity())==false ){
            Teacher teacher1 = new Teacher(lnam, fnam, add, dob, id,pass);
            s.teacher.add(teacher1);
            s.setCurrentTCapacity(s.getTeacher().size());
       }
        
    }
    //method to add student
    public void addStudent(String lnam, String fnam, String add, String dob, long id,String pass,School s){
         if(s.isFullS(s.getMaxSCapacity(), s.getCurrentSCapacity())==false ){
           Student student1 = new Student(lnam, fnam, add, dob, id, pass);
            s.students.add(student1);  
            s.setCurrentSCapacity(s.getStudents().size());
         }
        
    }
    //method to remove teacher
    public void removeTeacher(long id,Teacher tech,School s){
        int index=-1;
        for(int i = 0;i< s.teacher.size();i++){
            if(id==s.teacher.get(i).getIdentification()){
                index=i;
                s.teacher.remove(index);
                break;
            }
        }
        if(index==-1){
            System.out.println("Teacher not found");
    }
        
    }
    //method to remove student
    public void removeStudent(long id, Student s, School sc){
        int index=-1;
        for(int i = 0;i< sc.students.size();i++){
            if(id==sc.students.get(i).getIdentification()){
                index=i;
                sc.students.remove(index);
                break;
            }
        }
         if(index==-1){
            System.out.println("student not found");
    }
    }
    //methos to swap courses
    public boolean courseSwap(Course request,long studentNum,School s){
        if(request.isFull(request.getStudent().size())==false){
            int index=0;
            for(int i = 0; i< s.getStudents().size();i++){
                if(s.getStudents().get(i).getIdentification()==studentNum){
                    index=i;
                    break;
                }
            }
            request.getStudent().add(s.getStudents().get(index));
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public long getIdentification(){
        return identification;
    }

    /**
     * @return the MASTER_KEY
     */
    public long getMASTER_KEY() {
        return MASTER_KEY;
    }

   
}
