package com.example.purva.propertymanagment.ui.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.purva.propertymanagment.data.model.User;
import com.example.purva.propertymanagment.ui.home.MainActivity;
import com.example.purva.propertymanagment.ui.signup.Constants;
import com.example.purva.propertymanagment.ui.signup.SignUpActivity;
import com.example.purva.propertymanagment.ui.signup.landlord.RetrofitInstanceLandlordSignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by purva on 4/24/18.
 */

public class LoginPresenter implements ILoginPresenter {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void checkPermission() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!(ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);

            }
        }
    }

    @Override
    public void signUpClicked() {
        Intent signUpIntent = new Intent(context, SignUpActivity.class);
        context.startActivity(signUpIntent);
    }

    @Override
    public void callApiLogin(String email, String password) {
        sharedPreferences = context.getSharedPreferences("mydata",Context.MODE_PRIVATE);
        ApiServiceLogin apiService = RetrofitInstanceLandlordSignUp.getRetrofitInstance().create(ApiServiceLogin.class);
        Call<User> signUpCall =  apiService.getUserDetails(email,password);
        signUpCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(Constants.TAG,""+response.body().getMsg());
                if(response.body().getMsg().contains("success")){

                    editor = sharedPreferences.edit();
                    editor.putString("userid", response.body().getUserid());
                    editor.putString("usertype", response.body().getUsertype());
                    editor.putString("useremail", response.body().getUseremail());
                    editor.putString("appapikey", response.body().getAppapikey());
                    editor.commit();

                    if (response.body().getUsertype().contains("tenant")) {

                    }
                    else if(response.body().getUsertype().contains("landlord")){

                        Intent honeIntent = new Intent(context, MainActivity.class);
                        context.startActivity(honeIntent);
                    }

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i(Constants.TAG,""+t);
            }
        });
    }
}
