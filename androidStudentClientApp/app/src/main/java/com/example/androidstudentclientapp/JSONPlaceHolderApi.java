package com.example.androidstudentclientapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {


    @POST("/api/auth/signin")
    Call<userData> loginUser(@Body userData body);

    @GET("/api/test/check2")
    Call<Post> testDate(@Query("id") Integer id,
                        @Query("par") Integer par,
                       // @Headers({"Authorization", "Bearer "+ }));
                        @Header("Authorization")  String AccessToken);

    @GET("/api/test/check")
    Call<Post> testHeader(@Header("Bearer") String AccessToken);

    @GET("/api/test/teachers")
    Call<List<teacher>> testTeacherList(
            @Header("Authorization")  String AccessToken
    );

    @GET("/api/test/platoons")
    Call<List<platoon>> testPlatoonList(
            @Header("Authorization")  String AccessToken
    );
    @GET("/api/test/lessons/{id}")
    Call<List<Lesson>> testLessonPlatoon(
            @Path("id") Integer id,
            @Header("Authorization")  String AccessToken
    );

    @GET("/api/test/teacher_lessons/{id}")
    Call<List<Lesson>> testLessonTeacher(
            @Path("id") Long id,
            @Header("Authorization")  String AccessToken
    );

    @GET("/api/test/lesson_marks/{id}")
    Call<List<student>> getRating(
            @Path("id") Long id,
            @Header("Authorization")  String AccessToken
    );

    @POST("/api/test/lesson_marks/{id}")
    Call<Post> postRating(
            @Path("id") Long id,
            @Header("Authorization")  String AccessToken,
            @Body List<student> student
    );

    @GET("api/test/teacher_messages")
    Call<List<notification>> testNotificationTeacher(
            @Header("Authorization")  String AccessToken
    );
    @POST("/api/test/teacher_post_message")
    Call<Post> postMessage(
            @Header("Authorization")  String AccessToken,
            @Body notification notification
    );

}