package com.example.purva.propertymanagment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.model.Document;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.data.model.Tenant;
import com.example.purva.propertymanagment.data.model.Transaction;

import java.util.List;

/**
 * Created by purva on 5/1/18.
 */

public class DataManager implements IDataManager {

    Context context;
    DbHelper dbHelper;

    public DataManager(Context context, DbHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public List<Property.PropertyBean> getAllProperties() {
        return dbHelper.getAllProperties();
    }

    @Override
    public int getPropertyCount() {
        return dbHelper.getPropertyCount();
    }

    @Override
    public int insertPropertyRecord(String id, String lordId, String country, String state, String city, String street, String price, String mortage, String status) {
        return dbHelper.insertPropertyRecord(id, lordId, country,state,city,street,price,mortage,status);
    }

    @Override
    public void deletePropertyById(String propertyId, String landlordId) {
            dbHelper.deletePropertyById(propertyId,landlordId);
    }

    @Override
    public boolean clearPropertyTable() {
        return dbHelper.clearPropertyTable();
    }

    @Override
    public void closeDb() {
        dbHelper.closeDb();
    }

    @Override
    public SQLiteDatabase openDb() {
        return dbHelper.openDb();
    }

    @Override
    public boolean validateProperty(String propertyId) {
        return dbHelper.validateProperty(propertyId);
    }

    @Override
    public Property.PropertyBean getPropertyBeanByKeys(String proertyId, String landlordId) {
        return dbHelper.getPropertyBeanByKeys(proertyId, landlordId);
    }

    @Override
    public List<Tenant.TenantBean> getAllTenants() {
        return dbHelper.getAllTenants();
    }

    @Override
    public int getTenantCount() {
        return dbHelper.getTenantCount();
    }

    @Override
    public long insertTenantRecord(String propertyId, String landlordId, String name, String email, String mobile, String address) {
        return dbHelper.insertTenantRecord(propertyId,landlordId,name,email,mobile,address);
    }

    @Override
    public int deleteTenantByLandlordId(String email, String landlordId) {
        return dbHelper.deleteTenantByLandlordId(email,landlordId);
    }

    @Override
    public boolean clearTenantTable() {
        return dbHelper.clearTenantTable();
    }

    @Override
    public boolean validateTenant(Tenant tenant) {
        return dbHelper.validateTenant(tenant);
    }

    @Override
    public List<Transaction.TransactionBean> getAllTransaction() {
        return dbHelper.getAllTransaction();
    }

    @Override
    public int getTransactionCount() {
        return dbHelper.getTransactionCount();
    }

    @Override
    public int insertTransactionRecord(String LandlordId, String date, String summary, String description, String propertyId, String amount, String type) {
        return dbHelper.insertTransactionRecord(LandlordId,date,summary,description,propertyId,amount,type);
    }

    @Override
    public void deleteTransactionById(Integer transactionId) {
        dbHelper.deleteTransactionById(transactionId);
    }

    @Override
    public boolean clearTransactionTable() {
        return dbHelper.clearTransactionTable();
    }

    @Override
    public List<Document.DocumentBean> getAllDocuments() {
        return null;
    }

    @Override
    public int getDocumentCount() {
        return 0;
    }

    @Override
    public int insertDocument(String propertyId, String landlordId, String documentType, String documentName, String docComent, byte[] img) {
        return 0;
    }

    @Override
    public void deleteDocument(String propertyId, String landlordId, String documentId) {

    }
}
