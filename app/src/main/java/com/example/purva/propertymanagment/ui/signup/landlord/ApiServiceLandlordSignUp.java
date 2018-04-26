package com.example.purva.propertymanagment.ui.signup.landlord;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by purva on 4/25/18.
 */

public interface ApiServiceLandlordSignUp {
    /*http://rjtmobile.com/aamir/property-mgmt/pro_mgt_reg.php?&email=aa@aa.com&landlord_email=aah@aah.
    com&password=12345678&account_for=tenant*/

    @GET("pro_mgt_reg.php")
    Call<String> getSignUpDetails(@Query("email") String email, @Query("landlord_email") String landlordEmail,
                                          @Query("password") String password, @Query("account_for") String accountType );
}
