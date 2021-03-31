package com.app.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class adminpanel extends AppCompatActivity {
    Button fireb, Addmap,Addfeatures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
        fireb = (Button) findViewById(R.id.editusers);
        //Addmap = (Button) findViewById(R.id.addmap);
        Addfeatures = findViewById(R.id.addfeatures);
        fireb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                  Intent intent = new Intent(adminpanel.this, webviewbase.class);
                startActivity(intent);

            }
        });
       /** Addmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(adminpanel.this, placess.class);
                startActivity(intent);

            }
        });*/
        Addfeatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(adminpanel.this, featuresActivity.class);
                startActivity(intent);

            }
        });
    }
}