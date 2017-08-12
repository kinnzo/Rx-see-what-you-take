package com.example.nikhil.rx;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.File;

public class DinnerActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        db=openOrCreateDatabase("PrescriptDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS prescription(name VARCHAR,quantity VARCHAR, " +
                "once VARCHAR, twice VARCHAR, thrice VARCHAR, quad VARCHAR, imageID INTEGER, morning INTEGER, afternoon INTEGER, " +
                "evening INTEGER, dinner INTEGER);");
        Cursor c=db.rawQuery("SELECT * FROM prescription WHERE dinner=1", null);
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
            buffer.append("First    : "+c.getString(2)+"\n");
            buffer.append("Second   : "+c.getString(3)+"\n");
            buffer.append("Third    : "+c.getString(4)+"\n");
            buffer.append("Fourth   : "+c.getString(5)+"\n\n");
            /*
            mystr[i]=c.getString(0) + " (" +c.getString(1)+")";
            i++;
            mystr[i]=c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+"!";
            i++;
            */
            int j=c.getInt(6);
            switch(i)
            {
                case 0 : {i1.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                        i2.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");
                    switch (j)
                    {
                        case 1:j1.setImageResource(R.drawable.cap);break;
                        case 2:j1.setImageResource(R.drawable.tab);break;
                        case 3:j1.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 1 : {i3.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i4.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");
                    switch (j)
                    {
                        case 1:j2.setImageResource(R.drawable.cap);break;
                        case 2:j2.setImageResource(R.drawable.tab);break;
                        case 3:j2.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 3 :{ i5.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i6.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");
                    switch (j)
                    {
                        case 1:j3.setImageResource(R.drawable.cap);break;
                        case 2:j3.setImageResource(R.drawable.tab);break;
                        case 3:j3.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 4 :{ i7.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i8.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");
                    switch (j)
                    {
                        case 1:j4.setImageResource(R.drawable.cap);break;
                        case 2:j4.setImageResource(R.drawable.tab);break;
                        case 3:j4.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 5 : {i9.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i10.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");
                    switch (j)
                    {
                        case 1:j5.setImageResource(R.drawable.cap);break;
                        case 2:j5.setImageResource(R.drawable.tab);break;
                        case 3:j5.setImageResource(R.drawable.syrup);break;
                    }break;}
                case 6 : {i11.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i12.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");
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
