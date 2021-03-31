package com.app.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        findViewById(R.id.adminlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText emailText=findViewById(R.id.editmail);
                String email = emailText.getText().toString();
                EditText passwordText=findViewById(R.id.editassword);
                String password = passwordText.getText().toString();

                if (email.equals("admin@gmail.com") && password.equals("@admin2020")) {
                    Toast.makeText(admin.this, "Welcome Admin!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(admin.this, adminpanel.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(admin.this, "WRONG EMAIL/PASSWORD try again", Toast.LENGTH_SHORT).show();
                }



                // Opening admin activity using intent on button click.


            }
        });
    }
}