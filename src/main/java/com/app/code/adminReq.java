package com.app.code;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class adminReq extends AppCompatActivity {
    DatabaseReference rootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_req);
       final EditText info=(EditText)findViewById(R.id.maskinfo);
        final  EditText mprice=(EditText)findViewById(R.id.maskprice);
        final  EditText lprice=(EditText) findViewById(R.id.lprice);
        final  EditText fprice=(EditText) findViewById(R.id.fprice);
        Button update= findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = info.getText().toString();
                String value1 = mprice.getText().toString();
                String value2 = lprice.getText().toString();
                String value3 = fprice.getText().toString();

                HashMap<String, Object> map=new HashMap<>();
                map.put("info",value);
                map.put("mprice",value1);
                map.put("lprice",value2);
                map.put("fprice",value3);
                // Push creates a unique id in database
                FirebaseDatabase.getInstance().getReference().child("Admin"+value).child("requirements").updateChildren(map);
               }
        });
    }
}