package com.asif.studentrecordkeepingsystem;

import java.io.Serializable;

public class Student implements Serializable {
    String std_roll_number, std_name, std_Study_Program, city, cell_no;

    public Student( ) { }

    public Student(String std_roll_number, String std_name, String std_Study_Program, String city, String cell_no) {
        this.std_roll_number = std_roll_number;
        this.std_name = std_name;
        this.std_Study_Program = std_Study_Program;
        this.city = city;
        this.cell_no = cell_no;
    }

    public String getStd_roll_number() {
        return std_roll_number;
    }

    public void setStd_roll_number(String std_roll_number) {
        this.std_roll_number = std_roll_number;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getStd_Study_Program() {
        return std_Study_Program;
    }

    public void setStd_Study_Program(String std_Study_Program) {
        this.std_Study_Program = std_Study_Program;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCell_no() {
        return cell_no;
    }

    public void setCell_no(String cell_no) {
        this.cell_no = cell_no;
    }
}
