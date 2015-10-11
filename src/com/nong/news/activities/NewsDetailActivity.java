package com.nong.news.activities;

import com.nong.news.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * @Description: 展示新闻详细
 * 
 * @author 农韦依
 * @date 2015年10月10日
 */
public class NewsDetailActivity extends Activity {
	View view_btn_actionbar;
	WebView webview_news_detail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		initView();
		setWebWebSettingAndData();
	}

	/**
	 * @Description:拿到上一个activity床底过来的url并打开网页
	 * 
	 */
	private void setWebWebSettingAndData() {
		try {

			// 当网页跳转到另一个网页时，网页目标仍在当前网页显示
			webview_news_detail.setWebViewClient(new WebViewClient());
			// 设置支持javascript
			webview_news_detail.getSettings().setJavaScriptEnabled(true);
			// 把所有内容放大webview等宽的一列中
			webview_news_detail.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			webview_news_detail.getSettings().setLoadWithOverviewMode(true);
			Intent intent = this.getIntent();
			String url = intent.getExtras().getString("url");
			// 传入网址
			webview_news_detail.loadUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "网络异常，请检查网络后重试", 0).show();
		}
	}

	/**
	 * @Description:控件初始化
	 * 
	 */
	private void initView() {
		webview_news_detail = (WebView) this.findViewById(R.id.webview_news_detail);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.news_detail, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		return true;
	}

	/**
	 * actionbar上的选项按钮事件监听
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_share:
			Toast.makeText(this, "分享", 0).show();
			break;
		case R.id.action_collect:
			Toast.makeText(this, "收藏", 0).show();
			break;
		case R.id.action_screenCapture:
			Toast.makeText(this, "态度截屏", 0).show();
			break;
		case R.id.action_fontSize:
			Toast.makeText(this, "正文字号", 0).show();
			break;
		case R.id.action_nightMode:
			Toast.makeText(this, "夜间模式", 0).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
