package com.example.purva.propertymanagment.data.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.purva.propertymanagment.data.model.Property;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";
    private static final String DOUBLE_TYPE = "Double";
    private static final String PROPERTY_TABLE = "proerties.db";
    public final static String CREATE_PROPETY_TABLE="CREATE TABLE " + Property.TABLE_NAME  + "("
            + Property.COLUMN_PROPERTY_ID + " PRIMARY KEY" + COMMA_SEP
            + Property.COLUMN_LANDLORD_ID + TEXT_TYPE + COMMA_SEP
            + Property.COLUMN_COUNTRY + TEXT_TYPE + COMMA_SEP
            + Property.COLUMN_STATE + TEXT_TYPE + COMMA_SEP
            + Property.COLUMN_CITY + TEXT_TYPE + COMMA_SEP
            + Property.COLUMN_STREET + TEXT_TYPE + COMMA_SEP
            + Property.COLUMN_MORTGAGE + TEXT_TYPE + COMMA_SEP
            + Property.COLUMN_PRICE + TEXT_TYPE + COMMA_SEP
            + Property.COLUMN_STATUS + TEXT_TYPE + ")";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROPETY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PROPERTY_TABLE);
    }

//    ArrayList<Property> getAllProperties(){
//        String[] projection = {
//
//        }
//    }
}
