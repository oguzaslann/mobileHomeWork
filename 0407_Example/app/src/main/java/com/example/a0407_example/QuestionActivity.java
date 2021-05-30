package com.example.a0407_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        recyclerView = findViewById(R.id.recylerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        try {

            ArrayList<Question> questions = new ArrayList<>();
            SQLLiteHelperQuestions sqlLiteHelper = new SQLLiteHelperQuestions(this);

            /*
            sqlLiteHelper.dropTable(sqlLiteHelper.getWritableDatabase(), "Questions");
            sqlLiteHelper.onCreate(sqlLiteHelper.getWritableDatabase());

            ArrayList<Question> defaultQuestionsList = new ArrayList<>();
            defaultQuestionsList = Question.getDefaultQuestionsList();
            for (int i=0; i < defaultQuestionsList.size(); i++){
                sqlLiteHelper.AddQuestion(defaultQuestionsList.get(i));
            }
             */

            questions = sqlLiteHelper.GetQuestions();

            QuestionAdapter questionAdapter = new QuestionAdapter(questions, context);
            recyclerView.setAdapter(questionAdapter);

        } catch (Exception ex){
            Log.d("CREATION", ex.toString());
        }

    }
}