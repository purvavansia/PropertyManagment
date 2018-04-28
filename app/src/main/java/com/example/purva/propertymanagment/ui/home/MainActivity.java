package com.example.purva.propertymanagment.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.ui.signup.tenant.TenantSignupFragment;
import com.example.purva.propertymanagment.ui.tenant.TenantActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {


    private float[] yvalues = {50f, 25f, 25f};
    private String[] xvals = {"Mortgage Interest","Property Management","Other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("DBTEST", "TEST");
//        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
//        pieChart.setUsePercentValues(true);
//        pieChart.setRotationEnabled(true);
//        pieChart.setDrawHoleEnabled(false);
//
//        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();
//
//        for (int i = 0; i<yvalues.length; i++){
//            yEntrys.add(new PieEntry(yvalues[i],i));
//
//        }
//
//        ArrayList<String> xEntrys = new ArrayList<String>();
//
//        for (int i = 0; i<xvals.length; i++){
//            xEntrys.add(xvals[i]);
//
//        }
//        PieDataSet dataSet = new PieDataSet(yEntrys, "Expenses");
//        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//
//        Legend legend = pieChart.getLegend();
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
//
//        PieData data = new PieData(dataSet);
//        // In Percentage term
//        data.setValueFormatter(new PercentFormatter());
//        // Default value
//        //data.setValueFormatter(new DefaultValueFormatter(0));
//        pieChart.setData(data);
//        pieChart.getDescription().setEnabled(false);
//
//        data.setValueTextSize(13f);
//        data.setValueTextColor(Color.DKGRAY);
//        pieChart.setOnChartValueSelectedListener(MainActivity.this);
        IDbHelper iDbHelper = new DbHelper(MainActivity.this);
       // iDbHelper iDbHelper = new iDbHelper(MainActivity.this);
        iDbHelper.insertPropertyRecord("001", "0010", "USA","IL", "ST Charles",
                "3809 Illinois Ave", "4800$", "300$","Vacant");
        int rowNum = iDbHelper.getPropertyCount();
        Log.d("ROWS", rowNum + "");
        iDbHelper.insertPropertyRecord("004", "0010", "USA","IL", "ST Charles",
                "3809 Illinois Ave", "4800$", "300$","Vacant");
        rowNum = iDbHelper.getPropertyCount();
        Log.d("ROWS2",rowNum +  "");

        iDbHelper.clearPropertyTable();
        rowNum = iDbHelper.getPropertyCount();
        Log.d("EntryCounter", rowNum+"");

        iDbHelper.insertTenantRecord("0001", "p9090", "shabi apartment", "abc@gmail.com", "China", "7788414");
        iDbHelper.insertTenantRecord("0003", "p8989", "nimabi dingfeng cheng", "abcd@gmail.com", "China", "000032");
        int tenantCount = iDbHelper.getTenantCount();
        Log.d("TenantCount", tenantCount+"");
        iDbHelper.deleteTenantByLandlordId("abc@gmail.com", "0001");
        Log.d("Afterdeletion", iDbHelper.getTenantCount()+"");
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void onClickTenants(View view) {
        Log.d("TEnant", "clicked");
       Intent intent = new Intent(MainActivity.this, TenantActivity.class);
       startActivity(intent);

    }
}
