package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apps.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button signIn;
    FirebaseAuth fAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), FirstActivity.class));
        }
        login = findViewById(R.id.btnlogin);
        signIn = findViewById(R.id.btnsignin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

    }


    public FirebaseAuth getfAuth() {
        return fAuth;
    }
}
