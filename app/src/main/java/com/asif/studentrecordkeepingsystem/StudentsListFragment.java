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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Asif Mehmood on 18/10/19.
 */


public class StudentsListFragment extends Fragment {

    ListView studentList;
    StudentDatabase studentDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        studentDatabase = new StudentDatabase(getActivity().getApplicationContext());

        View view =  inflater.inflate(R.layout.fragment_students_list, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab_add_new_student);
        studentList = (ListView) view.findViewById(R.id.students_list);
        StudentsListAdapter studentsListAdapter = new StudentsListAdapter(getActivity().getApplicationContext(), studentDatabase.extractAllStudents());
        studentList.setAdapter(studentsListAdapter);
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity().getApplicationContext(), "Student List Item Clicked", Toast.LENGTH_SHORT).show();
            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please enter the values in the fields and Click Save Button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Fragment fragment = new AddStudentFragment();
                FragmentTransaction ft = ((MainActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Students List");
    }
}