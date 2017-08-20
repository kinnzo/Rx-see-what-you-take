package com.example.nikhil.rx;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Switch;
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
import static com.example.nikhil.rx.R.id.save_btn;
import static com.example.nikhil.rx.R.id.switch1;


public class MedicineActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    Button save,cancel;
    String put_data="https://somemedcare.000webhostapp.com/put_data.php?name=";
    EditText quantity;
    Switch mbf,abf,ebf,nbf;
    AutoCompleteTextView name;
    SharedPreferences shared;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView1,imageView2,imageView3,imageView4;
    int hd1=1,hd2=1,hd3=1,hd4=1;
    int morn=0,afte=0,even=0,nigh=0,count=0,mypos=0;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        shared=getSharedPreferences("app",Context.MODE_PRIVATE);
        mbf = (Switch) findViewById(R.id.switch1);
        abf = (Switch) findViewById(R.id.switch2);
        ebf = (Switch) findViewById(R.id.switch3);
        nbf = (Switch) findViewById(R.id.switch4);


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
        name = (AutoCompleteTextView) findViewById(R.id.name);
        String[] medicines = getResources().getStringArray(R.array.medicines_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicines);
        name.setAdapter(adapter);
        quantity = (EditText) findViewById(R.id.quantity);

                mbf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    if(mbf.isChecked())
                        hd1=0;
                    }
                });
        abf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(abf.isChecked())
                    hd2=0;
            }
        });
        ebf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ebf.isChecked())
                    hd3=0;
            }
        });
        nbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nbf.isChecked())
                    hd4=0;
            }
        });

        save = (Button) findViewById(R.id.save_btn);
        save.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(this);
        db=openOrCreateDatabase("PrescriptionDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS prescription(name VARCHAR,quantity VARCHAR, " +
                "mornbf INTEGER, aftebf INTEGER, evenbf INTEGER, nighbf INTEGER, imageID INTEGER, morning INTEGER, afternoon INTEGER, " +
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
        if(hd1==0 && morn==1){

        }

            db.execSQL("INSERT INTO prescription VALUES('"+name.getText()+"','"+quantity.getText()+
                    "','"+hd1+"','"+hd2+"','"+hd3+"','"+hd4+"','"+mypos+"','"+morn+"','"+afte+"','"+even+"','"+nigh+"');");

            Uri uri = Uri.parse(put_data+name.getText()+"&qty="+quantity.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==cancel)
            clearText();
    }

    public void checkState(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox1:
                if (checked)
                morn=1;
                else
                    morn=0;
                break;
            case R.id.checkBox2:
                if (checked)
                afte=1;
                else
                    afte=0;
                break;
            case R.id.checkBox3:
                if (checked)
                    even=1;
                else
                    even=0;
                break;
            case R.id.checkBox4:
                if (checked)
                    nigh=1;
                else
                    nigh=0;
                break;

        }
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
        morn=0;even=0;afte=0;nigh=0;
        hd1=1;hd2=1;hd3=1;hd4=1;mypos=0;
        CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb1.setChecked(false);
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb2.setChecked(false);
        CheckBox cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb3.setChecked(false);
        CheckBox cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb4.setChecked(false);
    }
}


