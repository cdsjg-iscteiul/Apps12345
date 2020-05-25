package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.example.apps.R;
import com.example.apps.items.alreadyBoughtProduct;

import java.util.Calendar;

public class AddProductMaria extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private Button button;
    private EditText editText;
    private NumberPicker numberPicker;
    private ImageView calendar;
    private EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_maria);
        editText = findViewById(R.id.EditTextAddProduct);
        button = findViewById(R.id.buttonAddProduct);
        numberPicker = findViewById(R.id.numberpickeraddproduct);
        numberPicker.setMaxValue(99);
        calendar = findViewById(R.id.imageView2);
        editText2 = findViewById(R.id.editText2);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showDate();}
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alreadyBoughtProduct p = new alreadyBoughtProduct(editText.getText().toString(), numberPicker.getValue(), editText2.getText().toString());
                Intent intent = new Intent(getApplicationContext(),Storage.class);
                intent.putExtra("ProductAdded", p);

                setResult(RESULT_OK,intent);
                finish();

            }
        });

    }

  //  public void
    public void showDate(){
        DatePickerDialog cal = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR));
        cal.show();
    }
    private void changeActivity(){
        Intent intent = new Intent(this, FirstActivity.class);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String data = dayOfMonth+"/"+month+"/"+year;
        editText2.setText(data);
    }
}

