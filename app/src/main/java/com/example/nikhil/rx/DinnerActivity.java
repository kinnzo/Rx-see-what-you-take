package com.example.nikhil.rx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class DinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        Button bp = (Button) findViewById(R.id.button);
        ImageView image = (ImageView) findViewById(R.id.image1);
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File myDir = new File(root);

        String fname = "Image-crocin.jpg";
        File file = new File (myDir, fname);
        if(! myDir.exists())
            bp.setVisibility(View.GONE);
        Bitmap bMap = BitmapFactory.decodeFile(String.valueOf(file));
        image.setImageBitmap(bMap);
    }

}
