package com.example.purva.propertymanagment.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.data.model.PropertyContract;

import java.util.ArrayList;
import java.util.List;

public class DbHelper implements  IDbHelper{

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
    public long insertRecord(String id, String lordId, String country, String state, String city, String street, String price, String mortage, String status) {
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

        Log.i("inseted",price+" "+mortage);

        long row_id = mSQLiteDatabase.insert(PropertyContract.PropertyEntry.TABLE_NAME, null, values);

        return row_id;
    }

    @Override
    public void deletePropertyById(String propertyId, String landlordId) {
        //SQLiteDatabase mSQLiteDatabase = this.getWritableDatabase();
        mSQLiteDatabase.delete(PropertyContract.PropertyEntry.TABLE_NAME, PropertyContract.PropertyEntry.PROPERTY_ID +
                " = ? and "+ PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID+ " = ?", new String[]{propertyId,landlordId});
        Log.i("deleterows",""+getPropertyCount());
    }

    @Override
    public boolean clearTable() {
        mSQLiteDatabase.execSQL("delete from " + PropertyContract.PropertyEntry.TABLE_NAME);
        return getPropertyCount()==0;
    }

    @Override
    public void closeDb() {
        mSQLiteDatabase.close();
    }
}