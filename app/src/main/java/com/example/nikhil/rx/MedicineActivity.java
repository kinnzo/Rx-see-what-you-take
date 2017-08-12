package com.example.nikhil.rx;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.nikhil.rx.R.id.Once;
import static com.example.nikhil.rx.R.id.Quad;
import static com.example.nikhil.rx.R.id.Thrice;
import static com.example.nikhil.rx.R.id.Twice;
import static com.example.nikhil.rx.R.id.save_btn;

public class MedicineActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    Button save,cancel;
    EditText once,twice,thrice,quad,name,quantity;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView1,imageView2,imageView3,imageView4;
    int hd1=-1,hd2=-1,hd3=-1,hd4=-1;
    int morn=0,afte=0,even=0,nigh=0,count=0,mypos=0;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        Intent intent = new Intent(this, AlarmReceiver.class);
          alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        final String[] str = {"Number of Times", "Once a day", "Twice a day", "Thrice a day", "Four times a day"};
        final Spinner sp = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, str);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adp);
        once = (EditText) findViewById(Once);
        twice = (EditText) findViewById(Twice);
        thrice = (EditText) findViewById(Thrice);
        quad = (EditText) findViewById(Quad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                switch(position)
                {
                    case 4: quad.setVisibility(View.VISIBLE);
                             thrice.setVisibility(View.VISIBLE);
                             twice.setVisibility(View.VISIBLE);
                            once.setVisibility(View.VISIBLE);break;
                    case 3: thrice.setVisibility(View.VISIBLE);
                             twice.setVisibility(View.VISIBLE);
                             once.setVisibility(View.VISIBLE);
                            quad.setVisibility(View.GONE);break;
                    case 2: quad.setVisibility(View.GONE);
                            thrice.setVisibility(View.GONE);
                            twice.setVisibility(View.VISIBLE);
                             once.setVisibility(View.VISIBLE);break;
                    case 1: quad.setVisibility(View.GONE);
                        thrice.setVisibility(View.GONE);
                        twice.setVisibility(View.GONE);
                        once.setVisibility(View.VISIBLE);break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        final String[] type = {"Type of Medicine", "Capsule", "Tablet", "Syrup"};
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adpn = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, type);
        adpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adpn);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                switch(position)
                {
                    case 3: imageView4.setVisibility(View.GONE);
                    imageView3.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.GONE);
                        imageView1.setVisibility(View.GONE);break;
                    case 2: imageView4.setVisibility(View.GONE);
                        imageView3.setVisibility(View.GONE);
                        imageView2.setVisibility(View.VISIBLE);
                        imageView1.setVisibility(View.GONE);break;
                    case 1: imageView4.setVisibility(View.GONE);
                        imageView3.setVisibility(View.GONE);
                        imageView2.setVisibility(View.GONE);
                        imageView1.setVisibility(View.VISIBLE);break;
                    case 0:imageView4.setVisibility(View.VISIBLE);
                        imageView3.setVisibility(View.GONE);
                        imageView2.setVisibility(View.GONE);
                        imageView1.setVisibility(View.GONE);
                }
                mypos=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        name = (EditText) findViewById(R.id.name);
        quantity = (EditText) findViewById(R.id.quantity);
                once.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get Current time
                        final Calendar c = Calendar.getInstance();
                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(MedicineActivity.this, new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                once.setText(hourOfDay + ":" + minute);
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
                                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent);

                            }
                        }, hour, minute, false);
                        timePickerDialog.show();


                    }
                });

        twice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(MedicineActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        twice.setText(hourOfDay + ":" + minute);
                       hd2=hourOfDay;
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
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent);

                    }
                }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        thrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(MedicineActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        thrice.setText(hourOfDay + ":" + minute);
                        hd3=hourOfDay;
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
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent);

                    }
                }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        quad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(MedicineActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        quad.setText(hourOfDay + ":" + minute);
                        hd4=hourOfDay;
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
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, alarmIntent);

                    }
                }, hour, minute, false);
                timePickerDialog.show();

            }
        });
        save = (Button) findViewById(R.id.save_btn);
        save.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(this);
        db=openOrCreateDatabase("PrescriptDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS prescription(name VARCHAR,quantity VARCHAR, " +
                "once VARCHAR, twice VARCHAR, thrice VARCHAR, quad VARCHAR, imageID INTEGER, morning INTEGER, afternoon INTEGER, " +
                "evening INTEGER, dinner INTEGER);");
    }

    @Override
    public void onClick(View view) {
        if(view==save)
        {
            if(name.getText().toString().trim().length()==0||
                    quantity.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter required values");
                return;
            }
            if(once.getText().toString().trim().length()==0)
                once.setText("-");
            if(twice.getText().toString().trim().length()==0)
                twice.setText("-");
            if(thrice.getText().toString().trim().length()==0)
                thrice.setText("-");
            if(quad.getText().toString().trim().length()==0)
                quad.setText("-");
            if((hd1>=12 && hd1<16)||(hd2>=12 && hd2<16)||(hd3>=12 && hd3<16)||(hd4>=12 && hd4<16))
                afte=1;
            if((hd1>=16 && hd1<19)||(hd2>=16 && hd2<19)||(hd3>=16 && hd3<19)||(hd4>=16 && hd4<19))
                even=1;
            if((hd1>=19 && hd1<24)||(hd2>=19 && hd2<24)||(hd3>=19 && hd3<24)||(hd4>=19 && hd4<24))
                nigh=1;
            if((hd1>=0 && hd1<12)||(hd2>=0 && hd2<12)||(hd3>=0 && hd3<12)||(hd4>=0 && hd4<12))
                morn=1;

            db.execSQL("INSERT INTO prescription VALUES('"+name.getText()+"','"+quantity.getText()+
                    "','"+once.getText()+"','"+twice.getText()+"','"+thrice.getText()+"','"+quad.getText()+"','"+mypos+"','"+morn+"','"+afte+"','"+even+"','"+nigh+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==cancel)
            clearText();
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText()
    {
        name.setText("");
        quantity.setText("");
        once.setText("");
        twice.setText("");
        thrice.setText("");
        quad.setText("");
        morn=0;even=0;afte=0;nigh=0;
        hd1=-1;hd2=-1;hd3=-1;hd4=-1;mypos=0;
    }
}


