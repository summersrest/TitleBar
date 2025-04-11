package com.sum.sample_title_bar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.sum.sample_title_bar.databinding.ActivityMainBinding;
import com.sum.title_bar.OnActionClickListener;
import com.sum.title_bar.OnBackClickListener;
import com.sum.title_bar.TitleBar;

/**
 * @author LiuJiang
 * created  at: 2024/5/14 13:14
 * Desc:
 */
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding viewBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

//        viewBinding.titleBar
//                //返回按钮图片
//                .setBackIcon(ContextCompat.getDrawable(this, R.mipmap.icon_back_white))
//                .setBackIconResource(R.mipmap.icon_back_white)
//                //返回文字
//                .setBackText("返回")
//                //标题栏背景颜色
//                .setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color.red))
//                //标题栏背景图（会覆盖背景色）
//                .setTitleBarBackground(ContextCompat.getDrawable(this, R.drawable.title_background))
//                .setTitleBarBackgroundResource(R.drawable.title_background)
//                //标题文字
//                .setTitle("标题")
//                //标题字体颜色
//                .setTitleColor(getColor(R.color.white))
//                //右侧按钮文字
//                .setActionText("保存")
//                //右侧按钮字体颜色
//                .setActionTextColor(R.color.black)
//                //右侧按钮图片
//                .setActionIcon(ContextCompat.getDrawable(this, R.mipmap.icon_glass_gray))
//                .setActionIconResource(R.mipmap.icon_glass_gray)
//                //右侧按钮是否显示
//                .setActionVisible(false)
//                //底部分割线颜色
//                .setDividerColor(ContextCompat.getColor(this, R.color.red))
//                //底部分割线背景图（会覆盖背景颜色）
////                .setDivider(ContextCompat.getDrawable(this, R.drawable.line))
////                .setDividerResource(R.drawable.line)
//                //标题栏透明的
//                .setBackgroundAlpha(255)
//                //分割线透明度
//                .setDividerAlpha(255)
//                //分割线与标题栏透明度
//                .setBackgroundAndDividerAlpha(255)
//                //底部分割线是否显示
//                .setDividerVisible(TitleBar.INVISIBLE)
//                //底部分割线高度
//                .setDividerSize(MainActivity.this, 2f)
//                //点击返回按钮是否关闭activity
//                .setCanFinishActivity(true)
//                //输入框提示文字
//                .setInputHint("请输入车牌号")
//                //返回按钮点击事件
//                .setOnBackClickListener(new OnBackClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "返回按钮点击事件", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                //右侧图片点击事件
//                .setOnActionClickListener(new OnActionClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "右侧按钮点击事件", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//        //设置输入框文字
//        viewBinding.titleBar.setInputText("输入框文字");
//        //设置输入框提示文字
//        viewBinding.titleBar.setInputHint("请输入车牌号");
//        //获取输入框控件
//        EditText editText = viewBinding.titleBar.getEditText();
//        //获取输入框中的文字
//        String content = viewBinding.titleBar.getInputText();
//        //清空输入框
//        viewBinding.titleBar.clearInput();
    }
}
