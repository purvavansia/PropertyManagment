package com.example.purva.propertymanagment.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.purva.propertymanagment.data.model.PropertyContract;

/**
 * Created by purva on 4/26/18.
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PropertyContract.PropertyEntry.TABLE_NAME + " (" +
                    PropertyContract.PropertyEntry.PROPERTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PropertyContract.PropertyEntry.COLUMN_NAME_LANDLORD_EMAIL + TEXT_TYPE + COMMA_SEP +
                    PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_Street + TEXT_TYPE + COMMA_SEP +
                    PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_CITY + TEXT_TYPE + COMMA_SEP +
                    PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_STATE + TEXT_TYPE + COMMA_SEP +
                    PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_COUNTRY + TEXT_TYPE + COMMA_SEP +
                    PropertyContract.PropertyEntry.COLUMN_NAME_PROPERTY_PRICE+ TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PropertyContract.PropertyEntry.TABLE_NAME;

    public DbOpenHelper(Context context) {
        super(context, "my_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
