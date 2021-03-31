package com.app.code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class requirements extends AppCompatActivity{
    Button Back;

    RelativeLayout r;
    public static int count=0;
    int[] drawablearray=new int[]{R.drawable.wall2,R.drawable.i1,R.drawable.pic6,
            R.drawable.pic5,
            R.drawable.pic2,
            R.drawable.pic,
            R.drawable.pic4,
            R.drawable.pic1,
            R.drawable.i4,R.drawable.i1};
    Timer _t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirements);
        Back = findViewById(R.id.back);

        r = findViewById(R.id.rr);
        _t = new Timer();
        _t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() // run on ui thread
                {
                    public void run() {
                        if (count < drawablearray.length) {

                            r.setBackgroundResource(drawablearray[count]);

                           //r.setBackgroundDrawable(drawablearray[count]);
                            count = (count + 1) % drawablearray.length;
                        }
                    }
                });
            }
        }, 1000, 1000);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requirements.this, MapsActivity.class);
                startActivity(i);
            }
        });
/**
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                r = findViewById(R.id.rr);
                r.setBackgroundResource(R.drawable.i1);
            }
        }, 800);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                r = findViewById(R.id.rr);
                r.setBackgroundResource(R.drawable.i2);
            }
        }, 800);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 800);

*/
    /** final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (i=0;i<=2;i++) {
                        r = findViewById(R.id.rr);
                        r.setBackgroundResource(myImageList[i]);
                        Toast.makeText(requirements.this, "image: " + i, Toast.LENGTH_SHORT).show();
                        handler.postDelayed(this, 1000);
                    }
                }
            };
            handler.postDelayed(runnable, 1000);
*/

}
}


