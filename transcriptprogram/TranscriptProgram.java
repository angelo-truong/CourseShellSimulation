/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transcriptprogram;
import java.io.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 *
 * @author 324786953
 * This program is a trascript program which allows teachers to enter assignments and input 
 * marks for their students and students are able to log in and se those marks.  Students are also
 * allowed to request a course to the admin which will either accpet or deny the request.  The admin
 * is also allowed to add/remove students and teachers from different school and they also have the power to 
 * reset the entire program by wiping the students and teacher files
 */
public class TranscriptProgram extends Application {

  int type;
    Text incorrect = new Text(null);
     TextField id = new TextField();
     //universal buttons
    Button logOut = new Button();
    Button add1 = new Button();
    Button add2 = new Button();
    Button add3 = new Button();
    Button add4 = new Button();
    Button add5 = new Button();
    Button add6 = new Button();
    Button back = new Button();
    //pasword text field
    PasswordField password = new PasswordField();
    //school object
    School school;
    //teacher object who is logged in
    Teacher teacher;
    //student object who is logged in
    Student student;
    //admin object who is logged in 
    Admin admin;
    //course object that user has accessed
    Course course;
    //reads the file 
    public void read(int num){
            //if num is 1 reads the Dwonsview folder information
                    if(num==1){
                        //reads school information
            try {
                BufferedReader br = new BufferedReader(new FileReader("Downsview/School.txt"));
                String line="";
                
                while(line!=null){
                    
                     line =  br.readLine();
                    String name = line;
                    System.out.println(name);
                    line = br.readLine();
                    String address = line;
                    line = br.readLine();
                    int maxS = Integer.parseInt(line);
                    line = br.readLine();
                    int maxT = Integer.parseInt(line);
                    line=br.readLine();
                    school = new School(name,address,maxS,2,maxT,1);
                }
                //read course information
                 br = new BufferedReader(new FileReader("Downsview/Courses.txt"));
                 line = br.readLine();
                 while(line !=null){
                     String code = line;
                     line = br.readLine();
                     String name = line;
                     line = br.readLine();
                     String lvl = line;
                     line = br.readLine();
                     Course c = new Course(code, name, lvl);
                     school.courses.add(c); 
                    
                 }
                 //reads student informations
                br= new BufferedReader(new FileReader("Downsview/Student.txt"));
                line=br.readLine();
                while(line !=null){
                    String lName = line;
                    line= br.readLine();
                    String fName = line;
                    line = br.readLine();
                    String address = line;
                    line = br.readLine();
                    String DOB = line;
                    line = br.readLine();
                    long id = Long.parseLong(line);
                    line = br.readLine();
                    String pass = line;
                    line = br.readLine();
                    String course1 = line;
                    line = br.readLine();
                    Student stud = new Student(lName,fName,address,DOB,id,pass);
                    
                     for(int i =0; i < school.courses.size();i++){
                       
                        if(course1.equals(school.courses.get(i).getCode())){
                            
                            for(int j =0; j<stud.course.length;j++){
                                if(stud.course[j]==null){
                                    stud.course[j]=school.courses.get(i).getCode();
                                    break;
                                }
                                    }
                        }
                     }
                    
                    school.students.add(stud);
                 
                }
                //reads teacher information 
                 br= new BufferedReader(new FileReader("Downsview/Teacher.txt"));
                  line=br.readLine();
                while(line !=null){
                    String lName = line;
                    line= br.readLine();
                    String fName = line;
                    line = br.readLine();
                    String address = line;
                    line = br.readLine();
                    String DOB = line;
                    line = br.readLine();
                    long id = Long.parseLong(line);
                    line = br.readLine();
                    String pass = line;
                    line = br.readLine();
                    String courseT = line;
                    line = br.readLine();
                    Teacher teach = new Teacher(lName,fName,address,DOB,id,pass);
                    String course1="",course2="";
                    System.out.println(courseT.length());
                    if(courseT.length()==14){
                        course1 = courseT.substring(0, 6);
                        course2 = courseT.substring(7,13);
                       
                    }
                    for(int i =0; i < school.courses.size();i++){
                       
                        if(course1.equals(school.courses.get(i).getCode()) || course2.equals(school.courses.get(i).getCode())){
                           
                            for(int j =0; j<teach.course.length;j++){
                                if(teach.course[j]==null){
                                    teach.course[j]=school.courses.get(i);
                                    break;
                                }
                                    }
                        }
                     }
                    school.teacher.add(teach); 
                }
                
            } catch (IOException ex) {
                System.out.println("file not found");
            }     
        }
                    //if num = 2 reads Northview folder information
          else if(num ==2){
                     try {
                         //reads northview school information
                BufferedReader br = new BufferedReader(new FileReader("Northview/School1.txt"));
                 String line="";
                while(line!=null){
                    
                     line =  br.readLine();
                    String name = line;
                    System.out.println(name);
                    line = br.readLine();
                    String address = line;
                    line = br.readLine();
                    int maxS = Integer.parseInt(line);
                    System.out.println(maxS);
                    line = br.readLine();
                    int maxT = Integer.parseInt(line);
                    line=br.readLine();
                    school = new School(name,address,maxS,2,maxT,1);
                }
                //read northview course informations
                 br = new BufferedReader(new FileReader("Northview/Course.txt"));
                 line = br.readLine();
                 while(line !=null){
                     String code = line;
                     line = br.readLine();
                     String name = line;
                     line = br.readLine();
                     String lvl = line;
                     line = br.readLine();
                     Course c = new Course(code, name, lvl);
                     school.courses.add(c); 
                     System.out.println(school.courses.size());
                 }
                 //reads nothview students information
                 br= new BufferedReader(new FileReader("Northview/Students.txt"));
                line=br.readLine();
                while(line !=null){
                    String lName = line;
                    line= br.readLine();
                    String fName = line;
                    line = br.readLine();
                    String address = line;
                    line = br.readLine();
                    String DOB = line;
                    System.out.println(DOB);
                    line = br.readLine();
                    long id = Long.parseLong(line);
                    System.out.println(id);
                    line = br.readLine();
                    String pass = line;
                    line = br.readLine();
                    String course1 = line;
                    line = br.readLine();
                    Student stud = new Student(lName,fName,address,DOB,id,pass);
                    
                     for(int i =0; i < school.courses.size();i++){
                       
                        if(course1.equals(school.courses.get(i).getCode())){
                            
                            for(int j =0; j<stud.course.length;j++){
                                if(stud.course[j]==null){
                                    System.out.println(j);
                                    stud.course[j]=school.courses.get(i).getCode();
                                    break;
                                }
                                    }
                        }
                     }
                    
                    school.students.add(stud); 
                                    
                }
                //reads northview teachers information
                 br= new BufferedReader(new FileReader("Northview/Teachers.txt"));
                  line=br.readLine();
                while(line !=null){
                    String lName = line;
                    line= br.readLine();
                    String fName = line;
                    line = br.readLine();
                    String address = line;
                    line = br.readLine();
                    String DOB = line;
                    line = br.readLine();
                    long id = Long.parseLong(line);
                    line = br.readLine();
                    String pass = line;
                    line = br.readLine();
                     String courseT = line;
                    line = br.readLine();
                    Teacher teach = new Teacher(lName,fName,address,DOB,id,pass);
                    
                    for(int i =0; i < school.courses.size();i++){
                       
                        if(courseT.equals(school.courses.get(i).getCode())){
                           
                            for(int j =0; j<teach.course.length;j++){
                                if(teach.course[j]==null){
                                    teach.course[j]=school.courses.get(i);
                                    break;
                                }
                                    }
                        }
                     }
                    school.teacher.add(teach);                 
                }
                    } catch (IOException ex) {
                    System.out.println("file not found");
             }
        }
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        
        //log in page
        TextField txt = new TextField();
        PasswordField p = new PasswordField();
        Button btn = new Button();
        btn.setText("Log In");
        BorderPane lawl = new BorderPane();
        GridPane gg = new GridPane();
        HBox welcome = new HBox();
        Text text = new Text("Welcome to the Scuffed Book");
        Text text2 = new Text("Username");
        Text text3 = new Text("Password");
        
        HBox user = new HBox();
        user.setPadding(new Insets(15, 0, 15, 15));
        user.getChildren().add(text2);
        HBox pass = new HBox();
        pass.setPadding(new Insets(45, 0, 15, -50));//Top, right, bottom, left
        pass.getChildren().add(text3);
        VBox log = new VBox();
        log.setPadding(new Insets(0, 200, 15, 25));
        log.setSpacing(15);
        log.getChildren().addAll(txt, p, btn);
        
        StackPane stack1 = new StackPane();
        stack1.getChildren().add(log);
        StackPane stack2 = new StackPane();
        stack2.getChildren().add(pass);
        
        user.getChildren().add(stack2);
        user.getChildren().add(stack1);
        
        welcome.getChildren().add(text);
        welcome.setStyle("-fx-background-color: #336699;");
        lawl.setTop(welcome);
        lawl.setCenter(user);
        Scene one = new Scene(lawl,500,500);
 
        //button to go back to log in 
        
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            //button to log in 
            public void handle(ActionEvent event) {
                //if username matches admin
                if(txt.getText().charAt(0)=='0'){
                    try {
                        BufferedReader br = new BufferedReader(new FileReader("Admin.txt"));
                        String line ="";
                        while(line!=null){
                            line=br.readLine();
                            String lName = line;
                            line= br.readLine();
                            String fName = line;
                            line = br.readLine();
                            String address = line;
                            line = br.readLine();
                            String DOB = line;
                            line = br.readLine();
                            long id = Long.parseLong(line);
                            line = br.readLine();
                            admin = new Admin(lName,fName,address, DOB,id);
                        }
                    } catch (IOException ex) {
                        System.out.println("Problem finding file ");
                    }
                    if(admin.getIdentification()==Long.parseLong(txt.getText()) && p.getText().equals("123456789")){
                        type=1;
                        primaryStage.setScene(postLog(type));
                    }
                    else{
                         incorrect= new Text("Incorrect log in details");
                         gg.add(incorrect, 2,6);
                       
                    }
                }
                //if username matches teacher 
                else if(txt.getText().charAt(0)=='2'){
                    type =2;
                    if(txt.getText().charAt(1)=='1'){
                        read(1);
                    }
                    else{
                        read(2);
                    }
                    for(int i=0;i<school.teacher.size();i++){
                        if(Long.parseLong(txt.getText())==school.teacher.get(i).getIdentification()){
                            teacher =school.teacher.get(i);              
                        }
                    }
                    if(teacher.getPassword().equals(p.getText())){
                        primaryStage.setScene(coursePage(type));
                        
                    }else{
                         incorrect= new Text("Incorrect log in details");
                        gg.add(incorrect, 2,6);
                       
                    }
                }
                //if username matches student
                else if(txt.getText().charAt(0)=='3'){
                   
                    type =3;
                     if(txt.getText().charAt(1)=='2'){
                         //reads file for downsview
                        read(1);
                    }
                    else{
                         //reads files for Northview
                        read(2);
                    }
                     //searches for students with matching id
                    for(int i=0;i<school.students.size();i++){
                        if(Long.parseLong(txt.getText())==school.students.get(i).getIdentification()){
                            student =school.students.get(i);              
                        }
                    }
                    if(student.getPassword().equals(p.getText())){
                        primaryStage.setScene(coursePage(type));
                        System.out.println("works 1");
                        
                    }else{
                         incorrect= new Text("Incorrect log in details");
                        gg.add(incorrect, 2,6);
                       
                    }

                }
                else{
                    incorrect= new Text("Incorrect log in details");
                    gg.add(incorrect, 2,6);
                    System.out.println("Wrong username or password");
                }
                
                
            }
        });
        //logout button
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                gg.getChildren().remove(incorrect);
                txt.clear();
                p.clear();
                primaryStage.setScene(one);
            }
        });
        //repsonsible for going back a page
        back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(type == 4){
                    type = 1;
                    primaryStage.setScene(postLog(type));
                }else if(type == 5 || type == 8){
                    type = 4;
                    primaryStage.setScene(schoolPage(type));
                }else if(type == 6){
                    type = 5;
                    primaryStage.setScene(teacherPage(type));
                }else if(type == 7){
                    type = 8;
                    primaryStage.setScene(studentPage(type));
                }else if(type == 100){
                    type = 4;
                    primaryStage.setScene(schoolPage(type));
                }else if(type == 200){
                    type = 2;
                    primaryStage.setScene(postLog(type));
                }else if(type == 300){
                    type = 2;
                    primaryStage.setScene(postLog(type));
                }else if(type == 9){
                    type = 2;
                    primaryStage.setScene(postLog(type));
                }else if(type == 400){
                    type = 3;
                    primaryStage.setScene(postLog(type));
                }
            }
        });
           
        add1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(type == 1){
                    type = 4;
                    primaryStage.setScene(schoolPage(type));
                }else if(type == 4){
                    type = 5;
                    primaryStage.setScene(teacherPage(type));
                }else if(type == 5){
                    type = 6;
                    primaryStage.setScene(addRemove(type));
                }else if(type == 6){
                    System.out.println("Teacher Added");
                    primaryStage.setScene(addRemove(type));
                }else if(type == 7){
                    System.out.println("Student Added");
                    primaryStage.setScene(addRemove(type));
                }else if(type == 8){
                    type = 7;
                    primaryStage.setScene(addRemove(type));
                }
                else if(type == 100){
                    System.out.println("Course Request Approved");
                }else if(type == 2){
                    type = 200;
                    primaryStage.setScene(assessment(type));
                }else if(type == 200){
                           
                    System.out.println("Assessment Added fail");
                    primaryStage.setScene(assessment(type));
                }
                else if(type == 300){
                    System.out.println("Mark Added");
                    primaryStage.setScene(mark(type));
                }else if(type == 9){
                    //teacher.sortBy(1, c);
                    System.out.println("Students Sorted");
                    primaryStage.setScene(studentPage(type));
                }else if(type == 3){
                    System.out.println("Course Requested");
                    primaryStage.setScene(postLog(type));
                }
                //course page for teachers that lead to postlog page for teachers
                else if(type ==11){
                    type=2;
                    course= teacher.course[0];
                    System.out.println(course.getCode());
                    for(int i =0; i<school.students.size();i++){
                       for(int j =0;j<1;j++){
                    if(school.students.get(i).getCourse()[j].equals(course.getCode())){
                        System.out.println(school.students.get(i).getFname());
                        course.student.add(school.students.get(i));
                    }
                       }
                }
                    primaryStage.setScene(postLog(2));
                }
                //leads to post log for student 
                else if(type==33){
                    type=3;
                    for(int i=0; i<2;i++){
                        System.out.println("whoa");
                        System.out.println(student.course[0]);
                        System.out.println(teacher.course[i].getName());
                        if(student.course[0].equals(teacher.course[i].getName())&& teacher.course[i]!=null){
                            course=teacher.course[i];
                            System.out.println("saved");
                        }
                    }
                    primaryStage.setScene(postLog(3));
                }
                
            }
        });
        
        add2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if((type == 1) && (password.getText().equals("Angelu"))){
                    System.out.println("System Wiped");
                }else if(type == 4){
                    type = 8;
                    primaryStage.setScene(studentPage(type));
                }else if(type == 6){
                    System.out.println("Teacher Removed");
                    primaryStage.setScene(addRemove(type));
                }else if(type == 7){
                    System.out.println("Student Removed");
                    primaryStage.setScene(addRemove(type));
                }else if(type == 100){
                    System.out.println("Course Request Denied");
                    primaryStage.setScene(course(100));
                }else if(type == 2){
                    type = 300;
                    primaryStage.setScene(mark(type));
                }else if(type == 3){
                    System.out.println("Course Switch Requested");
                    primaryStage.setScene(postLog(type));
                }
                //second course option for teacher leads to post log for teacher
                else if(type ==11){
                    type=2;
                    course = teacher.course[1];
                   
                    for(int i =0; i<school.students.size();i++){
                        
                     if(school.students.get(i).course[0].equals(course.getCode())){
                            System.out.println(school.students.get(i).getFname());
                            course.student.add(school.students.get(i));
                     }
                    }   System.out.println(course.getCode());
                    primaryStage.setScene(postLog(type));
                }
                //second course option for student leads to post log for student 
                 else if(type==33){
                     type=3;
                     for(int i=0; i<teacher.course.length;i++){
                        if(student.course[1].equals(teacher.course[i].getName())){
                            course=teacher.course[i];
                        }
                     }
                    primaryStage.setScene(postLog(3));
                }
            }
        });
        
        add3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(type == 4){
                    type = 100;
                    primaryStage.setScene(course(type));
                }else if(type == 2){
                    type = 9;
                    primaryStage.setScene(studentPage(type));
                }else if(type == 3){
                    System.out.println("Password Changed");
                    primaryStage.setScene(postLog(type));
                }
            }
        });
        
        add4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(type == 2){
                    System.out.println("Password Resetted");
                    primaryStage.setScene(postLog(type));
                }else if(type == 3){
                    type = 400;
                    primaryStage.setScene(studentMark(type));
                }
            }
        });
        
        add5.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(type == 2){
                    System.out.println("Average Calculated");
                    primaryStage.setScene(postLog(type));
                }
            }
        });
        
        primaryStage.setTitle("Scuffed Book");
        primaryStage.setScene(one);
        primaryStage.show();
    }
    //course page
    public Scene coursePage(int num){
            GridPane e = new GridPane();
            e.setHgap(10);
            e.setVgap(10);
            Text selection = new Text("Select a course");
            logOut.setText("Log Out");
            e.add(logOut, 20, 34);
            VBox course = new VBox();
            course.setPadding(new Insets(15, 15, 15, 15));
            course.setSpacing(15);
            course.getChildren().add(selection);
           //if teacher logged in 
        if(num==2){
             
          
                type=11;
                 if(teacher.course[0]!=null){
                
                     
                add1.setText(teacher.course[0].getName());
                     
                course.getChildren().add(add1);  
            }
                 if(teacher.course[1]!=null){
                     
                     add2.setText(teacher.course[1].getName());
                     course.getChildren().add(add2);
                 }
            
         
          
        }
        //if student logged in 
        else if (num==3){
            type =33;
            int index=0;
            if(student.course[0]!=null){
                
                for(int i =0; i <school.courses.size();i++){
                    if(student.course[0].equals(school.courses.get(i).getCode())){
                        index=i;
                        System.out.println(index);
                    }
                }
                add1.setText(school.courses.get(index).getName());
                course.getChildren().add(add1);
            }
            if(student.course[1]!=null){
                 for(int i =0; i <school.courses.size();i++){
                    if(student.course[1].equals(school.courses.get(i).getCode())){
                        index=i;
                    }
                }
                add2.setText(school.courses.get(index).getName());
                course.getChildren().add(add2);
            }
        }   
        e.getChildren().add(course);
        Scene Course =new Scene(e,500,500);
        return Course;
    }
    
    //page after the log in screen
    public Scene postLog(int num){
            GridPane e = new GridPane();
            e.setHgap(10);
            e.setVgap(10);
            logOut.setText("Log out");
            e.add(logOut,15,1);
            TextField textBox = new TextField();
            //if user logs in as an admin
        if(num==1){
            Button wipe = new Button();
            Text welcome = new Text("Welcome Admin");
            Text reset = new Text("Reset System");
            Text note = new Text("*Requires Input of Master Key");
            Text access = new Text("Access School List");
            add1.setText("School List");
            wipe.setText("Reset");
            e.add(welcome, 1, 1);
            e.add(reset, 10, 9);
            e.add(password, 10, 10);
            e.add(note, 10, 11);
            e.add(access, 1, 9);
            e.add(add1, 1, 10);
            e.add(wipe, 12, 10);
             wipe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
           
            public void handle(ActionEvent event) {
                if(password.getText().equals(admin.getMASTER_KEY()+"")){
                    admin.resetAll(admin.getMASTER_KEY());
                    Text success = new Text("system wiped");
                    e.add(success, 10, 12);
                    
                }
                else{
                    Text fail = new Text("Incorrect Key");
                    e.add(fail, 10, 12);
                }
                    
            }});
        }
        else if(num==2){
            back.setText("Back");
            e.add(back, 15, 37);
            Text text = new Text("Welcome Teacher");
            add1.setText("Add Assessments");
            add2.setText("Add Mark");
            add3.setText("Display Student List");
            add4.setText("Password Reset");
            TextField box1 = new TextField();
           // e.add(textBox, 1, 4);
            e.add(text,1,1);
            e.add(add1,1,4);
            e.add(add2, 12, 4);
            e.add(add3, 1, 7);
            e.add(add4, 12, 7);
            e.add(box1, 12, 8);
        }
        else if(num == 3){
            //e.add(logOut, 11, 1);
            Button request = new Button("Course Request");
            Text text = new Text("Welcome Student");
            //add1.setText("Course Request");
            //add2.setText("Course Swap");
            add3.setText("Change Password");
            TextField box1 = new TextField();
            //TextField box2 = new TextField();
            //TextField box3 = new TextField();
            add4.setText("Marks");
            e.add(text,1,1);
            e.add(request, 1, 8);
            //e.add(add2, 1, 16);
            e.add(add3, 3, 8);
            e.add(add4, 1, 16);
            e.add(box1, 1, 9);
            //e.add(box2, 1, 17);
            //e.add(box3, 1, 18);
            e.add(password, 3, 9);
             request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            //one of your add1 else if statements is useless cuz i did this but i aint sure which one -Angelo
            public void handle(ActionEvent event) {
                int counter=0;
                    for(int i =0; i<school.courses.size();i++){
                        if(box1.getText().equals(school.courses.get(i).getCode())){
                            try {
                                FileWriter fr = new FileWriter("Request.txt");
                                BufferedWriter br = new BufferedWriter(fr);
                                br.write(school.getName());
                                br.newLine();
                                br.write(student.getIdentification()+"");
                                br.newLine();
                                br.write(student.getLname());
                                br.newLine();
                                br.write(student.getFname());
                                br.newLine();
                                br.write(box1.getText());
                                br.close();
                            } catch (IOException ex) {
                                Logger.getLogger(TranscriptProgram.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            counter++;
                        }
                    } 
                    if(counter==1){
                        Text success = new Text("course request has been submitted");
                        e.add(success,1,10);
                    }
                    else{
                        Text fail = new Text("error in submitting course");
                        e.add(fail,1,10);
                    }
                    
                    
            }});
        }
         Scene postLog = new Scene(e,510, 500);
         return postLog;
    }
    //Type 4
    public Scene schoolPage(int num){
        GridPane e = new GridPane();
        Button teach = new Button();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut, 4, 1);
        back.setText("Back");
        e.add(back, 4, 39);
        
        Text school = new Text("Schools");
        Text s = new Text("Downsview =1");
        Text s2 = new Text("Northview = 2");
        Text schoolid = new Text("Choose School(ID)");
       
        add1.setText("View Teachers");
        add2.setText("View Students");
        add3.setText("Course Adjustments");
        e.add(school, 1, 1);
        e.add(s,1,3);
        e.add(s2, 1, 4);
        e.add(add1, 1, 39);
        e.add(add2, 2, 39);
        e.add(add3, 3, 39);
        e.add(schoolid, 1, 38);
        e.add(id, 2, 38);
        
        Scene schoolPage = new Scene(e, 500, 500);
        return schoolPage;
    }
    //Type 5
    public Scene teacherPage(int num){
        GridPane e = new GridPane();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut, 30, 1);
        back.setText("Back");
        e.add(back, 30, 44);
        
        Text teacher = new Text("Teachers");
        
        add1.setText("Add/Remove Teachers");
        e.add(teacher, 1, 1);
        e.add(add1, 1, 44);
        
        if(id.getText().equals("1")){
            read(1);
            for(int i =0;i<school.teacher.size();i++){
                Label l = new Label(school.teacher.get(i).getLname());
                Label f = new Label(school.teacher.get(i).getFname());
                Label iden = new Label(school.teacher.get(i).getIdentification()+"");
                Label DOB = new Label(school.teacher.get(i).getDateOfBirth());
                e.add(l,2,i+2);
                e.add(f,3,i+2);
                e.add(iden,4,i+2);
                e.add(DOB,5, i+2);
                
            }
        }
        else{
             read(2);
            for(int i =0;i<school.teacher.size();i++){
                Label l = new Label(school.teacher.get(i).getLname());
                Label f = new Label(school.teacher.get(i).getFname());
                Label iden = new Label(school.teacher.get(i).getIdentification()+"");
                Label DOB = new Label(school.teacher.get(i).getDateOfBirth());
                e.add(l,2,i+2);
                e.add(f,3,i+2);
                e.add(iden,4,i+2);
                e.add(DOB,5, i+2);
                
            }
        }
        
        Scene teacherPage = new Scene(e, 800, 800);
        return teacherPage;
    }
    //Type 8
    public Scene studentPage(int num){
        GridPane e = new GridPane();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut,30,1);
        back.setText("Back");
        e.add(back, 30, 44);
        
        Text student = new Text("Students");
        e.add(student, 1, 1);
        if(type == 8){
            add1.setText("Add/Remove Students");
            e.add(add1, 1, 44);
            if(id.getText().equals("1")){
            read(1);
            for(int i =0;i<school.students.size();i++){
                Label l = new Label(school.students.get(i).getLname());
                Label f = new Label(school.students.get(i).getFname());
                Label iden = new Label(school.students.get(i).getIdentification()+"");
                Label DOB = new Label(school.students.get(i).getDateOfBirth());
                e.add(l,2,i+2);
                e.add(f,3,i+2);
                e.add(iden,4,i+2);
                e.add(DOB,5, i+2);
                
            }
        }
        else{
             read(2);
            for(int i =0;i<school.students.size();i++){
                Label l = new Label(school.students.get(i).getLname());
                Label f = new Label(school.students.get(i).getFname());
                Label iden = new Label(school.students.get(i).getIdentification()+"");
                Label DOB = new Label(school.students.get(i).getDateOfBirth());
                e.add(l,2,i+2);
                e.add(f,3,i+2);
                e.add(iden,4,i+2);
                e.add(DOB,5, i+2);
                
            }
        }
            
        }else if(type == 9){
            Button sorts = new Button();
            ToggleGroup sorting = new ToggleGroup();
            RadioButton one = new RadioButton("Student ID");
            one.setToggleGroup(sorting);
            RadioButton two = new RadioButton("Last Name");
            two.setToggleGroup(sorting);
            Text sort = new Text("Sort By:");
            Text id = new Text("ID");
            Text last = new Text("Last Name");
            TextField box1 = new TextField();
            TextField box2 = new TextField();
            TextField box3 = new TextField();
            sorts.setText("Display");
            e.add(sort, 1, 4);
           // e.add(box1, 1, 5);
            e.add(one,1,5);
            //e.add(id, 1, 5);
            //e.add(box2, 6, 5);
            e.add(two, 1, 6);
            //e.add(last, 1, 6);
           // e.add(box3, 12, 5);
            e.add(sorts, 12, 10);
            sorts.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            //one of your add1 else if statements is useless cuz i did this but i aint sure which one -Angelo
            public void handle(ActionEvent event) {
                         if(one.isSelected()){
                             Student stud[]=teacher.sortBy(1, course);
                             for(int i=0; i <stud.length;i++){
                                 String lname = stud[i].getLname();
                                 String fname = stud[i].getFname();
                                 String txt = stud[i].getIdentification()+"";
                                 Text lnames = new Text(lname);
                                 Text fnames = new Text(fname);
                                 Text display = new Text(txt);
                                 
                                 e.add(display, 3, i+8);
                                 e.add(lnames,1,i+8);
                                 e.add(fnames,2,i+8);
                             }
                         }
                         else if(two.isSelected()){
                             Student stud[]=teacher.sortBy(2, course);
                             for(int i =0; i<stud.length;i++){
                                 String lname = stud[i].getLname();
                                 String fname = stud[i].getFname();
                                 String txt = stud[i].getIdentification()+"";
                                 Text lnames = new Text(lname);
                                 Text fnames = new Text(fname);
                                 Text display = new Text(txt);
                                 
                                 e.add(display, 3, i+8);
                                 e.add(lnames,1,i+8);
                                 e.add(fnames,2,i+8);
                             }
                         
                         }
            }});
        }
        
        Scene studentPage = new Scene(e, 700, 700);
        return studentPage;
    }
    //Type 6 or 7
    public Scene addRemove(int num){
        GridPane e = new GridPane();
        Button adding = new Button();
        Button remove = new Button();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut,14,1);
        back.setText("Back");
        e.add(back, 14, 15);
        
        Text alter = new Text();
        Text id = new Text("ID Num");
        Text first = new Text("First Name");
        Text last = new Text("Last Name");
        Text add = new Text("Address");
        Text date = new Text("DOB");
            
        TextField box1 = new TextField();
        TextField box2 = new TextField();
        TextField box3 = new TextField();
        TextField box4 = new TextField();
        TextField box5 = new TextField();
            
        if(num == 6){
            alter = new Text("Add/Remove Teachers");
            adding.setText("Add Teacher");
            remove.setText("Remove Teacher");
            
            adding.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                      long id = Long.parseLong(box1.getText());
                      String f = box2.getText();
                      String l = box3.getText();
                      String a = box4.getText();
                      String d = box5.getText();
                      admin.addTeacher(l,f ,a ,d , id, "Apples", school);
                    if(school.getName().charAt(0)=='D') { 
                try {
                    FileWriter file = new FileWriter("Downsview/Teacher.txt",true);
                    BufferedWriter bw = new BufferedWriter(file);
                    bw.newLine();
                    bw.write(l);
                    bw.newLine();
                    bw.write(f);
                    bw.newLine();
                    bw.write(a);
                    bw.newLine();
                    bw.write(d);
                    bw.newLine();
                    bw.write(box1.getText());
                    bw.newLine();
                    bw.write("Apples");
                    bw.newLine();
                    bw.write("ICS4U1");
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(TranscriptProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                    else{
                        try {
                    FileWriter file = new FileWriter("Northview/Teachers.txt",true);
                    BufferedWriter bw = new BufferedWriter(file);
                    bw.newLine();
                    bw.write(l);
                    bw.newLine();
                    bw.write(f);
                    bw.newLine();
                    bw.write(a);
                    bw.newLine();
                    bw.write(d);
                    bw.newLine();
                    bw.write(box1.getText());
                    bw.newLine();
                    bw.write("Apples");
                    bw.newLine();
                    bw.write("ICS4U1");
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(TranscriptProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                      
            }});
            remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                long id = Long.parseLong(box1.getText());
                      String f = box2.getText();
                      String l = box3.getText();
                      String a = box4.getText();
                      String d = box5.getText();
                       if(school.getName().charAt(0)=='D') {
                           int index =0;
                           for(int i=0; i< school.teacher.size();i++){
                               if(id== school.teacher.get(i).getIdentification()){
                                   index=i;
                                   break;
                               }
                           }
                           admin.removeTeacher(id, school.teacher.get(index), school);
                           try {
                    FileWriter file = new FileWriter("Downsview/Teacher.txt");
                    BufferedWriter bw = new BufferedWriter(file);
                    for(int i =0;i<school.teacher.size();i++){
                        bw.write(school.teacher.get(i).getLname());
                        bw.newLine();
                        bw.write(school.teacher.get(i).getFname());
                        bw.newLine();
                        bw.write(school.teacher.get(i).getAddress());
                        bw.newLine();
                        bw.write(school.teacher.get(i).getDateOfBirth());
                        bw.newLine();
                        bw.write(school.teacher.get(i).getIdentification()+"");
                        bw.newLine();
                        bw.write("Apples");
                        bw.newLine();
                        bw.write("SBI4U1");
                        bw.newLine();
                    }
                    bw.close();
                }   catch (IOException ex) {
                    
                       } 
                     }
            }});
        }else if(num == 7){
            alter = new Text("Add/Remove Students");
            adding.setText("Add Student");
            remove.setText("Remove Student");
            
             adding.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                      long id = Long.parseLong(box1.getText());
                      String f = box2.getText();
                      String l = box3.getText();
                      String a = box4.getText();
                      String d = box5.getText();
                      admin.addStudent(l,f ,a ,d , id, "Apples", school);
                    if(school.getName().charAt(0)=='D') { 
                try {
                    FileWriter file = new FileWriter("Downsview/Student.txt",true);
                    BufferedWriter bw = new BufferedWriter(file);
                    bw.newLine();
                    bw.write(l);
                    bw.newLine();
                    bw.write(f);
                    bw.newLine();
                    bw.write(a);
                    bw.newLine();
                    bw.write(d);
                    bw.newLine();
                    bw.write(box1.getText());
                    bw.newLine();
                    bw.write("Hello1");
                    bw.newLine();
                    bw.write("ICS4U1");
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(TranscriptProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                    else{
                        try {
                    FileWriter file = new FileWriter("Northview/Students.txt",true);
                    BufferedWriter bw = new BufferedWriter(file);
                    bw.newLine();
                    bw.write(l);
                    bw.newLine();
                    bw.write(f);
                    bw.newLine();
                    bw.write(a);
                    bw.newLine();
                    bw.write(d);
                    bw.newLine();
                    bw.write(box1.getText());
                    bw.newLine();
                    bw.write("Apples");
                    bw.newLine();
                    bw.write("ICS4U1");
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(TranscriptProgram.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }
                             
            }});
            remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                       long id = Long.parseLong(box1.getText());
                      String f = box2.getText();
                      String l = box3.getText();
                      String a = box4.getText();
                      String d = box5.getText();
                       if(school.getName().charAt(0)=='D') {
                           int index =0;
                           for(int i=0; i< school.students.size();i++){
                               if(id== school.teacher.get(i).getIdentification()){
                                   index=i;
                                   break;
                               }
                           }
                           admin.removeStudent(id, school.students.get(index), school);
                           try {
                    FileWriter file = new FileWriter("Downsview/Student.txt");
                    BufferedWriter bw = new BufferedWriter(file);
                    for(int i =0;i<school.students.size();i++){
                        bw.write(school.students.get(i).getLname());
                        bw.newLine();
                        bw.write(school.students.get(i).getFname());
                        bw.newLine();
                        bw.write(school.students.get(i).getAddress());
                        bw.newLine();
                        bw.write(school.students.get(i).getDateOfBirth());
                        bw.newLine();
                        bw.write(school.students.get(i).getIdentification()+"");
                        bw.newLine();
                        bw.write("Hello1");
                        bw.newLine();
                        bw.write("SBI4U1");
                        bw.newLine();
                    }
                    bw.close();
                }   catch (IOException ex) {
                    
                       } 
                     }else{
                            int index =0;
                           for(int i=0; i< school.students.size();i++){
                               if(id== school.teacher.get(i).getIdentification()){
                                   index=i;
                                   break;
                               }
                           }
                           admin.removeStudent(id, school.students.get(index), school);
                           try {
                    FileWriter file = new FileWriter("Northview/Students.txt");
                    BufferedWriter bw = new BufferedWriter(file);
                    for(int i =0;i<school.students.size();i++){
                        bw.write(school.students.get(i).getLname());
                        bw.newLine();
                        bw.write(school.students.get(i).getFname());
                        bw.newLine();
                        bw.write(school.students.get(i).getAddress());
                        bw.newLine();
                        bw.write(school.students.get(i).getDateOfBirth());
                        bw.newLine();
                        bw.write(school.students.get(i).getIdentification()+"");
                        bw.newLine();
                        bw.write("Hello1");
                        bw.newLine();
                        bw.write("SBI4U1");
                        bw.newLine();
                    }
                    bw.close();
                }   catch (IOException ex) {
                    
                       } 
                       }     
            }});
        }
            
        e.add(alter, 1, 1);
        e.add(id, 1, 4);
        e.add(box1, 1, 6);
        e.add(first, 6, 4);
        e.add(box2, 6, 6);
        e.add(last, 12, 4);
        e.add(box3, 12, 6);
        e.add(add, 1, 9);
        e.add(box4, 1, 11);
        e.add(date, 6, 9);
        e.add(box5, 6, 11);
        e.add(adding, 12, 9);
        e.add(remove, 12, 11);
        
            
        Scene addRemove = new Scene(e, 500, 500);
        return addRemove;
    }
    //Type 100
    public Scene course(int num){
        GridPane e = new GridPane();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut,6  ,1);
        back.setText("Back");
        e.add(back, 6, 39);
        Button check = new Button("Check Course");
        Button approve = new Button();
        Button deny = new Button();
        Text request = new Text("Course Requests");
        Text schoolN = new Text("School");
        String schoolName="";
        Text school1 = new Text();
        Text id = new Text("ID Num");
        String idStudent="";
        Text id1=new Text();
        Text last = new Text("Last Name");
         Text last1=new Text();
        Text first = new Text("First Name");
        Text first1=new Text();
        Text course1 = new Text("Current Course");
        Text course2 = new Text("Requested Course");
         Text courseR=new Text();
        Text prompt = new Text("Type Student ID");
        TextField box1 = new TextField();
        try{
            BufferedReader br = new BufferedReader(new FileReader("Request.txt"));
            //school 
            schoolName = br.readLine();
            school1 = new Text(schoolName);
            idStudent = br.readLine();
             id1 = new Text(idStudent);
            last1 = new Text(br.readLine());
            first1 = new Text(br.readLine());
            String courseF = br.readLine();
            String courseS = br.readLine();
            if(courseS==null){
                courseR = new Text(courseF);
                 e.add(school1,1,3);
                  e.add(id1,2,3);
                  e.add(last1,3,3);
                   e.add(first1,4,3);
                   e.add(courseR,6,3);
                  
            }
            else{
                Text courseC = new Text(courseF);
                courseR = new Text(courseS);
            }
        }
        catch(IOException ex){
        
        }
        approve.setText("Approve");
        add2.setText("Deny");
        
        e.add(request, 1, 1);
        e.add(schoolN,1,2);
        e.add(id, 2, 2);
        e.add(box1, 2, 38);
        e.add(last, 3, 2);
        e.add(first,4, 2);
        e.add(course1, 5, 2);
        e.add(course2, 6, 2);
        e.add(prompt, 1, 38);
        e.add(approve, 1, 39);
        e.add(add2, 2, 39);
        e.add(check, 3, 39);
        //if approve button is clicked
        approve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 String id = box1.getText();
                 try{
                      BufferedReader br = new BufferedReader(new FileReader("Request.txt"));
                      String s = br.readLine();
                      String idS = br.readLine();
                      String last = br.readLine();
                      String first = br.readLine();
                      String request = br.readLine();
                  int index =0;
                  //temp for student object
                  Student stud = new Student("a","b","c","d",1,"sdf");
                  //finds requested course
                   for(int i =0; i <school.courses.size();i++){
                              if(request.equals(school.courses.get(i))){
                                  index =i;
                              }
                          }
                   //finds student
                   for(int i =0; i < school.students.size();i++){
                          if(first.equals(school.students.get(i).getFname())){
                              stud = school.students.get(i);
                              for(int j =0; j< stud.course.length;j++){
                                 if(stud.course[j]==null){
                                     stud.course[j]=school.courses.get(index).getCode();
                                     break;
                                }
                              }
                              break;
                          }
                      }
                   school.courses.get(index).student.add(stud);
                   //rewrites file
                   if(s.charAt(0)=='D'){
                   FileWriter f = new FileWriter("Downsview/Student.txt");
                   BufferedWriter bw = new BufferedWriter(f);
                   for(int i =0;i <school.students.size();i++){
                       bw.write(school.students.get(i).getLname());
                       bw.newLine();
                       bw.write(school.students.get(i).getFname());
                       bw.newLine();
                       bw.write(school.students.get(i).getAddress());
                       bw.newLine();
                       bw.write(school.students.get(i).getDateOfBirth());
                       bw.newLine();
                       bw.write(school.students.get(i).getIdentification()+"");
                       bw.newLine();
                       bw.write(school.students.get(i).getPassword());
                       bw.newLine();
                       
                       String courseCode="";
                       
                       for(int j =0; j < 3;j++){
                          
                           if(school.students.get(i).course[j]!=null){
                               courseCode +=school.students.get(i).course[j]+" ";
                           }
                       }
                       bw.write(courseCode);
                       bw.newLine();
                   }
                   bw.close();
                   }
                   //if school doesn't begin with D
                   else{
                       FileWriter f = new FileWriter("Northview/Students.txt");
                   BufferedWriter bw = new BufferedWriter(f);
                   for(int i =0;i <school.students.size();i++){
                       bw.write(school.students.get(i).getLname());
                       bw.newLine();
                       bw.write(school.students.get(i).getFname());
                       bw.newLine();
                       bw.write(school.students.get(i).getAddress());
                       bw.newLine();
                       bw.write(school.students.get(i).getDateOfBirth());
                       bw.newLine();
                       bw.write(school.students.get(i).getIdentification()+"");
                       bw.newLine();
                       bw.write(school.students.get(i).getPassword());
                       bw.newLine();
                       String courseCode="";
                       
                       for(int j =0; j < school.courses.get(index).student.get(i).course.length;j++){
                           if(school.courses.get(index).student.get(i).course[j]!=null){
                               courseCode +=school.courses.get(index).student.get(i).course[j]+" ";
                           }
                       }
                       bw.write(courseCode);
                       bw.newLine();
                   }
                   }
                   }
                 catch(IOException ex){
                        System.out.println("ERROR");
            }             
                 File file = new File("Request.txt");
                 file.delete();
                 Text done = new Text("Course Approve");
                 e.add(done, 1, 40);
                 
                            
            }});
        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 try{
                      BufferedReader br = new BufferedReader(new FileReader("Request.txt"));
                      String s = br.readLine();
                      String id = br.readLine();
                      String last = br.readLine();
                      String first = br.readLine();
                      String request = br.readLine();
                      int index =0;
                      if(s.charAt(0)=='D'){
                          read(1);
                          for(int i =0; i <school.courses.size();i++){
                              if(request.equals(school.courses.get(i))){
                                  index =i;
                              }
                          }
                         if(school.courses.get(index).isFull(school.courses.get(index).student.size())==true){
                             Text full = new Text("Course is full");
                             e.add(full, 3, 38);
                             
                         } 
                         else{
                             Text full = new Text("Course is not full");
                             e.add(full, 3, 38);
                         }
                      }
                      else{
                          read(2);
                          for(int i =0; i <school.courses.size();i++){
                              if(request.equals(school.courses.get(i))){
                                  index =i;
                              }
                          }
                         if(school.courses.get(index).isFull(school.courses.get(index).student.size())==true){
                             Text full = new Text("Course is full");
                             e.add(full, 3, 38);
                             
                         } 
                         else{
                             Text full = new Text("Course is not full");
                             e.add(full, 3, 38);
                         }
                      }
                 }
                 catch(IOException ex){
                        System.out.println("ERROR");
            }             
            }});
        
              
        Scene course = new Scene(e, 800, 800);
        return course;
    }
    
    //Type 200
    //ANGELO'S COMMENT LOLOLOLOLOL so yeah added a local button for the adding marks page for teachers here
    public Scene assessment(int num){
        GridPane e = new GridPane();
        Button addAssesments = new Button();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut,14,1);
        back.setText("Back");
        e.add(back, 14, 34);
        
        Text assess = new Text("Assessments");
        Text name = new Text("Assessment Name");
        Text total = new Text("Total %");
        TextField box1 = new TextField();
        TextField box2 = new TextField();
        addAssesments.setText("Add Assessment");
        
        e.add(assess, 1, 1);
        e.add(name, 1, 4);
        e.add(total, 3, 4);
        e.add(box1, 1, 5);
        e.add(box2, 3, 5);
        e.add(addAssesments, 1, 7);
        //button to add assignments this allows me to take info from textfields to do the teacher methods
        addAssesments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            //one of your add1 else if statements is useless cuz i did this but i aint sure which one -Angelo
            public void handle(ActionEvent event) {
                           int total = Integer.parseInt(box2.getText());
                           teacher.addAssessment(box1.getText(),teacher.assignName.size()+1 , total, course);
                           //you can remove this for loop if you want i just wanted to see if each student in a course got updated and got a assignment
                           for(int i =0;i<course.student.size();i++){
                               System.out.println(course.student.get(i).getFname() + " " + course.student.get(i).assignments);
                           }
                           box1.setText("");
                           box2.setText("");
                    System.out.println("Assessment Added");    
            }});
        
        Scene assessment = new Scene(e, 500, 500);
        return assessment;
    }
    //Type 300
    //ANGELO'S COMMENT LOLOLOLOLOL so yeah added a local button for the adding marks page for teachers here  
    public Scene mark(int num){
        GridPane e = new GridPane();
        //button to add mark
        Button addMark = new Button();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut,14,1);
        back.setText("Back");
        e.add(back, 14, 34);
        
        Text student = new Text("Student Marks");
        Text assess = new Text("Assessment Name");
        Text name = new Text("Student ID");
        Text markval = new Text("Mark");
        TextField box1 = new TextField();
        TextField box2 = new TextField();
        TextField box3 = new TextField();
        addMark.setText("Add Mark");
        
        e.add(student, 1, 1);
        e.add(assess, 1, 4);
        e.add(name, 3, 4);
        e.add(markval, 1, 7);
        e.add(box1, 1, 5);
        e.add(box2, 3, 5);
        e.add(box3, 1, 8);
        e.add(addMark, 1, 10);
        //forgot which add1 method this made useless
        addMark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                        //takes infor from text box
                            long id = Long.parseLong(box2.getText());
                           double given = Double.parseDouble(box3.getText());
                           
                           //finds index of student
                         int index= teacher.search(id, teacher.sortBy(1, course) , 0, course.student.size());
                          int entry =0;
                          //finds out index of mark entry by matching the names 
                         for(int i =0; i <course.student.get(index).assignments.size();i++){
                             if(course.student.get(index).assignments.get(i).equals(box1.getText())){
                                 entry =i;
                             }
                         }
                            //adds marks 
                           teacher.addMark(id,given,course.student.get(index).marks.get(entry),entry, course);
                           //this is to see if the student's given mark got updated 
                           System.out.println(course.student.get(index).marks.get(0).getGivenMark());
                           //resets textboxs btw to blank
                           box1.setText("");
                           box2.setText("");
                           box3.setText("");
                           //updates teacher course 
                           
                           for(int i =0; i <teacher.course.length-1;i++){
                                if(teacher.course[i].getCode().endsWith(course.getCode())){
                                    teacher.course[i]=course;
                                }
                                    }
                   System.out.println("Marks Added");   
            }});
        
        Scene mark = new Scene(e, 500, 500);
        return mark;
    }
    //Type 400
    public Scene studentMark(int num){
        GridPane e = new GridPane();
        e.setHgap(10);
        e.setVgap(10);
        logOut.setText("Log out");
        e.add(logOut,31,1);
        back.setText("Back");
        e.add(back, 31, 43);
        
        Text marks = new Text("Marks");
        Text assessment = new Text("Assessments");
        Text mark = new Text("Mark");
        Text total = new Text("Total");
        int index=0;
       
        String fileName = student.getIdentification()+"";
         if(fileName.charAt(1)=='2'){
             fileName = "Downsview/"+student.getIdentification()+"";
        }
         else{
             fileName = "Northview/"+student.getIdentification()+"";
         }
        FileReader file;
       
      try {
          file = new FileReader(fileName+".txt");
           BufferedReader br = new BufferedReader(file);
           
             for(int i =0; i<course.student.get(index).assignments.size();i++){ 
                  String line = br.readLine();
          
               
               String data =line;
               System.out.println(data.length());
               int counter =7;
               int space =0;
               if(data.substring(0, 6).equals(course.getCode())){
                   while(counter<data.length()){
                       if(data.charAt(counter)==' '){
                           space =counter;
                           counter =88;
                       }
                       counter++;
                   }
                Text assign = new Text(data.substring(7,space));
                Text earned = new Text(data.substring(space+1,space+5));
                Text totals = new Text(data.substring(space+5));
                 e.add(assign, 1, i+5);
                e.add(earned,10,i+5);
                 e.add(totals,20,i+5);
               }
               //line=br.readLine();
           
             }
      } catch (IOException ex) {
          Logger.getLogger(TranscriptProgram.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("Error");
      }

        e.add(marks, 1, 1);
        e.add(assessment, 1, 4);
        e.add(mark, 10, 4);
        e.add(total, 20, 4);
        
        Scene studentMark = new Scene(e, 500, 500);
        return studentMark;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

