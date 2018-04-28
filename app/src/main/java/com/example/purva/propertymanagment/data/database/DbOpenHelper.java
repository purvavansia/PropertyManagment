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

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String id = PropertyContract.PropertyEntry.PROPERTY_ID;
    private static final String landlordId = PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID;
    private static final String country = PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY;
    private static final String state = PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE;
    private static final String city = PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY;
    private static final String street = PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STREET;
    private static final String status = PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATUS;
    private static final String price = PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE;
    private static final String mortage = PropertyContract.PropertyEntry.COLUMN_NAME_PROPETY_MORTAGE;
    private static final String dbname = "properties.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PropertyContract.PropertyEntry.TABLE_NAME + " ("
                    + PropertyContract.PropertyEntry.PROPERTY_ID + TEXT_TYPE+ COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STREET + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATUS + TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE+ TEXT_TYPE + COMMA_SEP
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPETY_MORTAGE + TEXT_TYPE +COMMA_SEP+
                    "PRIMARY KEY"+ "("+
                    PropertyContract.PropertyEntry.PROPERTY_ID + COMMA_SEP +PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID+
                    " ));";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PropertyContract.PropertyEntry.TABLE_NAME;

    public DbOpenHelper(Context context) {
        super(context, dbname, null, 1);
        Log.d("DB", "Create data base");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PropertyContract.PropertyEntry.TABLE_NAME);
    }
}
