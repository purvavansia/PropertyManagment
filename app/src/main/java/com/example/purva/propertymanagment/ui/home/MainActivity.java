package com.example.purva.propertymanagment.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.purva.propertymanagment.R;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.ui.Constants;
import com.example.purva.propertymanagment.ui.document.DocumentActivity;
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

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener, IHomeView {


    ImageView property, todo, transaction, viewTransaction, document;
    IHomePresenter iHomePresenter;
    private float[] yvalues = {50f, 25f, 25f};
    private String[] xvals = {Constants.MORTGAGE_INTEREST, Constants.PROPERTY_MANAGEMENT, Constants.OTHER};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        property = findViewById(R.id.imageProperty);
        todo = findViewById(R.id.imageToDo);
        transaction = findViewById(R.id.imageTransaction);

        document = findViewById(R.id.imageDocuments);
        viewTransaction = findViewById(R.id.imageViewTransaction);

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
                iHomePresenter.onClickAddTransaction();
            }
        });

        viewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               iHomePresenter.onClickViewTransactions();
            }
        });

        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DocumentActivity.class));
            }
        });

//        DbHelper dbHelper = new DbHelper(MainActivity.this);
//        File imgFile = new File(Environment.getExternalStorageDirectory()
//                + "/Android/data/"
//                + "/ImgFiles");
//        File[] fileList = imgFile.listFiles();
//        dbHelper.insertDocument()

    }



    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void onClickTenants(View view) {
        Log.d("TEnant", "clicked");
       iHomePresenter.onClickTenants();

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
        PieDataSet dataSet = new PieDataSet(yEntrys, Constants.EXPENSES);
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
