package com.example.nikhil.rx;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import static com.example.nikhil.rx.R.id.Once;
import static com.example.nikhil.rx.R.id.Quad;
import static com.example.nikhil.rx.R.id.Thrice;
import static com.example.nikhil.rx.R.id.Twice;
import static com.example.nikhil.rx.R.id.save_btn;

public class MedicineActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    Button save,cancel;
    EditText once;
    EditText twice;
    EditText thrice;
    EditText quad;
    EditText name;
    EditText quantity;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        this.imageView = (ImageView)this.findViewById(R.id.imageView);
        Button B = (Button) this.findViewById(R.id.camera);
        B.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

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
                             once.setVisibility(View.VISIBLE);break;
                    case 2: twice.setVisibility(View.VISIBLE);
                             once.setVisibility(View.VISIBLE);break;
                    case 1: once.setVisibility(View.VISIBLE);break;
                }

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
                    }
                }, hour, minute, false);
                timePickerDialog.show();

            }
        });
        save = (Button) findViewById(R.id.save_btn);
        save.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(this);
        db=openOrCreateDatabase("prescriptionDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS prescription(name VARCHAR,quantity VARCHAR, once VARCHAR, twice VARCHAR, thrice VARCHAR, quad VARCHAR);");
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
            db.execSQL("INSERT INTO prescription VALUES('"+name.getText()+"','"+quantity.getText()+
                    "','"+once.getText()+"','"+twice.getText()+"','"+thrice.getText()+"','"+quad.getText()+"');");
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
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap photo;
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            saveImage(photo);
        }
    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();

        String fname = "Image-"+ name.getText() +".jpg";
        File file = new File (myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


