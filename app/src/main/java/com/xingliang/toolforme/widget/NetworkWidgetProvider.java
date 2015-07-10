package com.xingliang.toolforme.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.xingliang.toolforme.R;

/**
 * Created by XingLiang on 2015/7/10.
 */
public class NetworkWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.network_widget_layout);
            views.setTextViewText(R.id.student_id, "未登录");

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
