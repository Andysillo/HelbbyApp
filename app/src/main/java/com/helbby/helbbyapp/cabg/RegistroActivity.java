package com.helbby.helbbyapp.cabg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        RegistroActivity.this.overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }
}
