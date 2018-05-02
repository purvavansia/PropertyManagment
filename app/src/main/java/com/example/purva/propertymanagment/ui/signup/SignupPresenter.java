package com.example.purva.propertymanagment.ui.signup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.purva.propertymanagment.data.adapters.TabsPagerAdapter;

/**
 * Created by purva on 5/2/18.
 */

public class SignupPresenter implements ISignupPresenter {

    Context context;

    public SignupPresenter(Context context) {
        this.context = context;
    }

    @Override
    public TabsPagerAdapter getTabs(int count) {
        TabsPagerAdapter pageAdapter = new TabsPagerAdapter(context,((AppCompatActivity)context). getSupportFragmentManager(),count);

        return pageAdapter;

    }
}
