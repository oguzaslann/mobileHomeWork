package com.example.a0407_example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class AddQuestionActivity extends AppCompatActivity {

    Button imageButton, senderButton;
    ImageView imageView;
    static final int SELECT_IMAGE = 5;
    Uri imageUri;
    Context context = this;
    SeekBar difficultyLevel;
    EditText description, answer1, answer2, answer3, answer4, correctAnswer;
    int difficultyLevelCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        defineVeriables();

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

        senderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder validationSb = validations();

                if(validationSb.length() > 0){
                    Toast.makeText(getApplicationContext(), validationSb.toString(), Toast.LENGTH_SHORT).show();
                } else{

                    try {
                        SQLLiteHelperQuestions sqlLiteHelperQuestions = new SQLLiteHelperQuestions(AddQuestionActivity.this);


                    /*
                    sqlLiteHelperQuestions.dropTable(sqlLiteHelperQuestions.getWritableDatabase(), "Questions");
                    sqlLiteHelperQuestions.onCreate(sqlLiteHelperQuestions.getWritableDatabase());
                     */

                            Question question = new Question();
                            question.setDescription(description.getText().toString().trim());
                            question.setAnswer1(answer1.getText().toString().trim());
                            question.setAnswer2(answer2.getText().toString().trim());
                            question.setAnswer3(answer3.getText().toString().trim());
                            question.setAnswer4(answer4.getText().toString().trim());
                            question.setCorrectAnswer(correctAnswer.getText().toString().trim());
                            question.setDifficultyLevel(difficultyLevelCount);
                            if(imageUri.toString() != ""){
                                question.setPhoto(imageUri.toString());
                            }
                            sqlLiteHelperQuestions.AddQuestion(question);

                            Toast.makeText(getApplicationContext(), "Kayıt başarılı.", Toast.LENGTH_SHORT).show();
                            Intent questionsIntent = new Intent(AddQuestionActivity.this, QuestionActivity.class);
                            AddQuestionActivity.this.startActivity(questionsIntent);


                    } catch (Exception ex){
                        Log.d("CREATION", ex.toString());
                    }
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageUploadIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageUploadIntent.setType("image/*");
                startActivityForResult(imageUploadIntent, SELECT_IMAGE);
            }
        });

    }

    public void defineVeriables() {
        description = findViewById(R.id.description);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        correctAnswer = findViewById(R.id.correctAnswer);
        imageButton = findViewById(R.id.imageButton);
        imageView = findViewById(R.id.imageView);
        difficultyLevel = findViewById(R.id.difficultyLevel);
        difficultyLevel.setMax(5);
        senderButton = findViewById(R.id.senderButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    public StringBuilder validations() {
        StringBuilder sb = new StringBuilder("");
        if (description.getText().toString().trim().length() < 1) {
            sb.append("Soru boş bırakılamaz.\n");
        }
        if (answer1.getText().toString().trim().length() < 1) {
            sb.append("Cevap A boş bırakılamaz.\n");
        }
        if (answer2.getText().toString().trim().length() < 1) {
            sb.append("Cevap B boş bırakılamaz.\n");
        }
        if (answer3.getText().toString().trim().length() < 1) {
            sb.append("Cevap C boş bırakılamaz.\n");
        }
        if (answer4.getText().toString().trim().length() < 1) {
            sb.append("Cevap D boş bırakılamaz.\n");
        }
        if (correctAnswer.getText().toString().trim().length() < 1) {
            sb.append("Doğru cevap boş bırakılamaz.\n");
        }

        return sb;
    }
}