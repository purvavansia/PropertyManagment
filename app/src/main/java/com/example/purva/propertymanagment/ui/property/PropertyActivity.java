package com.example.purva.propertymanagment.ui.property;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.purva.propertymanagment.R;

public class PropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        PropertyListFragment propertyListFragment = new PropertyListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutProperty,propertyListFragment,"adding frag").commit();

    }
}
