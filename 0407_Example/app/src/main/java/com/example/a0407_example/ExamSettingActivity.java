package com.example.a0407_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ExamSettingActivity extends AppCompatActivity {

    TextView examTime, examPoint;
    SeekBar difficultyLevel;
    Button saveButton;
    int difficultyLevelCount;
    LocalDataManager localDataManager = new LocalDataManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_setting);

        defineVeriables();
        setInputs();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localDataManager.setSharedPreference(getApplicationContext(), "defaultExamTime", examTime.getText().toString().trim());
                localDataManager.setSharedPreference(getApplicationContext(), "defaultExamPoint", examPoint.getText().toString().trim());
                localDataManager.setSharedPreference(getApplicationContext(), "defaultDifficultyLevel", String.valueOf(difficultyLevelCount));

                Toast.makeText(getApplicationContext(), "Kayıt başarılı.",Toast.LENGTH_SHORT).show();

                Intent menuIntent = new Intent(ExamSettingActivity.this, MenuActivity.class);
                ExamSettingActivity.this.startActivity((menuIntent));
            }
        });

        difficultyLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                difficultyLevelCount = progress;
            }
        });
    }

    public void defineVeriables(){
        examTime = findViewById(R.id.examTime);
        examPoint = findViewById(R.id.examPoint);
        difficultyLevel = findViewById(R.id.difficultyLevel);
        saveButton = findViewById(R.id.saveButton);
    }

    private void setInputs() {
        String defaultExamTime = localDataManager.getSharedPreference(getApplicationContext(), "defaultExamTime", "");
        if (defaultExamTime != null) {
            examTime.setText(defaultExamTime);
        }
        String defaultExamPoint = localDataManager.getSharedPreference(getApplicationContext(), "defaultExamPoint", "");
        if (defaultExamPoint != null) {
            examPoint.setText(defaultExamPoint);
        }
        String defaultDifficultyLevel = localDataManager.getSharedPreference(getApplicationContext(), "defaultDifficultyLevel", "");
        if (defaultDifficultyLevel != null) {
            difficultyLevel.setProgress(Integer.parseInt(defaultExamTime));
        }
    }
}