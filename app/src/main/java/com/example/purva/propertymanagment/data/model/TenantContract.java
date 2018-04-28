package com.example.purva.propertymanagment.data.model;

import android.provider.BaseColumns;

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