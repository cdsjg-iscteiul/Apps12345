package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddProductToTheList extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private Button button;
    private EditText editText;
    private NumberPicker numberPicker;
    private ImageView calendar;
    private int year;
    private int month;
    private int day;
    private EditText editText2;
    private AddProductToTheList addNot;


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
        day = cal.getDatePicker().getDayOfMonth();
        month = cal.getDatePicker().getMonth();
        year = cal.getDatePicker().getYear():
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


    public void addAlert(String title, String description, int dayOfMonth, int month, int year, boolean repeat) {
        String date = dayOfMonth + "/" + month + "/" + year;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        long time = calendar.getTimeInMillis();
        int id = addNot.createNotification(title, description, date);
        Intent intent = new Intent (getAtivity(), Receiver.class);
        intent.putExtra("title", title).putExtra("key", id).putExtra("description", description);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,id, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getActivity().getAplicationContext().getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }
    
    private int createNotification(String title, String name, String date){
     return 0;
    }
}

