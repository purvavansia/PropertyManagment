package com.example.purva.propertymanagment.ui.tenant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.TenantAdapter;
import com.example.purva.propertymanagment.data.adapters.TenantClickListener;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.model.Tenant;
import com.example.purva.propertymanagment.ui.home.MainActivity;

import java.util.List;


public class ListTenantFragment extends Fragment {
    Context mContext;
    RecyclerView mRecyclerView;
    TenantAdapter adapter;
    android.support.v7.widget.Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container !=null){
            container.removeAllViews();
        }
        View view = inflater.inflate(R.layout.fragment_list_tenant, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        inflater.inflate(R.menu.tenantdisplay, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_tenants) {
            ((AppCompatActivity)this.mContext).getSupportFragmentManager().beginTransaction().replace(R.id.tenantFragmentContainer, new AddTenantFragment()).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = ((AppCompatActivity)mContext).findViewById(R.id.toolbar);
        TextView tenantTv = mToolbar.findViewById(R.id.back);

        mRecyclerView = view.findViewById(R.id.tenant_list);
        DbHelper dbHelper = new DbHelper(getActivity());
        List<Tenant.TenantBean> tenants = dbHelper.getAllTenants();
        Log.d("ListTenantSize", tenants.size()+"");
        adapter = new TenantAdapter(tenants, mContext);
        adapter.notifyDataSetChanged();
        LinearLayoutManager llayout = new LinearLayoutManager(mContext);
        DividerItemDecoration itemDecor = new DividerItemDecoration(mRecyclerView.getContext(), llayout.getOrientation());
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(llayout);
        mRecyclerView.setAdapter(adapter);

        tenantTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), MainActivity.class);
               startActivity(intent);
            }
        });

        adapter.setTenantClickListener(new TenantClickListener() {
            @Override
            public void onTenantClicked(View v, int position) {

            }
        });

    }

}
