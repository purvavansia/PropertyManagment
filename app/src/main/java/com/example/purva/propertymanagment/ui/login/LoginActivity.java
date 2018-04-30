package com.example.purva.propertymanagment.ui.login;

import android.Manifest;
import android.content.Context;
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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.purva.propertymanagment.R;

import java.util.ArrayList;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkPermission();
        editTextPassword = findViewById(R.id.editTextLoginPassword);
        cbRemember = findViewById(R.id.checkRemember);
        editTextName = findViewById(R.id.editTextLoginName);
        speak = findViewById(R.id.micImage);
        login = findViewById(R.id.buttonLogin);
        signup = findViewById(R.id.buttonCreateAcc);
        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        iLoginPresenter = new LoginPresenter(this);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
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
                /*Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);*/
                iLoginPresenter.signUpClicked();
            }
        });
        speak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        //when the user removed the finger
                        mSpeechRecognizer.stopListening();
                        editTextPassword.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        //finger is on the button
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        editTextPassword.setText("");
                        editTextPassword.setHint("Listening...");
                        break;
                }
                return false;
            }

        });


    }

    private void checkPermission() {

        //iLoginPresenter.checkPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + this.getPackageName()));
                startActivity(intent);

                finish();
            }
        }
    }
}
