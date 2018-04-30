package com.example.purva.propertymanagment.ui.tenant;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceAddTenant {

    //http://rjtmobile.com/aamir/property-mgmt/
    //pro_mgt_add_tenants.php?name=aam&email=aah@aah.com&address=complte address&mobile=9876543210&propertyid=1&landlordid=3
  
    @GET("pro_mgt_add_tenants.php")
    Observable<String> addTenant(@Query("name") String name,
                                                       @Query("email") String email,
                                                       @Query("address") String address,
                                                       @Query("mobile") String mobile,
                                                       @Query("propertyid") String propertyid,
                                                       @Query("landlordid") String landlordid);

}
