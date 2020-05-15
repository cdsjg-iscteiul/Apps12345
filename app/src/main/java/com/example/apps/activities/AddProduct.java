package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;

import com.example.apps.R;

public class AddProduct extends AppCompatActivity {

    private NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        numberPicker = findViewById(R.id.numberpickeraddproduct);
        numberPicker.setMaxValue(99);


    }
}
