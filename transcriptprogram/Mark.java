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
public class Mark{
    
    private String assignmentName;
    private int assignmentNumber;
    private double givenMark;
    private int maxMark;
    private long Studentid;
    
    Mark(String aNam, int aNum, double gMark, int mMark,long id){
        assignmentName=aNam;
        assignmentNumber=aNum;
        givenMark=gMark;
        maxMark=mMark;
        Studentid=id;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }

    public void setAssignmentNumber(int assignmentNumber) {
        this.assignmentNumber = assignmentNumber;
    }

    public double getGivenMark() {
        return givenMark;
    }

    public void setGivenMark(double givenMark) {
        this.givenMark = givenMark;
    }

    public int getMaxMark() {
        return maxMark;
    }

    public void setMaxMark(int maxMark) {
        this.maxMark = maxMark;
    }
}
