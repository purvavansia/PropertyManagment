package com.example.purva.propertymanagment.ui.tenant;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.PropertySelectionAdapter;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.model.Property;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import java.util.List;

public class AddTenantFragment extends Fragment {
    @BindView(R.id.tenant_name)
    EditText nameEt;
    @BindView(R.id.tenant_email)
    EditText emailEt;
    @BindView(R.id.tenant_addr)
    EditText addrEt;
    @BindView(R.id.tenant_mobile)
    EditText mobileEt;
    @BindView(R.id.confirmBtn)
    Button confirmBtn;
    @BindView(R.id.property_id)
    EditText propertyEt;
    @BindView(R.id.spinner)
    Spinner propertySpinner;
    String tenantStreet;
    String cityStateCountry;
    int spinnerPos;
    DbHelper dbHelper;
    Toolbar mToolbar;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.tenantadd, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tenant, container, false);
        ButterKnife.bind(this, view);
        mToolbar = view.findViewById(R.id.toolbaraddTenant);

        dbHelper = new DbHelper(getActivity());
        List<Property.PropertyBean> propertyBeans = dbHelper.getAllProperties();
        String landlordId = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE).getString("userid", null);
        for (int i = 0; i < propertyBeans.size(); i++) {
            String getId = propertyBeans.get(i).getId();
            if (!propertyBeans.get(i).getLandlordId().equals(landlordId)) {
                propertyBeans.remove(i);
            }
        }
        PropertySelectionAdapter propertySelectionAdapter = new PropertySelectionAdapter(getActivity(), R.layout.property_selection_layout, propertyBeans);
        propertySelectionAdapter.setDropDownViewResource(R.layout.property_selection_layout);
        propertySpinner.setAdapter(propertySelectionAdapter);
        propertySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenantStreet = view.findViewById(R.id.streetAddr).toString();
                cityStateCountry = view.findViewById(R.id.city_state_country).toString();
                spinnerPos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    @OnClick(R.id.confirmBtn)
    public void confirmAccount() {
        String tenantName = nameEt.getText().toString();
        String tenantEmail = emailEt.getText().toString();
        String tenantAddr = addrEt.getText().toString();
        String tenantMobile = mobileEt.getText().toString();
        String propertyId = propertyEt.getText().toString();
        String landlordId = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE).getString("userid", null);
        if (tenantName.isEmpty() || tenantEmail.isEmpty() || tenantAddr.isEmpty()
                || tenantMobile.isEmpty() || landlordId.isEmpty() || propertyId.isEmpty()) {
            Toast.makeText(getActivity(), "No field can be empty", Toast.LENGTH_SHORT).show();
        } else {
            Retrofit retrofit = RetrofitInstanceTenant.getRetrofitInstance();
            retrofit.create(ApiServiceAddTenant.class)
                    .addTenant(tenantName, tenantEmail, tenantAddr, tenantMobile, propertyId, landlordId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("onSubscribe", "SUBBB");
                        }

                        @Override
                        public void onNext(String initStr) {
                            if (initStr.contains("successfully")) {
                                Log.d("AddedTenant", "Added Tenant Successfully");
                            }
                            long row_id = dbHelper.insertTenantRecord(propertyId, landlordId, tenantName, tenantEmail, tenantAddr, tenantMobile);
                            if (row_id == -1) {
                                Toast.makeText(getActivity(), "Database Operation INSERTION ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.d("onError90", t.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            ListTenantFragment listFragment = new ListTenantFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.tenantFragmentContainer, listFragment).commit();
                        }
                    });
        }
    }



}
