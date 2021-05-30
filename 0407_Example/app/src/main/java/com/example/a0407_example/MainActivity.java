

package com.example.a0407_example;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button loginButton, signUpButton;
    EditText username, password;
    TextView alertText;

    int counter = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineVeriables();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLLiteHelperPersons sqlLiteHelperPersons = new SQLLiteHelperPersons(v.getContext());
                Person person = sqlLiteHelperPersons.GetPersonWithEmailAndPassword(username.getText().toString().trim(), password.getText().toString().trim());
                if(person.getId() > 0) {
                    Toast.makeText(getApplicationContext(),
                            "Yönlendiriliyorsunuz...",Toast.LENGTH_SHORT).show();
                    LocalDataManager localDataManager = new LocalDataManager();
                    localDataManager.setSharedPreference(getApplicationContext(), "name", person.getName());
                    Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                    MainActivity.this.startActivity((menuIntent));
                }else{
                    Toast.makeText(getApplicationContext(), "Yanlış kullanıcı ya da parola!",Toast.LENGTH_SHORT).show();
                    counter--;
                    alertText.setText("Kalan hakkınız: " + Integer.toString(counter));
                    if (counter == 0) {
                        loginButton.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "Uygulama kapatılıyor!",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(MainActivity.this, SignUpActivity.class);
                MainActivity.this.startActivity((signUpIntent));
            }
        });
    }

    public void defineVeriables(){
        loginButton = (Button)findViewById(R.id.loginButton);
        signUpButton = (Button)findViewById(R.id.signUpButton);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        alertText = (TextView)findViewById(R.id.alertText);
    }
}