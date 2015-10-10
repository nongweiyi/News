package com.nong.news.activities;

import java.util.List;

import com.nong.news.R;
import com.nong.news.entities.News;
import com.nong.news.fragments.EntertainmentFragment;
import com.nong.news.fragments.FantasticFragment;
import com.nong.news.fragments.HeadlineFragment;
import com.nong.news.fragments.HealthFragment;
import com.nong.news.fragments.InlandFragment;
import com.nong.news.fragments.InternationalFragment;
import com.nong.news.fragments.SocietyFragment;
import com.nong.news.fragments.SportFragment;
import com.nong.news.fragments.TechnologyFragment;
import com.nong.news.fragments.TravelFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewsListActivity extends Activity implements OnClickListener {
	/********************************
	 * ѡ��ؼ�����,Ϊ�˴����Ż��ֽ��ؼ�ʵ��/id�ȷ���������
	 *************************/
	RelativeLayout rl_headline, rl_society, rl_entertainment, rl_inland, rl_sport, rl_international, rl_technology,
			rl_health, rl_travel, rl_fantastic;
	TextView tv_headline, tv_society, tv_entertainment, tv_inland, tv_sport, tv_international, tv_technology, tv_health,
			tv_travel, tv_fantastic;
	ImageView iv_headline, iv_society, iv_entertainment, iv_inland, iv_sport, iv_international, iv_technology,
			iv_health, iv_travel, iv_fantastic;
	RelativeLayout[] relativeLayoutArr = { rl_headline, rl_society, rl_entertainment, rl_inland, rl_sport,
			rl_international, rl_technology, rl_health, rl_travel, rl_fantastic };
	int[] relativeLayoutIdArr = { R.id.rl_headline_id, R.id.rl_society_id, R.id.rl_entertainment_id, R.id.rl_inland_id,
			R.id.rl_sport_id, R.id.rl_international_id, R.id.rl_technology_id, R.id.rl_health_id, R.id.rl_travel_id,
			R.id.rl_fantastic_id };
	TextView[] textViewArr = { tv_headline, tv_society, tv_entertainment, tv_inland, tv_sport, tv_international,
			tv_technology, tv_health, tv_travel, tv_fantastic };
	int textViewIdArr[] = { R.id.tv_headline_id, R.id.tv_society_id, R.id.tv_entertainment_id, R.id.tv_inland_id,
			R.id.tv_sport_id, R.id.tv_international_id, R.id.tv_technology_id, R.id.tv_health_id, R.id.tv_travel_id,
			R.id.tv_fantastic_id };
	ImageView[] imageViewArr = { iv_headline, iv_society, iv_entertainment, iv_inland, iv_sport, iv_international,
			iv_technology, iv_health, iv_travel, iv_fantastic };
	int[] imageViewIdArr = { R.id.iv_headline_id, R.id.iv_society_id, R.id.iv_entertainment_id, R.id.iv_inland_id,
			R.id.iv_sport_id, R.id.iv_international_id, R.id.iv_technology_id, R.id.iv_health_id, R.id.iv_travel_id,
			R.id.iv_fantastic_id };
	/****************************************** ������� ******************************************************/
	ViewPager viewpager_newsimage;
	TextView point1, point2, point3, point4, imageNewsTile;
	TextView points[] = { point1, point2, point3, point4 };
	ImageView imageflag;
	int pointId[] = { R.id.point1, R.id.point2, R.id.point3, R.id.point4 };
	ProgressBar  progressBar;
	/****************************************** �����б���� ******************************************************/
	ListView listview_newslist;
	List<News> newsList;
	Handler handler;
	private final String apiKey = "75fe3d32fd12df7611b52cbebe0ffa5c";
	private final int REQUESTHTTP_OK = 0x1111;
	private SocietyFragment societyFragment;
	private HeadlineFragment headlineFragment;
	EntertainmentFragment entertainmentFragment;
	FantasticFragment fantasticFragment;
	HealthFragment healthFragment;
	InlandFragment inlandFragment;
	InternationalFragment InternationalFragment;
	SportFragment sportFragment;
	TechnologyFragment technologyFragment;
	TravelFragment travelFragment;
	
	FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_list);
		initView();
		headlineFragment=new HeadlineFragment();
		societyFragment=new SocietyFragment();
		entertainmentFragment=new EntertainmentFragment();
		fantasticFragment=new FantasticFragment();
		healthFragment=new HealthFragment();
		inlandFragment=new InlandFragment();
		InternationalFragment=new InternationalFragment();
		sportFragment=new SportFragment();
		technologyFragment=new TechnologyFragment();
		travelFragment=new TravelFragment();
		
		fragmentManager=getFragmentManager();
		FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_layout, headlineFragment);
		fragmentTransaction.commit();	
	}


	/*
	 * ��ʼ���ؼ�
	 * 
	 */
	private void initView() {
		/****************************************** ѡ��ؼ���ʼ�� ******************************************************/
		for (int index = 0; index < relativeLayoutArr.length; index++) {
			// ��ʼ��RelativeLayout,TextView,ImageView
			relativeLayoutArr[index] = (RelativeLayout) this.findViewById(relativeLayoutIdArr[index]);
			textViewArr[index] = (TextView) this.findViewById(textViewIdArr[index]);
			imageViewArr[index] = (ImageView) this.findViewById(imageViewIdArr[index]);
			// ���ü���
			relativeLayoutArr[index].setOnClickListener(this);
		}
		
	}

	/*
	 * ����¼�����
	 * 
	 */
	@Override
	public void onClick(View v) {
		
		FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
		/****************************************** ѡ��ؼ�����¼� ******************************************************/
		switch (v.getId()) {
		/* ͷ�� */
		case R.id.rl_headline_id:
			setTextColorAndImageViewVisible(0);
			fragmentTransaction.replace(R.id.fragment_layout,headlineFragment);
			break;
		/* ��� */
		case R.id.rl_society_id:
			setTextColorAndImageViewVisible(1);
			fragmentTransaction.replace(R.id.fragment_layout, societyFragment);
			
			break;
		/* ���� */
		case R.id.rl_entertainment_id:
			setTextColorAndImageViewVisible(2);
			fragmentTransaction.replace(R.id.fragment_layout, entertainmentFragment);
			break;
		/* ���� */
		case R.id.rl_inland_id:
			setTextColorAndImageViewVisible(3);
			fragmentTransaction.replace(R.id.fragment_layout, inlandFragment);
			break;
		/* �˶� */
		case R.id.rl_sport_id:
			setTextColorAndImageViewVisible(4);
			fragmentTransaction.replace(R.id.fragment_layout, sportFragment);
			break;
		/* ���� */
		case R.id.rl_international_id:
			setTextColorAndImageViewVisible(5);
			fragmentTransaction.replace(R.id.fragment_layout, inlandFragment);
			break;
		/* �Ƽ� */
		case R.id.rl_technology_id:
			setTextColorAndImageViewVisible(6);
			fragmentTransaction.replace(R.id.fragment_layout, technologyFragment);
			break;
		/* ���� */
		case R.id.rl_health_id:
			setTextColorAndImageViewVisible(7);
			fragmentTransaction.replace(R.id.fragment_layout, healthFragment);
			break;
		/* ���� */
		case R.id.rl_travel_id:
			setTextColorAndImageViewVisible(8);
			fragmentTransaction.replace(R.id.fragment_layout, travelFragment);
			break;
		/* ���� */
		case R.id.rl_fantastic_id:
			setTextColorAndImageViewVisible(9);
			fragmentTransaction.replace(R.id.fragment_layout, fantasticFragment);
			break;
		default:
			break;
		}
		fragmentTransaction.commit();
	}

	/*
	 * ����ѡ��ؼ����ֵ���ɫ���»�����ʾ,positionΪ�����Ϊ��ɫ�������ɫ���»��߲��ɼ�
	 */
	private void setTextColorAndImageViewVisible(int position) {
		for (int index = 0; index < textViewArr.length; index++) {
			if (index == position) {
				textViewArr[index].setTextColor(Color.RED);
				imageViewArr[index].setVisibility(View.VISIBLE);
			} else {
				textViewArr[index].setTextColor(Color.GRAY);
				imageViewArr[index].setVisibility(View.GONE);
			}
		}

	}

}
