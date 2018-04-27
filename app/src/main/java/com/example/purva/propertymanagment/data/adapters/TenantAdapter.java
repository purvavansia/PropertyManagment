package com.example.purva.propertymanagment.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.model.Tenant;

import java.util.ArrayList;

public class TenantAdapter extends RecyclerView.Adapter<TenantAdapter.MyViewHolder> {
    ArrayList<Tenant> mTenants;
    Context mContext;

    public TenantAdapter(ArrayList<Tenant> tenants, Context context) {
        Log.i("TenantConstructor", "constructing");
        mTenants = tenants;
        mContext = context;
        Log.i("Tenant_Size2", mTenants.size()+"");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.tenant_list_item, parent, false);
       Log.i("Tenant_Size", mTenants.size()+"");
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageView imgView = holder.img;
        holder.address.setText(mTenants.get(position).getAddress());
        //holder.img.setImageDrawable();
        holder.name.setText(mTenants.get(position).getName());
        holder.itemView.setTag(this);
    }


    @Override
    public int getItemCount() {
        return mTenants.size();
    }

    public class MyViewHolder extends ViewHolder {
        ImageView img;
        TextView address, name;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tenant_img);
            name = itemView.findViewById(R.id.tenant_name);
            address = itemView.findViewById(R.id.tenant_addr);
        }
    }
}
