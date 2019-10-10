package com.asif.studentrecordkeepingsystem;

public class EnrolledStudents {

    String studentRollNo, courseCode;

    public EnrolledStudents(){}

    public EnrolledStudents(String studentRollNo, String courseCode) {
        this.studentRollNo = studentRollNo;
        this.courseCode = courseCode;
    }

    public String getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(String studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
