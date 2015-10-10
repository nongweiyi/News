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
 * ����ͷ��������Ƭ
 * 
 */
public class HealthFragment extends Fragment {
	private View view;// ��Ƭ�Ĳ���
	Handler handler;
	String httpUrl = "http://api.huceo.com/health/other/";
	String httpArg = "key=75fe3d32fd12df7611b52cbebe0ffa5c&num=10";
	int Headline_REQUESTHTTP_OK=0x1111;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.allfragment_news_list, container, false);
		// 1.���÷�����ʼ���ؼ�
		FragmentUtil.initView(view);

		// ��Ϣ���ջ���
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				//�������ݳɹ�
				if(Headline_REQUESTHTTP_OK==msg.what){
					LogUtil.i("nongweiyi", "HeadlineFragment====REQUESTHTTP_OK==");
					FragmentUtil.progressBar.setVisibility(View.GONE);
					FragmentUtil.setDataToListView(HealthFragment.this.getActivity());//��listview��������
					FragmentUtil.setImageToViewPager(HealthFragment.this.getActivity());//��������������
					FragmentUtil.setViewPageChangeListener();//���û���ͼƬ�л�����
				}
			}

		};
		// 2.���÷����������ϻ�ȡ����
		FragmentUtil.getNewsFromHttp(HealthFragment.this.getActivity(),handler, httpUrl, httpArg,Headline_REQUESTHTTP_OK);
		return view;
	}

}
