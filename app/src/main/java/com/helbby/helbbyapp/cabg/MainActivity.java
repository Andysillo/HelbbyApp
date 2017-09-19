package com.helbby.helbbyapp.cabg;

import android.content.Intent;

import android.graphics.Typeface;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    public static final int SIGN_IN_CODE = 777;
    TextView textViewHelbby, textView10;
    LoginButton buttonFacebook;
    GoogleApiClient googleApiClient;
    CallbackManager callbackManager;
    SignInButton signInButton;
    Button registrarse, ingresa_email;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;
    ProgressBar progressBar;
    ImageView imageViewFondo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializacion SDK de facebook //
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
        buttonFacebook= (LoginButton) findViewById(R.id.button_facebook);
        buttonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });

        // Inicializacion de botton google//
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.button_google);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    goMainScreen();
                }
            }
        };

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView10 = (TextView) findViewById(R.id.textView10);
        imageViewFondo = (ImageView) findViewById(R.id.imageViewFondo);

        registrarse = (Button) findViewById(R.id.registrarse);
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistrarse = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intentRegistrarse);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });

        ingresa_email = (Button) findViewById(R.id.ingresa_email);
        ingresa_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegistrarse = new Intent(MainActivity.this, LoginEmailActivity.class);
                startActivity(intentRegistrarse);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });


        // Cambiar fuente texview helbby //
        textViewHelbby = (TextView) findViewById(R.id.textHelbby);
        Typeface fuente = Typeface.createFromAsset(getAssets(), "fonts/Anydore.otf");
        textViewHelbby.setTypeface(fuente);
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE){
           GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            firebaseAuthWithGoogle(result.getSignInAccount());
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {

        progressBar.setVisibility(View.VISIBLE);
        buttonFacebook.setVisibility(View.GONE);
        signInButton.setVisibility(View.GONE);
        registrarse.setVisibility(View.GONE);
        textView10.setVisibility(View.GONE);
        ingresa_email.setVisibility(View.GONE);
        imageViewFondo.setVisibility(View.GONE);
        textViewHelbby.setVisibility(View.GONE);


        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                buttonFacebook.setVisibility(View.VISIBLE);
                signInButton.setVisibility(View.VISIBLE);
                registrarse.setVisibility(View.VISIBLE);
                textView10.setVisibility(View.VISIBLE);
                ingresa_email.setVisibility(View.VISIBLE);
                imageViewFondo.setVisibility(View.VISIBLE);
                textViewHelbby.setVisibility(View.VISIBLE);
                if (task.isSuccessful()){
                }

            }
        });
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, HelbbyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
}
