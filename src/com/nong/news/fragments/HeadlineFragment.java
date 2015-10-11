package com.nong.news.fragments;

import java.util.List;

import com.nong.news.R;
import com.nong.news.activities.NewsDetailActivity;
import com.nong.news.adapters.CustomListViewAdapter;
import com.nong.news.adapters.CustomPagerAdapter;
import com.nong.news.entities.News;
import com.nong.news.utils.FragmentUtil;
import com.nong.news.utils.LogUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 头条新闻碎片
 * 
 */
public class HeadlineFragment extends Fragment {
	/****************************************** 画廊相关 ******************************************************/
	private ViewPager viewpager_newsimage;
	private TextView point1, point2, point3, point4, imageNewsTile;
	private TextView points[] = { point1, point2, point3, point4 };
	private ImageView imageflag;
	private int pointId[] = { R.id.point1, R.id.point2, R.id.point3, R.id.point4 };

	/****************************************** 新闻列表相关 ******************************************************/
	private ListView listview_newslist;
	private ProgressBar progressBar;
	private List<News> newsList;

	private View view;// 碎片的布局
	private Handler handler;
	private String httpUrl = "http://api.huceo.com/apple/";
	private String httpArg = "key=75fe3d32fd12df7611b52cbebe0ffa5c&num=10";
	int Headline_REQUESTHTTP_OK = 0x1111;
	View viewPagerItems[];

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.allfragment_news_list, container, false);
		initView();

		// 消息接收机制
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				// 请求数据成功
				if (Headline_REQUESTHTTP_OK == msg.what) {
					/*LogUtil.i("nongweiyi", "=========Headline_REQUESTHTTP_OK===========");*/
					progressBar.setVisibility(View.GONE);
					newsList = (List<News>) msg.obj;
					if (newsList != null) {
						setDataToListView();
						setImageToViewPager();
						setViewPageChangeListener();
						setViewPagerItemsClickListener();
						setListViewItemClickListener();
					} else {
						Toast.makeText(getActivity(), "头条==当前数据为空", 0).show();
					}
				}
			}

		};
		// 2.调用方法从网络上获取数据
		FragmentUtil.getNewsFromHttp(HeadlineFragment.this.getActivity(), handler, httpUrl, httpArg,
				Headline_REQUESTHTTP_OK);
		return view;
	}

	private void setViewPagerItemsClickListener() {
		viewPagerItems[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startNewsDetailActivity(0);
			}
		});
		viewPagerItems[1].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startNewsDetailActivity(1);
			}
		});
		viewPagerItems[2].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startNewsDetailActivity(2);
			}
		});
		viewPagerItems[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startNewsDetailActivity(3);
			}
		});

	}
/**
 * @Description:跳转至新闻详细activity并传递url参数
 * 
 * @param i
 */
	private void startNewsDetailActivity(int i) {
		News news = newsList.get(i);
		Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
		intent.putExtra("url", news.getNewUrl());
		startActivity(intent);

	}

	/**
	 * @Description:新闻列表条目点击事件监听
	 *
	 */
	private void setListViewItemClickListener() {
		listview_newslist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				startNewsDetailActivity(arg2);

			}
		});

	}

	/**
	 * 
	 * @Name:setDataToListView
	 * @Description:为listview添加数据
	 */
	private void setDataToListView() {
		BaseAdapter adapter = new CustomListViewAdapter(HeadlineFragment.this.getActivity(), newsList);
		listview_newslist.setAdapter(adapter);

	}

	/**
	 * @Name:setImageToViewPager
	 * @Description:为viewpager加载图片
	 *
	 * @param context
	 *            上下文
	 * @param viewPager
	 */
	private void setImageToViewPager() {
		viewPagerItems = new View[4];
		viewPagerItems[0] = View.inflate(getActivity(), R.layout.viewpager_item1, null);

		viewPagerItems[1] = View.inflate(getActivity(), R.layout.viewpager_item2, null);
		viewPagerItems[2] = View.inflate(getActivity(), R.layout.viewpager_item3, null);
		viewPagerItems[3] = View.inflate(getActivity(), R.layout.viewpager_item4, null);
		ImageView imageview1 = (ImageView) viewPagerItems[0].findViewById(R.id.iv_viewpager_item1);
		ImageView imageview2 = (ImageView) viewPagerItems[1].findViewById(R.id.iv_viewpager_item2);
		ImageView imageview3 = (ImageView) viewPagerItems[2].findViewById(R.id.iv_viewpager_item3);
		ImageView imageview4 = (ImageView) viewPagerItems[3].findViewById(R.id.iv_viewpager_item4);
		imageview1.setImageDrawable(newsList.get(0).getImageDrawable());
		imageview2.setImageDrawable(newsList.get(1).getImageDrawable());
		imageview3.setImageDrawable(newsList.get(2).getImageDrawable());
		imageview4.setImageDrawable(newsList.get(3).getImageDrawable());
		PagerAdapter adapter = new CustomPagerAdapter(viewPagerItems);
		viewpager_newsimage.setAdapter(adapter);
	}

	/**
	 * @Description:设置viewpager监听器
	 */
	private void setViewPageChangeListener() {
		imageflag.setVisibility(View.VISIBLE);
		imageNewsTile.setText(newsList.get(0).getTitle());// 默认标题为第一条
		viewpager_newsimage.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					setPointsColor(3);
					break;
				case 1:
					setPointsColor(2);
					break;
				case 2:
					setPointsColor(1);
					break;
				case 3:
					setPointsColor(0);
					break;

				default:
					break;
				}
				imageNewsTile.setText(newsList.get(arg0).getTitle());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	/**
	 * @Name:setPointsColor
	 * @Description:设置四个点显示的颜色
	 *
	 * @param 当前对应点的位置
	 */
	private void setPointsColor(int position) {
		for (int index = 0; index < points.length; index++) {
			if (index == position) {

				points[index].setTextColor(Color.BLACK);
				TextPaint tp = points[index].getPaint();
				tp.setFakeBoldText(true);
			} else {
				points[index].setTextColor(Color.GRAY);
			}
		}

	}

	/**
	 * @Description:控件初始化
	 * 
	 */
	private void initView() {
		/****************************************** 画廊控件初始化 ******************************************************/
		viewpager_newsimage = (ViewPager) view.findViewById(R.id.viewpager_newsimage_id);
		imageNewsTile = (TextView) view.findViewById(R.id.imageNewsTile);
		imageflag = (ImageView) view.findViewById(R.id.imageflag);

		for (int index = 0; index < points.length; index++) {
			points[index] = (TextView) view.findViewById(pointId[index]);
			if (index == 3) {
				points[index].setTextColor(Color.BLACK);// 默认设置第四个点为黑色，且加粗，即最前面的点
				TextPaint tp = points[index].getPaint();
				tp.setFakeBoldText(true);
			}
		}

		/****************************************** 新闻控件初始化 ******************************************************/
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
		listview_newslist = (ListView) view.findViewById(R.id.listview_newslist_id);
		listview_newslist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

}
