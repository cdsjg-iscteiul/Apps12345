package com.example.apps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apps.activities.AddProduct;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Boolean s;
    private String text;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =  findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
        s=false;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();

            }
        });
       

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        text=data.getStringExtra("ListName");
        Toast.makeText(MainActivity.this, "text", Toast.LENGTH_SHORT).show();
        textView.setText(text);
        s=true;

    }

    private void change() {
        Intent intent = new Intent(this, AddProduct.class);
        /*
        if(!s)
        {
            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            s=true;
        }
        else

         */
            startActivityForResult(intent,1);
    }
}
