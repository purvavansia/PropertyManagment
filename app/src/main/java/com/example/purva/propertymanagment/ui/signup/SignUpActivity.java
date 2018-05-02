package com.example.purva.propertymanagment.ui.signup;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.adapters.TabsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    ISignupPresenter iSignupPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        iSignupPresenter = new SignupPresenter(this);
        Log.d("SIGNUPACT", "Sign up Activity");
        tabLayout.addTab(tabLayout.newTab().setText("LandLord"));
        tabLayout.addTab(tabLayout.newTab().setText("Tenant"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                tabLayout.setScrollPosition(position,0,true);
                tabLayout.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        int count = tabLayout.getTabCount();
        TabsPagerAdapter pageAdapter = iSignupPresenter.getTabs(count);
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
