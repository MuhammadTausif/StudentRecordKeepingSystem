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
 * Created by Asif Mehmoood on 18/10/19.
 */


public class CoursesListFragment extends Fragment {

    ListView coursesList;
    StudentDatabase studentDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        studentDatabase = new StudentDatabase(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.fragment_courses_list, container, false);
        coursesList = view.findViewById(R.id.courses_list);

        CoursesListAdapter coursesListAdapter = new CoursesListAdapter(getActivity().getApplicationContext(), studentDatabase.extractAllCourses());
        coursesList.setAdapter(coursesListAdapter);
        coursesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
//                Fragment fragment = new AddCourseFragment();
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, fragment);
//                ft.commit();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab_add_new_course);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please enter the values in the fields and Click Save Button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Fragment fragment = new AddCourseFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
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
        getActivity().setTitle("Courses List");
    }
}