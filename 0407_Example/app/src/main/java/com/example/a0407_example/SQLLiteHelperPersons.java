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

public class SQLLiteHelperPersons extends SQLiteOpenHelper {
    private static final int database_VERSION = 1;
    private static final String database_NAME = "ExamApp";
    private static final String table_PERSONS = "Persons";

    private static final String P_Column_Id = "Id";
    private static final String P_Column_Name = "Name";
    private static final String P_Column_Surname = "Surname";
    private static final String P_Column_Email = "Email";
    private static final String P_Column_Password = "Password";
    private static final String P_Column_Phone = "Phone";
    private static final String P_Column_Photo = "Photo";
    private static final String P_Column_BirthDate = "BirthDate";
    private static final String P_Column_Gender = "Gender";
    private static final String P_Column_Confirm = "Confirm";

    private static final String createTable_PERSONS = "CREATE TABLE " + table_PERSONS + " ( " +
            P_Column_Id + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            P_Column_Name + " TEXT, " +
            P_Column_Surname + " TEXT, " +
            P_Column_Email + " TEXT, " +
            P_Column_Password + " TEXT, " +
            P_Column_Phone + " INTEGER, " +
            P_Column_Photo + " TEXT, " +
            P_Column_BirthDate + " TEXT, " +
            P_Column_Gender + " TEXT, " +
            P_Column_Confirm + " TEXT " +
            " )";


    public SQLLiteHelperPersons(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable_PERSONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + table_PERSONS);
        onCreate(db);
    }

    public void dropTable(SQLiteDatabase db, String tableName){
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void AddPerson(Person person){
        Log.d("CREATION", "AddPerson started.");

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(P_Column_Name, person.getName());
            cv.put(P_Column_Surname, person.getSurname());
            cv.put(P_Column_Email, person.getEmail());
            cv.put(P_Column_Password, person.getPassword());
            cv.put(P_Column_Phone, person.getPhone());
            cv.put(P_Column_Photo, person.getPhoto());
            cv.put(P_Column_BirthDate, person.getBirthDate());
            cv.put(P_Column_Gender, person.getGender());
            cv.put(P_Column_Confirm, String.valueOf(person.getConfirm()));
            db.insert(table_PERSONS, null, cv);
            db.close();
        } catch (Exception ex){
            Log.d("CREATION", ex.toString());
        }


    }

    public ArrayList<Person> GetPersons(){
        ArrayList<Person> persons = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = { P_Column_Id, P_Column_Name, P_Column_Surname, P_Column_Email, P_Column_Password, P_Column_Phone, P_Column_Photo, P_Column_BirthDate, P_Column_Gender, P_Column_Confirm };
        Cursor cursor = db.query(table_PERSONS, columns, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Person person = new Person();
            person.setId(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setSurname(cursor.getString(2));
            person.setEmail(cursor.getString(3));
            person.setPassword(cursor.getString(4));
            person.setPhone(cursor.getInt(5));
            person.setPhoto(cursor.getString(6));
            person.setBirthDate(cursor.getString(7));
            person.setGender(cursor.getString(8));
            person.setConfirm(Boolean.getBoolean(cursor.getString(9)));
            persons.add(person);
        }

        return persons;
    }

    public Person GetPersonWithEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = { P_Column_Id, P_Column_Name, P_Column_Surname, P_Column_Email, P_Column_Password, P_Column_Phone, P_Column_Photo, P_Column_BirthDate, P_Column_Gender, P_Column_Confirm };
        Cursor cursor = db.query(table_PERSONS, columns, " Email = ?", new String[]{email}, null, null, null, null);

        Person person = new Person();
        if(cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            person.setId(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setSurname(cursor.getString(2));
            person.setEmail(cursor.getString(3));
            person.setPassword(cursor.getString(4));
            person.setPhone(cursor.getInt(5));
            person.setPhoto(cursor.getString(6));
            person.setBirthDate(cursor.getString(7));
            person.setGender(cursor.getString(8));
            person.setConfirm(Boolean.getBoolean(cursor.getString(9)));
        }
        cursor.close();

        return person;
    }

    public Person GetPersonWithEmailAndPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = { P_Column_Id, P_Column_Name, P_Column_Surname, P_Column_Email, P_Column_Password, P_Column_Phone, P_Column_Photo, P_Column_BirthDate, P_Column_Gender, P_Column_Confirm };
        Cursor cursor = db.query(table_PERSONS, columns, " Email = ? AND Password = ? ", new String[]{email, password}, null, null, null, null);

        Person person = new Person();
        if(cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            person.setId(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setSurname(cursor.getString(2));
            person.setEmail(cursor.getString(3));
            person.setPassword(cursor.getString(4));
            person.setPhone(cursor.getInt(5));
            person.setPhoto(cursor.getString(6));
            person.setBirthDate(cursor.getString(7));
            person.setGender(cursor.getString(8));
            person.setConfirm(Boolean.getBoolean(cursor.getString(9)));
        }
        cursor.close();

        return person;
    }
}
