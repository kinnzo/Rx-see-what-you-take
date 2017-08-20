package com.example.nikhil.rx;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MorningActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);
        db=openOrCreateDatabase("PrescriptionDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS prescription(name VARCHAR,quantity VARCHAR, " +
                "mornbf INTEGER, aftebf INTEGER, evenbf INTEGER, nighbf INTEGER, imageID INTEGER, morning INTEGER, afternoon INTEGER, " +
                "evening INTEGER, dinner INTEGER);");
        Cursor c=db.rawQuery("SELECT * FROM prescription WHERE morning=1", null);
        if(c.getCount()==0)
        {
            showMessage("Enjoy!", "No medicines to be taken :)");
            return;
        }

        int i=0;
        TextView i1= (TextView) findViewById(R.id.i1);
        TextView i2= (TextView) findViewById(R.id.i2);
        TextView i3= (TextView) findViewById(R.id.i3);
        TextView i4= (TextView) findViewById(R.id.i4);
        TextView i5= (TextView) findViewById(R.id.i5);
        TextView i6= (TextView) findViewById(R.id.i6);
        TextView i7= (TextView) findViewById(R.id.i7);
        TextView i8= (TextView) findViewById(R.id.i8);
        TextView i9= (TextView) findViewById(R.id.i9);
        TextView i10= (TextView) findViewById(R.id.i10);
        TextView i11= (TextView) findViewById(R.id.i11);
        TextView i12= (TextView) findViewById(R.id.i12);
        ImageView j1= (ImageView) findViewById(R.id.j1);
        ImageView j2= (ImageView) findViewById(R.id.j2);
        ImageView j3= (ImageView) findViewById(R.id.j3);
        ImageView j4= (ImageView) findViewById(R.id.j4);
        ImageView j5= (ImageView) findViewById(R.id.j5);
        ImageView j6= (ImageView) findViewById(R.id.j6);
        //String[] mystr = new String[100];
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Name     : "+c.getString(0)+"\n");
            buffer.append("Quantity : "+c.getString(1)+"\n");
            int j=c.getInt(6);
            switch(i)
            {
                case 0 : {i1.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    if(c.getInt(2)==0)
                        i2.setText("After Food");
                    else
                        i2.setText("Before Food");
                    switch (j)
                    {
                        case 1:j1.setImageResource(R.drawable.cap);break;
                        case 2:j1.setImageResource(R.drawable.tab);break;
                        case 3:j1.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 1 : {i3.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    if(c.getInt(2)==0)
                        i4.setText("After Food");
                    else
                        i4.setText("Before Food");
                    switch (j)
                    {
                        case 1:j2.setImageResource(R.drawable.cap);break;
                        case 2:j2.setImageResource(R.drawable.tab);break;
                        case 3:j2.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 3 :{ i5.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    if(c.getInt(2)==0)
                        i6.setText("After Food");
                    else
                        i6.setText("Before Food");
                    switch (j)
                    {
                        case 1:j3.setImageResource(R.drawable.cap);break;
                        case 2:j3.setImageResource(R.drawable.tab);break;
                        case 3:j3.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 4 :{ i7.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    if(c.getInt(2)==0)
                        i8.setText("After Food");
                    else
                        i8.setText("Before Food");
                    switch (j)
                    {
                        case 1:j4.setImageResource(R.drawable.cap);break;
                        case 2:j4.setImageResource(R.drawable.tab);break;
                        case 3:j4.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 5 : {i9.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    if(c.getInt(2)==0)
                        i10.setText("After Food");
                    else
                        i10.setText("Before Food");
                    switch (j)
                    {
                        case 1:j5.setImageResource(R.drawable.cap);break;
                        case 2:j5.setImageResource(R.drawable.tab);break;
                        case 3:j5.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 6 : {i11.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    if(c.getInt(2)==0)
                        i12.setText("After Food");
                    else
                        i12.setText("Before Food");
                    switch (j)
                    {
                        case 1:j6.setImageResource(R.drawable.cap);break;
                        case 2:j6.setImageResource(R.drawable.tab);break;
                        case 3:j6.setImageResource(R.drawable.syrup);break;
                    }break;}
            }
            i++;
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
}
