package com.example.purva.propertymanagment.ui.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

/**
 * Created by purva on 4/24/18.
 */

public class LoginPresenter implements ILoginPresenter {

    Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void checkPermission() {
        /*private void checkPermission() {*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!(ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);

            }
        }
    }
}
