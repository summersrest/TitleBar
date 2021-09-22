package com.sum.sample_titlebar;

import android.app.Application;

import com.sum.titlebar.TitleBar;

import androidx.core.content.ContextCompat;


/**
 * @author liujiang
 * created at: 2021/9/22 9:43
 * Desc:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TitleBar.getDefaultBuilder()
                //返回按钮图片
                .setBackIcon(R.mipmap.back_white)
                //返回文字
                .setBackText("返回")
                //标题栏背景颜色
                .setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color.red))
                //标题栏背景图（会覆盖背景色）
                .setTitleBarBackground(ContextCompat.getDrawable(this, R.drawable.title_background))
                //标题字体颜色
                .setTitleColor(getColor(R.color.white))
                //右侧按钮字体颜色
                .setActionTextColor(R.color.black)
                //底部分割线颜色
                .setDiverColor(ContextCompat.getColor(this, R.color.red))
                //底部分割线背景图（会覆盖背景颜色）
                .setDiverDrawable(ContextCompat.getDrawable(this, R.drawable.line))
                //底部分割线是否显示
                .setDiverVisible(TitleBar.VISIBLE);
    }
}
