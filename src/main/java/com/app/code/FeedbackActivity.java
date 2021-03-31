package com.app.code;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {
    /*
        This acitivty handles providing the feedback about the application to the developers.
    */
    EditText name, feedback;
    Button submitBtn;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        name = (EditText) findViewById(R.id.Name);
        feedback = (EditText) findViewById(R.id.feedback);
        submitBtn = (Button) findViewById((R.id.submitBtn));

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = name.getText().toString();
                String feedBackTxt = feedback.getText().toString();
                //Get reference of the Real time database.
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(FeedbackActivity.this, "Please enter name id", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(feedBackTxt)) {
                    Toast.makeText(FeedbackActivity.this, "Please enter feedback", Toast.LENGTH_SHORT).show();
                }

                else {

                    ConnectivityManager cm = (ConnectivityManager) FeedbackActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = cm.getActiveNetworkInfo();
                    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        reference = FirebaseDatabase.getInstance().getReference().child("Feedback:" + userName);
                        //Push the values to the database.
                        reference.push().setValue(feedBackTxt);

                        name.setText("");
                        feedback.setText("");

                        Toast.makeText(FeedbackActivity.this, "Thank you for providing the feedback.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FeedbackActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    public boolean isOnline() {
     return true;
    }
}
