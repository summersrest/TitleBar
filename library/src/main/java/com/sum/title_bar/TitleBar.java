package com.sum.title_bar;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.sum.title_bar.databinding.ViewTitleBarBinding;

public class TitleBar extends Toolbar {
    private ViewTitleBarBinding viewBinding;

    /**
     * 全局默认背景图片
     */
    private static Drawable defaultBackgroundDrawable;

    /**
     * 全局默认背景色
     */
    private static int defaultBackgroundColor;

    /**
     * 全局默认背景透明度
     */
    private static int defaultBackgroundAlpha = 255;

    /**
     * 全局默认标题栏高度
     */
    private static int defaultHeight;

    /**
     * 全局默认返回按钮
     */
    private static int defaultBackIcon;

    /**
     * 全局默认返回按钮宽度
     */
    private static int defaultBackIconWidth;

    /**
     * 全局默认返回按钮高度
     */
    private static int defaultBackIconHeight;

    /**
     * 全局默认返回文字
     */
    private static String defaultBackText;

    /**
     * 全局默认返回文字颜色
     */
    private static int defaultBackTextColor = Color.parseColor("#22293A");

    /**
     * 全局默认返回文字尺寸
     */
    private static int defaultBackTextSize;

    /**
     * 全局默认标题文字颜色
     */
    private static int defaultTitleTextColor = Color.parseColor("#22293A");

    /**
     * 全局默认标题文字尺寸
     */
    private static int defaultTitleTextSize;

    /**
     * 全局默认右侧按钮文字颜色
     */
    private static int defaultActionTextColor = Color.parseColor("#22293A");

    /**
     * 全局默认右侧按钮文字尺寸
     */
    private static int defaultActionTextSize;

    /**
     * 全局默认右侧按钮图片宽度
     */
    private static int defaultActionIconWidth;

    /**
     * 全局默认右侧按钮图片高度
     */
    private static int defaultActionIconHeight;

    /**
     * 全局默认Action右边距
     */
    private static int defaultActionMarginEnd;

    /**
     * 全局默认分割线是否显示
     */
    private static int defaultDriverVisible = View.VISIBLE;

    /**
     * 全局默认分割线图片
     */
    private static Drawable defaultDividerDrawable;

    /**
     * 全局默认分割线颜色
     */
    private static int defaultDividerColor = Color.parseColor("#EFEFEF");

    /**
     * 全局默认分割线高度
     */
    private static int defaultDividerSize;

    /**
     * 全局默认分割线透明度
     */
    private static int defaultDividerAlpha = 255;

    /**
     * 返回按钮监听事件
     */
    private OnBackClickListener onBackClickListener;

    /**
     * 右侧按钮点击监听事件
     */
    private OnActionClickListener onActionClickListener;

    private boolean autoFinishAct = true;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        viewBinding = ViewTitleBarBinding.inflate(inflater, this, true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

        // 去除toolbar前后边距
        setContentInsetsAbsolute(0, 0);

        // 设置标题栏背景
        setBackgroundAndHeight(context, typedArray);

        // 返回按钮设置
        setBackButton(context, typedArray);

        // 标题属性设置
        setTitle(context, typedArray);

        //右侧按钮属性设置
        setAction(context, typedArray);

        // 分割线属性设置
        setDivider(context, typedArray);

        // 回收
        typedArray.recycle();

    }

    /**
     * 设置标题栏背景与高度
     *
     * @param typedArray
     */
    private void setBackgroundAndHeight(Context context, TypedArray typedArray) {
        //xml中设置的背景图
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.TitleBar_tb_background_drawable);
        //若xml中未设置，背景色设置为全局的默认背景图
        if (null == backgroundDrawable) {
            backgroundDrawable = defaultBackgroundDrawable;
        }
        //设置titleBar背景图
        if (null != backgroundDrawable) {
            setBackground(backgroundDrawable);
        } else {
            //若未设置过背景图，设置背景色
            //xml中设置的背景色
            int backgroundColor = typedArray.getColor(R.styleable.TitleBar_tb_background_color, 0);
            //xml中未设置背景色，取全局默认的背景色
            if (0 == backgroundColor) {
                backgroundColor = defaultBackgroundColor;
            }
            //未设置过全局默认背景色,默认设置为白色
            if (0 == backgroundColor) {
                backgroundColor = Color.parseColor("#FFFFFF");
            }
            setBackgroundColor(backgroundColor);
        }
        //背景透明度
        int alpha = typedArray.getInteger(R.styleable.TitleBar_tb_background_alpha, defaultBackgroundAlpha);
        getBackground().mutate().setAlpha(alpha);

