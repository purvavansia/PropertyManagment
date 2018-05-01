package com.example.purva.propertymanagment.ui.property;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.PropertyAdapter;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.network.ApiService;
import com.example.purva.propertymanagment.network.RetrofitInstance;
import com.example.purva.propertymanagment.ui.signup.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

/**
 * Created by purva on 4/26/18.
 */

public class PropertyListFragment extends Fragment {

    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    ArrayList<Property.PropertyBean> properties;

    Toolbar toolbar;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_list,container,false);

        toolbar = view.findViewById(R.id.toolbar_property);
        imageView = toolbar.findViewById(R.id.imageViewAddProp);
        recyclerView = view.findViewById(R.id.recyclerview_property);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        properties = new ArrayList<>();

       sharedPreferences = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE);
        String userid = sharedPreferences.getString("userid","");

        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        Call<Property> propertyCall =  apiService.getPropertyDetails(userid, Constants.LANDLORD);
        propertyCall.enqueue(new Callback<Property>() {
            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                Log.i(Constants.TAG, "SIZE: " + response.body().getProperty().size()+"");
                PropertyAdapter propertyAdapter = new PropertyAdapter(response.body().getProperty(),getActivity());
                propertyAdapter.setImages(response.body().getProperty().size());
                recyclerView.setAdapter(propertyAdapter);
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
                Log.i(Constants.TAG,""+t);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyInfoFragment propertyInfoFragment = new PropertyInfoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutProperty,propertyInfoFragment,"add frag").commit();
            }
        });

        return view;
    }


}
