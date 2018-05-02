package com.example.purva.propertymanagment.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.example.purva.propertymanagment.R;

import io.paperdb.Paper;

/**
 * Implementation of App Widget functionality.
 */
public class ToDoWidget extends AppWidgetProvider {


    private ArrayAdapter<String> mAdapter;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {



        Paper.init(context);
        String task = Paper.book().read("text");
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.to_do_widget);
        views.setTextViewText(R.id.appwidget_text, ""+task);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Paper.init(context);
            String task = Paper.book().read("text");
            CharSequence widgetText = context.getString(R.string.appwidget_text);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.to_do_widget);
            views.setTextViewText(R.id.appwidget_text, ""+task);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Paper.init(context);
        String task = Paper.book().read("text");
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.to_do_widget);
        views.setTextViewText(R.id.appwidget_text, ""+task);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

