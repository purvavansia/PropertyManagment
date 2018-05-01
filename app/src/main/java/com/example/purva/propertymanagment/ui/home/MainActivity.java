package com.example.purva.propertymanagment.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.ui.document.DocumentActivity;
import com.example.purva.propertymanagment.ui.property.PropertyActivity;
import com.example.purva.propertymanagment.ui.tenant.TenantActivity;
import com.example.purva.propertymanagment.ui.todo.ToDoActivity;
import com.example.purva.propertymanagment.ui.transaction.TransactionActivity;
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


public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener, IHomeView {



    ImageView property, todo, transaction, document;
    IHomePresenter iHomePresenter;
    private float[] yvalues = {50f, 25f, 25f};
    private String[] xvals = {"Mortgage Interest","Property Management","Other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        property = findViewById(R.id.imageProperty);
        todo = findViewById(R.id.imageToDo);
        transaction = findViewById(R.id.imageTransaction);
        document = findViewById(R.id.imageDocuments);
        iHomePresenter = new HomePresenter(this, (IHomeView) this);

        property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               iHomePresenter.onClickProperty();
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iHomePresenter.onClickTodo();
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transactionIntent = new Intent(MainActivity.this, TransactionActivity.class);
                startActivity(transactionIntent);
            }
        });

        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DocumentActivity.class));
            }
        });

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

    @Override
    public void displayPieChart() {
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
        //legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setOnChartValueSelectedListener(MainActivity.this);
    }
}
