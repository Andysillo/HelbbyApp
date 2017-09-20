package com.helbby.helbbyapp.cabg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends Activity {

    TextView textViewHelbby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2500);

      /* textViewHelbby = (TextView) findViewById(R.id.textHelbby);
        Typeface fuente = Typeface.createFromAsset(getAssets(), "fonts/Anydore.otf");
        textViewHelbby.setTypeface(fuente); */
    }
}
