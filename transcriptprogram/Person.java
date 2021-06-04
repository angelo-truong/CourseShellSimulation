/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transcriptprogram;

/**
 *
 * @author 324786953
 */
//searching method
public class Person {
    //person's first name
    private String fname;
    //persons last name
    private String lname;
    //persons address
    private String address;
    //person date of birth
    private String dateOfBirth;
    
    public Person(String lnam, String fnam, String add, String dob){
        lname=lnam;
        fname=fnam;
        address=add;
        dateOfBirth=dob;
    }
    
    public String getFname(){
        return fname;
    }

    public String getLname(){
        return lname;
    }

    public String getAddress(){
        return address;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }
}
