package com.example.purva.propertymanagment.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.model.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by purva on 4/26/18.
 */

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {

    List<Property.PropertyBean> propertyList;
    Context context;
    private int image[]= {R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree,
            R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree,
            R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree,
            R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree,
            R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree,
            R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree,
            R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree,R.drawable.buildingone,R.drawable.buidingtwo,R.drawable.buildingthree};

    public PropertyAdapter(List<Property.PropertyBean> propertyList, Context context) {
        this.propertyList = propertyList;
        this.context = context;
    }

    @NonNull
    @Override
    public PropertyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyAdapter.MyViewHolder holder, int position) {
        Property.PropertyBean property = propertyList.get(position);
        holder.address.setText("Address: "+property.getPropertyaddress()+" "+property.getPropertycity()+" "+property.getPropertystate()+" "+property.getPropertycountry());
        holder.price.setText("Price: "+property.getPropertypurchaseprice());
            holder.property_image.setImageResource(image[position]);

    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView address, price;
        ImageView property_image;

        public MyViewHolder(View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.propertyAddress);
            price = itemView.findViewById(R.id.propertyPrice);
            property_image = itemView.findViewById(R.id.imageProperty);
        }
    }
}