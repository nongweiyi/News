package com.nong.news.fragments;

import com.nong.news.R;
import com.nong.news.utils.FragmentUtil;
import com.nong.news.utils.LogUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * 健康头条新闻碎片
 * 
 */
public class HealthFragment extends Fragment {
	private View view;// 碎片的布局
	Handler handler;
	String httpUrl = "http://api.huceo.com/health/other/";
	String httpArg = "key=75fe3d32fd12df7611b52cbebe0ffa5c&num=10";
	int Headline_REQUESTHTTP_OK=0x1111;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.allfragment_news_list, container, false);
		// 1.调用方法初始化控件
		FragmentUtil.initView(view);

		// 消息接收机制
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				//请求数据成功
				if(Headline_REQUESTHTTP_OK==msg.what){
					LogUtil.i("nongweiyi", "HeadlineFragment====REQUESTHTTP_OK==");
					FragmentUtil.progressBar.setVisibility(View.GONE);
					FragmentUtil.setDataToListView(HealthFragment.this.getActivity());//给listview设置数据
					FragmentUtil.setImageToViewPager(HealthFragment.this.getActivity());//给画廊设置数据
					FragmentUtil.setViewPageChangeListener();//设置画廊图片切换监听
				}
			}

		};
		// 2.调用方法从网络上获取数据
		FragmentUtil.getNewsFromHttp(HealthFragment.this.getActivity(),handler, httpUrl, httpArg,Headline_REQUESTHTTP_OK);
		return view;
	}

}
