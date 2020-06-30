package com.example.androidstudentclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    //Activity авторизации пользователя
    SharedPreferences myPreferences;
    private Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public void clikInLogin (View view) {
        final EditText editTextLogin = this.findViewById(R.id.editTextLogin);
        final EditText editTextPassword = this.findViewById(R.id.editTextPassword);
        userData test = new userData(editTextLogin.getText().toString(), editTextPassword.getText().toString(), "");
        //Отправляем данные авторизации на сервер
        NetworkService.getInstance()
                .getJSONApi()
                .loginUser(test)
                .enqueue(new Callback<userData>() {
                    @Override
                    public void onResponse(@NonNull Call<userData> call, @NonNull Response<userData> response) {
                        userData post = response.body();
                        if (post != null) {
                            //В случае успешной валидации сохраняем их локально
                            SharedPreferences.Editor myEditor = myPreferences.edit();
                            myEditor.putString("Login", editTextLogin.getText().toString());
                            myEditor.putString("Password", editTextPassword.getText().toString());
                            myEditor.putString("AccessToken", post.getAcessToken());
                            myEditor.putLong("teacherId", post.getPlatoonID());
                            myEditor.commit();

                            showBaciActivity();
                        }
                        else {
                            showToast();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<userData> call, @NonNull Throwable t) {
                        showToast();
                    }
                });
    }

    private void showToast() {
        Toast.makeText(this, "Ошибка! Неверный логин или пароль!",
                Toast.LENGTH_SHORT).show();
    }

    private void showBaciActivity() {
        Intent intent = new Intent(this, main.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
        finish();
        System.runFinalizersOnExit(true);
        System.exit(0);
    }
    @Override
    protected void onStop(){
        super.onStop();
        finish();
    }
}
