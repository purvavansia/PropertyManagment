package com.example.purva.propertymanagment.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.data.model.Tenant;

import java.util.ArrayList;
import java.util.List;

public class DbHelper implements  IDbHelper{

    DbOpenHelper mDbOpenHelper;
    SQLiteDatabase mSQLiteDatabase;
    Context mContext;

    public DbHelper(Context context){
        mDbOpenHelper = new DbOpenHelper(context);
        mSQLiteDatabase = mDbOpenHelper.getWritableDatabase();
    }
    @Override
    public List<Property.PropertyBean> getAllProperties() {
        List<Property.PropertyBean> properties = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DbOpenHelper.PropertyContract.PropertyEntry.TABLE_NAME;
      
        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                String properyId = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.PROPERTY_ID));
                String landlordId = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID));
                String propertyCountry = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY));
                String propertyState = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE));
                String propertyCity = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY));
                String propertyStreet = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STREET));
                String propertyPurchasePrice = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE));
                String propertymortage = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPETY_MORTAGE));
                String propertyStatus = cursor.getString(cursor.getColumnIndex(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATUS));
                Property.PropertyBean propertyBean = new Property.PropertyBean(properyId, landlordId, propertyCountry, propertyState, propertyCity,
                        propertyStreet, propertyStatus, propertyPurchasePrice, propertymortage);
                properties.add(propertyBean);
            }while(cursor.moveToNext());
        }
        return properties;
    }

    @Override
    public int getPropertyCount() {
        return (int) DatabaseUtils.queryNumEntries(mSQLiteDatabase, DbOpenHelper.PropertyContract.PropertyEntry.TABLE_NAME);
    }

    @Override
    public boolean validateProperty(String propertyId) {
        String[] projection = {
                DbOpenHelper.PropertyContract.PropertyEntry.PROPERTY_ID
        };
        String where_str = DbOpenHelper.PropertyContract.PropertyEntry.PROPERTY_ID + "= ?";
        String[] where_clause = {propertyId};
        Cursor c = openDb().query(
                DbOpenHelper.PropertyContract.PropertyEntry.TABLE_NAME,
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
        if(validateProperty(id)==true)
            return -1;
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.PROPERTY_ID, id);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID, lordId);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY, country);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE, state);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY, city);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STREET, street);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE,price);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE, mortage);
        values.put(DbOpenHelper.PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATUS, status);

        long row_id = mSQLiteDatabase.insert(DbOpenHelper.PropertyContract.PropertyEntry.TABLE_NAME, null, values);

        return (int)row_id;
    }

    @Override
    public void deletePropertyById(String propertyId) {
        mSQLiteDatabase.delete(DbOpenHelper.PropertyContract.PropertyEntry.TABLE_NAME, DbOpenHelper.PropertyContract.PropertyEntry.PROPERTY_ID + " = ?", new String[]{propertyId});
    }

    @Override
    public boolean clearPropertyTable() {
        mSQLiteDatabase.execSQL("delete from " + DbOpenHelper.PropertyContract.PropertyEntry.TABLE_NAME);
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
        String selectQuery = "SELECT * FROM " + DbOpenHelper.TenantContract.TenantEntry.TABLE_NAME;

        Cursor cursor = mSQLiteDatabase.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                String properyId = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TenantContract.TenantEntry.PROPERTY_ID));
                String landlordId = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TenantContract.TenantEntry.LANDLORD_ID));
                String name = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TenantContract.TenantEntry.NAME));
                String email = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TenantContract.TenantEntry.EMAIL));
                String address = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TenantContract.TenantEntry.ADDRESS));
                String mobile = cursor.getString(cursor.getColumnIndex(DbOpenHelper.TenantContract.TenantEntry.MOBILE));
                Tenant.TenantBean tenantBean = new Tenant.TenantBean(properyId, landlordId, name, email, address, mobile);
                tenantBeans.add(tenantBean);
            }while(cursor.moveToNext());
        }
        return tenantBeans;
    }

    @Override
    public int getTenantCount() {
        return (int) DatabaseUtils.queryNumEntries(mSQLiteDatabase, DbOpenHelper.TenantContract.TenantEntry.TABLE_NAME);
    }

    @Override
    public long insertTenantRecord(String propertyId, String landlordId, String name, String email, String address, String mobile) {
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.TenantContract.TenantEntry.PROPERTY_ID, propertyId);
        values.put(DbOpenHelper.TenantContract.TenantEntry.LANDLORD_ID, landlordId);
        values.put(DbOpenHelper.TenantContract.TenantEntry.NAME, name);
        values.put(DbOpenHelper.TenantContract.TenantEntry.EMAIL, email);
        values.put(DbOpenHelper.TenantContract.TenantEntry.ADDRESS, address);
        values.put(DbOpenHelper.TenantContract.TenantEntry.MOBILE, mobile);
        long row_id = mSQLiteDatabase.insert(DbOpenHelper.TenantContract.TenantEntry.TABLE_NAME, null, values);
        return row_id;
    }

    @Override
    public int deleteTenantByPropertyId(String email, String propertyId) {
        String whereClause = DbOpenHelper.TenantContract.TenantEntry.PROPERTY_ID + " =? and " + DbOpenHelper.TenantContract.TenantEntry.EMAIL + " =?";
        int res = mSQLiteDatabase.delete(DbOpenHelper.TenantContract.TenantEntry.TABLE_NAME, "property_id=? and tenant_email=?", new String[]{propertyId, email});
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

}
