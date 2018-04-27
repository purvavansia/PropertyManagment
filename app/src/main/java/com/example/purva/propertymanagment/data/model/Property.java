package com.example.purva.propertymanagment.data.model;

public class Property {
    public static final String TABLE_NAME="properties.db";
    public static final String COLUMN_PROPERTY_ID = "Id";
    public static final String COLUMN_LANDLORD_ID="LandlordId";
    public static final String COLUMN_COUNTRY="Country";
    public static final String COLUMN_STATE="State";
    public static final String COLUMN_CITY="City";
    public static final String COLUMN_STREET = "Street";
    public static final String COLUMN_MORTGAGE="Mortgage";
    public static final String COLUMN_PRICE="Price";
    public static final String COLUMN_STATUS="Status";

    private String propertyId;
    private String landlordId;
    private String country;
    private String state;
    private String city;
    private String street;
    private String mortgage;
    private double price;
    private int status;

    public Property(String propertyId, String landlordId, String country, String state, String city, String street, String mortgage, double price, int status) {
        this.propertyId = propertyId;
        this.landlordId = landlordId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.mortgage = mortgage;
        this.price = price;
        this.status = status;
    }
}
