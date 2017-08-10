package com.example.nikhil.rx;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EveningActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evening);
        db=openOrCreateDatabase("prescriptDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS prescription(name VARCHAR,quantity VARCHAR, " +
                "once VARCHAR, twice VARCHAR, thrice VARCHAR, quad VARCHAR, morning INTEGER, afternoon INTEGER, " +
                "evening INTEGER, dinner INTEGER);");
        Cursor c=db.rawQuery("SELECT * FROM prescription WHERE evening=1", null);
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found");
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

        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append("Name     : "+c.getString(0)+"\n");
            buffer.append("Quantity : "+c.getString(1)+"\n");
            buffer.append("First    : "+c.getString(2)+"\n");
            buffer.append("Second   : "+c.getString(3)+"\n");
            buffer.append("Third    : "+c.getString(4)+"\n");
            buffer.append("Fourth   : "+c.getString(5)+"\n\n");
            switch(i)
            {
                case 0 : i1.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i2.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");break;
                case 1 : i3.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i4.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");break;
                case 3 : i5.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i6.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");break;
                case 4 : i7.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i8.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");break;
                case 5 : i9.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i10.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");break;
                case 6 : i11.setText(c.getString(0) + " (" +c.getString(1)+ ")");
                    i12.setText(c.getString(2)+" & "+c.getString(3)+" & "+c.getString(4)+" & "+c.getString(5)+" & ");break;
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
