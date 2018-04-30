package com.example.purva.propertymanagment.ui.transaction;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.data.model.Property;

import java.text.SimpleDateFormat;
import java.util.*;

public class TransactionActivity extends AppCompatActivity {

    Spinner dropdownType, dropdownProperty;
    IDbHelper iDbHelper;
    EditText dateEt, summaryEt, descEt, amountEt;
    Button addTransaction;
    SharedPreferences sharedPreferences;
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        iDbHelper = new DbHelper(this);
        sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
        dropdownType = findViewById(R.id.spinnerTransactionType);
        dropdownProperty = findViewById(R.id.spinnerTransactionProperty);
        dateEt = findViewById(R.id.transactionDate);
        summaryEt = findViewById(R.id.transactionSummary);
        descEt = findViewById(R.id.transactionDescription);
        amountEt = findViewById(R.id.transactionAmount);
        addTransaction = findViewById(R.id.buttonAddTransaction);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        dateEt.setText(dateFormat.format(new Date()));
        String[] items = new String[]{"Rent Payment", "Full Property Payment", "Deposit"};

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datetext = dateEt.getText().toString();
                String summary = summaryEt.getText().toString();
                String desc = descEt.getText().toString();
                String amount = amountEt.getText().toString();
                String type = dropdownType.getSelectedItem().toString();
                String propertyId = dropdownProperty.getSelectedItem().toString();
                String landlordId = sharedPreferences.getString("userid","");
                iDbHelper.insertTransactionRecord(landlordId,datetext,summary,desc,amount,type,propertyId);
                int rowNum = iDbHelper.getTransactionCount();
                Log.d("ROWS", ""+rowNum );
                Toast.makeText(TransactionActivity.this,"Transaction Added",Toast.LENGTH_SHORT).show();

            }
        });


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TransactionActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdownType.setAdapter(adapter);

        iDbHelper = new DbHelper(this);

        List<Property.PropertyBean> propertyList = iDbHelper.getAllProperties();
        String[] propertyIds = new String[propertyList.size()];
        ListIterator<Property.PropertyBean> listIterator = propertyList.listIterator();
        for(int i=0; i< propertyList.size(); i++){
            propertyIds[i] = propertyList.get(i).getId();
        }

        ArrayAdapter<String> adapterProperty = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, propertyIds);
        dropdownProperty.setAdapter(adapterProperty);
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateEt.setText(sdf.format(myCalendar.getTime()));
    }



}