        //标题栏高度
        int titleHeight = (int) typedArray.getDimension(R.styleable.TitleBar_tb_title_bar_size, 0);
        //全局设置
        if (0 == titleHeight) {
            titleHeight = ToolsUtils.dip2px(context, defaultHeight);
        }
        //全部未设置，设置一个默认值
        if (0 == titleHeight) {
            titleHeight = ToolsUtils.dip2px(context, 60f);
        }
        ToolsUtils.setViewSize(viewBinding.titleLayout, ViewGroup.LayoutParams.MATCH_PARENT, titleHeight);
    }

    /**
     * 设置返回按钮
     *
     * @param context
     * @param typedArray
     */
    private void setBackButton(Context context, TypedArray typedArray) {
        //xml中设置的回退按钮
        int backIcon = typedArray.getResourceId(R.styleable.TitleBar_tb_back_icon, 0);
        //xml中未设置，取全局默认按钮
        if (0 == backIcon) {
            backIcon = defaultBackIcon;
        }
        //若未设置全局默认按钮
        if (0 == backIcon) {
            backIcon = R.mipmap.back;
        }
        viewBinding.iconBack.setImageResource(backIcon);

        //回退按钮宽度
        //xml中设置
        int backIconWidth = (int) typedArray.getDimension(R.styleable.TitleBar_tb_back_icon_width, 0);
        //全局设置
        if (0 == backIconWidth) {
            backIconWidth = ToolsUtils.dip2px(context, defaultBackIconWidth);
        }
        //均未设置，设置一个默认值
        if (0 == backIconWidth) {
            backIconWidth = ToolsUtils.dip2px(context, 30);
        }
        //回退按钮高度
        //xml中设置
        int backIconHeight = (int) typedArray.getDimension(R.styleable.TitleBar_tb_back_icon_height, 0);
        //全局设置
        if (0 == backIconHeight) {
            backIconHeight = ToolsUtils.dip2px(context, defaultBackIconHeight);
        }
        //均未设置设置一个默认值
        if (0 == backIconHeight) {
            backIconHeight = ToolsUtils.dip2px(context, 20);
        }
        //设置回退按钮尺寸
        ToolsUtils.setViewSize(viewBinding.iconBack, backIconWidth, backIconHeight);

        /******回退按钮文字*******/
        //xml中设置
        String backText = typedArray.getString(R.styleable.TitleBar_tb_back_text);
        //xml中未设置取全局设置
        if (TextUtils.isEmpty(backText)) {
            backText = defaultBackText;
        }
        //均为设置默认为""
        if (TextUtils.isEmpty(backText)) {
            backText = "";
        }
        viewBinding.tvBack.setText(backText);
        /******回退按钮文字颜色*******/
        //xml中取值
        int backTextColor = typedArray.getColor(R.styleable.TitleBar_tb_back_text_color, defaultBackTextColor);
        viewBinding.tvBack.setTextColor(backTextColor);

        /******回退按钮文字大小*******/
        int backTextSizeDefault = defaultBackTextSize == 0 ?
                getResources().getDimensionPixelSize(R.dimen.title_text_size) : defaultBackTextSize;
        float backTextSize = typedArray.getDimension(R.styleable.TitleBar_tb_back_text_size, backTextSizeDefault);
        viewBinding.tvBack.setTextSize(TypedValue.COMPLEX_UNIT_PX, backTextSize);

        //返回按钮是否显示
        int visible = typedArray.getInt(R.styleable.TitleBar_tb_back_button_visible, View.VISIBLE);
        viewBinding.btnBack.setVisibility(visible);

        //点击返回按钮是否关闭当前activity
        autoFinishAct = typedArray.getBoolean(R.styleable.TitleBar_tb_can_finish_activity, true);
        //设置返回按钮点击事件
        viewBinding.btnBack.setOnClickListener(v -> {
            if (null != onBackClickListener) {
                onBackClickListener.onClick(v);
            }
            if (autoFinishAct) {
                ToolsUtils.context2Activity(context).finish();
            }
        });
    }

    /**
     * 设置标题属性
     *
     * @param context
     * @param typedArray
     */
    private void setTitle(Context context, TypedArray typedArray) {
        //显示标题还是输入框？
        if (typedArray.getBoolean(R.styleable.TitleBar_tb_show_input_box, false)) {
            //显示输入框
            viewBinding.etLayout.setVisibility(View.VISIBLE);
            viewBinding.tvTitle.setVisibility(View.GONE);
            //输入框文字
            String inputText = typedArray.getString(R.styleable.TitleBar_tb_input_text);
            if (!TextUtils.isEmpty(inputText)) {
                viewBinding.etSearch.setText(inputText);
            }
            //输入框提示文字
            String inputHint = typedArray.getString(R.styleable.TitleBar_tb_input_hint);
            if (!TextUtils.isEmpty(inputHint)) {
                viewBinding.etSearch.setHint(inputHint);
            }
            //输入框文字颜色
            int etColor = typedArray.getColor(R.styleable.TitleBar_tb_input_text_color, Color.parseColor("#22293A"));
            viewBinding.etSearch.setTextColor(etColor);
            //输入框文字大小
            int etTextSizeDefault = getResources().getDimensionPixelSize(R.dimen.input_text_size);
            float etTextSize = typedArray.getDimension(R.styleable.TitleBar_tb_input_text_size, etTextSizeDefault);
            viewBinding.etSearch.setTextSize(TypedValue.COMPLEX_UNIT_PX, etTextSize);
            //输入框背景图
            int etBackground = typedArray.getResourceId(R.styleable.TitleBar_tb_input_background, R.drawable.bg_default_et);
            viewBinding.etLayout.setBackgroundResource(etBackground);
            //设置大小
            int etWidth = (int) typedArray.getDimension(R.styleable.TitleBar_tb_input_width, ToolsUtils.dip2px(context, 240));
            int etHeight = (int) typedArray.getDimension(R.styleable.TitleBar_tb_input_height, ToolsUtils.dip2px(context, 40));
            ToolsUtils.setViewSize(viewBinding.etLayout, etWidth, etHeight);
            //输入框前的图标
            int drawStart = typedArray.getResourceId(R.styleable.TitleBar_tb_input_draw, 0);
            if (0 != drawStart) {
                viewBinding.ivEtStart.setVisibility(View.VISIBLE);
                //设置图片
                viewBinding.ivEtStart.setImageResource(drawStart);
                //设置大小
                int drawWidth = (int) typedArray.getDimension(R.styleable.TitleBar_tb_input_draw_width, ToolsUtils.dip2px(context, 20));
                int drawHeight = (int) typedArray.getDimension(R.styleable.TitleBar_tb_input_draw_height, ToolsUtils.dip2px(context, 20));
                ToolsUtils.setViewSize(viewBinding.ivEtStart, drawWidth, drawHeight);
            } else {
                viewBinding.ivEtStart.setVisibility(View.GONE);
            }
        } else {
            //显示标题
            viewBinding.etLayout.setVisibility(View.GONE);
            viewBinding.tvTitle.setVisibility(View.VISIBLE);
            /******标题文字*******/
            //标题文字
            String titleText = typedArray.getString(R.styleable.TitleBar_tb_title);
            if (!TextUtils.isEmpty(titleText)) {
                viewBinding.tvTitle.setText(titleText);
            }
            /******标题文字颜色*******/
            //xml中取值
            int titleColor = typedArray.getColor(R.styleable.TitleBar_tb_title_text_color, defaultTitleTextColor);
            viewBinding.tvTitle.setTextColor(titleColor);
            /******标题文字大小*******/
            int titleTextSizeDefault = defaultTitleTextSize == 0 ?
                    getResources().getDimensionPixelSize(R.dimen.title_text_size) : defaultTitleTextSize;
            float titleTextSize = typedArray.getDimension(R.styleable.TitleBar_tb_title_text_size, titleTextSizeDefault);
            viewBinding.tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        }
    }

    /**
     * 设置右侧Action属性
     *
     * @param context
     * @param typedArray
     */
    private void setAction(Context context, TypedArray typedArray) {
        //右侧事件文字
        String actionText = typedArray.getString(R.styleable.TitleBar_tb_action_text);
        //右侧文字颜色
        //xml设置
        int actionTextColor = typedArray.getColor(R.styleable.TitleBar_tb_action_text_color, defaultActionTextColor);
        //全局设置
        viewBinding.tvAction.setTextColor(actionTextColor);
        //右侧文字大小
        int actionTextSizeDefault = defaultActionTextSize == 0 ?
                getResources().getDimensionPixelSize(R.dimen.action_text_size) : defaultActionTextSize;
        int actionTextSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_tb_action_text_size, actionTextSizeDefault);
        viewBinding.tvAction.setTextSize(TypedValue.COMPLEX_UNIT_PX, actionTextSize);
        if (!TextUtils.isEmpty(actionText)) {
            viewBinding.tvAction.setVisibility(View.VISIBLE);
            viewBinding.tvAction.setText(actionText);
        } else {
            viewBinding.tvAction.setVisibility(View.GONE);
        }
        //右侧事件图片
        Drawable actionIcon = typedArray.getDrawable(R.styleable.TitleBar_tb_action_icon);
        //图片宽度
        int iconWidth = (int) typedArray.getDimension(R.styleable.TitleBar_tb_action_icon_width,
                ToolsUtils.dip2px(context, defaultActionIconWidth));
        //图片高度
        int iconHeight = (int) typedArray.getDimension(R.styleable.TitleBar_tb_action_icon_height,
                ToolsUtils.dip2px(context, defaultActionIconHeight));
        //设置图片尺寸
        ToolsUtils.setViewSize(viewBinding.ivAction, iconWidth, iconHeight);
        if (null != actionIcon) {
            viewBinding.ivAction.setVisibility(View.VISIBLE);
            viewBinding.ivAction.setBackground(actionIcon);
        } else {
            viewBinding.ivAction.setVisibility(View.GONE);
        }
        //按钮背景
        Drawable btnDrawable = typedArray.getDrawable(R.styleable.TitleBar_tb_action_background);
        if (null != btnDrawable) {
            viewBinding.btnAction.setBackground(btnDrawable);
        }

        //按钮宽度
        int btnWidth = (int) typedArray.getDimension(R.styleable.TitleBar_tb_action_width, 0);
        if (0 == btnWidth) {
            btnWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        //按钮高度
        int btnHeight = (int) typedArray.getDimension(R.styleable.TitleBar_tb_action_height, 0);
        if (0 == btnHeight) {
            btnHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        //按钮与右边距
        int div = (int) typedArray.getDimension(R.styleable.TitleBar_tb_action_marginEnd, defaultActionMarginEnd);
        ToolsUtils.setViewSize(viewBinding.btnAction, btnWidth, btnHeight, div);
        //右侧按钮图片与文字均未设置，则默认为按钮隐藏按钮
        boolean defaultActionVisible = !TextUtils.isEmpty(actionText) || null != actionIcon || null != btnDrawable;
        boolean actionVisible = typedArray.getBoolean(R.styleable.TitleBar_tb_action_visible, defaultActionVisible);
        if (actionVisible) {
            viewBinding.btnAction.setVisibility(View.VISIBLE);
        } else {
            viewBinding.btnAction.setVisibility(View.GONE);
        }

        viewBinding.btnAction.setOnClickListener(v -> {
            if (null != onActionClickListener) {
                onActionClickListener.onClick(v);
            }
        });
    }

    /**
     * 设置底部分割线相关属性
     *
     * @param context
     * @param typedArray
     */
    private void setDivider(Context context, TypedArray typedArray) {
        /******分割线是否显示*******/
        //xml中读取不到，从全局读取
        int visible = typedArray.getInt(R.styleable.TitleBar_tb_divider_visible, defaultDriverVisible);
        viewBinding.iconLine.setVisibility(visible);
        if (visible == View.VISIBLE) {
            /******分割线图片*******/
            //xml中设置
            Drawable lineDrawable = typedArray.getDrawable(R.styleable.TitleBar_tb_divider_drawable);
            //全局设置
            if (null == lineDrawable) {
                lineDrawable = defaultDividerDrawable;
            }
            if (null != lineDrawable) {
                viewBinding.iconLine.setBackground(lineDrawable);
            } else {
                /******分割线图片*******/
//                int lineColor = typedArray.getColor(R.styleable.TitleBar_tb_divider_color, Color.parseColor("#EFEFEF"));
                //xml中读取不到，显示全局设置的颜色
                int lineColor = typedArray.getColor(R.styleable.TitleBar_tb_divider_color, defaultDividerColor);
                viewBinding.iconLine.setBackgroundColor(lineColor);
            }
            /********分割线高度********/
            //xml中未设置，读取全局默认值
            int dividerHeight = (int) typedArray.getDimension(R.styleable.TitleBar_tb_divider_size,
                    ToolsUtils.dip2px(context, defaultDividerSize == 0 ? 1f : defaultDividerSize));
            //设置分割线高度
            ToolsUtils.setViewSize(viewBinding.iconLine, ViewGroup.LayoutParams.MATCH_PARENT, dividerHeight);
        } else {
            viewBinding.iconLine.setVisibility(View.GONE);
        }
        //分割线透明度
        int alphaDivider = typedArray.getInteger(R.styleable.TitleBar_tb_divider_alpha, defaultDividerAlpha);
        viewBinding.iconLine.getBackground().mutate().setAlpha(alphaDivider);

    }


    /**
     * 设置标题栏背景颜色
     *
     * @param backgroundColor
     */
    public TitleBar setTitleBarBackgroundColor(int backgroundColor) {
        setBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param drawable
     */
    public TitleBar setTitleBarBackground(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param resource
     */
    public TitleBar setTitleBarBackgroundResource(int resource) {
        setBackgroundResource(resource);
        return this;
    }


    /**
     * 设置返回按钮图片
     *
     * @param drawable
     * @return
     */
    public TitleBar setBackIcon(Drawable drawable) {
        viewBinding.iconBack.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置返回按钮图片
     *
     * @param resource
     * @return
     */
    public TitleBar setBackIconResource(int resource) {
        viewBinding.iconBack.setImageResource(resource);
        return this;
    }

    /**
     * 设置回退按钮文字
     *
     * @param text
     * @return
     */
    public TitleBar setBackText(String text) {
        viewBinding.tvBack.setText(text);
        return this;
    }

    /**
     * 设置标题文字
     *
     * @param title
     * @return
     */
    public TitleBar setTitle(String title) {
        viewBinding.tvTitle.setText(title);
        return this;
    }

    /**
     * 设置标题字体颜色
     *
     * @param color
     * @return
     */
    public TitleBar setTitleColor(int color) {
        viewBinding.tvTitle.setTextColor(color);
        return this;
    }

    /**
     * 设置右侧事件按钮文字
     *
     * @param text
     * @return
     */
    public TitleBar setActionText(String text) {
        viewBinding.tvAction.setText(text);
        viewBinding.tvAction.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右侧事件按钮文字颜色
     *
     * @param color
     * @return
     */
    public TitleBar setActionTextColor(int color) {
        viewBinding.tvAction.setTextColor(color);
        return this;
    }

    /**
     * 设置右侧事件按钮图片
     *
     * @param drawable
     * @return
     */
    public TitleBar setActionIcon(Drawable drawable) {
        viewBinding.ivAction.setBackground(drawable);
        viewBinding.ivAction.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置右侧事件按钮图片
     *
     * @param resource
     * @return
     */
    public TitleBar setActionIconResource(int resource) {
        viewBinding.ivAction.setBackgroundResource(resource);
        viewBinding.ivAction.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 右侧按钮是否显示
     *
     * @param visible
     * @return
     */
    public TitleBar setActionVisible(boolean visible) {
        viewBinding.btnAction.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置分割线颜色
     *
     * @param color
     * @return
     */
    public TitleBar setDividerColor(int color) {
        viewBinding.iconLine.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置分割线图片
     *
     * @param drawable
     * @return
     */
    public TitleBar setDivider(Drawable drawable) {
        viewBinding.iconLine.setBackground(drawable);
        return this;
    }

    /**
     * 设置分割线图片
     *
     * @param resource
     * @return
     */
    public TitleBar setDividerResource(int resource) {
        viewBinding.iconLine.setBackgroundResource(resource);
        return this;
    }

    /**
     * 分割线是否显示
     *
     * @param visible
     * @return
     */
    public TitleBar setDividerVisible(int visible) {
        viewBinding.iconLine.setVisibility(visible == TitleBar.VISIBLE ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置分割线高度
     *
     * @param size
     * @return
     */
    public TitleBar setDividerSize(Context context, float size) {
        ToolsUtils.setViewSize(viewBinding.iconLine, ViewGroup.LayoutParams.MATCH_PARENT, ToolsUtils.dip2px(context, size));
        return this;
    }

    /**
     * 设置TitleBar背景透明度
     *
     * @param alpha 0 ~ 255
     * @return
     */
    public TitleBar setBackgroundAlpha(int alpha) {
        getBackground().mutate().setAlpha(alpha);
        return this;
    }

    /**
     * 设置TitleBar背景与底部分割线透明度
     *
     * @param alpha 0 ~ 255
     * @return
     */
    public TitleBar setBackgroundAndDividerAlpha(int alpha) {
//        viewBinding.toolbar.getBackground().mutate().setAlpha(alpha);
        getBackground().mutate().setAlpha(alpha);
        viewBinding.iconLine.getBackground().mutate().setAlpha(alpha);
        return this;
    }

    /**
     * 设置输入框文字
     *
     * @param text
     */
    public void setInputText(String text) {
        viewBinding.etSearch.setText(text);
    }

    /**
     * 获取输入框中的文字
     *
     * @return
     */
    public String getInputText() {
        return viewBinding.etSearch.getText().toString().trim();
    }

    /**
     * 获取输入框EditText
     *
     * @return
     */
    public EditText getEditText() {
        return viewBinding.etSearch;
    }

    /**
     * 清空输入框
     */
    public void clearInput() {
        viewBinding.etSearch.setText("");
    }

    /**
     * 设置输入框提示文字
     *
     * @param hint
     * @return
     */
    public TitleBar setInputHint(String hint) {
        viewBinding.etSearch.setHint(hint);
        return this;
    }

    /**
     * 设置底部分割线透明度
     *
     * @param alpha
     * @return
     */
    public TitleBar setDividerAlpha(int alpha) {
        viewBinding.iconLine.getBackground().mutate().setAlpha(alpha);
        return this;
    }

    /**
     * 返回按钮监听事件
     *
     * @param onBackClickListener
     * @return
     */
    public TitleBar setOnBackClickListener(OnBackClickListener onBackClickListener) {
        this.onBackClickListener = onBackClickListener;
        return this;
    }

    /**
     * 右侧按钮点击事件
     *
     * @param onActionClickListener
     * @return
     */
    public TitleBar setOnActionClickListener(OnActionClickListener onActionClickListener) {
        this.onActionClickListener = onActionClickListener;
        return this;
    }

    /**
     * 点击返回按钮是否关闭当前Activity
     *
     * @param isFinish
     * @return
     */
    public TitleBar setCanFinishActivity(boolean isFinish) {
        this.autoFinishAct = isFinish;
        return this;
    }

    public static DefaultBuilder getDefaultBuilder() {
        return new DefaultBuilder();
    }

    public static class DefaultBuilder {

        /**
         * 设置默认背景图
         *
         * @param drawable
         * @return
         */
        public DefaultBuilder setBackground(Drawable drawable) {
            defaultBackgroundDrawable = drawable;
            return this;
        }

        /**
         * 设置全局默认背景色
         *
         * @param color
         * @return
         */
        public DefaultBuilder setBackgroundColor(int color) {
            defaultBackgroundColor = color;
            return this;
        }

        /**
         * 设置全局默认背景透明度
         *
         * @param alpha
         * @return
         */
        public DefaultBuilder setBackgroundAlpha(int alpha) {
            defaultBackgroundAlpha = alpha;
            return this;
        }


        /**
         * 标题栏默认高度（不包括分割线高度，单纯标题栏的高度）
         *
         * @param height
         * @return
         */
        public DefaultBuilder setHeight(int height) {
            defaultHeight = height;
            return this;
        }

        /**
         * 设置全局默认返回按钮
         *
         * @param resource
         * @return
         */
        public DefaultBuilder setBackIcon(int resource) {
            defaultBackIcon = resource;
            return this;
        }

        /**
         * 设置全局默认返回文字
         *
         * @param text
         * @return
         */
        public DefaultBuilder setBackText(String text) {
            defaultBackText = text;
            return this;
        }

        /**
         * 设置全局默认返回按钮文字颜色
         *
         * @param color
         * @return
         */
        public DefaultBuilder setBackTextColor(int color) {
            defaultBackTextColor = color;
            return this;
        }

        /**
         * 设置全局默认返回按钮文字大小
         *
         * @param size
         * @return
         */
        public DefaultBuilder setBackTextSize(int size) {
            defaultBackTextSize = size;
            return this;
        }

        /**
         * 设置全局默认返回按钮宽度
         *
         * @param width
         * @return
         */
        public DefaultBuilder setBackIconWidth(int width) {
            defaultBackIconWidth = width;
            return this;
        }

        /**
         * 设置全局默认返回按钮高度
         *
         * @param height
         * @return
         */
        public DefaultBuilder setBackIconHeight(int height) {
            defaultBackIconHeight = height;
            return this;
        }

        /**
         * 设置全局默认标题文字颜色
         *
         * @param color
         * @return
         */
        public DefaultBuilder setTitleTextColor(int color) {
            defaultTitleTextColor = color;
            return this;
        }

        /**
         * 设置全局默认标题文字大小
         *
         * @param size
         * @return
         */
        public DefaultBuilder setTitleTextSize(int size) {
            defaultTitleTextSize = size;
            return this;
        }

        /**
         * 设置右侧按钮默认文字颜色
         *
         * @param color
         * @return
         */
        public DefaultBuilder setActionTextColor(int color) {
            defaultActionTextColor = color;
            return this;
        }

        /**
         * 设置右侧按钮默认文字大小
         *
         * @param size
         * @return
         */
        public DefaultBuilder setActionTextSize(int size) {
            defaultActionTextSize = size;
            return this;
        }

        /**
         * 设置右侧按钮默认图标宽度
         *
         * @param width
         * @return
         */
        public DefaultBuilder setActionIconWidth(int width) {
            defaultActionIconWidth = width;
            return this;
        }

        /**
         * 设置右侧按钮默认图标高度
         *
         * @param height
         * @return
         */
        public DefaultBuilder setActionIconHeight(int height) {
            defaultActionIconHeight = height;
            return this;
        }

        /**
         * 全局默认Action右边距
         *
         * @param marginEnd
         * @return
         */
        public DefaultBuilder setActionMarginEnd(int marginEnd) {
            defaultActionMarginEnd = marginEnd;
            return this;
        }

        /**
         * 设置全局默认分割线是否显示
         *
         * @param visible
         * @return
         */
        public DefaultBuilder setDividerVisible(int visible) {
            defaultDriverVisible = visible;
            return this;
        }

        /**
         * 设置全局默认分割线图片
         *
         * @param drawable
         * @return
         */
        public DefaultBuilder setDivider(Drawable drawable) {
            defaultDividerDrawable = drawable;
            return this;
        }

        /**
         * 设置全局默认分割线颜色
         *
         * @param color
         * @return
         */
        public DefaultBuilder setDividerColor(int color) {
            defaultDividerColor = color;
            return this;
        }

        /**
         * 全局设置分割线高度
         *
         * @param height
         * @return
         */
        public DefaultBuilder setDividerHeight(int height) {
            defaultDividerSize = height;
            return this;
        }

        /**
         * 设置全局默认分割线透明度
         *
         * @param alpha
         * @return
         */
        public DefaultBuilder setDividerAlpha(int alpha) {
            defaultDividerAlpha = alpha;
            return this;
        }
    }
}