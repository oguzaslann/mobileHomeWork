package com.example.a0407_example;

import android.app.Person;

import java.util.ArrayList;

public class Question {
    private int id;
    private String description;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private int difficultyLevel;
    private String photo;

    public Question() {
    }

    public Question(String description, String answer1, String answer2, String answer3, String answer4, String correctAnswer, int difficultyLevel, String photo){
        this.description = description;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
        this.photo = photo;
    }

    public int getId() { return id; }
    public String getDescription() { return  description; }
    public String getAnswer1() { return  answer1; }
    public String getAnswer2() { return  answer2; }
    public String getAnswer3() { return  answer3; }
    public String getAnswer4() { return  answer4; }
    public String getCorrectAnswer() { return  correctAnswer; }
    public int getDifficultyLevel() { return  difficultyLevel; }
    public String getPhoto() { return  photo; }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static ArrayList<Question> getDefaultQuestionsList(){

      ArrayList questions = new ArrayList();
        questions.add( new Question("Soru 1", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap A", 1, "" ));
        questions.add( new Question("Soru 2", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap B", 3, "" ));
        questions.add( new Question("Soru 3", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap C", 4, "" ));
        questions.add( new Question("Soru 4", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap D", 2, "" ));
        questions.add( new Question("Soru 5", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap A", 5, "" ));
        questions.add( new Question("Soru 6", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap B", 2, "" ));
        questions.add( new Question("Soru 7", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap C", 2, "" ));
            questions.add( new Question("Soru 8", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap D", 4, "" ));
        questions.add( new Question("Soru 9", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap A", 3, "" ));
        questions.add( new Question("Soru 10", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap B", 2, "" ));
        questions.add( new Question("Soru 11", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap C", 1, "" ));
        questions.add( new Question("Soru 12", "Cevap A", "Cevap B", "Cevap C", "Cevap D", "Cevap D", 5, "" ));

        return  questions;
    }


}
