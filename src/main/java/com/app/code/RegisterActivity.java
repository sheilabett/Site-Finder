package com.app.code;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    /*
       This activity handles the process of registration of new users.
    */
    EditText userName,password,password1,name;
    TextView AccountExists;
    Button register;
    private FirebaseAuth mAuth;//Used for firebase authentication
    private ProgressDialog loadingBar;//Used to show the progress of the registration process
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeUI();
    }
    /*
        To intialize the UI and other components.
     */
    private void initializeUI() {
        mAuth = FirebaseAuth.getInstance();
        userName = (EditText) findViewById(R.id.username2);
       name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.Password2);
        password1 = (EditText) findViewById(R.id.pass2);
        register = (Button) findViewById(R.id.submit_btn);
        AccountExists = (TextView) findViewById(R.id.Already_link);
        loadingBar = new ProgressDialog(this);
        //When user has  an account already he should be sent to login activity.
        AccountExists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToLoginActivity();
            }
        });
        //When user clicks on register create a new account for user
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    /*
        This method creates new account for new users.
     */
    private void createNewAccount() {
        final String email = userName.getText().toString().trim();
        String pwd = password.getText().toString();
        String cmp = password1.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(RegisterActivity.this,"Please enter email id", Toast.LENGTH_SHORT).show();
        }
       else if(TextUtils.isEmpty(pwd))
        {
            Toast.makeText(RegisterActivity.this,"Please enter password", Toast.LENGTH_SHORT).show();
        }
       else if(!pwd.equals(cmp))
        {
            Toast.makeText(RegisterActivity.this,"Please Check the Confirm Password!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //When both email and password are available create a new accountToast.makeText(RegisterActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
            //Show the progress on Progress Dialog
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait, we are creating new Account");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();
            final String value1 = userName.getText().toString();
            final String value2 = password.getText().toString();
            final String value3 = name.getText().toString();
            mAuth.createUserWithEmailAndPassword(email,pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())//If account creation successful print message and send user to Login Activity
                            {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("Name", value3);
                                map.put("Email", value1);
                                map.put("Password", value2);

                                //map.put("Date", value3);
                                // Push creates a unique id in database
                                FirebaseDatabase.getInstance().getReference().child("New User" + value3).child("Details").updateChildren(map);

                                sendUserToLoginActivity();
                                Toast.makeText(RegisterActivity.this,"Account created and user added successfully", Toast.LENGTH_SHORT).show();




                                loadingBar.dismiss();
                            }
                            else//Print the error message incase of failure
                            {
                                String msg = task.getException().toString();
                                Toast.makeText(RegisterActivity.this,"Error: "+msg, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }
    /*
        After successfull registration send user to Login page.
     */
    private void sendUserToLoginActivity() {
        //This is to send user to Login Activity.
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
