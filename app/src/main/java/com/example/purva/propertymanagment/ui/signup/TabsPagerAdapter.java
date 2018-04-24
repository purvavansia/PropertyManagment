package com.example.purva.propertymanagment.ui.signup;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by purva on 4/23/18.
 */

public class TabsPagerAdapter extends FragmentStatePagerAdapter{

    int tabCount;


    Context mContext;
    public TabsPagerAdapter(Context context, FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);
        this.mContext = context;
        this.tabCount = tabCount;

    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                LandlordSignup tab1 = new LandlordSignup();
                return tab1;
            case 1:
                TenantSignupFragment tab2 = new TenantSignupFragment();
               // FragmentTransaction fragmentTransaction = this.m
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
