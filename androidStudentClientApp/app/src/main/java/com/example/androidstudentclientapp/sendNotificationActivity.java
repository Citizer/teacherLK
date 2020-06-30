package com.example.androidstudentclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sendNotificationActivity extends AppCompatActivity {
    //Activity создания нового уведомления
    notification notification = new notification();
    Spinner spinner;
    EditText message;
    SharedPreferences myPreferences;
    String testToken;
    Context thisContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        spinner = this.findViewById(R.id.spinner3);
        message = this.findViewById(R.id.editText);
        testToken = "Bearer " + myPreferences.getString("AccessToken", "0");
        thisContext = this;
    }


    public void cancelS(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void sendMessage (View view) {
        if (spinner.getSelectedItemId() == 0) {
            notification.setPlatoonId((long) -1);
        } else {
            notification.setPlatoonId(spinner.getSelectedItemId());
        }
        notification.setText(message.getText().toString());
        //Отправка уведомления на сервер
        NetworkService.getInstance()
                .getJSONApi()
                .postMessage(testToken, notification)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                       String message = response.body().getMessage();
                       if (message.equals("ok")) {
                           Toast.makeText(thisContext, "Уведомление отправлено!",
                                   Toast.LENGTH_SHORT).show();
                       } else {
                           Toast.makeText(thisContext, "Ошибка!",
                                   Toast.LENGTH_SHORT).show();
                       }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                        Toast.makeText(thisContext, "Ошибка 1!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
