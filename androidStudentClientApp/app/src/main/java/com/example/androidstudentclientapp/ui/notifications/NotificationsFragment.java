package com.example.androidstudentclientapp.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidstudentclientapp.Lesson;
import com.example.androidstudentclientapp.LessonAdapter;
import com.example.androidstudentclientapp.NetworkService;
import com.example.androidstudentclientapp.R;
import com.example.androidstudentclientapp.login;
import com.example.androidstudentclientapp.main;
import com.example.androidstudentclientapp.notification;
import com.example.androidstudentclientapp.notificationAdapter;
import com.example.androidstudentclientapp.sendNotificationActivity;
import com.example.androidstudentclientapp.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    ListView notificationList;
    SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    public main mainQuiz;
    List<notification> notifications = new ArrayList();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationList = (ListView) root.findViewById(R.id.notificationListView);
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        setHasOptionsMenu(true);

        return root;
    }



    public void createNewNotification(View view) {
        Intent intent = new Intent(this.getContext(), sendNotificationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        final String testToken = "Bearer " + myPreferences.getString("AccessToken", "0");
        //Получаем список отправленных преподавателей уведомлений
        NetworkService.getInstance()
                .getJSONApi()
                .testNotificationTeacher(testToken)
                .enqueue(new Callback<List<notification>>() {
                    @Override
                    public void onResponse(Call<List<notification>> call, Response<List<notification>> response) {
                        notifications = response.body();
                        if(notifications.size() != 0) {
                            //Создаём адаптер для listView
                            notificationAdapter notificationAdapter = new notificationAdapter(getContext(), R.layout.list_notification, notifications);
                            notificationList.setAdapter(notificationAdapter);
                        }// mainQuiz.lessonsTeacher = lessonsTeacher;
                    }

                    @Override
                    public void onFailure(Call<List<notification>> call, Throwable t) {
                        Integer e = 5;
                    }
                });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.notificationmenu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //Нажатие на кнопку создания нового уведомления
        if (id == R.id.newNotification) {
            Intent intent = new Intent(getContext(), sendNotificationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
