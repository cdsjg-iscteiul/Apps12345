package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.apps.R;
import com.example.apps.items.toBuyProduct;
import com.example.apps.utility.TypeOfProduct;

public class AddProduct extends AppCompatActivity {

    private NumberPicker numberPicker;
    private NumberPicker type;
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        numberPicker = findViewById(R.id.numberpickeraddproduct);
        numberPicker.setMaxValue(99);
        type = findViewById(R.id.typeofproduct);
        type.setMinValue(0);
        type.setMaxValue(4);
        type.setDisplayedValues(new String[] {"Amount", "Kg", "g", "L","mL"});

        editText = findViewById(R.id.editTextAddProduct);

        getSupportActionBar().setTitle("Add Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = findViewById(R.id.buttonAddProduct);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBuyProduct p = new toBuyProduct(editText.getText().toString(),numberPicker.getValue(), TypeOfProduct.Type(type.getDisplayedValues()[type.getValue()]));
                Intent intent = new Intent(AddProduct.this ,ShoppingList.class);
                intent.putExtra("ProductAdded", p);
                setResult(RESULT_OK,intent);
                finish();

            }
        });



    }
}
