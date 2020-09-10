package com.redzone.bandipora.utils;

import android.app.Activity;
import android.content.Intent;

import com.redzone.bandipora.models.Posts;

public class ActivityUtilities {

    private static ActivityUtilities sActivityUtilities = null;

    public static ActivityUtilities getInstance() {
        if (sActivityUtilities == null) {
            sActivityUtilities = new ActivityUtilities();
        }
        return sActivityUtilities;
    }

    public void invokeNewActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeCustomUrlActivity(Activity activity, Class<?> tClass, String pageTitle, String pageUrl, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra("title", pageTitle);
        intent.putExtra("url", pageUrl);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeCatWisePostListActiviy(Activity activity, Class<?> tClass, String categoryName, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra("title", categoryName);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

    public void invokeDetailsActiviy(Activity activity, Class<?> tClass, Posts model, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra("item", model);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

}
