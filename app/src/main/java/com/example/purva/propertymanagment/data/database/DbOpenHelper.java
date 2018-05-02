package com.example.purva.propertymanagment.data.database;
//

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import com.example.purva.propertymanagment.data.model.DocumentContract;
import com.example.purva.propertymanagment.data.model.PropertyContract;
import com.example.purva.propertymanagment.data.model.TenantContract;
import com.example.purva.propertymanagment.data.model.TransactionContract;

import java.sql.Blob;

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String dbname = "properties.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " integer";
    private static final String COMMA_SEP = ",";
    private static final String BLOB_TYPE = " BLOB";
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
                    + PropertyContract.PropertyEntry.COLUMN_NAME_PROPETY_MORTAGE + TEXT_TYPE +COMMA_SEP
                    + "PRIMARY KEY"+ "("+
                    PropertyContract.PropertyEntry.PROPERTY_ID + COMMA_SEP +PropertyContract.PropertyEntry.COLUMN_LANDLORD_ID+
                    " ));";

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


    private static final String SQL_CREATE_TRANSACTION_ENTRIES =
            "CREATE TABLE " + TransactionContract.TransactionEntry.TABLE_NAME + " ("
                    + TransactionContract.TransactionEntry.TRANSACTION_ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP
                    + TransactionContract.TransactionEntry.COLUMN_LANDLORD_ID + TEXT_TYPE + COMMA_SEP
                    + TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_DATE + TEXT_TYPE + COMMA_SEP
                    + TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_SUMMARY + TEXT_TYPE + COMMA_SEP
                    + TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_DESCRIPTION + TEXT_TYPE + COMMA_SEP
                    + TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_PROPERTY_ID + TEXT_TYPE + COMMA_SEP
                    + TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_AMOUNT + TEXT_TYPE + COMMA_SEP
                    + TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION_TYPE + TEXT_TYPE +
                    ");";
    private static final String SQL_CREATE_DOCUMENT_ENTRIES=
            "CREATE TABLE " + DocumentContract.DocumentEntry.TABLE_NAME + " ("
                    + DocumentContract.DocumentEntry.DOCUMENT_ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP
                    + DocumentContract.DocumentEntry.PROPERTY_ID + TEXT_TYPE + COMMA_SEP
                    + DocumentContract.DocumentEntry.LANDLORD_ID + TEXT_TYPE + COMMA_SEP
                    + DocumentContract.DocumentEntry.DOCUMENT_NAME + TEXT_TYPE + COMMA_SEP
                    + DocumentContract.DocumentEntry.DOCUMENT_TYPE + TEXT_TYPE + COMMA_SEP
                    + DocumentContract.DocumentEntry.DOCUMENT_COMMENT + TEXT_TYPE + COMMA_SEP
                    + DocumentContract.DocumentEntry.IMAGE_ID + BLOB_TYPE + ");";


    public DbOpenHelper(Context context) {
        super(context, dbname, null, 1);
        Log.d("DB", "Create data base");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_TENANT_ENTRIES);
        db.execSQL(SQL_CREATE_TRANSACTION_ENTRIES);
        db.execSQL(SQL_CREATE_DOCUMENT_ENTRIES);
    }
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PropertyContract.PropertyEntry.TABLE_NAME;


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PropertyContract.PropertyEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TenantContract.TenantEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TransactionContract.TransactionEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DocumentContract.DocumentEntry.TABLE_NAME);
        onCreate(db);
    }
}
