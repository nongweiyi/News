package com.nong.news.activities;

import com.nong.news.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

/**
 * 
 * @ClassName: LoadActivity
 * @Description: 展示欢迎页面并检查网络是否正常
 *
 * @author 农韦依
 * @date 2015年10月9日
 *
 */
public class LoadActivity extends Activity {
	ImageView iv_loadimage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setImageAnimation();
	}

	/**
	 * @Title: setImageAnimation @Description: 设置图片透明渐变效果显示 @param @return
	 * void @throws
	 */
	private void setImageAnimation() {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
		alphaAnimation.setDuration(5000);
		iv_loadimage.setAnimation(alphaAnimation);
		setImageAnimationListener(alphaAnimation);
	}

	/**
	 * @param alphaAnimation @Title: setImageAnimationListener @Description:
	 * 设置图片动画监听 @param @return void @throws
	 */
	private void setImageAnimationListener(AlphaAnimation alphaAnimation) {
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO 动画开始时调用

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO 动画重复时调用

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 网络可用
				if (isNetworkAvailable()) {
					// 跳转到新闻列表页面
					startActivity(new Intent(LoadActivity.this, NewsListActivity.class));
					LoadActivity.this.finish();
				} else {
					// 弹出对话框并提示跳转到网络设置页面
					new AlertDialog.Builder(LoadActivity.this).setTitle("提示").setMessage("当前网络不可用，请检查设置")
							.setPositiveButton("确定", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));

						}
					}).create().show();
				}

			}
		});

	}

	/**
	 * @Title: isNetworkAvailable @Description: 判断网络是否可用 @param @return @return
	 * Boolean @throws
	 */
	private Boolean isNetworkAvailable() {
		// 获得网络管理器
		ConnectivityManager connectivityManager = (ConnectivityManager) LoadActivity.this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		} else {
			NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
			if (networkInfos != null) {
				for (NetworkInfo newNetworkInfo : networkInfos) {
					if (newNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @Title: initView @Description: 初始化控件 @param @return void @throws
	 */
	private void initView() {
		iv_loadimage = (ImageView) LoadActivity.this.findViewById(R.id.iv_loadimage);
	}

}
