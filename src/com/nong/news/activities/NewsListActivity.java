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
 * @Description: 展示各类新闻
 *
 * @author 农韦依
 * @date 2015年10月1日
 */
public class NewsListActivity extends FragmentActivity implements OnClickListener {
	/********************************
	 * 选项控件声明,为了代码优化现将控件实例/id等放入数组中
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
	 * @Description:为侧滑菜单的listview添加选项数据并设置点击事件监听
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
	 * 显示询问是否退出对话框
	 * 
	 */
	private void showAskExitDialog() {
		// 创建builder
		AlertDialog.Builder builder = new AlertDialog.Builder(NewsListActivity.this);
		builder.setTitle("提示"); // 标题
		builder.setMessage("确定退出该程序吗？");
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();
			}

		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				NewsListActivity.this.finish();

			}
		});
		builder.create();
		builder.show();

	}

	/**
	 * 获取菜单栏listview选项数据
	 * 
	 */
	private List<String> getMenuListViewData() {
		List<String> list = new ArrayList<String>();

		list.add("退出");
		return list;
	}

	/**
	 * 返回键捕捉
	 */
	@Override
	public void onBackPressed() {
		backPressCount++;
		if (2 == backPressCount) {
			this.finish();
		} else {
			Toast.makeText(this, "再按一次退出程序", 0).show();
		}
	}

	/**
	 * @Description:设置fragment滑动监听
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
	 * @Description:通过适配器将碎片添加到ViewPager中
	 *
	 */
	private void addFragmentToViewPager() {
		CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(fragmentManager, fragmentList);
		// 给ViewPager设置适配器
		viewpager_fragmentviewpager.setAdapter(adapter);
		viewpager_fragmentviewpager.setCurrentItem(0, true);// 设置当前显示标签页为第一页
		viewpager_fragmentviewpager.setOffscreenPageLimit(9);// 设置缓存
	}

	/**
	 * @Description:获取fragment对象
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
	 * @Description:初始化FragmentViewPager
	 *
	 */
	private void initFragmentViewPager() {
		viewpager_fragmentviewpager = (ViewPager) NewsListActivity.this.findViewById(R.id.viewpager_fragmentviewpager);
	}

	/**
	 * @Name:initView
	 * @Description:初始化选项控件
	 */
	private void initView() {
		for (int index = 0; index < relativeLayoutArr.length; index++) {
			// 初始化RelativeLayout,TextView,ImageView
			relativeLayoutArr[index] = (RelativeLayout) this.findViewById(relativeLayoutIdArr[index]);
			textViewArr[index] = (TextView) this.findViewById(textViewIdArr[index]);
			imageViewArr[index] = (ImageView) this.findViewById(imageViewIdArr[index]);
			// 设置监听
			relativeLayoutArr[index].setOnClickListener(this);
		}
		/************************* 使用自定义类实现侧滑效果 *************************************/
		SideslipView sideslipView = (SideslipView) findViewById(R.id.sideslipView);
		View jokeShow = findViewById(R.id.newsShow);
		View menu = findViewById(R.id.sideMenu);
		sideslipView.setSideAndBaseView(jokeShow, menu);
		sideMenu_listview = (ListView) this.findViewById(R.id.sideMenu_listview);
	}

	/**
	 * @Description:新闻类型选项点击事件监听
	 */
	@Override
	public void onClick(View v) {

		/****************************************** 选项控件点击事件 ******************************************************/
		switch (v.getId()) {
		/* 头条 */
		case R.id.rl_headline_id:
			setTextColorAndImageViewVisible(0);
			viewpager_fragmentviewpager.setCurrentItem(0);
			break;
		/* 社会 */
		case R.id.rl_society_id:
			setTextColorAndImageViewVisible(1);
			viewpager_fragmentviewpager.setCurrentItem(1);

			break;
		/* 娱乐 */
		case R.id.rl_entertainment_id:
			setTextColorAndImageViewVisible(2);
			viewpager_fragmentviewpager.setCurrentItem(2);

			break;
		/* 国内 */
		case R.id.rl_inland_id:
			setTextColorAndImageViewVisible(3);
			viewpager_fragmentviewpager.setCurrentItem(3);

			break;
		/* 运动 */
		case R.id.rl_sport_id:
			setTextColorAndImageViewVisible(4);
			viewpager_fragmentviewpager.setCurrentItem(4);

			break;
		/* 国际 */
		case R.id.rl_international_id:
			setTextColorAndImageViewVisible(5);
			viewpager_fragmentviewpager.setCurrentItem(5);

			break;
		/* 科技 */
		case R.id.rl_technology_id:
			setTextColorAndImageViewVisible(6);
			viewpager_fragmentviewpager.setCurrentItem(6);

			break;
		/* 健康 */
		case R.id.rl_health_id:
			setTextColorAndImageViewVisible(7);
			viewpager_fragmentviewpager.setCurrentItem(7);

			break;
		/* 旅游 */
		case R.id.rl_travel_id:
			setTextColorAndImageViewVisible(8);
			viewpager_fragmentviewpager.setCurrentItem(8);

			break;
		/* 奇闻 */
		case R.id.rl_fantastic_id:
			setTextColorAndImageViewVisible(9);
			viewpager_fragmentviewpager.setCurrentItem(9);

			break;
		default:
			break;
		}
	}

	/**
	 * @Description:设置选项控件文字的颜色和下划线显示,position为点击设为红色，其余灰色，下划线不可见
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
