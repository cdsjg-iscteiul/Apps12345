package com.example.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apps.activities.FirstActivity;

public class AddProductMaria extends AppCompatActivity {

    private Button button;
    private EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_maria);
        editText = findViewById(R.id.EditTextAddProduct);
        button = findViewById(R.id.buttonAddProduct);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();

            }
        });

    }


    private void changeActivity(){
        Intent intent = new Intent(this, FirstActivity.class);
    }
}

