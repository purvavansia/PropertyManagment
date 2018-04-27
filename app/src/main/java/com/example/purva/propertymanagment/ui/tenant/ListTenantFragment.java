package com.example.purva.propertymanagment.ui.tenant;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.TenantAdapter;
import com.example.purva.propertymanagment.data.model.Tenant;
import com.example.purva.propertymanagment.ui.BaseFragment;

import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;

public class ListTenantFragment extends BaseFragment {
    Context mContext;
    RecyclerView mRecyclerView;
   // TenantAdapter mTenantAdapter;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.mContext = context;
    }

    public static ListTenantFragment INSTANT = null;

    public static ListTenantFragment getInstant() {
        if (INSTANT == null) {
            INSTANT = new ListTenantFragment();
        }
        return INSTANT;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_list_tenant;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.tenant_list);
        ArrayList<Tenant> tenants = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            tenants.add(new Tenant("jason@gmail.com", "Jason", "USA", "Property"+ String.valueOf(i*30), "LLID", "4444444444"));
        }

        TenantAdapter adapter = new TenantAdapter(tenants, mContext);
        Log.i("ADAPTER", "Finish creating adapter");
        LinearLayoutManager llayout = new LinearLayoutManager(mContext);
        DividerItemDecoration itemDecor = new DividerItemDecoration(mRecyclerView.getContext(), llayout.getOrientation());
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(llayout);
        mRecyclerView.setAdapter(adapter);

    }

}
