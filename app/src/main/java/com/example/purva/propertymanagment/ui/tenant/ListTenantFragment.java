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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.TenantAdapter;
import com.example.purva.propertymanagment.data.adapters.TenantClickListener;
import com.example.purva.propertymanagment.data.model.Tenant;
import com.example.purva.propertymanagment.ui.BaseFragment;
import com.example.purva.propertymanagment.ui.home.MainActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;


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

//    public static ListTenantFragment INSTANT = null;
//
//    public static ListTenantFragment getInstant() {
//        if (INSTANT == null) {
//            INSTANT = new ListTenantFragment();
//        }
//        return INSTANT;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
            adapter.addItem(new Tenant("gg@gmail.com", "Rui", "USA", "Tenant", "890", "Ray"));
        }
        return super.onOptionsItemSelected(item);
    }


//
//    @Override
//    protected int getLayoutResource() {
//        return R.layout.fragment_list_tenant;
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = ((AppCompatActivity)mContext).findViewById(R.id.toolbar);
        TextView tenantTv = mToolbar.findViewById(R.id.back);

        mRecyclerView = view.findViewById(R.id.tenant_list);
        ArrayList<Tenant> tenants = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tenants.add(new Tenant("jason@gmail.com", "Jason", "USA", "Property"+ String.valueOf(i*30), "LLID", "4444444444"));
        }

        adapter = new TenantAdapter(tenants, mContext);
        Log.i("ADAPTER", "Finish creating adapter");
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
