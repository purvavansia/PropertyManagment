package com.example.purva.propertymanagment.ui.login;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.ui.Constants;
import com.example.purva.propertymanagment.ui.home.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {


    private static final int RC_SIGN_IN = 007 ;
    private static final String TAG = "sign on test" ;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView mStatusTextView;
    private ConstraintLayout container;

    EditText editTextPassword, editTextName;
    ImageButton speak;
    Button login,signup;
    ILoginPresenter iLoginPresenter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private CheckBox cbRemember;
    String storedName;
    String password;
    Boolean storedCheck;
    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.custom_dialog, null);
        alertDialog.setView(customView);
        TextView messageView = (TextView)customView.findViewById(R.id.messagetv);
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Technologies used");
        //alertDialog.setMessage();

        alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        messageView.setText("Retrofit \n\nRxJava \n\nRetrofit with RxJava \n\nButterKnife \n\nRecyclerView " +
                "\n\nCardView \n\nSQLite Database \n\nArchitecture: MVP");
        AlertDialog alert = alertDialog.create();
        alert.show();



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        //instantiating the google sign in client to communicate with the google sign on api.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signOut();
        mStatusTextView =findViewById(R.id.status);
        SignInButton signInButton = findViewById(R.id.sign_in_button);

        container = findViewById(R.id.constraintLt);

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(LoginActivity.this);


        checkPermission();
        editTextPassword = findViewById(R.id.editTextLoginPassword);
        cbRemember = findViewById(R.id.checkRemember);
        editTextName = findViewById(R.id.editTextLoginName);
        speak = findViewById(R.id.micImage);
        login = findViewById(R.id.buttonLogin);
        signup = findViewById(R.id.buttonCreateAcc);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        iLoginPresenter = new LoginPresenter(this,this);

        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null) {
                    editTextPassword.setText(matches.get(0));
                    password = matches.get(0);
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
        sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
        storedName = sharedPreferences.getString("useremail","");
        storedCheck = sharedPreferences.getBoolean("checkbox", false);
        editTextName.setText(storedName);
        cbRemember.setChecked(storedCheck);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_email = editTextName.getText().toString();
                String login_password = editTextPassword.getText().toString();
                Boolean remember = cbRemember.isChecked();
                if(cbRemember.isChecked()) {
                    editor = sharedPreferences.edit();
                    editor.putString("useremail", login_email);
                    editor.putBoolean("checkbox", remember);
                    editor.commit();
                }
                else {
                    editor = sharedPreferences.edit();
                    editor.putString("useremail", "");
                    editor.putBoolean("checkbox", false);
                    editor.commit();
                }
                iLoginPresenter.callApiLogin(login_email,login_password);

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iLoginPresenter.signUpClicked();
            }
        });
        speak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                iLoginPresenter.getView(v,event);
                return false;
            }

        });


    }

    private void checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                iLoginPresenter.checkPermission();
                finish();
            }
        }
    }

    @Override
    public void setHint(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //when the user removed the finger
                mSpeechRecognizer.stopListening();
                editTextPassword.setHint(Constants.YOU_WILL_SEE_INPUT_HERE);
                break;

            case MotionEvent.ACTION_DOWN:
                //finger is on the button
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                editTextPassword.setText("");
                editTextPassword.setHint(Constants.LISTENING);
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //log user out
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        updateUI(null);
                        // [END_EXCLUDE]
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {
            mStatusTextView.setText("signed in"+ account.getDisplayName());
            Log.i(TAG, "updateUI: "+"\n display name: "+account.getDisplayName()+"" +
                    "\n email: "+account.getEmail()+"" +
                    "n"+account.getFamilyName()
                    +"\n "+account.zzabc());

            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            Snackbar.make(container,"Display Name: "+account.getDisplayName() +
                    "\n Email: "+account.getEmail()+"" +
                    "\n Family Name: "+account.getFamilyName(), Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);

        } else {
            mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

}
