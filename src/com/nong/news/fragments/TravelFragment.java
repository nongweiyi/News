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
 * ����������Ƭ
 * 
 */
public class TravelFragment extends Fragment {
	private View view;// ��Ƭ�Ĳ���
	Handler handler;
	String httpUrl = "http://api.huceo.com/travel/other/";
	String httpArg = "key=75fe3d32fd12df7611b52cbebe0ffa5c&num=10";
	int REQUESTHTTP_OK = 0x1111;

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
				// �������ݳɹ�
				if (REQUESTHTTP_OK == msg.what) {

					FragmentUtil.progressBar.setVisibility(View.GONE);
					FragmentUtil.setDataToListView(TravelFragment.this.getActivity());// ��listview��������
					FragmentUtil.setImageToViewPager(TravelFragment.this.getActivity());// ��������������
					FragmentUtil.setViewPageChangeListener();// ���û���ͼƬ�л�����
				}
			}

		};
		// 2.���÷����������ϻ�ȡ����
		/*
		 * FragmentUtil.getNewsFromHttp(TravelFragment.this.getActivity(),
		 * handler, httpUrl, httpArg, Headline_REQUESTHTTP_OK);
		 * 
		 */ FragmentUtil.getTravelNewsFromHttp(TravelFragment.this.getActivity(), handler, httpUrl, httpArg,
				REQUESTHTTP_OK);
		return view;
	}

}