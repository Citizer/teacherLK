package com.example.androidstudentclientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LessonAdapter extends ArrayAdapter<Lesson> {
    //Адаптер для отображения расписания занятий
    private LayoutInflater inflater;
    private int layout;
    private List<Lesson> lessons;

    public LessonAdapter(Context context, int resource, List<Lesson> lessons) {
        super(context, resource, lessons);
        this.lessons = lessons;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);
        Lesson lesson = lessons.get(position);

        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView temView = (TextView) view.findViewById(R.id.tem);
        TextView roomView = (TextView) view.findViewById(R.id.room);
        TextView timeView = (TextView) view.findViewById(R.id.time);
        TextView teacherView = (TextView) view.findViewById(R.id.platoon);
        teacherView.setText(lesson.getPlatoon());
        TextView platoonView = (TextView) view.findViewById(R.id.teacher);
        platoonView.setText(lesson.getTeacher());

        nameView.setText(lesson.getSubject());
        temView.setText(lesson.getTheme());
        roomView.setText(lesson.getClassroom());
        timeView.setText(lesson.getTimeStart() + "-" + lesson.getTimeEnd());

        return view;
    }
}