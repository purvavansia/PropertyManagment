package com.example.purva.propertymanagment.data.database;

import com.example.purva.propertymanagment.data.model.Property;

import java.util.List;

public interface IDbHelper {
    List<Property.PropertyBean> getAllProperties();
    public int getPropertyCount();
    public long insertRecord(String id, String lordId, String country, String state, String city,
                             String street, String price, String mortage, String status);
    public void deletePropertyById(String propertyId, String landloardId);
    public boolean clearTable();
    public void closeDb();
}