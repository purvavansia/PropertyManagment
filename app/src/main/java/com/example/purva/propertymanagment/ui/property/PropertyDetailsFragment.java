package com.example.purva.propertymanagment.ui.property;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.network.ApiService;
import com.example.purva.propertymanagment.network.RetrofitInstance;

import com.example.purva.propertymanagment.ui.Constants;
import com.example.purva.propertymanagment.ui.home.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by purva on 4/28/18.
 */

public class PropertyDetailsFragment extends android.support.v4.app.Fragment {

    TextView street, city, state, country, price,status, mortgage;
    Button delete;
    SharedPreferences sharedPreferences;
    IDbHelper iDbHelper;
    Toolbar toolbar;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_details,container,false);

        toolbar = view.findViewById(R.id.toolbar_propertyDeatails);
        imageView = toolbar.findViewById(R.id.imageViewDetailsHome);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getActivity(),MainActivity.class);
                startActivity(homeIntent);
            }
        });

        sharedPreferences = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE);
        String userid = sharedPreferences.getString("userid","");
        String pro_street = sharedPreferences.getString("propertystreet","");
        String pro_city = sharedPreferences.getString("propertycity","");
        String pro_state = sharedPreferences.getString("propertystate","");
        String pro_country = sharedPreferences.getString("propertycountry","");
        String pro_status = sharedPreferences.getString("propertystatus","");
        String pro_mortgage = sharedPreferences.getString("propertymortgage","");
        String pro_price = sharedPreferences.getString("propertyprice","");
        String propertyid = sharedPreferences.getString("propertyid","");
        street = view.findViewById(R.id.propertyDetailsStreet);
        city = view.findViewById(R.id.propertyDetailsCity);
        state = view.findViewById(R.id.propertyDetailsState);
        country = view.findViewById(R.id.propertyDetailsCountry);
        price = view.findViewById(R.id.propertyDetailsPrice);
        status = view.findViewById(R.id.propertyDetailsStatus);
        mortgage = view.findViewById(R.id.propertyDetailsMortgage);
        delete = view.findViewById(R.id.buttonDeleteProperty);
        iDbHelper = new DbHelper(getActivity());

        street.setText("Street Addresss: "+pro_street);
        city.setText("City: "+pro_city);
        state.setText("State: "+pro_state);
        country.setText("Country: "+pro_country);
        price.setText("Price: $"+pro_price);
        status.setText("Property For: "+pro_status);
        mortgage.setText("Mortgage Info: "+pro_mortgage);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
                Call<Property> propertyCall  = apiService.removeProperty(propertyid);
                propertyCall.enqueue(new Callback<Property>() {
                    @Override
                    public void onResponse(Call<Property> call, Response<Property> response) {
                        Log.i(Constants.TAG,"deleted");
                    }

                    @Override
                    public void onFailure(Call<Property> call, Throwable t) {

                    }
                });

                iDbHelper.deletePropertyById(propertyid,userid);
               PropertyListFragment propertyListFragment = new PropertyListFragment();
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutProperty, propertyListFragment).commit();
            }
        });

        return view;
    }
}
