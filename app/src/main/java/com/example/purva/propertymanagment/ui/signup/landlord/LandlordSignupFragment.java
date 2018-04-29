package com.example.purva.propertymanagment.ui.signup.landlord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.ui.BaseFragment;
import com.example.purva.propertymanagment.ui.login.LoginActivity;
import com.example.purva.propertymanagment.ui.signup.Constants;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.subscriptions.CompositeSubscription;



public class LandlordSignupFragment extends BaseFragment {


    //TextInput Layout
    @BindView(R.id.landlord_email_text_input_layout)
    TextInputLayout text_input_email;
    @BindView(R.id.landlord_password_text_input_layout)
    TextInputLayout text_input_password;
    @BindView(R.id.landlord_confirmPassword_text_input_layout)
    TextInputLayout text_input_pwconfirm;

    //EditText
    @BindView(R.id.editTextLandlordEmail)
    EditText emailEt;
    @BindView(R.id.editTextLandlordPassword)
    EditText passwordEt;
    @BindView(R.id.editTextLandlordConfirmPassword)
    EditText pwConfirmEt;

    //Button
    @BindView(R.id.buttonLandlordSignUp)
    Button signupBtn;

    Unbinder unbinder;

    //subscription that represents a group of Subscriptions that are unsubscriped together
    private CompositeSubscription mCompositeSubscription;

    private Observable<CharSequence> mEmailObservable, mPasswordObservable, mPasswordConfirmObservable;

    public static LandlordSignupFragment INSTANT = null;

    public static LandlordSignupFragment getInstant() {
        if (INSTANT == null) {
            INSTANT = new LandlordSignupFragment();
        }
        return INSTANT;
        //return new LandlordSignupFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_landlord_signup;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCompositeSubscription = new CompositeSubscription();
        initEditTextObservable();
        initSubscriber();
    }

    /**
     * init edittext observable
     */
    private void initEditTextObservable() {
        //mEmailLandlordObservable = RxTextView.textChanges()
        mEmailObservable = RxTextView.textChanges(emailEt);
        mPasswordObservable = RxTextView.textChanges(passwordEt);
        mPasswordConfirmObservable = RxTextView.textChanges(pwConfirmEt);
    }

