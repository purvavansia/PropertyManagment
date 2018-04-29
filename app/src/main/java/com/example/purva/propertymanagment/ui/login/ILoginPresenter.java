package com.example.purva.propertymanagment.ui.login;

/**
 * Created by purva on 4/24/18.
 */

public interface ILoginPresenter {

    public void checkPermission();
    public void signUpClicked();
    public void callApiLogin(String email, String password);
}
