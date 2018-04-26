package com.example.purva.propertymanagment.ui.home;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.purva.propertymanagment.R;

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
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.setDrawHoleEnabled(false);

        ArrayList<PieEntry> yEntrys = new ArrayList<PieEntry>();

        for (int i = 0; i<yvalues.length; i++){
            yEntrys.add(new PieEntry(yvalues[i],i));

        }

        ArrayList<String> xEntrys = new ArrayList<String>();

        for (int i = 0; i<xvals.length; i++){
            xEntrys.add(xvals[i]);

        }
        PieDataSet dataSet = new PieDataSet(yEntrys, "Expenses");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData data = new PieData(dataSet);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setOnChartValueSelectedListener(MainActivity.this);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

        /*int pos1 = e.toString().indexOf("");
        String sales = e.toString().substring(pos1 + 7);
        for(int i = 0; i < yvalues.length; i++){
            if(yvalues[i] == Float.parseFloat(sales)){
                pos1 = i;
                break;
            }
        }
        String employee = xvals[pos1 + 1];
        Toast.makeText(MainActivity.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onNothingSelected() {

    }
}
