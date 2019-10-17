package com.asif.studentrecordkeepingsystem;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentsListForEnrollmentFragment extends Fragment {

    ListView studentList;
    StudentDatabase studentDatabase;

    public StudentsListForEnrollmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        studentDatabase = new StudentDatabase(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.fragment_students_list_for_enrollment, container, false);

        studentList = (ListView) view.findViewById(R.id.students_list_for_enrollment);
        ArrayList<Student> list = studentDatabase.extractAllStudents();
        EnrollStudentsListAdapter studentsListAdapter = new EnrollStudentsListAdapter(getActivity().getApplicationContext(), list);
        studentList.setAdapter(studentsListAdapter);
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                studentList.getItemIdAtPosition(position);
                Fragment fragment = new EnrollStudentsFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                Student student = (Student) studentList.getItemAtPosition(position);
                bundle.putSerializable("student", student);
                fragment.setArguments(bundle);
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
                getActivity().setTitle("Enroll Student");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Enroll Student in Courses");
    }

}
