package com.example.purva.propertymanagment.network;

import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.data.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by purva on 4/25/18.
 */

public interface ApiService {

     /*http://rjtmobile.com/aamir/property-mgmt/pro_mgt_login.php?email=aa@aa.com&password=12345678*/

     //http://rjtmobile.com/aamir/property-mgmt/remove-property.php?propertyid=3


    @GET("pro_mgt_login.php")
    Call<User> getUserDetails(@Query("email") String email,@Query("password") String password);

    @GET("property.php")
    Call<Property> getPropertyDetails(@Query("userid") String userid, @Query("usertype") String usertype);

    @GET("pro_mgt_add_pro.php")
    Call<String> addPropertyDetails(@Query(" address") String address, @Query("city") String city,
                                    @Query("state") String state, @Query("country") String country,
                                    @Query("pro_status") String pro_status, @Query("purchase_price") String purchase_price, @Query("mortage_info") String mortage_info,
                                    @Query("userid") String userid,
                                    @Query("usertype") String usertype);

    @GET("pro_mgt_reg.php")
    Call<String> getSignUpDetails(@Query("email") String email, @Query("landlord_email") String landlordEmail,
                                  @Query("password") String password, @Query("account_for") String accountType );

    @GET("remove-property.php")
    Call<Property> removeProperty(@Query("propertyid") String property_id);

}
