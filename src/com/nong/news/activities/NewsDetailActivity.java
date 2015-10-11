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
 * @Description: չʾ������ϸ
 * 
 * @author ũΤ��
 * @date 2015��10��10��
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
	 * @Description:�õ���һ��activity���׹�����url������ҳ
	 * 
	 */
	private void setWebWebSettingAndData() {
		try {

			// ����ҳ��ת����һ����ҳʱ����ҳĿ�����ڵ�ǰ��ҳ��ʾ
			webview_news_detail.setWebViewClient(new WebViewClient());
			// ����֧��javascript
			webview_news_detail.getSettings().setJavaScriptEnabled(true);
			// ���������ݷŴ�webview�ȿ��һ����
			webview_news_detail.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			webview_news_detail.getSettings().setLoadWithOverviewMode(true);
			Intent intent = this.getIntent();
			String url = intent.getExtras().getString("url");
			// ������ַ
			webview_news_detail.loadUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "�����쳣���������������", 0).show();
		}
	}

	/**
	 * @Description:�ؼ���ʼ��
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
	 * actionbar�ϵ�ѡ�ť�¼�����
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_share:
			Toast.makeText(this, "����", 0).show();
			break;
		case R.id.action_collect:
			Toast.makeText(this, "�ղ�", 0).show();
			break;
		case R.id.action_screenCapture:
			Toast.makeText(this, "̬�Ƚ���", 0).show();
			break;
		case R.id.action_fontSize:
			Toast.makeText(this, "�����ֺ�", 0).show();
			break;
		case R.id.action_nightMode:
			Toast.makeText(this, "ҹ��ģʽ", 0).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
