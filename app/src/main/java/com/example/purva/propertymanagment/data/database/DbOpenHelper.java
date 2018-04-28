package com.example.purva.propertymanagment.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.purva.propertymanagment.data.model.PropertyContract;
import com.example.purva.propertymanagment.data.model.TenantContract;
//
//import com.example.purva.propertymanagment.data.model.PropertyContract;
//import com.example.purva.propertymanagment.data.model.TenantContract;

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String database_name = "PropertyManagement.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_PROPERTY_ENTRIES =
            "CREATE TABLE " + PropertyContract.PropertyEntry.TABLE_NAME + " ("
                    + PropertyContract.PropertyEntry.PROPERTY_ID + " PRIMARY KEY,"
                    + PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STREET + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATUS + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE+ TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPETY_MORTAGE + TEXT_TYPE + " )";

    private static final String SQL_CREATE_TENANT_ENTRIES =
            "CREATE TABLE " + TenantContract.TenantEntry.TABLE_NAME + " ("
                    + TenantContract.TenantEntry.PROPERTY_ID + TEXT_TYPE + COMMA_SEP
                    + TenantContract.TenantEntry.LANDLORD_ID + TEXT_TYPE + COMMA_SEP
                    + TenantContract.TenantEntry.NAME + TEXT_TYPE + COMMA_SEP
                    + TenantContract.TenantEntry.EMAIL + TEXT_TYPE + COMMA_SEP
                    + TenantContract.TenantEntry.ADDRESS + TEXT_TYPE + COMMA_SEP
                    + TenantContract.TenantEntry.MOBILE + TEXT_TYPE  + COMMA_SEP
                    + "PRIMARY KEY " + "(" +
                    TenantContract.TenantEntry.EMAIL + COMMA_SEP  + TenantContract.TenantEntry.LANDLORD_ID
                    +"));";
    public DbOpenHelper(Context context) {
        super(context, database_name, null, 1);
        Log.d("DB", "Create data base");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROPERTY_ENTRIES);
        db.execSQL(SQL_CREATE_TENANT_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PropertyContract.PropertyEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TenantContract.TenantEntry.TABLE_NAME);
        onCreate(db);
    }
}
