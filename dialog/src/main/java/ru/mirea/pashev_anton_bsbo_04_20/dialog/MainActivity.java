package ru.mirea.pashev_anton_bsbo_04_20.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView timer,date1;
    int hour,minute,day,year,month;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.time_display);
        date1 = findViewById(R.id.date_display);

    }

    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");

    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }


    public void setTime(View view){
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour1, int minute1) {
                        hour = hour1;
                        minute = minute1;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,hour,minute);
                        timer.setText(DateFormat.format("hh:mm aa", calendar));
                    }
                },12,0,false);
        timePickerDialog.updateTime(hour,minute);
        timePickerDialog.show();
    }

    public void setDate(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int day1) {
                        year = year1;
                        month = month1+1;
                        day = day1;
                        String date = day+"/"+month+"/"+year;
                        date1.setText(date);
                    }
                },year,month,day);
        datePickerDialog.show();
    }

    public void setProg(View view){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();

        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
    }

    @Override
    public void onBackPressed() {
        progressDialog.dismiss();
    }
}