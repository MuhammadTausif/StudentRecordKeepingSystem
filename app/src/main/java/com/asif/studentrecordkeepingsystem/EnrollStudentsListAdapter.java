package com.asif.studentrecordkeepingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class EnrollStudentsListAdapter extends ArrayAdapter<Student> {
    private Context context;
    private List<Student> students;

    public EnrollStudentsListAdapter(Context context, List<Student> students) {
        super(context, R.layout.fragment_students_list_for_enrollment, students);
        this.context = context;
        this.students = students;
    }

    public EnrollStudentsListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.students_list, parent, false);

        TextView student_roll_no = (TextView) view.findViewById(R.id.student_roll_no);
        TextView student_name = (TextView) view.findViewById(R.id.student_name);
        TextView student_study_program = (TextView) view.findViewById(R.id.student_study_program);
        TextView student_city = (TextView) view.findViewById(R.id.student_city);
        TextView student_cell_no = (TextView) view.findViewById(R.id.student_cell_no);

        student_roll_no.setText("Roll Number: " + students.get(position).getStd_roll_number());
        student_name.setText("Name: " + students.get(position).getStd_name());
        student_study_program.setText("Study Program: " + students.get(position).getStd_Study_Program());
        student_city.setText("City: " + students.get(position).getCity());
        student_cell_no.setText("Cell #: " + students.get(position).getCell_no());

        return view;
    }
}
