package com.example.purva.propertymanagment.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.data.model.PropertyContract;
import com.example.purva.propertymanagment.data.model.Tenant;
import com.example.purva.propertymanagment.data.model.TenantContract;
import com.example.purva.propertymanagment.data.model.Transaction;
import com.example.purva.propertymanagment.data.model.TransactionContract;

import java.util.ArrayList;
import java.util.List;

public class DbHelper implements IDbHelper{

    DbOpenHelper mDbOpenHelper;
    SQLiteDatabase mSQLiteDatabase;
    public DbHelper(Context context){
        mDbOpenHelper = new DbOpenHelper(context);
        mSQLiteDatabase = mDbOpenHelper.getWritableDatabase();
    }
    @Override
    public List<Property.PropertyBean> getAllProperties() {
        List<Property.PropertyBean> properties = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + PropertyContract.PropertyEntry.TABLE_NAME;
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                String properyId = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.PROPERTY_ID));
                String landlordId = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID));
                String propertyCountry = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY));
                String propertyState = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE));
                String propertyCity = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY));
                String propertyStreet = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STREET));
                String propertyPurchasePrice = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE));
                String propertymortage = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_NAME_PROPETY_MORTAGE));
                String propertyStatus = cursor.getString(cursor.getColumnIndex(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATUS));
                Property.PropertyBean propertyBean = new Property.PropertyBean(properyId, landlordId, propertyCountry, propertyState, propertyCity,
                        propertyStreet, propertyStatus, propertyPurchasePrice, propertymortage);
                properties.add(propertyBean);
            }while(cursor.moveToNext());
        }
        return properties;
    }

    @Override
    public int getPropertyCount() {
        return (int) DatabaseUtils.queryNumEntries(mSQLiteDatabase, PropertyContract.PropertyEntry.TABLE_NAME);
    }

    @Override
    public boolean validateProperty(String propertyId) {
        String[] projection = {
                PropertyContract.PropertyEntry.PROPERTY_ID
        };
        String where_str = PropertyContract.PropertyEntry.PROPERTY_ID + "= ?";
        String[] where_clause = {propertyId};
        Cursor c = openDb().query(
                PropertyContract.PropertyEntry.TABLE_NAME,
                projection,
                where_str,
                where_clause,
                null,
                null,
                null
        );
        return c.moveToFirst();
    }


    @Override
    public int insertPropertyRecord(String id, String lordId, String country, String state, String city, String street, String price, String mortage, String status) {
        ContentValues values = new ContentValues();
        values.put(PropertyContract.PropertyEntry.PROPERTY_ID, id);
        values.put(PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID, lordId);
        values.put(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY, country);
        values.put(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE, state);
        values.put(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY, city);
        values.put(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STREET, street);
        values.put(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATUS, status);
        values.put(PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE,price);
        values.put(PropertyContract.PropertyEntry.COLUMN_NAME_PROPETY_MORTAGE, mortage);
        Log.d("PROPERTY_ID",id);
        long row_id = mSQLiteDatabase.insert(PropertyContract.PropertyEntry.TABLE_NAME, null, values);
        return (int)row_id;
    }

    @Override
    public void deletePropertyById(String propertyId, String landlordId) {
        //SQLiteDatabase mSQLiteDatabase = this.getWritableDatabase();
        mSQLiteDatabase.delete(PropertyContract.PropertyEntry.TABLE_NAME, PropertyContract.PropertyEntry.PROPERTY_ID +
                " = ? and "+ PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID+ " = ?", new String[]{propertyId,landlordId});
        Log.i("deleterows",""+getPropertyCount());
    }
    @Override
    public boolean clearPropertyTable() {
        mSQLiteDatabase.execSQL("delete from " + PropertyContract.PropertyEntry.TABLE_NAME);
        return getPropertyCount()==0;

    }

    @Override
    public void closeDb() {
        mSQLiteDatabase.close();
    }

    @Override
    public SQLiteDatabase openDb() {
        return mDbOpenHelper.getWritableDatabase();
    }

    @Override
    public List<Tenant.TenantBean> getAllTenants() {
        List<Tenant.TenantBean> tenantBeans = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TenantContract.TenantEntry.TABLE_NAME;

        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                String properyId = cursor.getString(cursor.getColumnIndex(TenantContract.TenantEntry.PROPERTY_ID));
                String landlordId = cursor.getString(cursor.getColumnIndex(TenantContract.TenantEntry.LANDLORD_ID));
                String name = cursor.getString(cursor.getColumnIndex(TenantContract.TenantEntry.NAME));
                String email = cursor.getString(cursor.getColumnIndex(TenantContract.TenantEntry.EMAIL));
                String address = cursor.getString(cursor.getColumnIndex(TenantContract.TenantEntry.ADDRESS));
                String mobile = cursor.getString(cursor.getColumnIndex(TenantContract.TenantEntry.MOBILE));
                Tenant.TenantBean tenantBean = new Tenant.TenantBean(properyId, landlordId, name, email, address, mobile);
                tenantBeans.add(tenantBean);
            }while(cursor.moveToNext());
        }
        return tenantBeans;
    }

    @Override
    public int getTenantCount() {
        return (int) DatabaseUtils.queryNumEntries(mSQLiteDatabase, TenantContract.TenantEntry.TABLE_NAME);
    }

    @Override
    public long insertTenantRecord(String propertyId, String landlordId, String name, String email, String address, String mobile) {
        ContentValues values = new ContentValues();
        values.put(TenantContract.TenantEntry.PROPERTY_ID, propertyId);
        values.put(TenantContract.TenantEntry.LANDLORD_ID, landlordId);
        values.put(TenantContract.TenantEntry.NAME, name);
        values.put(TenantContract.TenantEntry.EMAIL, email);
        values.put(TenantContract.TenantEntry.ADDRESS, address);
        values.put(TenantContract.TenantEntry.MOBILE, mobile);
        long row_id = mSQLiteDatabase.insert(TenantContract.TenantEntry.TABLE_NAME, null, values);
        return row_id;
    }

    @Override
    public int deleteTenantByLandlordId(String email, String landlordId) {
        String whereClause = TenantContract.TenantEntry.LANDLORD_ID + " =? and " + TenantContract.TenantEntry.EMAIL + " =?";
        int res = mSQLiteDatabase.delete(TenantContract.TenantEntry.TABLE_NAME, "landlord_id=? and tenant_email=?", new String[]{landlordId, email});
        Log.d("DeleteRes", res+"");
        return res;
    }

    @Override
    public boolean clearTenantTable() {
        return false;
    }

    @Override
    public boolean validateTenant(Tenant tenant) {
        return false;
    }



    //Transaction db methods
    @Override
    public List<Transaction.TransactionBean> getAllTransaction() {

        List<Transaction.TransactionBean> transactions = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TransactionContract.TransactionEntry.TABLE_NAME;
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                String transactionId = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.TRANSACTION_ID));
                String landlordId = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_LANDLORD_ID));
                String transactionDate = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_DATE));
                String transactionSummary = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_SUMMARY));
                String transactionDescription = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_DESCRIPTION));
                String propertyId = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_PROPERTY_ID));
                String transactionAmount = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_AMOUNT));
                String transactionType = cursor.getString(cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_TYPE));
                Transaction.TransactionBean transactionBean = new Transaction.TransactionBean(transactionId,landlordId,transactionDate,
                        transactionSummary,transactionDescription,propertyId,transactionAmount,transactionType);
                transactions.add(transactionBean);

            }while (cursor.moveToNext());
        }
        return transactions;
    }

    @Override
    public int getTransactionCount() {
        return (int) DatabaseUtils.queryNumEntries(mSQLiteDatabase, TransactionContract.TransactionEntry.TABLE_NAME);
    }

    @Override
    public int insertTransactionRecord( String LandlordId, String date, String summary, String description, String propertyId, String amount, String type) {

        ContentValues values = new ContentValues();
        values.put(TransactionContract.TransactionEntry.COLUMN_LANDLORD_ID,LandlordId);
        values.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_DATE,date);
        values.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_SUMMARY,summary);
        values.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_DESCRIPTION,description);
        values.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_PROPERTY_ID,propertyId);
        values.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_AMOUNT,amount);
        values.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_TYPE,type);
        long row_id = mSQLiteDatabase.insert(TransactionContract.TransactionEntry.TABLE_NAME,null,values);

        return (int)row_id;
    }

    @Override
    public void deleteTransactionById(String transactionId) {
        mSQLiteDatabase.delete(TransactionContract.TransactionEntry.TABLE_NAME, TransactionContract.TransactionEntry.TRANSACTION_ID,
                new String[]{transactionId});
        Log.i("deleterows",""+getTransactionCount());
    }

    @Override
    public boolean clearTransactionTable() {
        mSQLiteDatabase.execSQL("delete from " + TransactionContract.TransactionEntry.TABLE_NAME);
        return getTransactionCount()==0;
    }

}