package com.example.purva.propertymanagment.ui.property;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.purva.propertymanagment.R;

/**
 * Created by purva on 5/2/18.
 */

public class PropertyPresenter implements IPropertyPresenter {

    Context context;

    public PropertyPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void addFragment() {
        PropertyListFragment propertyListFragment = new PropertyListFragment();
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutProperty,propertyListFragment,"adding frag").commit();
    }
}
