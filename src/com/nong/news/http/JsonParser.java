package com.nong.news.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nong.news.R;
import com.nong.news.entities.News;
import com.nong.news.utils.LogUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

public class JsonParser {
	public static List<News> jsonParse(Context context, String respons)
			throws JSONException, MalformedURLException, IOException {

		JSONObject jsonObject = new JSONObject(respons);
		int code = jsonObject.getInt("code");
		String msg = jsonObject.getString("msg");
		// 返回成功
		if (200 == code && "ok".equals(msg)) {
			JSONArray newsListArray = jsonObject.getJSONArray("newslist");
			List<News> newsList = new ArrayList<News>();
			for (int index = 0; index < newsListArray.length(); index++) {
				JSONObject newsDetailObject = newsListArray.getJSONObject(index);
				String hottime = newsDetailObject.getString("ctime");
				String title = newsDetailObject.getString("title");
				String description = newsDetailObject.getString("description");
				String picUrl = newsDetailObject.getString("picUrl");
				Drawable imageDrawable = null;
				// 如果拿到的图片为空
				if (TextUtils.isEmpty(picUrl)) {
					LogUtil.i("nongweiyi", "==========picUrl====null");
					imageDrawable = context.getResources().getDrawable(R.drawable.nullimg);
				} else {
					imageDrawable = loadImageFromNetwork(picUrl);
				}

				String url = newsDetailObject.getString("url");
				News news = new News(title, description, hottime, picUrl, url, imageDrawable);
				newsList.add(news);
			}
			return newsList;
		}
		return null;
	}

	public static List<News> jsonParseTravel(Context context, String respons)
			throws JSONException, MalformedURLException, IOException {
		JSONArray newsListArray = new JSONArray(respons);
		List<News> newsList = new ArrayList<News>();
		for (int index = 0; index < newsListArray.length(); index++) {
			JSONObject newsDetailObject = newsListArray.getJSONObject(index);
			String hottime = newsDetailObject.getString("ctime");
			String title = newsDetailObject.getString("title");
			String description = newsDetailObject.getString("description");
			String picUrl = newsDetailObject.getString("picUrl");
			String url = newsDetailObject.getString("url");
			Drawable imageDrawable = null;
		
			LogUtil.i("nongweiyi", "==========hottime===="+hottime);
			LogUtil.i("nongweiyi", "==========title===="+title);
			LogUtil.i("nongweiyi", "==========description===="+description);
			LogUtil.i("nongweiyi", "==========picUrl===="+picUrl);
			LogUtil.i("nongweiyi", "==========url===="+url);
			// 如果拿到的图片为空
			if (TextUtils.isEmpty(picUrl)) {
				LogUtil.i("nongweiyi", "==========picUrl====null");
				imageDrawable = context.getResources().getDrawable(R.drawable.nullimg);
			} else {
				imageDrawable = loadImageFromNetwork(picUrl);
			}
			
			News news = new News(title, description, hottime, picUrl, url, imageDrawable);
			newsList.add(news);
		}
		return newsList;
	}

	private static Drawable loadImageFromNetwork(String urladdr) throws MalformedURLException, IOException {
		Drawable drawable = null;
		drawable = Drawable.createFromStream(new URL(urladdr).openStream(), "image.jpg");
		if (drawable == null) {
			LogUtil.d("nongweiyi", "null drawable");
		} else {
			return drawable;
		}
		return null;
	}

}
