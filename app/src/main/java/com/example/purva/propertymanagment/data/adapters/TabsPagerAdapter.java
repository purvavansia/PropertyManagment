package com.example.purva.propertymanagment.data.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.purva.propertymanagment.ui.signup.landlord.LandlordSignupFragment;
import com.example.purva.propertymanagment.ui.signup.tenant.TenantSignupFragment;

/**
 * Created by purva on 4/23/18.
 */

public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    private int PAGE_COUNT = 2;
    int tabCount;
    private Context mContext;

    public TabsPagerAdapter(Context context, FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        this.mContext = context;
        this.tabCount = tabCount;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LandlordSignupFragment.getInstant();
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
}

