package com.helbby.helbbyapp.cabg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class OlviContActivity extends AppCompatActivity {

    EditText etRecuperar;
    Button btRecuperar;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvi_cont);

        etRecuperar = (EditText) findViewById(R.id.emailRecuperar);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        auth = FirebaseAuth.getInstance();

        btRecuperar = (Button) findViewById(R.id.button_recuperar);
        btRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etRecuperar.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    etRecuperar.setError(getText(R.string.escriba_email));
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                etRecuperar.setVisibility(View.GONE);
                btRecuperar.setVisibility(View.GONE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                etRecuperar.setVisibility(View.INVISIBLE);
                                btRecuperar.setVisibility(View.INVISIBLE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(OlviContActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    finish();

                                }

                            }
                        });

            }
        });

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        OlviContActivity.this.overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }
}