    private void initSubscriber() {
        Subscription mEmailSubscription = mEmailObservable.doOnNext(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                emailEditTextError(1);  //disable email
            }
        }).debounce(100, TimeUnit.MILLISECONDS).filter(new Func1<CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence) {
                return  !TextUtils.isEmpty(charSequence.toString());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CharSequence>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("mPwdConfirmSubscription", e.getMessage());
            }

            @Override
            public void onNext(CharSequence charSequence) {
                if (!isUserInputValid(charSequence.toString(), "", 1)) {
                    emailEditTextError(2);
                } else {
                    emailEditTextError(1);
                }
            }
        });
        mCompositeSubscription.add(mEmailSubscription);


        //============================================================
        //Password Validation
        Subscription mPasswordSubscription = mPasswordObservable.doOnNext(new Action1<CharSequence>() {

            @Override
            public void call(CharSequence charSequence) {
                passwordEditTextError(1);
            }
        }).debounce(500, TimeUnit.MILLISECONDS).filter(new Func1<CharSequence, Boolean>() {

            @Override
            public Boolean call(CharSequence charSequence) {
                return !TextUtils.isEmpty(charSequence);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CharSequence>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("mPasswordSubscription", e.getMessage());
            }

            @Override
            public void onNext(CharSequence charSequence) {
                if (!isUserInputValid(charSequence.toString(), "", 2)) {
                    passwordEditTextError(2);
                } else {
                    passwordEditTextError(1);
                }
            }
        });
        mCompositeSubscription.add(mPasswordSubscription);


        //================================================
        //Password Confirmation Subscription
        Subscription mPasswordConfirmSubscription = mPasswordConfirmObservable.doOnNext(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                passwordEditTextError(3);
            }
        }).debounce(500, TimeUnit.MILLISECONDS).filter(new Func1<CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence) {
                return !TextUtils.isEmpty(passwordEt.getText().toString()) && !TextUtils.isEmpty(charSequence.toString());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CharSequence>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CharSequence charSequence) {
                if (!isUserInputValid(charSequence.toString(), passwordEt.getText().toString(), 3)) {
                    passwordEditTextError(4);
                } else {
                    passwordEditTextError(3);
                }
            }
        });
        mCompositeSubscription.add(mPasswordConfirmSubscription);

        Subscription allSignUpFieldSubScription = Observable.combineLatest(mEmailObservable, mPasswordObservable, mPasswordConfirmObservable, new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence mEmail, CharSequence mPassword, CharSequence mPasswordConfirm) {

                return isUserInputValid(mEmail.toString(), "", 1) // Email
                        && isUserInputValid(mPassword.toString(), "", 2) // Password
                        && isUserInputValid(mPassword.toString(), mPasswordConfirm.toString(), 3) // Confirm Password
                        ;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("SignUpFieldSubScription", e.getMessage());
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    signUpButtonState(1);
                } else {
                    signUpButtonState(2);
                }
            }
        });
        mCompositeSubscription.add(allSignUpFieldSubScription);
    }

    /**
     * Enable and disable login button as per case
     *
     * @param whichCase : 1 -> enable , 2 ->  disable
     */
    private void signUpButtonState(int whichCase) {
        switch (whichCase) {
            case 1: // enable button
                signupBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary));
                signupBtn.setEnabled(true);
                signupBtn.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                break;
            case 2: // disable button
                signupBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_disable));
                signupBtn.setEnabled(false);
                signupBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.color_disable_text));
                break;
        }

    }


    /**
     * Enable and disable Email error as per case
     *
     * @param whichCase: 1 -> for hide , 2 -> for show
     */
    private void emailEditTextError(int whichCase) {
        switch (whichCase) {
            case 1: // for hide error
                if (text_input_email.getChildCount() == 2) {
                    text_input_email.getChildAt(1).setVisibility(View.GONE);
                }
                text_input_email.setError(null);
                break;
            case 2: // for show error
                if (text_input_email.getChildCount() == 2) {
                    text_input_email.getChildAt(1).setVisibility(View.VISIBLE);
                }
                text_input_email.setError(getString(R.string.str_enter_valid_email));
                break;
        }
    }

    /**
     * Enable and disable Email error as per case
     *
     * @param whichCase: 1 -> for hide , 2 -> for show
     */
    private void passwordEditTextError(int whichCase) {
        switch (whichCase) {
            case 1: // for hide error
                if (text_input_password.getChildCount() == 2) {
                    text_input_password.getChildAt(1).setVisibility(View.GONE);
                }
                text_input_password.setError(null);
                break;
            case 2: // for show error
                if (text_input_password.getChildCount() == 2) {
                    text_input_password.getChildAt(1).setVisibility(View.VISIBLE);
                }
                text_input_password.setError(getString(R.string.str_enter_password));
                break;
            case 3: // for hide error
                if (text_input_pwconfirm.getChildCount() == 2) {
                    text_input_pwconfirm.getChildAt(1).setVisibility(View.GONE);
                }
                text_input_pwconfirm.setError(null);
                break;
            case 4: // for show error
                if (text_input_pwconfirm.getChildCount() == 2) {
                    text_input_pwconfirm.getChildAt(1).setVisibility(View.VISIBLE);
                }
                text_input_pwconfirm.setError(getString(R.string.str_password_must_be_same));
                break;
        }
    }

    /**
     * Validate user details for email and password
     */

    private boolean isUserInputValid(String userInput, String userInputMatch, int whichCase) {
        switch (whichCase) {
            case 1: // check email input
                return !TextUtils.isEmpty(userInput) && Patterns.EMAIL_ADDRESS.matcher(userInput).matches();
            case 2: // check password input
                return userInput.length() >= 6; // password should be 6 or more than 6.
            case 3: // confirm password
                return TextUtils.equals(userInput, userInputMatch); // for both password same
            case 4: // Check Phone number
                return userInput.length() >= 10;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.buttonLandlordSignUp)
    public void onViewClicked() {

        String landlord_email = emailEt.getText().toString();
        String landlord_password = passwordEt.getText().toString();
        ApiServiceLandlordSignUp apiService = RetrofitInstanceLandlordSignUp.getRetrofitInstance().create(ApiServiceLandlordSignUp.class);

        Call<String> signUpCall =  apiService.getSignUpDetails(landlord_email,landlord_email,landlord_password, Constants.LANDLORD);
        signUpCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.i(Constants.TAG, ""+response.body());
                if(response.body().contains("successfully")){
                    Intent loginIntent = new Intent(getContext(), LoginActivity.class);
                    startActivity(loginIntent);
                }
                else{
                    Toast.makeText(getActivity(),""+response.body(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.i(Constants.TAG, ""+t);
            }
        });
    }
}

