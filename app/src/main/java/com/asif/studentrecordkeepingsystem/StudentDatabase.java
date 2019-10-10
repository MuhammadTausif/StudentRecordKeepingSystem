package com.asif.studentrecordkeepingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_STUDENT = "tbl_student";
    public static final String TABLE_COURSE = "tbl_course";
    public static final String TABLE_STUDENT_COURSE = "tbl_student_course";

    public StudentDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public StudentDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_STUDENT + " (std_roll_no TEXT,std_name TEXT, std_study_program TEXT, city TEXT, std_cell_no TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_COURSE + " (course_code TEXT,course_name TEXT, cridet_hours INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_STUDENT_COURSE + " (std_roll_no INTEGER,std_course_code TEXT);");

    }

    public boolean insertStudent(Student student) {
        try{
            SQLiteDatabase dbl = this.getWritableDatabase();
            ContentValues studentValues = new ContentValues();
            studentValues.put("std_roll_no", student.getStd_roll_number());
            studentValues.put("std_name", student.getStd_name());
            studentValues.put("std_study_program", student.getStd_Study_Program());
            studentValues.put("city", student.getCity());
            studentValues.put("std_cell_no", student.getCell_no());

            long result = dbl.insert(TABLE_STUDENT, null, studentValues);

            dbl.close();
            if (result == -1)
                return false;
            else
                return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean insertCourse(Course course){
        try{
            SQLiteDatabase dbl = this.getWritableDatabase();
            ContentValues studentValues = new ContentValues();
            studentValues.put("course_code", course.getCourse_Code());
            studentValues.put("course_name", course.getCourse_Name());
            studentValues.put("cridet_hours", course.getCridet_Hours());

            long result = dbl.insert(TABLE_COURSE, null, studentValues);

            dbl.close();
            if (result == -1)
                return false;
            else
                return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean insertStudentCourse(Student student, Course course){
        try{
            SQLiteDatabase dbl = this.getWritableDatabase();
            ContentValues studentValues = new ContentValues();
            studentValues.put("std_roll_no", student.getStd_roll_number());
            studentValues.put("std_course_code", course.getCourse_Code());

            long result = dbl.insert(TABLE_STUDENT_COURSE, null, studentValues);

            dbl.close();
            if (result == -1)
                return false;
            else
                return true;
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<Student> extractAllStudents(){
        try {
            ArrayList<Student> studentList = new ArrayList<Student>();
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
            if(cursor.moveToFirst()){
                do{
                    Student student = new Student();
                    student.setStd_roll_number(cursor.getString(0));
                    student.setStd_name(cursor.getString(1));
                    student.setStd_Study_Program(cursor.getString(2));
                    student.setCity(cursor.getString(3));
                    student.setCell_no(cursor.getString(4));

                    studentList.add(student);
                }while (cursor.moveToNext());
            }
            return studentList;
        }catch (Exception e){
            return null;
        }
    }

    public List<Course> extractAllCourses(){
        try {
            ArrayList<Course> coursesList = new ArrayList<Course>();
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COURSE, null);
            if(cursor.moveToFirst()){
                do{
                    Course course = new Course();
                    course.setCourse_Code(cursor.getString(0));
                    course.setCourse_Name(cursor.getString(1));
                    course.setCridet_Hours(cursor.getString(2));

                    coursesList.add(course);
                }while (cursor.moveToNext());
            }
            return coursesList;
        }catch (Exception e){
            return null;
        }
    }

    public void updateStudent(Student student){

    }

    public void updateCourse(Course course){

    }

    public boolean deleteStudent(Student student){
        return false;
    }

    public boolean deleteStudent(Course course){ return false;}


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
