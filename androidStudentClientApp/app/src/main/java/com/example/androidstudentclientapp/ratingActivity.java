package com.example.androidstudentclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ratingActivity extends AppCompatActivity {
    //Активити редактирования оценок за занятие
    SharedPreferences myPreferences;
    List<student> students = new ArrayList();
    ListView ratingList;
    Spinner spinner;
    String testToken;
    Long id;
    Context curContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Bundle arguments = getIntent().getExtras();
        //Получаем название дисцпиплины и дату проведения
        String name = arguments.get("discip").toString();
        String date = arguments.get("date").toString();
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        testToken = "Bearer " + myPreferences.getString("AccessToken", "0");
        ratingList = (ListView) this.findViewById(R.id.ratingList);

        id = arguments.getLong("id");
        TextView discipView = this.findViewById(R.id.discip);
        TextView dateView = this.findViewById(R.id.date);
        discipView.setText(name);
        dateView.setText(date);
        curContext = this.getApplicationContext();
        //Выполняем получение уже выставленных оценок с сервера
        NetworkService.getInstance()
                .getJSONApi()
                .getRating(id, testToken)
                .enqueue(new Callback<List<student>>() {
                    @Override
                    public void onResponse(Call<List<student>> call, Response<List<student>> response) {
                        students = response.body();
                        setAdapter();
                    }

                    @Override
                    public void onFailure(Call<List<student>> call, Throwable t) {
                        Integer e = 5;
                    }
                });
    }


    private void setAdapter() {
    ratingAdapter adapter = new ratingAdapter(this, R.layout.list_rating, students);
    ratingList.setAdapter(adapter);
    }

    public void cancel(View view) {
        finish();
    }

    public void save(View view) {
        //Функция сохранения оценок
        List<student> test = students;
        NetworkService.getInstance()
                .getJSONApi()
                .postRating(id, testToken, students)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.body().getMessage() == "ok") {
                            Toast.makeText(curContext, "Изменения сохранены",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(curContext, "Изменения успешно сохранены",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t)
                    {
                        Toast.makeText(curContext, "Отсутствует соединение с сервером",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
