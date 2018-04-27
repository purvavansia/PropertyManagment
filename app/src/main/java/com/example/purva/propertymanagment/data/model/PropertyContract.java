package com.example.purva.propertymanagment.data.model;

import android.provider.BaseColumns;

/**
 * Created by purva on 4/26/18.
 */

public final class PropertyContract {
    public PropertyContract() {
    }
    public abstract class PropertyEntry implements BaseColumns{
        public static final String TABLE_NAME ="tbl_property";
        public static final String PROPERTY_ID = "property_id";
        public static final String COLUMN_NAME_LANDLORD_EMAIL = "landlord_email";
        public static final String COLUMN_NAME_PROPERTY_Street = "property_street";
        public static final String COLUMN_NAME_PROPERTY_CITY = "property_city";
        public static final String COLUMN_NAME_PROPERTY_STATE = "property_state";
        public static final String COLUMN_NAME_PROPERTY_COUNTRY = "property_country";
        public static final String COLUMN_NAME_PROPERTY_PRICE = "property_price";
    }
}
