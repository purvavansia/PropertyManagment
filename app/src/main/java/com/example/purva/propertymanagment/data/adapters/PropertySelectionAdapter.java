package com.example.purva.propertymanagment.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.model.Property;

import java.util.List;

public class PropertySelectionAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private Context mContext;
    private List<Property.PropertyBean> propertyBeans;
    public PropertySelectionAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, 0, objects);
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.propertyBeans = objects;
        Log.d("LISTSIZE", this.propertyBeans.size()+"");
        for(int i = 0; i < propertyBeans.size(); i++){
            Log.d("BeanInfo"+i, propertyBeans.get(i).getId()+" "+propertyBeans.get(i).getPropertycountry());
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        View view = this.inflater.inflate(R.layout.property_selection_layout, parent, false);
        TextView street = view.findViewById(R.id.streetAddr);
        TextView cityStateCountry = view.findViewById(R.id.city_state_country);
        street.setText(this.propertyBeans.get(position).getPropertyaddress());
        cityStateCountry.setText(String.format("%s %s %s", this.propertyBeans.get(position).getPropertycity(), this.propertyBeans.get(position).getPropertystate(), this.propertyBeans.get(position).getPropertycountry()));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return createItemView(position, convertView, parent);
    }
}
