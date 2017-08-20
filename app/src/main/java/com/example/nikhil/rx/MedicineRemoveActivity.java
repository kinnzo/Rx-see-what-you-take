package com.example.nikhil.rx;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.nikhil.rx.R.id.delete_btn;

public class MedicineRemoveActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    EditText dele;
    Button bt;
    String delete_addr="https://somemedcare.000webhostapp.com/delete_data.php?name=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_remove);
        db=openOrCreateDatabase("PrescriptionDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS prescription(name VARCHAR,quantity VARCHAR, " +
                "mornbf INTEGER, aftebf INTEGER, evenbf INTEGER, nighbf INTEGER, imageID INTEGER, morning INTEGER, afternoon INTEGER, " +
                "evening INTEGER, dinner INTEGER);");
        dele = (EditText) findViewById(R.id.del_name);
        bt = (Button) findViewById(delete_btn);
        bt.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if(view == bt) {
            if (dele.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Name");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM prescription WHERE name='" + dele.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM prescription WHERE name='" + dele.getText() + "'");
                showMessage("Success", "Record Deleted");
                Uri uri = Uri.parse(delete_addr+dele.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                dele.setText("");
            } else {
                showMessage("Error", "Invalid Name");
            }
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
