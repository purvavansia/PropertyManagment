package com.example.purva.propertymanagment.ui.property;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.ui.login.LoginActivity;
import com.example.purva.propertymanagment.ui.signup.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by purva on 4/27/18.
 */

public class PropertyInfoFragment extends Fragment {


    EditText street, city, state, country, price,propertFor,mortgageInfo;
    Button addProperty;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_property_info,container,false);
        sharedPreferences = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE);
        String userid = sharedPreferences.getString("userid","");
        street = view.findViewById(R.id.propertyAddStreet);
        city = view.findViewById(R.id.propertyAddCity);
        state = view.findViewById(R.id.propertyAddState);
        country = view.findViewById(R.id.propertyAddCountry);
        price = view.findViewById(R.id.propertyAddPrice);
        propertFor = view.findViewById(R.id.propertyAddStatus);
        mortgageInfo = view.findViewById(R.id.propertyAddMortgage);
        addProperty = view.findViewById(R.id.buttonAddNewProperty);

        addProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pro_street = street.getText().toString();
                String pro_city = city.getText().toString();
                String pro_state = state.getText().toString();
                String pro_country = country.getText().toString();
                String pro_price = price.getText().toString();
                String pro_for = propertFor.getText().toString();
                String pro_mortgage = mortgageInfo.getText().toString();

                ApiServiceProperty apiService = RetrofitInstanceProperty.getRetrofitInstance().create(ApiServiceProperty.class);
                Call<String> addPropertCall = apiService.addPropertyDetails(pro_street, pro_city, pro_state, pro_country,
                        pro_for, pro_price, pro_mortgage, userid, Constants.LANDLORD);

                addPropertCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i(Constants.TAG, ""+response.body());
                        if(response.body().contains("successfully")){
                            Intent propertyIntent = new Intent(getActivity(), PropertyActivity.class);
                            propertyIntent.putExtra("selection","main");
                            startActivity(propertyIntent);
                        }
                        else{
                            Toast.makeText(getActivity(),""+response.body(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i(Constants.TAG, ""+t);
                    }
                });
            }
        });




        return view;
    }
}
