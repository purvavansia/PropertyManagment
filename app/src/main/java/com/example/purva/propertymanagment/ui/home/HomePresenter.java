package com.example.purva.propertymanagment.ui.home;

import android.content.Context;
import android.content.Intent;

import com.example.purva.propertymanagment.ui.property.PropertyActivity;
import com.example.purva.propertymanagment.ui.tenant.TenantActivity;
import com.example.purva.propertymanagment.ui.todo.ToDoActivity;
import com.example.purva.propertymanagment.ui.transaction.TransactionActivity;
import com.example.purva.propertymanagment.ui.transaction.TransactionListActivity;

/**
 * Created by purva on 4/29/18.
 */

public class HomePresenter implements IHomePresenter {

    Context context;
    IHomeView iHomeView;
    public HomePresenter(Context context, IHomeView iHomeView) {
        this.context = context;
        this.iHomeView = iHomeView;
        iHomeView.displayPieChart();
    }

    @Override
    public void onClickProperty() {
        Intent propertyIntent = new Intent(context, PropertyActivity.class);
        propertyIntent.putExtra("selection","main");
        context.startActivity(propertyIntent);
    }

    @Override
    public void onClickTodo() {
        Intent todoIntent = new Intent(context, ToDoActivity.class);
        context.startActivity(todoIntent);
    }

    @Override
    public void onClickAddTransaction() {
        Intent transactionIntent = new Intent(context, TransactionActivity.class);
        context.startActivity(transactionIntent);
    }

    @Override
    public void onClickViewTransactions() {
        Intent viewTransactionIntent = new Intent(context, TransactionListActivity.class);
        context.startActivity(viewTransactionIntent);
    }

    @Override
    public void onClickTenants() {
        Intent intent = new Intent(context, TenantActivity.class);
        context.startActivity(intent);
    }
}
