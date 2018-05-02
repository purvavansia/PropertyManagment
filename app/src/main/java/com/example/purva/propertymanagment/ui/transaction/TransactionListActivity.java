package com.example.purva.propertymanagment.ui.transaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.TransactionAdapter;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.data.model.Transaction;
import com.example.purva.propertymanagment.ui.home.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionListActivity extends AppCompatActivity {

    IDbHelper iDbHelper;
    List<Transaction.TransactionBean> transactionBeanList;
    RecyclerView recyclerView;
    TransactionAdapter transactionAdapter;
    Toolbar toolbar;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        toolbar = findViewById(R.id.toolbar_transactionList);
        imageView = toolbar.findViewById(R.id.imageViewTransactionList);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(TransactionListActivity.this,MainActivity.class);
                startActivity(homeIntent);
            }
        });


        transactionBeanList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview_transactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        iDbHelper = new DbHelper(this);
        transactionBeanList = iDbHelper.getAllTransaction();
        transactionAdapter = new TransactionAdapter(transactionBeanList,this);
        recyclerView.setAdapter(transactionAdapter);
        transactionAdapter.notifyDataSetChanged();
    }
}
