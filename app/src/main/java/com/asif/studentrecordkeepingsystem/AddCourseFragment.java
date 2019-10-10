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

public class AddCourseFragment extends Fragment {

    Button btnAddCourse, btnGoBack;
    EditText editTextCourseCode, editTextCourseName, editTextCreditHours;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);

        // Inflating buttons
        btnAddCourse = view.findViewById(R.id.btn_add_course);
        btnGoBack = view.findViewById(R.id.btn_go_back_courses_list);

        // Inflating editTexts
        editTextCourseCode = view.findViewById(R.id.course_code_editText);
        editTextCourseName = view.findViewById(R.id.course_name_editText);
        editTextCreditHours = view.findViewById(R.id.credit_hours_editText);

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCourse();
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        return view;
    }

    public void goBack() {
        Fragment fragment = new CoursesListFragment();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void addNewCourse() {
        StudentDatabase studentDatabase = new StudentDatabase(getActivity().getApplicationContext());
        try {
            if (editTextCourseCode.getText().toString().trim().length() == 0 ||
                    editTextCourseName.getText().toString().trim().length() == 0 ||
                    editTextCreditHours.getText().toString().trim().length() == 0
            ) {
                Toast.makeText(getActivity().getApplicationContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show();
            } else {
                boolean test = studentDatabase.insertCourse(new Course(
                        editTextCourseCode.getText().toString(),
                        editTextCourseName.getText().toString(),
                        editTextCreditHours.getText().toString()
                ));
                if (test) {
                    Toast.makeText(getActivity().getApplicationContext(), "Course Inserted", Toast.LENGTH_SHORT).show();
                }
                goBack();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), "Failed: Exception occurred.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add New Course");
    }

}
