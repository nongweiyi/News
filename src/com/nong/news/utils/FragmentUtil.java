package com.nong.news.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.nong.news.R;
import com.nong.news.adapters.CustomListViewAdapter;
import com.nong.news.adapters.CustomPagerAdapter;
import com.nong.news.entities.News;
import com.nong.news.http.HttpRequestUtil;
import com.nong.news.http.JsonParser;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FragmentUtil {
	/****************************************** ������� ******************************************************/
	public static ViewPager viewpager_newsimage;
	public static TextView point1, point2, point3, point4, imageNewsTile;
	public static TextView points[] = { point1, point2, point3, point4 };
	public static ImageView imageflag;
	public static int pointId[] = { R.id.point1, R.id.point2, R.id.point3, R.id.point4 };
	
	/****************************************** �����б���� ******************************************************/
	public static ListView listview_newslist;
	public static ProgressBar  progressBar;
	public static List<News> newsList;
	public final static String apiKey = "75fe3d32fd12df7611b52cbebe0ffa5c";
	
	/*
	 * �������ϻ�ȡ����
	 * 
	 */
	public static void getNewsFromHttp(final Context context,final Handler handler,final String httpUrl,final String httpArg,final int msgflag) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				/*String httpUrl = "http://api.huceo.com/apple/";
				String httpArg = "key=75fe3d32fd12df7611b52cbebe0ffa5c&num=10";*/
				String jsonResult = HttpRequestUtil.request(httpUrl, httpArg);
				LogUtil.i("nongweiyi", "==jsnResullt="+jsonResult);
				newsList = new ArrayList<News>();
				if (TextUtils.isEmpty(jsonResult)) {
					LogUtil.i("nongweiyi", "jsonResult=== + �յ�==");
				}
				try {
					newsList = JsonParser.jsonParse(context,jsonResult);
					LogUtil.i("nongweiyi", "newsList.size()"+newsList.size());
					if (0 != newsList.size()) {
						Message msg = Message.obtain();
						msg.what = msgflag;
						handler.sendMessage(msg);
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "JSONJ��������");
				} catch (IOException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "IOException");
				}
			}
		}).start();

	}
	
	/*
	 * �������ϻ�ȡ����
	 * 
	 */
	public static void getTravelNewsFromHttp(final Context context,final Handler handler,final String httpUrl,final String httpArg,final int msgflag) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				String jsonResult = HttpRequestUtil.request(httpUrl, httpArg);
				LogUtil.i("nongweiyi", "==jsnResullt="+jsonResult);
				newsList = new ArrayList<News>();
				if (TextUtils.isEmpty(jsonResult)) {
					LogUtil.i("nongweiyi", "jsonResult=== + �յ�==");
				}
				try {
					newsList = JsonParser.jsonParseTravel(context,jsonResult);
					if (0 != newsList.size()) {
						Message msg = Message.obtain();
						msg.what = msgflag;
						handler.sendMessage(msg);
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "JSONJ��������");
				} catch (IOException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "IOException");
				}
			}
		}).start();

	}
	/*
	 * ��ʼ���ؼ�
	 * 
	 */
	public static void initView(View view ) {
		
		/****************************************** ���ȿؼ���ʼ�� ******************************************************/
		viewpager_newsimage = (ViewPager) view.findViewById(R.id.viewpager_newsimage_id);
		imageNewsTile = (TextView) view.findViewById(R.id.imageNewsTile);
		imageflag=(ImageView)view.findViewById(R.id.imageflag);
		
		for (int index = 0; index < points.length; index++) {
			points[index] = (TextView) view.findViewById(pointId[index]);
			if (index == 3) {
				points[index].setTextColor(Color.BLACK);// Ĭ�����õ��ĸ���Ϊ��ɫ���ҼӴ֣�����ǰ��ĵ�
				TextPaint tp = points[index].getPaint();
				tp.setFakeBoldText(true);
			}
		}

		/****************************************** ���ſؼ���ʼ�� ******************************************************/
		progressBar=(ProgressBar) view.findViewById(R.id.progressBar);
		listview_newslist = (ListView) view.findViewById(R.id.listview_newslist_id);

	}
	/*
	 * Ϊlistview�������
	 * 
	 */
	public static void setDataToListView(Context context) {
		BaseAdapter adapter = new CustomListViewAdapter(context, newsList);
		listview_newslist.setAdapter(adapter);
	}
	/*
	 * Ϊviewpager����ͼƬ
	 * 
	 */
	public static void setImageToViewPager(Context context) {
		View viewPagerItems[] = new View[4];
		viewPagerItems[0] = View.inflate(context, R.layout.viewpager_item1, null);

		viewPagerItems[1] = View.inflate(context, R.layout.viewpager_item2, null);
		viewPagerItems[2] = View.inflate(context, R.layout.viewpager_item3, null);
		viewPagerItems[3] = View.inflate(context, R.layout.viewpager_item4, null);
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
	
	/*
	 * ����viewpager������
	 * 
	 */
	public static void setViewPageChangeListener() {
		imageflag.setVisibility(View.VISIBLE);
		imageNewsTile.setText(newsList.get(0).getTitle());// Ĭ�ϱ���Ϊ��һ��
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

	/*
	 * �����ĸ�����ʾ����ɫ
	 * 
	 */
	public static void setPointsColor(int position) {
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

}
