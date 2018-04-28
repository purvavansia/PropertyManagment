package com.example.purva.propertymanagment.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
//
//import com.example.purva.propertymanagment.data.model.PropertyContract;
//import com.example.purva.propertymanagment.data.model.TenantContract;

public class DbOpenHelper extends SQLiteOpenHelper {
    public final class TenantContract {
        public TenantContract(){

        }
        public abstract class TenantEntry implements BaseColumns {
            public static final String TABLE_NAME ="tenant_table";
            public static final String ROW_ID = "row_id";
            public static final String PROPERTY_ID = "property_id";
            public static final String LANDLORD_ID = "landlord_id";
            public static final String NAME="tenant_name";
            public static final String EMAIL = "tenant_email";
            public static final String MOBILE = "tenant_mobile";
            public static final String ADDRESS ="property_address";
        }
    }

    public final class PropertyContract {
        public PropertyContract() {
        }
        public abstract class PropertyEntry implements BaseColumns {
            public static final String TABLE_NAME ="property_table";
            public static final String ROW_ID = "row_id";
            public static final String PROPERTY_ID = "property_id";
            public static final String COLUMN_LANDLORD_ID = "landlord_id";
            public static final String COLUMN_NAME_PROPERTY_STREET = "property_street";
            public static final String COLUMN_NAME_PROPERTY_CITY = "property_city";
            public static final String COLUMN_NAME_PROPERTY_STATE = "property_state";
            public static final String COLUMN_NAME_PROPERTY_COUNTRY = "property_country";
            public static final String COLUMN_NAME_PROPERTY_STATUS = "proerty_status";
            public static final String COLUMN_NAME_PROPERTY_PRICE = "property_price";
            public static final String COLUMN_NAME_PROPETY_MORTAGE="property_mortage";
        }
    }

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
                    TenantContract.TenantEntry.EMAIL + COMMA_SEP  + TenantContract.TenantEntry.PROPERTY_ID
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
