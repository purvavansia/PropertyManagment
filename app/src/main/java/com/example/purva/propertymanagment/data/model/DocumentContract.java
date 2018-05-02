package com.example.purva.propertymanagment.data.model;

import android.provider.BaseColumns;

public final class DocumentContract {
    public DocumentContract(){

    }
    public abstract class DocumentEntry implements BaseColumns{
        public static final String TABLE_NAME ="table_document";
        public static final String DOCUMENT_ID="document_id";
        public static final String PROPERTY_ID = "property_id";
        public static final String LANDLORD_ID = "landlord_id";
        public static final String DOCUMENT_NAME="document_name";
        public static final String DOCUMENT_TYPE="document_type";
        public static final String DOCUMENT_COMMENT="document_comment";
        public static final String IMAGE_ID="image_id";

    }
}
