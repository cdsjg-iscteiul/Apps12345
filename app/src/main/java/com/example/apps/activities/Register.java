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
import com.example.apps.items.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText password;
    private Button register;

    private FirebaseAuth fAuth;
    private FirebaseDatabase database;
    private DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nome = findViewById(R.id.etNome);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        register = findViewById(R.id.btnRegister);


        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reff = database.getReference("user");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nome.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                final User user = new User(name, mail, pass);

                if (TextUtils.isEmpty(name.trim())) {
                    nome.setError("Name is Required");
                    return;
                }

                if (TextUtils.isEmpty(mail.trim())) {
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty((pass.trim()))) {
                    password.setError("Password is Required");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            database.getReference().child(fAuth.getUid()).setValue(user);
                            startActivity(new Intent(Register.this, MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
}

