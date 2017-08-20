package com.example.nikhil.rx;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.R.string.cancel;

public class ModifyTimes extends AppCompatActivity  {
    EditText mornbf,aftebf,evenbf,nighbf,mornaf,afteaf,evenaf,nighaf;
    private AlarmManager alarmMgr,alarmMgr2,alarmMgr3,alarmMgr4,alarmMgr5,alarmMgr6,alarmMgr7,alarmMgr8;
    private PendingIntent alarmIntent1,alarmIntent2,alarmIntent3,alarmIntent4,alarmIntent5,alarmIntent6,alarmIntent7,alarmIntent8;
    int hd1;
    Button save,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_times);
        Intent intent1 = new Intent(this, AlarmReceiver.class);
        alarmIntent1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
        alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent2 = new Intent(this, AlarmReceiver.class);
        alarmIntent2 = PendingIntent.getBroadcast(this, 1, intent2, 0);
        alarmMgr2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent3 = new Intent(this, AlarmReceiver.class);
        alarmIntent3 = PendingIntent.getBroadcast(this, 2, intent3, 0);
        alarmMgr3 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent4 = new Intent(this, AlarmReceiver.class);
        alarmIntent4 = PendingIntent.getBroadcast(this, 3, intent4, 0);
        alarmMgr4 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent5 = new Intent(this, AlarmReceiver.class);
        alarmIntent5 = PendingIntent.getBroadcast(this, 4, intent5, 0);
        alarmMgr5 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent6 = new Intent(this, AlarmReceiver.class);
        alarmIntent6 = PendingIntent.getBroadcast(this, 5, intent6, 0);
        alarmMgr6 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent7 = new Intent(this, AlarmReceiver.class);
        alarmIntent7 = PendingIntent.getBroadcast(this, 6, intent7, 0);
        alarmMgr7 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent8 = new Intent(this, AlarmReceiver.class);
        alarmIntent8 = PendingIntent.getBroadcast(this, 7, intent8, 0);
        alarmMgr8 = (AlarmManager) getSystemService(ALARM_SERVICE);



        final EditText mornbf = (EditText) findViewById(R.id.mornbf);
        final EditText aftebf = (EditText) findViewById(R.id.aftebf);
        final EditText evenbf = (EditText) findViewById(R.id.evenbf);
        final EditText nighbf = (EditText) findViewById(R.id.nighbf);
        final EditText mornaf = (EditText) findViewById(R.id.mornaf);
        final EditText afteaf = (EditText) findViewById(R.id.afteaf);
        final EditText evenaf = (EditText) findViewById(R.id.evenaf);
        final EditText nighaf = (EditText) findViewById(R.id.nighaf);

        mornbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mornbf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent1);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        aftebf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        aftebf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent2);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        evenbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        evenbf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent3);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        nighbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        nighbf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent4);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        mornaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mornaf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent5);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        afteaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        afteaf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent6);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        evenaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        evenaf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent7);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

        nighaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyTimes.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        nighaf.setText(hourOfDay + ":" + minute);
                        hd1=hourOfDay;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, view.getCurrentHour());
                        calendar.set(Calendar.MINUTE, view.getCurrentMinute());
                        long time;
                        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (calendar.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent8);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    /*public void onClick(View view) {
        if(view==save)
        {
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==cancel)
            clearText();
    }
    public void clearText()
    {
        mornbf.setText("");
        mornaf.setText("");
        aftebf.setText("");
        afteaf.setText("");
        evenbf.setText("");
        evenaf.setText("");
        nighbf.setText("");
        nighaf.setText("");
    }*/
    public void gotoMain(View view)
    {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}

