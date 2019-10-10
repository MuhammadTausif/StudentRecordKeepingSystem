package com.asif.studentrecordkeepingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CoursesListAdapter extends ArrayAdapter<Course> {
    private Context context;
    private List<Course> courses;

    public CoursesListAdapter(Context context, List<Course> courses) {
        super(context, R.layout.fragment_courses_list, courses);
        this.context = context;
        this.courses = courses;
    }

    public CoursesListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.courses_list, parent, false);

        TextView courses_code = (TextView) view.findViewById(R.id.course_code_textView);
        TextView courses_name = (TextView) view.findViewById(R.id.course_name_textView);
        TextView courses_credit_hours = (TextView) view.findViewById(R.id.course_credit_hours_textView);

         courses_code.setText("Course Code: " + courses.get(position).getCourse_Code());
        courses_name.setText("Course Name: " + courses.get(position).getCourse_Name());
        courses_credit_hours.setText("Credit Hours: " + courses.get(position).getCridet_Hours());

        return view;
    }
}
