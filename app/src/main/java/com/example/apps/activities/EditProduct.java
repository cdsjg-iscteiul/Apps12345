package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class EditProduct extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Button edit;
    private Button delete;
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
    private alreadyBoughtProduct p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        editText = findViewById(R.id.editTextAddProduct2);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        numberPicker = findViewById(R.id.numberpickeraddproduct2);
        type = findViewById(R.id.typeofproduct3);
        numberPicker.setMaxValue(99);
        type.setMinValue(0);
        type.setMaxValue(4);
        type.setDisplayedValues(new String[] {"Amount", "Kg", "g", "L","mL"});
        calendar = findViewById(R.id.imageView3);
        editText2 = findViewById(R.id.editText);
        p =  getIntent().getParcelableExtra("produto");

        type.setValue(TypeOfProduct.getPosition(p.getType()));
        numberPicker.setValue(p.getAmount());
        editText2.setText(p.getDate());
        editText.setText(p.getName());

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showDate();}
        });

        NotificationCounter = getIntent().getIntExtra("NotificationNumber",0);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setAmount(numberPicker.getValue());
                p.setExpire(editText2.getText().toString());
                p.setName(editText.getText().toString());
                p.setType(TypeOfProduct.Type(type.getDisplayedValues()[type.getValue()]));
                startAlarm(p,day,month,year);
                Intent intent = getIntent();
                intent.putExtra("edited", p);
                Log.e("id","OKET VAMOS LA VE XD "+ getIntent().getIntExtra("id",-1));
                intent.putExtra("remove","no");
                intent.putExtra("id2",getIntent().getIntExtra("id",-1));
                setResult(RESULT_OK,intent);
                finish();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("remove","yes");
                intent.putExtra("delete",intent.getIntExtra("id",-1));
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
}
