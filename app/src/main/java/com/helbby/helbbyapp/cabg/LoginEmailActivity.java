package com.helbby.helbbyapp.cabg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginEmailActivity extends AppCompatActivity {

    Button olvidarContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);


        olvidarContra = (Button) findViewById(R.id.olvidarContra);
        olvidarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistrarse = new Intent(LoginEmailActivity.this, OlviContActivity.class);
                startActivity(intentRegistrarse);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });


    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        LoginEmailActivity.this.overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }
}
