package com.example.a0407_example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    TextView title;
    EditText name, surname, email, password, phone, birthDate;
    Switch switchConfirm;
    Button imageButton, senderButton, changeDateButton;
    RadioButton male, female;
    ImageView imageView;
    Uri imageUri;
    static final int SELECT_IMAGE = 5;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        defineVeriables();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageUploadIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageUploadIntent.setType("image/*");
                startActivityForResult(imageUploadIntent, SELECT_IMAGE);
            }
        });

        changeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int yearC = calendar.get(Calendar.YEAR);
                int mounthC = calendar.get(Calendar.MONTH);
                int dayC = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(SignUpActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month += 1;
                                birthDate.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, yearC, mounthC, dayC);

                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();

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
                        SQLLiteHelperPersons sqlLiteHelperPersons = new SQLLiteHelperPersons(SignUpActivity.this);

                        /*
                    sqlLiteHelperPersons.dropTable(sqlLiteHelperPersons.getWritableDatabase(), "Persons");
                    sqlLiteHelperPersons.onCreate(sqlLiteHelperPersons.getWritableDatabase());
                        */
                        Person checkPerson = sqlLiteHelperPersons.GetPersonWithEmail(email.getText().toString().trim());
                        if(checkPerson.getId() < 1){
                            Person person = new Person();
                            person.setName(name.getText().toString().trim());
                            person.setSurname(surname.getText().toString().trim());
                            person.setEmail(email.getText().toString().trim());
                            person.setPassword(password.getText().toString().trim());
                            person.setPhone(Integer.parseInt(phone.getText().toString()));
                            person.setPhoto(imageUri.toString());
                            person.setBirthDate(birthDate.getText().toString().trim());
                            person.setGender(male.isChecked() ? "E" : female.isChecked() ? "K" : "B");
                            person.setConfirm(true);
                            sqlLiteHelperPersons.AddPerson(person);

                            Toast.makeText(getApplicationContext(), "Kayıt başarılı.", Toast.LENGTH_SHORT).show();
                            Intent mainActivityIntent = new Intent(SignUpActivity.this, MainActivity.class);
                            SignUpActivity.this.startActivity(mainActivityIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Girdiğiniz email daha önce kayıtlı.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex){
                        Log.d("CREATION", ex.toString());
                    }
                }
            }

        });

    }

    public void defineVeriables(){
        title = (TextView) findViewById(R.id.title);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        imageButton = findViewById(R.id.imageButton);
        imageView = findViewById(R.id.imageView);
        phone = (EditText) findViewById(R.id.phone);
        changeDateButton = (Button) findViewById(R.id.changeDateButton);
        birthDate = (EditText) findViewById(R.id.birthDate);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        switchConfirm = (Switch) findViewById(R.id.switchConfirm);
        senderButton = (Button) findViewById(R.id.buttonSender);
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
            if(name.getText().toString().trim().length() < 1) {
                sb.append("Ad boş bırakılamaz.\n");
            }
            if(surname.getText().toString().trim().length() < 1) {
                sb.append("Soyad boş bırakılamaz.\n");
            }
            if(!email.getText().toString().trim().matches(emailPattern)) {
                sb.append("Geçerli bir email giriniz.\n");
            }
            if(password.getText().toString().trim().length() < 4 || password.getText().toString().trim().length() > 16) {
                sb.append("En az 5 en fazla 15 karakterli parola giriniz.\n");
            }
            if(phone.getText().toString().trim().length() < 1) {
                sb.append("Telefon boş bırakılamaz.\n");
            }
            if(imageUri == null) {
                sb.append("Profil fotoğrafı boş olamaz.\n");
            }

        return sb;

    }


}