package com.example.purva.propertymanagment.data.model;

import java.util.List;

public class Document {
    private List<DocumentBean> documentBeans;

    List<DocumentBean> getAllDocumentBeans() {
        return documentBeans;
    }

    public class DocumentBean {
        public DocumentBean(String propertyId, String landlordId, String documentPath, String documentType, String documentName, String docComment) {
            this.propertyId = propertyId;
            this.landlordId = landlordId;
            this.documentPath = documentPath;
            this.documentType = documentType;
            this.documentName = documentName;
            this.docComment = docComment;
        }
        private String propertyId;
        private String landlordId;
        private String documentPath;
        private String documentType;
        private String documentName;
        private String docComment;
        public DocumentBean(String propertyId, String landlordId, String documentPath, String documentType, String documentName) {
            this.propertyId = propertyId;
            this.landlordId = landlordId;
            this.documentPath = documentPath;
            this.documentType = documentType;
            this.documentName = documentName;
        }


        public DocumentBean(String propertyId, String landlordId, String documentPath) {
            this.propertyId = propertyId;
            this.landlordId = landlordId;
            this.documentPath = documentPath;
        }

        public String getDocumentName() {
            return documentName;
        }

        public void setDocumentName(String documentName) {
            this.documentName = documentName;
        }

        public String getDocumentPath() {
            return documentPath;
        }

        public void setDocumentPath(String documentPath) {
            this.documentPath = documentPath;
        }

        public String getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(String propertyId) {
            this.propertyId = propertyId;
        }

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }
    }


}
