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
    private int PAGE_COUNT=2;
    int tabCount;
    //private String tabTitles[] = new String[]{"Login", "SignUp"};
    private Context mContext;
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
               return TenantSignupFragment.getInstant();
            default:
                return TenantSignupFragment.getInstant();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

//    @Override
//    public CharSequence getPageTitle(int pos){return tabTitles[pos];}
}
