package com.nong.news.activities;

import java.util.ArrayList;
import java.util.List;

import com.nong.news.R;
import com.nong.news.adapters.CustomFragmentPagerAdapter;
import com.nong.news.customView.SideslipView;
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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @Name: NewsListActivity
 * @Description: չʾ��������
 *
 * @author ũΤ��
 * @date 2015��10��1��
 */
public class NewsListActivity extends FragmentActivity implements OnClickListener {
	/********************************
	 * ѡ��ؼ�����,Ϊ�˴����Ż��ֽ��ؼ�ʵ��/id�ȷ���������
	 *************************/
	private RelativeLayout rl_headline, rl_society, rl_entertainment, rl_inland, rl_sport, rl_international,
			rl_technology, rl_health, rl_travel, rl_fantastic;
	private TextView tv_headline, tv_society, tv_entertainment, tv_inland, tv_sport, tv_international, tv_technology,
			tv_health, tv_travel, tv_fantastic;
	private ImageView iv_headline, iv_society, iv_entertainment, iv_inland, iv_sport, iv_international, iv_technology,
			iv_health, iv_travel, iv_fantastic;
	private RelativeLayout[] relativeLayoutArr = { rl_headline, rl_society, rl_entertainment, rl_inland, rl_sport,
			rl_international, rl_technology, rl_health, rl_travel, rl_fantastic };
	private int[] relativeLayoutIdArr = { R.id.rl_headline_id, R.id.rl_society_id, R.id.rl_entertainment_id,
			R.id.rl_inland_id, R.id.rl_sport_id, R.id.rl_international_id, R.id.rl_technology_id, R.id.rl_health_id,
			R.id.rl_travel_id, R.id.rl_fantastic_id };
	private TextView[] textViewArr = { tv_headline, tv_society, tv_entertainment, tv_inland, tv_sport, tv_international,
			tv_technology, tv_health, tv_travel, tv_fantastic };
	private int textViewIdArr[] = { R.id.tv_headline_id, R.id.tv_society_id, R.id.tv_entertainment_id,
			R.id.tv_inland_id, R.id.tv_sport_id, R.id.tv_international_id, R.id.tv_technology_id, R.id.tv_health_id,
			R.id.tv_travel_id, R.id.tv_fantastic_id };
	private ImageView[] imageViewArr = { iv_headline, iv_society, iv_entertainment, iv_inland, iv_sport,
			iv_international, iv_technology, iv_health, iv_travel, iv_fantastic };
	private int[] imageViewIdArr = { R.id.iv_headline_id, R.id.iv_society_id, R.id.iv_entertainment_id,
			R.id.iv_inland_id, R.id.iv_sport_id, R.id.iv_international_id, R.id.iv_technology_id, R.id.iv_health_id,
			R.id.iv_travel_id, R.id.iv_fantastic_id };

	ProgressBar progressBar;

