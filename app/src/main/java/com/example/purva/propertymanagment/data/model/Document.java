package com.example.purva.propertymanagment.data.model;

import java.util.List;

public class Document {
    private List<DocumentBean> documentBeans;

    List<DocumentBean> getAllDocumentBeans() {
        return documentBeans;
    }

    public static class DocumentBean {
        public int getDocumentId() {
            return documentId;
        }

        public void setDocumentId(int documentId) {
            this.documentId = documentId;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getDocComment() {
            return docComment;
        }

        public void setDocComment(String docComment) {
            this.docComment = docComment;
        }

        public byte[] getImage() {
            return image;
        }

        public void setImage(byte[] image) {
            this.image = image;
        }

        private int documentId;
        private String propertyId;
        private String landlordId;
        private String documentType;
        private String documentName;
        private String docComment;
        private byte[] image;

        public DocumentBean(int documentId, String propertyId, String landlordId, String documentType, String documentName, String docComment, byte[] image) {
            this.documentId = documentId;
            this.propertyId = propertyId;
            this.landlordId = landlordId;
            this.documentType = documentType;
            this.documentName = documentName;
            this.docComment = docComment;
            this.image = image;
        }

        public DocumentBean(String propertyId, String landlordId, String documentType, String documentName, String docComment, byte[] image) {
            this.propertyId = propertyId;
            this.landlordId = landlordId;
            this.documentType = documentType;
            this.documentName = documentName;
            this.docComment = docComment;
            this.image = image;
        }

        public String getDocumentName() {
            return documentName;
        }

        public void setDocumentName(String documentName) {
            this.documentName = documentName;
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
