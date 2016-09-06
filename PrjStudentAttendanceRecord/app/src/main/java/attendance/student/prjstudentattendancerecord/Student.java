/*
Student.java
Contains student attributes. This class will be instantiated
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/4/16
 */
package attendance.student.prjstudentattendancerecord;

public class Student {

    private String studNumber;
    private String studName;
    private String dateOfCheckin;
    private String timeOfCheckIn;
    private String moduleCode;
    private String courseCode;
    private String timeOfCheckOut;
    //***********************************************************************************************
    public Student(){

    }
    //***********************************************************************************************
    public Student(String num,String modCode, String checkIn, String checkout, String dateCheckIn)
    {
        studNumber = num;
        moduleCode = modCode;
        timeOfCheckIn = checkIn;
        timeOfCheckOut = checkout;
        dateOfCheckin = dateCheckIn;
    }
    //***********************************************************************************************
    public Student(String studNumber, String studName, String dateOfCheckin, String timeOfCheckIn, String moduleCode, String courseCode, String timeOfCheckOut)
    {
        this.studNumber = studNumber;
        this.studName = studName;
        this.dateOfCheckin = dateOfCheckin;
        this.timeOfCheckIn = timeOfCheckIn;
        this.moduleCode = moduleCode;
        this.courseCode = courseCode;
        this.timeOfCheckOut = timeOfCheckOut;
    }
    //***********************************************************************************************
    public String getCourseCode() {
        return courseCode;
    }
    //***********************************************************************************************
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    //***********************************************************************************************
    public String getDateOfCheckin() {
        return dateOfCheckin;
    }
    //***********************************************************************************************
    public String getTimeOfCheckIn() {
        return timeOfCheckIn;
    }
    //***********************************************************************************************
    public String getTimeOfCheckOut() {
        return timeOfCheckOut;
    }
    //***********************************************************************************************
    public String getStudNumber() {
        return studNumber;
    }
    //***********************************************************************************************
    public void setStudNumber(String studNumber) {
        this.studNumber = studNumber;
    }
    //***********************************************************************************************
    public String getModuleCode() {
        return moduleCode;
    }
    //***********************************************************************************************
    public String getStudName() {
        return studName;
    }
    //***********************************************************************************************
    public void setStudName(String studName) {
        this.studName = studName;
    }
    //***********************************************************************************************
}
