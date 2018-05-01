package com.example.purva.propertymanagment.data.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
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
import com.example.purva.propertymanagment.data.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by purva on 4/30/18.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {


    List<Transaction.TransactionBean> transactionBeanList;
    Context context;
    IDbHelper iDbHelper;

    public TransactionAdapter(List<Transaction.TransactionBean> transactionBeanList, Context context) {
        this.transactionBeanList = transactionBeanList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction,parent,false);

        return new TransactionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        iDbHelper = new DbHelper(context);
        Transaction.TransactionBean transactionBean = transactionBeanList.get(position);
        holder.date.setText("Transaction Date: "+transactionBean.getDate());
        holder.summary.setText("Transaction Summary: "+transactionBean.getSummary());
        holder.description.setText("Transaction Description: "+transactionBean.getDescription());
        holder.amount.setText("Transaction Amount: "+transactionBean.getAmount());
        holder.type.setText("Transaction Type: "+transactionBean.getType());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setCancelable(false);
                alertDialog.setTitle("Transaction Details");
                alertDialog.setMessage("Transaction Date: "+transactionBean.getDate()+"\n"+"Transaction Summary: "+transactionBean.getSummary()
                +"\n"+"Transaction Description: "+transactionBean.getDescription()+"\n"+"Transaction Amount: "+transactionBean.getAmount()
                        +"\n"+"Transaction Type: "+transactionBean.getType());
                alertDialog.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        iDbHelper.deleteTransactionById(transactionBean.getTransactionId());
                        transactionBeanList.remove(position);
                        notifyDataSetChanged();

                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setContentView(R.layout.dialog_layout);
                alert.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return transactionBeanList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date, summary, description, amount, type;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.textViewDate);
            summary = itemView.findViewById(R.id.textViewSummary);
            description = itemView.findViewById(R.id.textViewDesc);
            amount = itemView.findViewById(R.id.textViewAmount);
            type = itemView.findViewById(R.id.textViewType);
            linearLayout = itemView.findViewById(R.id.linearlayoutTransaction);

        }
    }
}
