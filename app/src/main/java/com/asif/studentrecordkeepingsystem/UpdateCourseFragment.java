package com.asif.studentrecordkeepingsystem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class UpdateCourseFragment extends Fragment {

    Button goBack, updateCourse, deleteCourse;
    EditText courseCode, courseName, creditHours;

    public UpdateCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_course, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Update Course");
        Bundle bundle = getArguments();
        Course course = (Course) bundle.getSerializable("course");

        courseCode = view.findViewById(R.id.course_code_update_EditText);
        courseCode.setText(course.getCourse_Code());
        courseName = view.findViewById(R.id.course_name_update_EditText);
        courseName.setText(course.getCourse_Name());
        creditHours = view.findViewById(R.id.credit_hours_update_EditText);
        creditHours.setText(course.getCridet_Hours());

        goBack = view.findViewById(R.id.btn_go_back_courses_list_update_course);
        goBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        updateCourse = view.findViewById(R.id.btn_update_course_update_course);
        updateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDatabase studentDatabase = new StudentDatabase(getActivity().getApplicationContext());
                try {
                    if (courseCode.getText().toString().trim().length() == 0 ||
                            courseName.getText().toString().trim().length() == 0 ||
                            creditHours.getText().toString().trim().length() == 0
                    ) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean test = studentDatabase.updateCourse(new Course(
                                courseCode.getText().toString(),
                                courseName.getText().toString(),
                                creditHours.getText().toString()
                        ));
                        if (test) {
                            Toast.makeText(getActivity().getApplicationContext(), "Course Updated", Toast.LENGTH_SHORT).show();
                        }
                        goBack();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed: Exception occurred.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goBack() {
        Fragment fragment = new CoursesListFragment();
        FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }
}
