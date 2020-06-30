package com.example.androidstudentclientapp.ui.home;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidstudentclientapp.NetworkService;
import com.example.androidstudentclientapp.R;
import com.example.androidstudentclientapp.Lesson;
import com.example.androidstudentclientapp.LessonAdapter;
import com.example.androidstudentclientapp.login;
import com.example.androidstudentclientapp.main;
import com.example.androidstudentclientapp.platoon;
import com.example.androidstudentclientapp.ratingActivity;
import com.example.androidstudentclientapp.userData;
import com.example.androidstudentclientapp.teacher;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class scheduleFragment extends Fragment implements ListView.OnItemClickListener{

    private List<Lesson> lessonsPlatoon = new ArrayList();
    private List<Lesson> lessonsTeacher = new ArrayList();
    SharedPreferences myPreferences;
    private List<teacher> teacherList = new ArrayList();
    private List<platoon> platoonList = new ArrayList();
    private Long teacherId;
    Spinner spinnerTeacher;
    Spinner spinnerPlatoon;
    Spinner spinnerDate;
    ListView countriesList;
    public main mainQuiz;
    Boolean flag;
    private SharedPreferences.Editor myEditor;
    AdapterView.OnItemSelectedListener itemPlatoonSelected;
    Boolean testFlag = false;
    Integer position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        mainQuiz = (main) getActivity();
        //Получаем пользовательские данные
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        teacherId = myPreferences.getLong("teacherId", 0);

        final String accessToken = "Bearer " + myPreferences.getString("AccessToken", "0");

        myEditor = myPreferences.edit();
        setHasOptionsMenu(true);
       // final Spinner test = root.findViewById(R.id.spinnerTeacherOrPlatoon);
        spinnerPlatoon = root.findViewById(R.id.spinnerPlatoon);

         spinnerTeacher = root.findViewById(R.id.spinnerTeacher);
        final teacher teacher = new teacher();
        teacherList = mainQuiz.getTeacherList();
        platoonList = mainQuiz.getPlatoonList();
        lessonsTeacher = mainQuiz.lessonsTeacher;
        lessonsPlatoon = mainQuiz.lessonsPlatoon;
        spinnerDate = root.findViewById(R.id.date);
        countriesList = (ListView) root.findViewById(R.id.countriesList);

        flag = mainQuiz.flag;

        ArrayAdapter<teacher> adapterTeacher = new ArrayAdapter<teacher>(getContext(), android.R.layout.simple_list_item_1, teacherList);
        spinnerTeacher.setAdapter(adapterTeacher);
        ArrayAdapter<platoon> adapterPlatoon = new ArrayAdapter<platoon>(getContext(), android.R.layout.simple_list_item_1, platoonList);
        spinnerPlatoon.setAdapter(adapterPlatoon);
        //Устанавливаем адаптеры списка взводов и преподов


        //Слушатель выбора списка преподавателей
        AdapterView.OnItemSelectedListener itemTeacherSelected = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                final teacher item = (teacher) parent.getItemAtPosition(position);
                //Получаем список преподавателей с сервера
                    NetworkService.getInstance()
                            .getJSONApi()
                            .testLessonTeacher(item.getId(), accessToken)
                            .enqueue(new Callback<List<Lesson>>() {
                                @Override
                                public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                                    lessonsTeacher = response.body();
                                    //Создаём адаптер для listView
                                    LessonAdapter lessonAdapter = new LessonAdapter(getContext(), R.layout.list_item, lessonsTeacher);
                                    countriesList.setAdapter(lessonAdapter);
                                    mainQuiz.lessonsTeacher = lessonsTeacher;
                                }

                                @Override
                                public void onFailure(Call<List<Lesson>> call, Throwable t) {
                                    Integer e = 5;
                                }
                            });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    LessonAdapter lessonAdapter = new LessonAdapter(getContext(), R.layout.list_item, lessonsTeacher);
                    countriesList.setAdapter(lessonAdapter);
            }
        };

        final platoon item = (platoon) platoonList.get(0);
        NetworkService.getInstance()
                .getJSONApi()
                .testLessonPlatoon(item.getId(), accessToken)
                .enqueue(new Callback<List<Lesson>>() {
                    @Override
                    public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                        lessonsPlatoon = response.body();
                        //Создаём адаптер для listView
                        mainQuiz.lessonsPlatoon = lessonsPlatoon;
                    }

                    @Override
                    public void onFailure(Call<List<Lesson>> call, Throwable t) {
                        Integer e = 5;
                    }
                });


        //Слушатель выбора списка взводов
         itemPlatoonSelected = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                final platoon item = (platoon) parent.getItemAtPosition(position);
                //Получаем список взводов с сервера
                    NetworkService.getInstance()
                            .getJSONApi()
                            .testLessonPlatoon(item.getId(), accessToken)
                            .enqueue(new Callback<List<Lesson>>() {
                                @Override
                                public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                                    lessonsPlatoon = response.body();
                                    //Создаём адаптер для listView
                                    LessonAdapter lessonAdapter = new LessonAdapter(getContext(), R.layout.list_item, lessonsPlatoon);
                                    countriesList.setAdapter(lessonAdapter);
                                    mainQuiz.lessonsPlatoon = lessonsPlatoon;
                                    myEditor.putInt("idPlatoonDefaultSelected", item.getId());
                                    myEditor.commit();

                                }

                                @Override
                                public void onFailure(Call<List<Lesson>> call, Throwable t) {
                                    Integer e = 5;
                                }
                            });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    LessonAdapter lessonAdapter = new LessonAdapter(getContext(), R.layout.list_item, lessonsPlatoon);
                    countriesList.setAdapter(lessonAdapter);
            }
        };


        spinnerTeacher.setOnItemSelectedListener(itemTeacherSelected);

        //Слушатель выбора даты из списка
        AdapterView.OnItemSelectedListener itemDateSelectListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinnerDate.setOnItemSelectedListener(itemDateSelectListener);
        countriesList.setOnItemClickListener(this);
        //Открываем расписание авторизованного преподавателя
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherId == teacherList.get(i).getId()) {
                position = i;
            }
        }
        spinnerTeacher.setSelection(position);

        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        //Нажатие на занятие в расписании для выставления оценки
        Lesson selectedLesson = (Lesson)parent.getItemAtPosition(position);

        Intent intent = new Intent(getContext(), ratingActivity.class);
        if (selectedLesson.getTeacherid() == teacherId) {
            intent.putExtra("discip", selectedLesson.getSubject());
            intent.putExtra("date", spinnerDate.getSelectedItem().toString());
            intent.putExtra("id", selectedLesson.getId());
            startActivity(intent);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Кастомное меню
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.spinnermenu, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) item.getActionView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.typeSchedule, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        //Слушатель выбора списка переключения режима отображения расписание (расписание преподавателей/взводов)
        AdapterView.OnItemSelectedListener testSelected = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                if (position == 1) {
                    Integer e = 5;
                    spinnerPlatoon.setVisibility(View.INVISIBLE);
                    spinnerTeacher.setVisibility(View.VISIBLE);
                    LessonAdapter lessonAdapter = new LessonAdapter(getContext(), R.layout.list_item, lessonsTeacher);
                    countriesList.setAdapter(lessonAdapter);
                } else {
                    testFlag = false;

                    flag = false;
                    spinnerPlatoon.setOnItemSelectedListener(itemPlatoonSelected);
                    LessonAdapter lessonAdapter = new LessonAdapter(getContext(), R.layout.list_item, lessonsPlatoon);
                    countriesList.setAdapter(lessonAdapter);
                    spinnerPlatoon.setVisibility(View.VISIBLE);
                    spinnerTeacher.setVisibility(View.INVISIBLE);

                }
                String item = (String) parent.getItemAtPosition(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinner.setOnItemSelectedListener(testSelected);
        spinner.setSelection(1);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //Выход пользователя из системы
        if (id == R.id.exit) {
            myEditor.putString("Login", "");
            myEditor.putString("Password", "");
            myEditor.putString("AccessToken", "");
            myEditor.commit();
            Intent intent = new Intent(getContext(), login.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        spinnerTeacher.setSelection(position);
    }
}
