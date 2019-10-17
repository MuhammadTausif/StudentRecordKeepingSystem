package com.asif.studentrecordkeepingsystem;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnrollStudentsFragment extends Fragment {


    ListView coursesList;
    ListView unenrolledCoursesList;

    Student selectedStudent;
    TextView selectedStudentText;
    StudentDatabase studentDatabase;

    public EnrollStudentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enroll_student, container, false);

        getActivity().setTitle("Enroll Student in Courses");
        Bundle bundle = getArguments();
        final Student student = (Student) bundle.getSerializable("student");
        studentDatabase = new StudentDatabase(getActivity().getApplicationContext());

        coursesList = view.findViewById(R.id.course_list_for_enrollment);
        unenrolledCoursesList= view.findViewById(R.id.course_list_for_unenrollment);

        selectedStudentText = view.findViewById(R.id.student_name_for_enrollment);
        selectedStudentText.setText(student.getStd_name() + " ( Roll No: " + student.getStd_roll_number() + " )");

        CoursesEnrollListAdapter coursesListAdapter = new CoursesEnrollListAdapter(getActivity().getApplicationContext(), studentDatabase.extractEnrolledAllCourses( student.getStd_roll_number()));
        CoursesUnenrollListAdapter ucoursesListAdapter = new CoursesUnenrollListAdapter(getActivity().getApplicationContext(), studentDatabase.extractEnrolledAllCourses( student.getStd_roll_number()));
        coursesList.setAdapter(coursesListAdapter);
        coursesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = (Course) coursesList.getItemAtPosition(position);
                if(studentDatabase.insertStudentCourse(student, course))
                    Toast.makeText(getActivity().getApplicationContext(), "Inserted \nStudent: " + student.getStd_name() + ", Course: " + course.getCourse_Code(), Toast.LENGTH_SHORT).show();
            }
        });
        unenrolledCoursesList.setAdapter(ucoursesListAdapter);

        return view;
    }

}
