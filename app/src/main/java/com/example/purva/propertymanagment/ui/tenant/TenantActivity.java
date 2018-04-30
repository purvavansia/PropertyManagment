package com.example.purva.propertymanagment.ui.tenant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.purva.propertymanagment.R;

public class TenantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
            ListTenantFragment ltf = new ListTenantFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.tenantFragmentContainer, ltf).commit();
    }
}
