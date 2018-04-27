package com.example.purva.propertymanagment.ui.property;

import com.example.purva.propertymanagment.data.model.Property;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by purva on 4/26/18.
 */

public interface ApiServiceProperty {

    //http://rjtmobile.com/aamir/property-mgmt/property.php?userid=3&usertype=landlord
    //http://rjtmobile.com/aamir/property-mgmt/pro_mgt_add_pro.php? address=fla1234&city=Noida&state=UP&country=India&pro_status=tenants&purchase_price=12000&mortage_info=no&userid=3&usertype=landlord

    @GET("property.php")
    Call<Property> getPropertyDetails(@Query("userid") String userid, @Query("usertype") String usertype);

    @GET("pro_mgt_add_pro.php")
    Call<String> addPropertyDetails(@Query(" address") String address,@Query("city") String city,
                                    @Query("state") String state,@Query("country") String country,
                                    @Query("pro_status") String pro_status,@Query("purchase_price") String purchase_price,@Query("mortage_info") String mortage_info,
                                    @Query("userid") String userid,
                                    @Query("usertype") String usertype);


}
