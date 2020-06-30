package com.example.androidstudentclientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class notificationAdapter extends ArrayAdapter<notification> {
    //Адаптер для отображения списка уведомлений
    private LayoutInflater inflater;
    private int layout;
    private List<notification> notifications;


    public notificationAdapter(Context context, int resource, List<notification> notifications) {
        super(context, resource, notifications);
        this.notifications = notifications;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);
        final notification notification = notifications.get(position);

        TextView nameView = (TextView) view.findViewById(R.id.nameFrom);
        nameView.setText(notification.getPlatoonName());
        TextView dateView = (TextView) view.findViewById(R.id.dateFrom);
        dateView.setText(notification.getDate());
        TextView text = (TextView) view.findViewById(R.id.textNotification);
        text.setText(notification.getText());

        return view;
    }
}
