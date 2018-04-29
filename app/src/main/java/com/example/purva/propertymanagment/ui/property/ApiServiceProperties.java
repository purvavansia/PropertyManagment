package com.example.purva.propertymanagment.ui.property;

import com.example.purva.propertymanagment.data.model.Property;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by purva on 4/28/18.
 */

public interface ApiServiceProperties {

    @GET("property.php")
    Call<Property> getPropertyDetails(@Query("userid") String userid, @Query("usertype") String usertype);

    @GET("pro_mgt_add_pro.php")
    Call<String> addPropertyDetails(@Query(" address") String address, @Query("city") String city,
                                    @Query("state") String state, @Query("country") String country,
                                    @Query("pro_status") String pro_status, @Query("purchase_price") String purchase_price, @Query("mortage_info") String mortage_info,
                                    @Query("userid") String userid,
                                    @Query("usertype") String usertype);

}
