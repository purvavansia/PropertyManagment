package com.example.purva.propertymanagment.ui.login;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by purva on 4/24/18.
 */

public interface ILoginPresenter {

    public void checkPermission();
    public void signUpClicked();
    public void callApiLogin(String email, String password);
    public void getView(View v, MotionEvent event);

}
