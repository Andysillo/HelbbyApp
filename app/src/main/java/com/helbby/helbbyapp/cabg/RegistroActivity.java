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

public class RegistroActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthListener;
    EditText etNombre, etEmail, etPass;
    Button btRegis;
    ProgressDialog mProgress;


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        auth = FirebaseAuth.getInstance();

        etNombre = (EditText) findViewById(R.id.nombre);
        etEmail = (EditText) findViewById(R.id.email);
        etPass = (EditText) findViewById(R.id.password);


        mProgress = new ProgressDialog(this);
        btRegis = (Button) findViewById(R.id.buttonRegistrarse);
        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(RegistroActivity.this, HelbbyActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.trans_zoom_forward_in, R.anim.trans_zoom_forward_out);
                    finish();
                }
            }
        };
    }

    private void startRegister() {
        String name = etNombre.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String password = etPass.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            etNombre.setError(getText(R.string.escriba_nombre));
            return;
        }
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getText(R.string.escriba_email));
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPass.setError(getText(R.string.escriba_contra));
            return;
        }

        if (password.length() < 8) {
            etPass.setError(getText(R.string.contra_corta));
            return;
        }
        mProgress.setMessage("Registering, please wait...");
        mProgress.show();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        mProgress.dismiss();
                        if (task.isSuccessful()) {
                            auth.signInWithEmailAndPassword(email, password);

                        }

                    }
                });
    }



    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        RegistroActivity.this.overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }
}
