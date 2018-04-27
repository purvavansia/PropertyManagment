package com.example.purva.propertymanagment.ui.property;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.purva.propertymanagment.R;

public class PropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        if(getIntent().getExtras().getString("selection").equals("main")){
            PropertyListFragment propertyListFragment = new PropertyListFragment();
            getFragmentManager().beginTransaction().add(R.id.frameLayoutProperty,propertyListFragment,"adding frag").commit();
        }
        if(getIntent().getExtras().getString("selection").equals("addprop")){
            PropertyInfoFragment propertyInfoFragment = new PropertyInfoFragment();
            getFragmentManager().beginTransaction().add(R.id.frameLayoutProperty,propertyInfoFragment,"add frag").commit();
        }


    }
}
