package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
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
import com.example.apps.notifications.Receiver;
import com.example.apps.utility.TypeOfProduct;

import java.util.Calendar;

public class AddProductToTheList extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private Button button;
    private EditText editText;
    private NumberPicker numberPicker;
    private NumberPicker type;
    private ImageView calendar;
    private int year;
    private int month;
    private int day;
    private EditText editText2;
    private AddProductToTheList addNot;
    private int NotificationCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_to_the_list);
        editText = findViewById(R.id.EditTextAddProduct);
        button = findViewById(R.id.buttonAddProduct);
        numberPicker = findViewById(R.id.numberpickeraddproduct);
        type = findViewById(R.id.typeofproduct2);
        numberPicker.setMaxValue(99);
        type.setMinValue(0);
        type.setMaxValue(4);
        type.setDisplayedValues(new String[] {"Amount", "Kg", "g", "L","mL"});
        calendar = findViewById(R.id.imageView2);
        editText2 = findViewById(R.id.editText2);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showDate();}
        });

        NotificationCounter = getIntent().getIntExtra("NotificationNumber",0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alreadyBoughtProduct p = new alreadyBoughtProduct(editText.getText().toString(), numberPicker.getValue(),TypeOfProduct.Type(type.getDisplayedValues()[type.getValue()]), editText2.getText().toString());
                startAlarm(p,day,month,year);
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
        day = dayOfMonth;
        this.month = month;
        this.year = year;
    }

    private void startAlarm(alreadyBoughtProduct p,int d,int m,int y) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, d);
        c.set(Calendar.MONTH, m);
        c.set(Calendar.YEAR, y);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Receiver.class).putExtra("Name",p.getName()).putExtra("Key",NotificationCounter);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NotificationCounter, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

/*
    public void addAlert(String title, String description, int dayOfMonth, int month, int year, boolean repeat) {
        String date = dayOfMonth + "/" + month + "/" + year;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        long time = calendar.getTimeInMillis();
        int id = addNot.createNotification(title, description, date);
        Intent intent = new Intent (getApplicationContext(), Receiver.class);
        intent.putExtra("title", title).putExtra("key", id).putExtra("description", description);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,id, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }
    
    private int createNotification(String title, String name, String date){
     return 0;
    }

 */
}

