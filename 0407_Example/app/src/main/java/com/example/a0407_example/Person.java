package com.example.a0407_example;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class Person {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int phone;
    private String photo;
    private String birthDate;
    private String gender;
    private boolean confirm;

    public Person() {
    }

    public Person(String name, String surname, String email, String password, int phone, String photo, String birthDate, String gender, boolean confirm){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.photo = photo;
        this.birthDate = birthDate;
        this.gender = gender;
        this.confirm = confirm;
    }

    public int getId() { return id; }
    public String getName() { return  name; }
    public String getSurname() { return  surname; }
    public String getEmail() { return  email; }
    public String getPassword() { return  password; }
    public int getPhone() { return  phone; }
    public String getPhoto() { return  photo; }
    public String getBirthDate() { return birthDate; }
    public String getGender() { return  gender; }
    public boolean getConfirm() { return  confirm; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }


    public static ArrayList<Person> getDefaultPersonsList() {
        ArrayList persons = new ArrayList();
        persons.add(new Person("Ahmet", "Çiçek", "ahmet@test.com", "admin", 555555555, "1", "01-01-2020", "E", true));
        persons.add(new Person("Ayşe", "Kamil", "ayse@test.com", "admin", 555555555, "01-01-2020", "1",  "K",true));
        persons.add(new Person("Nilüfer", "Turuncu", "nilufer@test.com", "admin", 555555555, "01-01-2020", "1", "K",true));
        persons.add(new Person("Emine" ,"Yeşil", "emine@test.com", "admin", 555555555, "01-01-2020", "1",  "K",true));
        persons.add(new Person("Kamil", "Altın", "kamil@test.com", "admin", 555555555, "01-01-2020", "1", "E", true));
        persons.add(new Person("Furkan", "Kaplan", "furkan@test.com", "admin", 555555555, "01-01-2020", "1", "E", true));
        persons.add(new Person("Mehmet", "Gönül", "mehmet@test.com", "admin", 555555555, "01-01-2020", "1", "E", true));
        persons.add(new Person("Zafer", "Aziz", "zafer@test.com", "admin", 555555555, "01-01-2020", "1", "E", true));
        persons.add(new Person("Mehtap", "Yüzük", "mehtap@test.com", "admin", 555555555, "01-01-2020", "1", "K", true));
        persons.add(new Person("Necla", "Halay", "necla@test.com", "admin", 555555555, "01-01-2020", "1", "K", true));
        persons.add(new Person("Emin", "Kopuk", "emin@test.com", "admin", 555555555, "01-01-2020", "1", "E",true));
        persons.add(new Person("Hüseyin", "Yonca", "huseyin@test.com", "admin", 555555555, "01-01-2020", "1",  "E",true));
        persons.add(new Person("Elif", "Kara", "elif@test.com", "admin", 555555555, "01-01-2020", "1", "K", true));
        return persons;
    }

}
