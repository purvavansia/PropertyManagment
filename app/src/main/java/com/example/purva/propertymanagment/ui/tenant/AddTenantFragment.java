package com.example.purva.propertymanagment.ui.tenant;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.PropertySelectionAdapter;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.data.model.Tenant;
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
    @BindView(R.id.landlord_id)
    EditText landlordIdEt;
    @BindView(R.id.confirmBtn)
    Button confirmBtn;
    @BindView(R.id.property_id)
    EditText propertyEt;
    @BindView(R.id.spinner)
    Spinner propertySpinner;
    String tenantStreet;
    String cityStateCountry;
    int spinnerPos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tenant, container, false);
        ButterKnife.bind(this, view);

        DbHelper dbHelper = new DbHelper(getActivity());
        List<Property.PropertyBean> propertyBeans = dbHelper.getAllProperties();
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
                            Log.d("onNext", initStr);
                            DbHelper dbHelper = new DbHelper(getActivity());
                            long row_id = dbHelper.insertTenantRecord(propertyId, landlordId, tenantName, tenantEmail, tenantAddr,tenantMobile);
                            Log.d("DBTenant", row_id+"");
                            if(row_id == -1){
                                Toast.makeText(getActivity(), "Database Operation INSERTION ERROR", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.d("onError90", t.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.d("onCompleted", "Request completed");
                        }
                    });
        }
    }


}
