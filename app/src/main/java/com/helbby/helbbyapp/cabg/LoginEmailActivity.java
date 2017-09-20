package com.helbby.helbbyapp.cabg;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginEmailActivity extends AppCompatActivity {

    Button olvidarContra, buttonIngresa;
    EditText etEmail, etPass;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthListener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        auth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(LoginEmailActivity.this, HelbbyActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.trans_zoom_forward_in, R.anim.trans_zoom_forward_out);
                    finish();
                    //mAuth.signOut();
                }
            }
        };

        etEmail = (EditText) findViewById(R.id.emailLogin);
        etPass = (EditText) findViewById(R.id.passwordLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        buttonIngresa = (Button) findViewById(R.id.buttonIngresar);
        buttonIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });

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
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    private void startRegister(){
        final String email = etEmail.getText().toString();
        final String password = etPass.getText().toString();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            progressBar.setVisibility(View.VISIBLE);
            etEmail.setVisibility(View.GONE);
            etPass.setVisibility(View.GONE);
            buttonIngresa.setVisibility(View.GONE);
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            etEmail.setVisibility(View.VISIBLE);
                            etPass.setVisibility(View.VISIBLE);
                            buttonIngresa.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                auth.signInWithEmailAndPassword(email, password);
                            }
                        }
                    });
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        LoginEmailActivity.this.overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }
}
