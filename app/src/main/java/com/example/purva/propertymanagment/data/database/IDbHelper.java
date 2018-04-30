package com.example.purva.propertymanagment.data.database;


import android.database.sqlite.SQLiteDatabase;
import com.example.purva.propertymanagment.data.model.Tenant;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.data.model.Transaction;
import com.example.purva.propertymanagment.data.model.TransactionContract;

import java.util.List;

public interface IDbHelper {
    List<Property.PropertyBean> getAllProperties();
    public int getPropertyCount();
    public int insertPropertyRecord(String id, String lordId, String country, String state, String city,
                             String street, String price, String mortage, String status);
    public void deletePropertyById(String propertyId, String landlordId);
    public boolean clearPropertyTable();
    public void closeDb();
    public SQLiteDatabase openDb();
    public boolean validateProperty(String propertyId);

    List<Tenant.TenantBean> getAllTenants();
    public int getTenantCount();
    public long insertTenantRecord(String propertyId, String landlordId, String name, String email, String mobile, String address);
    public int deleteTenantByLandlordId(String email, String landlordId);
    public boolean clearTenantTable();
    public boolean validateTenant(Tenant tenant);


    List<Transaction.TransactionBean> getAllTransaction();
    public int getTransactionCount();
    public int insertTransactionRecord(String LandlordId, String date, String summary, String description,
                                       String propertyId, String amount, String type);
    public void deleteTransactionById(String transactionId);
    public boolean clearTransactionTable();

}


