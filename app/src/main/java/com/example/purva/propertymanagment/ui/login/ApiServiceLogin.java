package com.example.purva.propertymanagment.ui.login;

import com.example.purva.propertymanagment.data.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by purva on 4/25/18.
 */

public interface ApiServiceLogin {

     /*http://rjtmobile.com/aamir/property-mgmt/pro_mgt_login.php?email=aa@aa.com&password=12345678*/

    @GET("pro_mgt_login.php")
    Call<User> getUserDetails(@Query("email") String email,@Query("password") String password);
}
