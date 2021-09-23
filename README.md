# TitleBar
自定义标题栏
## **1、导入**
 1.引入jitpack
 ```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2.添加
```java
implementation 'com.github.summersrest:TitleBar:v1.0.3'
```
## **2、使用**
### 1、xml中使用
```xml
<com.sum.titlebar.TitleBar
        android:id="@+id/title_bar"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        app:tb_action_background="@drawable/btn_orange"
        app:tb_action_height="40dp"
        app:tb_action_marginEnd="10dp"
        app:tb_action_width="80dp"
        app:tb_background_alpha="255"
        app:tb_background_color="@color/white"
        app:tb_background_drawable="@drawable/title_background"
        app:tb_can_finish_activity="true"
        app:tb_divider_alpha="255"
        app:tb_divider_color="@color/red"
        app:tb_divider_drawable="@drawable/line"
        app:tb_divider_size="2dp"
        app:tb_divider_visible="visible"
        app:tb_icon_action="@mipmap/glass_gray"
        app:tb_icon_action_height="10dp"
        app:tb_icon_action_width="10dp"
        app:tb_icon_back="@mipmap/back"
        app:tb_icon_back_height="20dp"
        app:tb_icon_back_width="30dp"
        app:tb_text_action="保存"
        app:tb_text_action_color="@color/red"
        app:tb_text_action_text_size="14sp"
        app:tb_text_back="返回"
        app:tb_title="标题"
        app:tb_title_color="@color/black"
        app:tb_title_text_size="18sp" />
```
### Attributes
| name  | format   | description | 
| ------------- |:-------------:| -----:| 
| tb_background_color  | reference | titleBar背景色 
| tb_background_drawable  | reference  | titleBar背景图 
| tb_background_alpha | integer  | titleBar背景透明度(0~255) 
### 2、代码中设置样式属性
```java
titleBar
	//返回按钮图片
	.setBackIcon(ContextCompat.getDrawable(this, R.mipmap.back_white))
	.setBackIconResource(R.mipmap.back_white)
	//返回文字
	.setBackText("返回")
	//标题栏背景颜色
	.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color.red))
	//标题栏背景图（会覆盖背景色）
	.setTitleBarBackground(ContextCompat.getDrawable(this, R.drawable.title_background))
	.setTitleBarBackgroundResource(R.drawable.title_background)
	//标题文字
	.setTitle("标题")
	//标题字体颜色
	.setTitleColor(getColor(R.color.white))
	//右侧按钮文字
	.setActionText("保存")
	//右侧按钮字体颜色
	.setActionTextColor(R.color.black)
	//右侧按钮图片
	.setActionIcon(ContextCompat.getDrawable(this, R.mipmap.glass_gray))
	.setActionIconResource(R.mipmap.glass_gray)
	//右侧按钮是否显示
	.setActionVisible(false)
	//底部分割线颜色
	.setDividerColor(ContextCompat.getColor(this, R.color.red))
	//底部分割线背景图（会覆盖背景颜色）
	.setDivider(ContextCompat.getDrawable(this, R.drawable.line))
	.setDividerResource(R.drawable.line)
	//标题栏透明的
	.setBackgroundAlpha(255)
	//分割线透明度
	.setDividerAlpha(255)
	//分割线与标题栏透明度
	.setBackgroundAndDividerAlpha(255)
	//底部分割线是否显示
	.setDividerVisible(TitleBar.VISIBLE)
	//底部分割线高度
	.setDividerSize(MainActivity.this, 2f)
	//点击返回按钮是否关闭activity
	.setCanFinishActivity(true)
	//返回按钮点击事件
	.setOnBackClickListener(new OnBackClickListener() {
	    @Override
	    public void onClick(View v) {
		Toast.makeText(MainActivity.this, "返回按钮点击事件", Toast.LENGTH_SHORT).show();
	    }
	})
	//右侧图片点击事件
	.setOnActionClickListener(new OnActionClickListener() {
	    @Override
	    public void onClick(View v) {
		Toast.makeText(MainActivity.this, "右侧按钮点击事件", Toast.LENGTH_SHORT).show();
	    }
	});
```
### 3、设置全局默认属性
可以在`Application`中统一设置样式属性
```java
TitleBar.getDefaultBuilder()
	//返回按钮图片
	.setBackIcon(R.mipmap.back_white)
	//返回按钮尺寸
	.setBackIconWidth(30)
	.setBackIconHeight(20)
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
	.setDividerColor(ContextCompat.getColor(this, R.color.red))
	//底部分割线背景图（会覆盖背景颜色）
	.setDivider(ContextCompat.getDrawable(this, R.drawable.line))
	//设置分割线高度
	.setDividerSize(2)
	//底部分割线是否显示
	.setDividerVisible(TitleBar.VISIBLE);
```

## **3、备注**
> 1、标题栏背景图与底部分割线背景图会覆盖掉背景色，也就是如果设置了背景图，再设置背景色无效。    
> 2、样式设置优先级：代码>xml>全局。代码中未设置样式属性，以xml中设置的样式为准，代码与xml中都未设置，以Applicaion中设置的全局默认样式属性为准。  
