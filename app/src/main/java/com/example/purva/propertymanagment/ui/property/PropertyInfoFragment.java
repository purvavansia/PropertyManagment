package com.example.purva.propertymanagment.ui.property;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.data.model.Property;
import com.example.purva.propertymanagment.network.ApiServiceProperty;
import com.example.purva.propertymanagment.ui.signup.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.ListIterator;


/**
 * Created by purva on 4/27/18.
 */

public class PropertyInfoFragment extends Fragment {

    final static String BASE_URL = "http://rjtmobile.com/aamir/property-mgmt/";
    EditText street, city, state, country, price, propertFor, mortgageInfo;
    Button addProperty;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_property_info, container, false);
        sharedPreferences = getActivity().getSharedPreferences("mydata", Context.MODE_PRIVATE);

        String userid = sharedPreferences.getString("userid", "");
        street = view.findViewById(R.id.propertyAddStreet);
        city = view.findViewById(R.id.propertyAddCity);
        state = view.findViewById(R.id.propertyAddState);
        country = view.findViewById(R.id.propertyAddCountry);
        price = view.findViewById(R.id.propertyAddPrice);
        propertFor = view.findViewById(R.id.propertyAddStatus);
        mortgageInfo = view.findViewById(R.id.propertyAddMortgage);
        addProperty = view.findViewById(R.id.buttonAddNewProperty);

        addProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();

                String pro_street = street.getText().toString();
                String pro_city = city.getText().toString();
                String pro_state = state.getText().toString();
                String pro_country = country.getText().toString();
                String pro_price = price.getText().toString();
                String pro_for = propertFor.getText().toString();
                String pro_mortgage = mortgageInfo.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();

                Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).build();
                final Observable<Property> propertyObservable = retrofit.create(ApiServiceProperty.class).getPropertyDetails(userid, Constants.LANDLORD);


                retrofit.create(ApiServiceProperty.class)
                        .addPropertyDetails(pro_street, pro_city, pro_state, pro_country, pro_for, pro_price, pro_mortgage, userid, Constants.LANDLORD)

                        .flatMap(new Function<String, ObservableSource<Property>>() {
                            @Override
                            public ObservableSource<Property> apply(String reponse) throws Exception {

                                Log.i("response", "" + reponse);
                                if (reponse.contains("successfully")) {

                                    Log.i("response", "" + reponse);
                                    return propertyObservable;
                                }
                                return null;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Property>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.i("response", "subscribed");

                            }

                            @Override
                            public void onNext(Property property) {
                                Log.i("response", property.getProperty().get(0).getId());
                                List<Property.PropertyBean> propertyList = property.getProperty();
                                ListIterator<Property.PropertyBean> listIterator = propertyList.listIterator();
                                String propertyId = null;
                                while (listIterator.hasNext()) {
                                    Property.PropertyBean propertyBean = listIterator.next();
                                    String country = propertyBean.getPropertycountry();
                                    String state = propertyBean.getPropertystate();
                                    String city = propertyBean.getPropertycity();
                                    String street = propertyBean.getPropertyaddress();
                                    String price = propertyBean.getPropertypurchaseprice();
                                    String mortgage = propertyBean.getPropertymortageinfo();
                                    String status = propertyBean.getPropertystatus();
                                    if (country.equals(pro_country) && state.equals(pro_state) && city.equals(pro_city) && street.equals(pro_street)
                                            && price.equals(pro_price) && mortgage.equals(pro_mortgage) && status.equals(pro_for)) {
                                        propertyId = propertyBean.getId();
                                        Log.d("FoundID", "PropertyID: " + propertyId);
                                        break;
                                    }
                                }
                                IDbHelper iDbHelper = new DbHelper(getActivity());
                                int row_id = iDbHelper.insertPropertyRecord(propertyId, userid, pro_country, pro_state, pro_city, pro_street, pro_price, pro_mortgage, pro_for);
                                if (row_id == -1) {
                                    Log.d("FAILURE_INSERTED", "Failed inserted");
                                }
                                PropertyListFragment propertyListFragment = new PropertyListFragment();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutProperty, propertyListFragment, "back_to_main").commit();
                            }

                            @Override
                            public void onError(Throwable e) {

                                Log.i("response", "error" + e);
                            }

                            @Override
                            public void onComplete() {
                                Log.i("response", "completed");
                            }
                        });

            }
        });

        return view;
    }
}
