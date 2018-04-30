package com.example.purva.propertymanagment.data.model;

import java.util.List;

/**
 * Created by purva on 4/30/18.
 */

public class Transaction {

    private List<TransactionBean> transaction;

    public List<TransactionBean> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionBean> Transaction) {
        this.transaction = transaction;
    }

    public static class TransactionBean{

        String transactionId, landlordId, date, summary, description, propertyId, amount, type;

        public TransactionBean(String transactionId, String landlordId, String date, String summary, String description, String propertyId, String amount, String type) {
            this.transactionId = transactionId;
            this.landlordId = landlordId;
            this.date = date;
            this.summary = summary;
            this.description = description;
            this.propertyId = propertyId;
            this.amount = amount;
            this.type = type;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getLandlordId() {
            return landlordId;
        }

        public void setLandlordId(String landlordId) {
            this.landlordId = landlordId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(String propertyId) {
            this.propertyId = propertyId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
