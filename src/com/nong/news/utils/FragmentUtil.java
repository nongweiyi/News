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


/**
 * @Name: FragmentUtil
 * @Description:
 *
 * @author 农韦依
 * @date 2015年10月10日
 *
 */
public class FragmentUtil {
	

	/**
	 * @Name:getNewsFromHttp
	 * @Description:从网络上获取数据
	 *
	 * @param context:上下文       
	 * @param handler:消息处理
	 * @param httpUrl
	 * @param httpArg
	 * @param msgflag:子线程发送给主线程的消息         
	 */
	public static void getNewsFromHttp(final Context context, final Handler handler, final String httpUrl,
			final String httpArg, final int msgflag) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
				String jsonResult = HttpRequestUtil.request(httpUrl, httpArg);
				/*LogUtil.i("nongweiyi", "==jsnResullt=" + jsonResult);*/
				List<News> newsList = new ArrayList<News>();
				if (TextUtils.isEmpty(jsonResult)) {
					/*LogUtil.i("nongweiyi", "jsonResult=== + 空的==");*/
				}
				
					newsList = JsonParser.jsonParse(context, jsonResult);
					/*LogUtil.i("nongweiyi", "newsList.size()" + newsList.size());*/
					if (0 != newsList.size()) {
						Message msg = Message.obtain();
						msg.what = msgflag;
						msg.obj=newsList;
						handler.sendMessage(msg);

					}

				} catch (JSONException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "FragmentUtil==getNewsFromHttp==JSONJ解析出错");
				} catch (IOException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "FragmentUtil==getNewsFromHttp===IOException");
				}
				
			}
			
		}).start();
		
		
	}

	/**
	 * 
	 * @Name:getTravelNewsFromHttp
	 * @Description:从网络上获取旅游新闻数据，因返回的数据格式与其他新闻类型格式不同所以分开写
	 * 
	 * @param context
	 * @param handler
	 * @param httpUrl
	 * @param httpArg
	 * @param msgflag
	 */
	public static void getTravelNewsFromHttp(final Context context, final Handler handler, final String httpUrl,
			final String httpArg, final int msgflag) {

		new Thread(new Runnable() {
			List<News> newsList;
			@Override
			public void run() {
				try {
				String jsonResult = HttpRequestUtil.request(httpUrl, httpArg);
				/*LogUtil.i("nongweiyi", "==jsnResullt=" + jsonResult);*/
				newsList = new ArrayList<News>();
				if (TextUtils.isEmpty(jsonResult)) {
					LogUtil.i("nongweiyi", "jsonResult=== + 空的==");
				}
				
					newsList = JsonParser.jsonParseTravel(context, jsonResult);
					if (0 != newsList.size()) {
						Message msg = Message.obtain();
						msg.obj=newsList;
						msg.what = msgflag;
						handler.sendMessage(msg);
					}

				} catch (JSONException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "FragmentUtil==getTravelNewsFromHttp==JSONJ解析出错");
				} catch (IOException e) {
					e.printStackTrace();
					LogUtil.e("nongweiyi", "FragmentUtil==getTravelNewsFromHttp===IOException");
				}
			}
		}).start();

	}

	/**
	 * @Name:initView
	 * @Description:初始化fragment中的控件
	 *
	 * @param view
	 *            view实例
	 *//*
	public static void initView(View view) {

		*//****************************************** 画廊控件初始化 ******************************************************//*
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

		*//****************************************** 新闻控件初始化 ******************************************************//*
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
		listview_newslist = (ListView) view.findViewById(R.id.listview_newslist_id);

	}*/

	/**
	 * 
	 * @Name:setDataToListView
	 * @Description:为listview添加数据
	 *
	 * @param context
	 * @param newsList2 
	 * @param listview_newslist2 
	 *//*
	public static void setDataToListView(Context context, ListView listview_newslist2, List<News> newsList2) {
		BaseAdapter adapter = new CustomListViewAdapter(context, newsList2);
		listview_newslist2.setAdapter(adapter);
		
	}

	*//**
	 * @Name:setImageToViewPager
	 * @Description:为viewpager加载图片
	 *
	 * @param context
	 *            上下文
	 * @param viewPager 
	 *//*
	public static void setImageToViewPager(Context context, ViewPager viewPager) {
		View viewPagerItems[]= new View[4];
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
		viewPager.setAdapter(adapter);
	}

	*//**
	 * @param newsList2 
	 * @param viewPager 
	 * @param imageNewsTile2 
	 * @param imageflag2 
	 * @Name:setViewPageChangeListener
	 * @Description:设置viewpager监听器
	 *//*
	public static void setViewPageChangeListener(ImageView imageflag2, final TextView imageNewsTile2, ViewPager viewPager, final List<News> newsList2) {
		imageflag2.setVisibility(View.VISIBLE);
		imageNewsTile2.setText(newsList2.get(0).getTitle());// 默认标题为第一条
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

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
				imageNewsTile2.setText(newsList2.get(arg0).getTitle());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	*//**
	 * @Name:setPointsColor
	 * @Description:设置四个点显示的颜色
	 *
	 * @param 当前对应点的位置
	 *//*
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

	}*/

}
