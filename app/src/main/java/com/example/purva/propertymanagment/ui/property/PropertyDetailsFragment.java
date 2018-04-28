package com.example.purva.propertymanagment.ui.property;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.purva.propertymanagment.R;

/**
 * Created by purva on 4/28/18.
 */

public class PropertyDetailsFragment extends Fragment {

    TextView street, city, state, country, price,status, mortgage;
    Button delete;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_details,container,false);

        street = view.findViewById(R.id.propertyDetailsStreet);
        city = view.findViewById(R.id.propertyDetailsCity);
        state = view.findViewById(R.id.propertyDetailsState);
        country = view.findViewById(R.id.propertyDetailsCountry);
        price = view.findViewById(R.id.propertyDetailsPrice);
        status = view.findViewById(R.id.propertyDetailsStatus);
        mortgage = view.findViewById(R.id.propertyDetailsMortgage);
        delete = view.findViewById(R.id.buttonDeleteProperty);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return view;
    }
}
