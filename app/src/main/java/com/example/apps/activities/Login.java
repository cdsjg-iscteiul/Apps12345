package com.example.apps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button login;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fAuth=FirebaseAuth.getInstance();



        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = (EditText) findViewById(R.id.etEmail);
        password =(EditText)findViewById(R.id.etPassword);
        login =(Button) findViewById(R.id.btnLogin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString());

            }
        });

    }

    private void validate (String mail, String userPassword){
        if(TextUtils.isEmpty(mail.trim())){
            email.setError("Email is Required");
            return;
        }
        if(TextUtils.isEmpty((userPassword.trim()))){
            password.setError("Password is Required");
            return;
        }
        fAuth.signInWithEmailAndPassword(mail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, FirstActivity.class));
                }else{
                    Toast.makeText(Login.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}
