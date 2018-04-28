package com.example.purva.propertymanagment.data.model;

import android.provider.BaseColumns;

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