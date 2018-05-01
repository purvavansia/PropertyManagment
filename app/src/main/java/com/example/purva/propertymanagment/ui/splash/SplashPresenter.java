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
   ISplashView iSplashView;

    public SplashPresenter(Context context, ISplashView iSplashView) {
        this.context = context;
        this.iSplashView = iSplashView;
        iSplashView.displayVideo();

    }

    @Override
    public void startHomeActivity() {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
