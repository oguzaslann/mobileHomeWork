package com.example.a0407_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    LinearLayout addQuestion, questions, addExam, examManagement;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        defineVeriables();
        LocalDataManager localDataManager = new LocalDataManager();
        String name = localDataManager.getSharedPreference(getApplicationContext(), "name", "");
        welcome.setText("Hoşgeldiniz, " + name);

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addQuestionIntent = new Intent(MenuActivity.this, AddQuestionActivity.class);
                MenuActivity.this.startActivity((addQuestionIntent));
            }
        });

        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionsIntent = new Intent(MenuActivity.this, QuestionActivity.class);
                MenuActivity.this.startActivity((questionsIntent));
            }
        });

        addExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addQuestionIntent = new Intent(MenuActivity.this, AddQuestionActivity.class);
                MenuActivity.this.startActivity((addQuestionIntent));
            }
        });

        examManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent examSettingIntent = new Intent(MenuActivity.this, ExamSettingActivity.class);
                MenuActivity.this.startActivity((examSettingIntent));
            }
        });
    }

    public void defineVeriables(){
        welcome = findViewById(R.id.welcome);
        addQuestion = findViewById(R.id.addQuestion);
        questions = findViewById(R.id.questions);
        addExam = findViewById(R.id.addExam);
        examManagement = findViewById(R.id.examManagement);
    }
}