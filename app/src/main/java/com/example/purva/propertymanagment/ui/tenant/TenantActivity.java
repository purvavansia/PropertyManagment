package com.example.purva.propertymanagment.ui.tenant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.purva.propertymanagment.R;

public class TenantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TENANTACTIVITY", "On Create Tenant Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
        ListTenantFragment ltf = ListTenantFragment.getInstant();
        getSupportFragmentManager().beginTransaction().replace(R.id.tenantFragmentContainer, ltf).commit();


    }
}
