package com.example.purva.propertymanagment.data.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.purva.propertymanagment.ui.signup.landlord.LandlordSignup;
import com.example.purva.propertymanagment.ui.signup.tenant.TenantSignup;

/**
 * Created by purva on 4/23/18.
 */

public class TabsPagerAdapter extends FragmentStatePagerAdapter{

    int tabCount;


    public TabsPagerAdapter(FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);
        this.tabCount = tabCount;

    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                LandlordSignup tab1 = new LandlordSignup();
                return tab1;
            case 1:
                TenantSignup tab2 = new TenantSignup();
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
