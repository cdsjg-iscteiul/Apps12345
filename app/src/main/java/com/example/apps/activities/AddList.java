package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apps.R;

public class AddList extends AppCompatActivity {
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);


        getSupportActionBar().setTitle("Add Shopping List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.EditTextAddList);
        button = findViewById(R.id.buttonAddList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeActivity();

            }
        });

    }

    private void changeActivity() {

        Intent intent = new Intent();//por aqui a ativit
        intent.putExtra("ListName",editText.getText().toString());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
