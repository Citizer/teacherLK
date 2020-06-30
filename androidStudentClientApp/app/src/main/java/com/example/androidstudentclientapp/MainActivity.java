package com.example.androidstudentclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String login = myPreferences.getString("Login", "0");
        String password = myPreferences.getString("Password", "0");
        //Проверям авторизован ли пользователь в приложении
        if (login == "0") {
            showLoginActivity();
        } else {
            userData test = new userData(login, password, "");
            //Валидация пользовательских данные на сервере
            NetworkService.getInstance()
                    .getJSONApi()
                    .loginUser(test)
                    .enqueue(new Callback<userData>() {
                        @Override
                        public void onResponse(@NonNull Call<userData> call, @NonNull Response<userData> response) {
                            userData post = response.body();
                            if (post != null) {
                                SharedPreferences.Editor myEditor = myPreferences.edit();
                                myEditor.putString("AccessToken", post.getAcessToken());
                                myEditor.putLong("teacherId", post.getPlatoonID());
                                myEditor.commit();

                                showBaciActivity();
                            } else {
                                showLoginActivity();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<userData> call, @NonNull Throwable t) {
                            ProgressBar progressBar = findViewById(R.id.progressBar);
                            progressBar.setVisibility(View.INVISIBLE);
                            TextView textView = findViewById(R.id.textView2);
                            textView.setText("Ошибка! Отсутствует соединение с сервером.");
                        }
                    });
        }
    }


    private void showBaciActivity() {
        //Открытие основого окна приложения
        Intent intent = new Intent(this, main.class);
        startActivity(intent);
    }

    private void showLoginActivity() {
        //Открытие окна аутентификцаии
        Intent intent = new Intent(this, login.class);
        startActivity(intent);

    }
}


