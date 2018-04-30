package com.example.purva.propertymanagment.data.model;

import android.provider.BaseColumns;

/**
 * Created by purva on 4/30/18.
 */

public class TransactionContract {
    public TransactionContract() {
    }
    public abstract class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME ="tbl_transaction";
        public static final String ROW_ID = "row_id";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String COLUMN_LANDLORD_ID = "landlord_id";
        public static final String COLUMN_NAME_TRANSACTION_DATE = "transaction_date";
        public static final String COLUMN_NAME_TRANSACTION_SUMMARY = "transaction_summary";
        public static final String COLUMN_NAME_TRANSACTION_DESCRIPTION = "transaction_description";
        public static final String COLUMN_NAME_TRANSACTION_PROPERTY_ID = "property_id";
        public static final String COLUMN_NAME_TRANSACTION_AMOUNT = "transaction_amount";
        public static final String COLUMN_NAME_TRANSACTION_TYPE = "transation_type";

    }

}
