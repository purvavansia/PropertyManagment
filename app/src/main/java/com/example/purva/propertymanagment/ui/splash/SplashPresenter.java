package com.example.purva.propertymanagment.ui.splash;

import android.content.Context;
import android.content.Intent;

import com.example.purva.propertymanagment.ui.home.MainActivity;
import com.example.purva.propertymanagment.ui.login.LoginActivity;

/**
 * Created by purva on 4/23/18.
 */

public class SplashPresenter implements ISplashPresenter {

   Context context;

    public SplashPresenter(Context context) {
        this.context = context;

    }

    @Override
    public void startHomeActivity() {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
