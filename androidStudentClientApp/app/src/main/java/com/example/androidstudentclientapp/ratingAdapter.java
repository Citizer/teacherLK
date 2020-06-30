package com.example.androidstudentclientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ratingAdapter extends ArrayAdapter<student> {
    //адаптер для отображения оценок студентов
    private LayoutInflater inflater;
    private int layout;
    private List<student> students;


    public ratingAdapter(Context context, int resource, List<student> students) {
        super(context, resource, students);
        this.students = students;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);
        final student student = students.get(position);

        TextView nameView = (TextView) view.findViewById(R.id.nameStudentInRating);
        nameView.setText(student.getStudentName());
        Spinner spinnerMark = (Spinner) view.findViewById(R.id.spinnerMark);

        if(student.getMark() != 0) {
            spinnerMark.setSelection(student.getMark() - 1);
        }

        AdapterView.OnItemSelectedListener itemDateSelectListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                student.setMark(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinnerMark.setOnItemSelectedListener(itemDateSelectListener);

        return view;
    }
}
