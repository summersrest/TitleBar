package com.sum.sample_title_bar;

import android.app.Application;

import androidx.core.content.ContextCompat;

import com.sum.title_bar.TitleBar;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TitleBar.getDefaultBuilder()
                //返回按钮图片
                .setBackIcon(R.mipmap.icon_back_white)
                //返回按钮尺寸
                .setBackIconWidth(30)
                .setBackIconHeight(20)
                //返回文字
                .setBackText("返回")
                //标题栏背景颜色
                .setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color.red))
                //标题栏背景图（会覆盖背景色）
//                .setTitleBarBackground(ContextCompat.getDrawable(this, R.drawable.title_background))
                //标题字体颜色
                .setTitleColor(getColor(R.color.white))
                //右侧按钮字体颜色
                .setActionTextColor(R.color.black)
                //底部分割线颜色
                .setDividerColor(ContextCompat.getColor(this, R.color.red))
                //底部分割线背景图（会覆盖背景颜色）
//                .setDivider(ContextCompat.getDrawable(this, R.drawable.line))
                //设置标题栏高度（标题栏高度不包含分割线的高度）
                .setTitleBarHeight(60)
                //设置分割线高度
                .setDividerHeight(2)
                //底部分割线是否显示
                .setDividerVisible(TitleBar.VISIBLE);
    }
}
