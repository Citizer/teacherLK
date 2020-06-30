package com.example.androidstudentclientapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class main extends AppCompatActivity {
    public List<Lesson> lessonsTeacher = new ArrayList();
    public List<Lesson> lessonsPlatoon = new ArrayList();

    public List<teacher> teacherList = new ArrayList();
    public teacher teacher = new teacher();
    public List<platoon> platoonList = new ArrayList();
    private Timer mTimer;
    public Boolean flag = false;
    public Boolean flagFirst = true;
   // private MyTimerTask mMyTimerTask;
   SharedPreferences myPreferences;

    ListView countriesList;

    public AdapterView.OnClickListener itemLisener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintest);
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String login = myPreferences.getString("Login", "0");
        //Если в системе нет логина и пароля пользователя - открываем окно аутентификации
        if (login == "0") showLoginActivity();
        if (login == "") showLoginActivity();

        final String testToken = "Bearer " + myPreferences.getString("AccessToken", "0");
        //Получаем список преподавателей с сервера
        NetworkService.getInstance()
                .getJSONApi()
                .testTeacherList(testToken)
                .enqueue(new Callback<List<teacher>>() {
                    @Override
                    public void onResponse(Call<List<teacher>> call, Response<List<teacher>> response) {
                        //Получаем список взводов с сервера
                        teacherList = response.body();
                        NetworkService.getInstance()
                                .getJSONApi()
                                .testPlatoonList(testToken)
                                .enqueue(new Callback<List<platoon>>() {
                                    @Override
                                    public void onResponse(Call<List<platoon>> call, Response<List<platoon>> response) {
                                        //Отображаем фрагменты
                                        platoonList = response.body();
                                        createFragment();
                                    }

                                    @Override
                                    public void onFailure(Call<List<platoon>> call, Throwable t) {
                                        Integer e = 5;
                                    }
                                });
                    }

                    @Override
                    public void onFailure(Call<List<teacher>> call, Throwable t) {
                        Integer e = 5;
                    }
                });


    }


    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
        finish();
        System.runFinalizersOnExit(true);
        System.exit(0);
        this.onDestroy();
    }

    public void createFragment() {
        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfigurations = new AppBarConfiguration.Builder(
                R.id.navigation_home ,R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfigurations);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public List<teacher> getTeacherList() {
        return teacherList;
    }

    public List<platoon> getPlatoonList() {
        return platoonList;
    }

    private void showLoginActivity() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);

    }
}
