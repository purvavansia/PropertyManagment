package com.example.purva.propertymanagment.ui.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rx.Subscription;
import rx.functions.Action1;


import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.ui.PropertyConstant;
import com.malinkang.rxvalidator.RxValidator;
import com.malinkang.rxvalidator.ValidationFail;
import com.malinkang.rxvalidator.ValidationResult;
import com.malinkang.rxvalidator.annotations.MaxLength;
import com.malinkang.rxvalidator.annotations.MinLength;
import com.malinkang.rxvalidator.annotations.NotEmpty;
import com.malinkang.rxvalidator.annotations.RegExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by purva on 4/23/18.
 */

public class TenantSignupFragment extends Fragment implements View.OnClickListener {

    @NotEmpty(order = 1, message = PropertyConstant.EMAIL_CAN_NOT_BE_EMPTY)
    @RegExp(order = 2, message = PropertyConstant.EMAIL_PROMPT, regexp= PropertyConstant.EMAIL_LEGAL_PATTERN)
    EditText emailEt;
    TextInputLayout emailTextInputLayout;
    Map<EditText, TextInputLayout> inputLayoutMap = new HashMap<>();

    @NotEmpty(order=3, message =PropertyConstant.PASSWORD_CAN_NOT_BE_EMPTY)
    @MinLength(order = 4, length = 6, message=PropertyConstant.PASSWORD_LEN_MUST_BE_OVER_SIX)
    @MaxLength(order = 5, length = 12, message =PropertyConstant.PASSWORD_LEN_MUST_BE_NOT_OVER_TWELVE)
    EditText passEt;
    TextInputLayout passTextInputLayout;

    @NotEmpty(order=6, message =PropertyConstant.PASSWORD_CAN_NOT_BE_EMPTY)
    @MinLength(order = 7, length = 6, message=PropertyConstant.PASSWORD_LEN_MUST_BE_OVER_SIX)
    @MaxLength(order = 8, length = 12, message =PropertyConstant.PASSWORD_LEN_MUST_BE_NOT_OVER_TWELVE)
    EditText confirmEt;
    TextInputLayout confirmTextInputLayout;

    Button signupBtn;

    public TenantSignupFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tenant_signup, container, false);
        signupBtn = view.findViewById(R.id.buttonTenantSignUp);
        emailEt = view.findViewById(R.id.editTextTenantEmail);
        emailTextInputLayout = view.findViewById(R.id.tenant_email_text_input_layout);
        Log.d("CHECK", "Validation CHeck");
        passEt = view.findViewById(R.id.editTextTenantPassword);
        passTextInputLayout = view.findViewById(R.id.tenant_password_text_input_layout);


        confirmEt = view.findViewById(R.id.editTextTenantConfirmPassword);
        confirmTextInputLayout = view.findViewById(R.id.tenant_confirmPassword_text_input_layout);

        inputLayoutMap.put(emailEt, emailTextInputLayout);
        inputLayoutMap.put(passEt, passTextInputLayout);
        inputLayoutMap.put(confirmEt, confirmTextInputLayout);
        signupBtn.setOnClickListener(this);
        return view;
    }

    Subscription subscription;
    private boolean isValid;

    @Override
    public void onClick(View v)
    {
        Log.d("BTNCLICK", "Button clicked");
        if (subscription == null || subscription.isUnsubscribed())
        {
            subscription = RxValidator.validate(this).subscribe(new Action1<ValidationResult>() {
                @Override
                public void call(ValidationResult validationResult) {
                    isValid = validationResult.isValid;
                    for (EditText editText : inputLayoutMap.keySet()) {
                        TextInputLayout textInputLayout = inputLayoutMap.get(editText);
                        textInputLayout.setErrorEnabled(false);
                    }
                    if (!validationResult.isValid) {
                        ArrayList<ValidationFail> errors = validationResult.getFails();
                        for (ValidationFail fail : errors) {
                            TextInputLayout textInputLayout = inputLayoutMap.get(fail.getView());
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError(fail.getMessage());
                        }
                    }
                }
            });
        }
        if (isValid) {
            Log.d("Valid", "email and password are given");
            String password = passEt.getText().toString();
            String confimPw = confirmEt.getText().toString();
            if(password.equals(confimPw))
                Toast.makeText(getActivity(), PropertyConstant.SUCCESS_REGISTRATION, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), PropertyConstant.PASSWORD_MUST_BE_MATChED, Toast.LENGTH_SHORT).show();
        }

    }
}
