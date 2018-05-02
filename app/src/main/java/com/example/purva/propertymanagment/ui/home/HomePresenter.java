package com.example.purva.propertymanagment.ui.home;

import android.content.Context;
import android.content.Intent;

import com.example.purva.propertymanagment.ui.Constants;
import com.example.purva.propertymanagment.ui.document.DocumentActivity;
import com.example.purva.propertymanagment.ui.login.LoginActivity;
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

    /**
     *
     * @param context this is the context of the activity
     * @param iHomeView same context is passed to the IHomeView interface in order to call its methods
     */
    public HomePresenter(Context context, IHomeView iHomeView) {
        this.context = context;
        this.iHomeView = iHomeView;
        iHomeView.displayPieChart();
    }

    @Override
    public void onClickProperty() {
        Intent propertyIntent = new Intent(context, PropertyActivity.class);
        propertyIntent.putExtra(Constants.SELECTION, Constants.MAIN);
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

    @Override
    public void onClickDocuments() {
        context.startActivity(new Intent(context, DocumentActivity.class));
    }

    @Override
    public void onClickLogout() {
        Intent logoutIntent = new Intent(context, LoginActivity.class);
        context.startActivity(logoutIntent);
    }
}
