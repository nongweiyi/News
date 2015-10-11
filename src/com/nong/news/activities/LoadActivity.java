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
 * @Description: չʾ��ӭҳ�沢��������Ƿ�����
 *
 * @author ũΤ��
 * @date 2015��10��9��
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
	 * @Title: setImageAnimation @Description: ����ͼƬ͸������Ч����ʾ @param @return
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
	 * ����ͼƬ�������� @param @return void @throws
	 */
	private void setImageAnimationListener(AlphaAnimation alphaAnimation) {
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO ������ʼʱ����

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO �����ظ�ʱ����

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// �������
				if (isNetworkAvailable()) {
					// ��ת�������б�ҳ��
					startActivity(new Intent(LoadActivity.this, NewsListActivity.class));
					LoadActivity.this.finish();
				} else {
					// �����Ի�����ʾ��ת����������ҳ��
					new AlertDialog.Builder(LoadActivity.this).setTitle("��ʾ").setMessage("��ǰ���粻���ã���������")
							.setPositiveButton("ȷ��", new OnClickListener() {

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
	 * @Title: isNetworkAvailable @Description: �ж������Ƿ���� @param @return @return
	 * Boolean @throws
	 */
	private Boolean isNetworkAvailable() {
		// ������������
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
	 * @Title: initView @Description: ��ʼ���ؼ� @param @return void @throws
	 */
	private void initView() {
		iv_loadimage = (ImageView) LoadActivity.this.findViewById(R.id.iv_loadimage);
	}

}