	private SocietyFragment societyFragment;
	private HeadlineFragment headlineFragment;
	private EntertainmentFragment entertainmentFragment;
	private FantasticFragment fantasticFragment;
	private HealthFragment healthFragment;
	private InlandFragment inlandFragment;
	private InternationalFragment internationalFragment;
	private SportFragment sportFragment;
	private TechnologyFragment technologyFragment;
	private TravelFragment travelFragment;
	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	private FragmentManager fragmentManager;
	private ViewPager viewpager_fragmentviewpager;
	private int backPressCount = 0;
	private ListView sideMenu_listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_list);
		initView();
		setSideMenuDataandListener();
		initFragmentViewPager();
		getFragmentObject();
		addFragmentToViewPager();
		setFragmentViewPagerListenner();

	}

	/**
	 * @Description:Ϊ�໬�˵���listview���ѡ�����ݲ����õ���¼�����
	 * 
	 */
	private void setSideMenuDataandListener() {
		List<String> data = getMenuListViewData();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewsListActivity.this, R.layout.sidemenu_listview_item,
				R.id.tv_sidemenu_listview_item, data);
		sideMenu_listview.setAdapter(adapter);
		sideMenu_listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		sideMenu_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				showAskExitDialog();
			}
		});

	}

	/**
	 * ��ʾѯ���Ƿ��˳��Ի���
	 * 
	 */
	private void showAskExitDialog() {
		// ����builder
		AlertDialog.Builder builder = new AlertDialog.Builder(NewsListActivity.this);
		builder.setTitle("��ʾ"); // ����
		builder.setMessage("ȷ���˳��ó�����");
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();
			}

		});
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				NewsListActivity.this.finish();

			}
		});
		builder.create();
		builder.show();

	}

	/**
	 * ��ȡ�˵���listviewѡ������
	 * 
	 */
	private List<String> getMenuListViewData() {
		List<String> list = new ArrayList<String>();

		list.add("�˳�");
		return list;
	}

	/**
	 * ���ؼ���׽
	 */
	@Override
	public void onBackPressed() {
		backPressCount++;
		if (2 == backPressCount) {
			this.finish();
		} else {
			Toast.makeText(this, "�ٰ�һ���˳�����", 0).show();
		}
	}

	/**
	 * @Description:����fragment��������
	 */
	private void setFragmentViewPagerListenner() {
		viewpager_fragmentviewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				setTextColorAndImageViewVisible(arg0);
				viewpager_fragmentviewpager.setCurrentItem(arg0);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * @Description:ͨ������������Ƭ��ӵ�ViewPager��
	 *
	 */
	private void addFragmentToViewPager() {
		CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(fragmentManager, fragmentList);
		// ��ViewPager����������
		viewpager_fragmentviewpager.setAdapter(adapter);
		viewpager_fragmentviewpager.setCurrentItem(0, true);// ���õ�ǰ��ʾ��ǩҳΪ��һҳ
		viewpager_fragmentviewpager.setOffscreenPageLimit(9);// ���û���
	}

	/**
	 * @Description:��ȡfragment����
	 */
	private void getFragmentObject() {
		fragmentManager = getSupportFragmentManager();

		headlineFragment = new HeadlineFragment();
		societyFragment = new SocietyFragment();
		entertainmentFragment = new EntertainmentFragment();
		fantasticFragment = new FantasticFragment();
		healthFragment = new HealthFragment();
		inlandFragment = new InlandFragment();
		internationalFragment = new InternationalFragment();
		sportFragment = new SportFragment();
		technologyFragment = new TechnologyFragment();
		travelFragment = new TravelFragment();
		fragmentList.add(headlineFragment);
		fragmentList.add(societyFragment);
		fragmentList.add(entertainmentFragment);
		fragmentList.add(inlandFragment);
		fragmentList.add(sportFragment);
		fragmentList.add(internationalFragment);
		fragmentList.add(technologyFragment);
		fragmentList.add(healthFragment);
		fragmentList.add(travelFragment);
		fragmentList.add(fantasticFragment);
	}

	/**
	 * @Name:initFragmentViewPager
	 * @Description:��ʼ��FragmentViewPager
	 *
	 */
	private void initFragmentViewPager() {
		viewpager_fragmentviewpager = (ViewPager) NewsListActivity.this.findViewById(R.id.viewpager_fragmentviewpager);
	}

	/**
	 * @Name:initView
	 * @Description:��ʼ��ѡ��ؼ�
	 */
	private void initView() {
		for (int index = 0; index < relativeLayoutArr.length; index++) {
			// ��ʼ��RelativeLayout,TextView,ImageView
			relativeLayoutArr[index] = (RelativeLayout) this.findViewById(relativeLayoutIdArr[index]);
			textViewArr[index] = (TextView) this.findViewById(textViewIdArr[index]);
			imageViewArr[index] = (ImageView) this.findViewById(imageViewIdArr[index]);
			// ���ü���
			relativeLayoutArr[index].setOnClickListener(this);
		}
		/************************* ʹ���Զ�����ʵ�ֲ໬Ч�� *************************************/
		SideslipView sideslipView = (SideslipView) findViewById(R.id.sideslipView);
		View jokeShow = findViewById(R.id.newsShow);
		View menu = findViewById(R.id.sideMenu);
		sideslipView.setSideAndBaseView(jokeShow, menu);
		sideMenu_listview = (ListView) this.findViewById(R.id.sideMenu_listview);
	}

	/**
	 * @Description:��������ѡ�����¼�����
	 */
	@Override
	public void onClick(View v) {

		/****************************************** ѡ��ؼ�����¼� ******************************************************/
		switch (v.getId()) {
		/* ͷ�� */
		case R.id.rl_headline_id:
			setTextColorAndImageViewVisible(0);
			viewpager_fragmentviewpager.setCurrentItem(0);
			break;
		/* ��� */
		case R.id.rl_society_id:
			setTextColorAndImageViewVisible(1);
			viewpager_fragmentviewpager.setCurrentItem(1);

			break;
		/* ���� */
		case R.id.rl_entertainment_id:
			setTextColorAndImageViewVisible(2);
			viewpager_fragmentviewpager.setCurrentItem(2);

			break;
		/* ���� */
		case R.id.rl_inland_id:
			setTextColorAndImageViewVisible(3);
			viewpager_fragmentviewpager.setCurrentItem(3);

			break;
		/* �˶� */
		case R.id.rl_sport_id:
			setTextColorAndImageViewVisible(4);
			viewpager_fragmentviewpager.setCurrentItem(4);

			break;
		/* ���� */
		case R.id.rl_international_id:
			setTextColorAndImageViewVisible(5);
			viewpager_fragmentviewpager.setCurrentItem(5);

			break;
		/* �Ƽ� */
		case R.id.rl_technology_id:
			setTextColorAndImageViewVisible(6);
			viewpager_fragmentviewpager.setCurrentItem(6);

			break;
		/* ���� */
		case R.id.rl_health_id:
			setTextColorAndImageViewVisible(7);
			viewpager_fragmentviewpager.setCurrentItem(7);

			break;
		/* ���� */
		case R.id.rl_travel_id:
			setTextColorAndImageViewVisible(8);
			viewpager_fragmentviewpager.setCurrentItem(8);

			break;
		/* ���� */
		case R.id.rl_fantastic_id:
			setTextColorAndImageViewVisible(9);
			viewpager_fragmentviewpager.setCurrentItem(9);

			break;
		default:
			break;
		}
	}

	/**
	 * @Description:����ѡ��ؼ����ֵ���ɫ���»�����ʾ,positionΪ�����Ϊ��ɫ�������ɫ���»��߲��ɼ�
	 * @param position
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
