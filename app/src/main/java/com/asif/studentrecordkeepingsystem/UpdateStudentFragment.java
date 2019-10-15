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


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateStudentFragment extends Fragment {

    Button goBack, updateStudent, deleteStudent;
    EditText rollNo, name, studyProgram, city, cellNo;

    public UpdateStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_student, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Update Student");
        Bundle bundle = getArguments();
        Student student = (Student) bundle.getSerializable("student");

        rollNo = view.findViewById(R.id.std_name_update_EditText);
        rollNo.setText(student.getStd_roll_number());
        name = view.findViewById(R.id.std_roll_no_update_EditText);
        name.setText(student.getStd_name());
        studyProgram = view.findViewById(R.id.std_study_program_update_EditText);
        studyProgram.setText(student.getStd_Study_Program());
        city = view.findViewById(R.id.std_city_update_EditText);
        city.setText(student.getCity());
        cellNo = view.findViewById(R.id.std_cell_no_update_EditText);
        cellNo.setText(student.getCell_no());

        goBack = view.findViewById(R.id.btn_go_back_students_list_update_student);
        goBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        updateStudent = view.findViewById(R.id.btn_add_student_update_student);
        updateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDatabase studentDatabase = new StudentDatabase(getActivity().getApplicationContext());
                try {
                    if (rollNo.getText().toString().trim().length() == 0 ||
                            name.getText().toString().trim().length() == 0 ||
                            studyProgram.getText().toString().trim().length() == 0 ||
                            city.getText().toString().trim().length() == 0 ||
                            cellNo.getText().toString().trim().length() == 0
                    ) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean test = studentDatabase.updateStudent(new Student(
                                rollNo.getText().toString(),
                                name.getText().toString(),
                                studyProgram.getText().toString(),
                                city.getText().toString(),
                                cellNo.getText().toString()
                        ));
                        if (test) {
                            Toast.makeText(getActivity().getApplicationContext(), "Student Updated", Toast.LENGTH_SHORT).show();
                        }
                        goBack();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed: Exception occurred.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteStudent = view.findViewById(R.id.btn_delete_student_update_student);
        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDatabase studentDatabase = new StudentDatabase(getActivity().getApplicationContext());
                try {
                    if (rollNo.getText().toString().trim().length() == 0
                    ) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please enter roll Number.", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean test = studentDatabase.deleteStudent(new Student(
                                rollNo.getText().toString(),
                                name.getText().toString(),
                                studyProgram.getText().toString(),
                                city.getText().toString(),
                                cellNo.getText().toString()
                        ));
                        if (test) {
                            Toast.makeText(getActivity().getApplicationContext(), "Student Deleted", Toast.LENGTH_SHORT).show();
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
        Fragment fragment = new StudentsListFragment();
        FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

}
