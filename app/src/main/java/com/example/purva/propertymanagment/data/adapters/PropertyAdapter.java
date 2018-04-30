package com.example.purva.propertymanagment.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.ui.property.PropertyActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by purva on 4/26/18.
 */

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
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

        sharedPreferences = context.getSharedPreferences("mydata", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String userid = sharedPreferences.getString("userid","");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //property.getId();
                Intent detailsIntent = new Intent(context, PropertyActivity.class);
                detailsIntent.putExtra("selection","details");
                /*Property.PropertyBean propertyBean = new Property.PropertyBean(property.getId(),userid,property.getPropertycountry(),
                        property.getPropertystate(),property.getPropertycity(),property.getPropertyaddress(),
                        property.getPropertypurchaseprice(),property.getPropertymortageinfo(),property.getPropertystatus());
                Bundle bundle = new Bundle();
                bundle.putParcelable("propertyInfo",propertyBean);*/
                editor.putString("propertyid",property.getId());
                editor.putString("propertycountry",property.getPropertycountry());
                editor.putString("propertystate",property.getPropertystate());
                editor.putString("propertycity",property.getPropertycity());
                editor.putString("propertystreet",property.getPropertyaddress());
                editor.putString("propertyprice",property.getPropertypurchaseprice());
                editor.putString("propertymortgage",property.getPropertymortageinfo());
                editor.putString("propertystatus",property.getPropertystatus());
                editor.commit();
                context.startActivity(detailsIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView address, price;
        ImageView property_image;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            address = itemView.findViewById(R.id.propertyAddress);
            price = itemView.findViewById(R.id.propertyPrice);
            property_image = itemView.findViewById(R.id.imageProperty);
            linearLayout = itemView.findViewById(R.id.item_property_linear_layout);
        }
    }
}