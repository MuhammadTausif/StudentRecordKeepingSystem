package com.asif.studentrecordkeepingsystem;

public class Course {

    String course_Code, course_Name, cridet_Hours;

    public Course(){}
    public Course(String course_Code, String course_Name, String cridet_Hours) {
        this.course_Code = course_Code;
        this.course_Name = course_Name;
        this.cridet_Hours = cridet_Hours;
    }

    public String getCourse_Code() {
        return course_Code;
    }

    public void setCourse_Code(String course_Code) {
        this.course_Code = course_Code;
    }

    public String getCourse_Name() {
        return course_Name;
    }

    public void setCourse_Name(String course_Name) {
        this.course_Name = course_Name;
    }

    public String getCridet_Hours() {
        return cridet_Hours;
    }

    public void setCridet_Hours(String cridet_Hours) {
        this.cridet_Hours = cridet_Hours;
    }
}
