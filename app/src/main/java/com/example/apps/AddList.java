package com.example.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddList extends AppCompatActivity {
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        editText = findViewById(R.id.EditText1);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changeActivity();

            }
        });

    }

    private void changeActivity() {
        Intent intent = new Intent(this, MainActivity.class);//por aqui a ativity
        intent.putExtra("ListName",editText.getText());
        setResult(RESULT_OK,intent);
        startActivityForResult(intent,2);
        finish();
    }
}
