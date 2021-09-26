package com.sum.titlebar;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @author liujiang
 * created at: 2021/9/23 9:50
 * Desc:
 */
public class ToolsUtils {
    /**
     * dp转px
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

    /**
     * Context转Activity
     * @param context
     * @return
     */
    public static Activity context2Activity(Context context) {
        if (context == null)
            return null;
        else if (context instanceof Activity)
            return (Activity)context;
        else if (context instanceof ContextWrapper)
            return context2Activity(((ContextWrapper)context).getBaseContext());
        return null;
    }

    /**
     * 设置屏幕宽高
     * @param view
     * @param width
     * @param height
     */
    public static void setViewSize(View view, int width, int height) {
        ViewGroup.LayoutParams lp =  view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);
    }

    /**
     * 设置屏幕宽高
     * @param view
     * @param width
     * @param height
     */
    public static void setViewSize(View view, int width, int height, int marginEnd) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        lp.setMarginEnd(marginEnd);
        view.setLayoutParams(lp);
    }
} 
