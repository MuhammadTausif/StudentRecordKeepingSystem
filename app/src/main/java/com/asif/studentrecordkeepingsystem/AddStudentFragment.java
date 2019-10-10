package com.asif.studentrecordkeepingsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddStudentFragment extends Fragment {

    Button btnAddStudent, btnGoBack;
    TextView stdRollNo, stdName, stdStudyProgram, stdCity, stdCellNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);

        btnAddStudent = (Button) view.findViewById(R.id.btn_add_student);
        btnGoBack = (Button) view.findViewById(R.id.btn_go_back_students_list);

        // Inflating TextViews
        stdRollNo = (EditText) view.findViewById(R.id.std_roll_no);
        stdName = (EditText) view.findViewById(R.id.std_name);
        stdStudyProgram = (EditText) view.findViewById(R.id.std_study_program);
        stdCity = (EditText) view.findViewById(R.id.std_city);
        stdCellNo = (EditText) view.findViewById(R.id.std_cell_no);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDatabase studentDatabase = new StudentDatabase(getActivity().getApplicationContext());
                try {
                    if (stdRollNo.getText().toString().trim().length() == 0 ||
                            stdName.getText().toString().trim().length() == 0 ||
                            stdStudyProgram.getText().toString().trim().length() == 0 ||
                            stdCity.getText().toString().trim().length() == 0 ||
                            stdCellNo.getText().toString().trim().length() == 0
                    ) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean test = studentDatabase.insertStudent(new Student(
                                stdRollNo.getText().toString(),
                                stdName.getText().toString(),
                                stdStudyProgram.getText().toString(),
                                stdCity.getText().toString(),
                                stdCellNo.getText().toString()
                                ));
                        if (test) {
                            Toast.makeText(getActivity().getApplicationContext(), "Student Inserted", Toast.LENGTH_SHORT).show();
                        }
                        goBack();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Failed: Exception occurred.", Toast.LENGTH_SHORT).show();
                }
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
        Fragment fragment = new StudentsListFragment();
        FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Add New Student");
    }

}
