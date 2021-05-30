package com.example.a0407_example;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;

public class SQLLiteHelperQuestions extends SQLiteOpenHelper {
    private static final int database_VERSION = 1;
    private static final String database_DB_PATH = "/data/data/com.example.a0407_example/databases";
    private static final String database_NAME = "ExamApp";
    private static final String table_PERSONS = "Persons";
    private static final String table_QUESTIONS = "Questions";

    private static final String Q_Column_Id = "Id";
    private static final String Q_Column_Description = "Description";
    private static final String Q_Column_Answer1 = "Answer1";
    private static final String Q_Column_Answer2 = "Answer2";
    private static final String Q_Column_Answer3 = "Answer3";
    private static final String Q_Column_Answer4 = "Answer4";
    private static final String Q_Column_CorrectAnswer = "CorrectAnswer";
    private static final String Q_Column_DifficultyLevel = "DifficultyLevel";
    private static final String Q_Column_Photo = "Photo";

    private static final String createTable_QUESTIONS = "CREATE TABLE " + table_QUESTIONS + " ( " +
            Q_Column_Id + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            Q_Column_Description + " TEXT, " +
            Q_Column_Answer1 + " TEXT, " +
            Q_Column_Answer2 + " TEXT, " +
            Q_Column_Answer3 + " TEXT, " +
            Q_Column_Answer4 + " TEXT, " +
            Q_Column_CorrectAnswer + " TEXT, " +
            Q_Column_DifficultyLevel + " INTEGER, " +
            Q_Column_Photo + " TEXT " +
            " )";


    public SQLLiteHelperQuestions(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable_QUESTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + table_QUESTIONS);
        onCreate(db);
    }

    public void dropTable(SQLiteDatabase db, String tableName){
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void AddQuestion(Question question){
        Log.d("CREATION", "AddQuestion started.");

        try {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Q_Column_Description, question.getDescription());
        cv.put(Q_Column_Answer1, question.getAnswer1());
        cv.put(Q_Column_Answer2, question.getAnswer2());
        cv.put(Q_Column_Answer3, question.getAnswer3());
        cv.put(Q_Column_Answer4, question.getAnswer4());
        cv.put(Q_Column_CorrectAnswer, question.getCorrectAnswer());
        cv.put(Q_Column_DifficultyLevel, question.getDifficultyLevel());
        cv.put(Q_Column_Photo, question.getPhoto());
        db.insert(table_QUESTIONS, null, cv);
        db.close();
        } catch (Exception ex){
            Log.d("CREATION", ex.toString());
        }
    }

    public ArrayList<Question> GetQuestions(){
        ArrayList<Question> questions = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = { Q_Column_Id, Q_Column_Description, Q_Column_Answer1, Q_Column_Answer2, Q_Column_Answer3, Q_Column_Answer4, Q_Column_CorrectAnswer, Q_Column_DifficultyLevel, Q_Column_Photo };
        Cursor cursor = db.query(table_QUESTIONS, columns, null, null, null, null, null, null);
        while (cursor.moveToNext()){
        Question question = new Question();
            question.setId(cursor.getInt(0));
            question.setDescription(cursor.getString(1));
            question.setAnswer1(cursor.getString(2));
            question.setAnswer2(cursor.getString(3));
            question.setAnswer3(cursor.getString(4));
            question.setAnswer4(cursor.getString(5));
            question.setCorrectAnswer(cursor.getString(6));
            question.setDifficultyLevel(cursor.getInt(7));
            question.setPhoto(cursor.getString(8));

            questions.add(question);
        }

        return questions;
    }

    public Question GetQuestionWithId(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = { Q_Column_Id, Q_Column_Description, Q_Column_Answer1, Q_Column_Answer2, Q_Column_Answer3, Q_Column_Answer4, Q_Column_CorrectAnswer, Q_Column_DifficultyLevel, Q_Column_Photo };
        Cursor cursor = db.query(table_QUESTIONS, columns, " Id = ?", new String[]{id}, null, null, null, null);

        Question question = new Question();
        if(cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            question.setId(cursor.getInt(0));
            question.setDescription(cursor.getString(1));
            question.setAnswer1(cursor.getString(2));
            question.setAnswer2(cursor.getString(3));
            question.setAnswer3(cursor.getString(4));
            question.setAnswer4(cursor.getString(5));
            question.setCorrectAnswer(cursor.getString(6));
            question.setDifficultyLevel(cursor.getInt(7));
            question.setPhoto(cursor.getString(8));
        }
        cursor.close();

        return question;
    }

    public void UpdateQuestion(Question question){
        Log.d("CREATION", "UpdateQuestion started.");

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Q_Column_Description, question.getDescription());
            cv.put(Q_Column_Answer1, question.getAnswer1());
            cv.put(Q_Column_Answer2, question.getAnswer2());
            cv.put(Q_Column_Answer3, question.getAnswer3());
            cv.put(Q_Column_Answer4, question.getAnswer4());
            cv.put(Q_Column_CorrectAnswer, question.getCorrectAnswer());
            cv.put(Q_Column_DifficultyLevel, question.getDifficultyLevel());
            cv.put(Q_Column_Photo, question.getPhoto());
            String[] whereArgs = new String[] {String.valueOf(question.getId())};
            db.update(table_QUESTIONS, cv, " id =? ", whereArgs);
            db.close();
        } catch (Exception ex){
            Log.d("CREATION", ex.toString());
        }
    }

    public void DeleteQuestion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Questions WHERE Id = " + id);
    }
}
