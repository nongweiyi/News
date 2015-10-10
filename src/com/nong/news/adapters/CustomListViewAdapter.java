package com.nong.news.adapters;

import java.net.URL;
import java.util.List;

import com.nong.news.R;
import com.nong.news.entities.News;
import com.nong.news.utils.LogUtil;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter {
	Context context;
	List<News> newsList;

	public CustomListViewAdapter(Context context, List<News> newsList) {
		super();
		this.context = context;
		this.newsList = newsList;
	}

	@Override
	public int getCount() {

		return newsList.size();
	}

	@Override
	public Object getItem(int position) {

		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder viewHolder;
		News news=newsList.get(position);
		if(null==convertView){
			viewHolder=new ViewHolder();
			view=View.inflate(context, R.layout.listview_item, null);
			viewHolder.tv_new_image=(ImageView) view.findViewById(R.id.new_image_id);
			viewHolder.tv_new_title=(TextView) view.findViewById(R.id.new_title_id);
			viewHolder.tv_new_description=(TextView) view.findViewById(R.id.new_description_id);
			viewHolder.tv_new_hottime=(TextView) view.findViewById(R.id.new_hottime_id);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		viewHolder.tv_new_image.setImageDrawable(news.getImageDrawable());
		viewHolder.tv_new_title.setText(news.getTitle());
		viewHolder.tv_new_description.setText(news.getDescription());
		viewHolder.tv_new_hottime.setText(news.getHottime());
		return view;
	}

	class ViewHolder {
		ImageView tv_new_image;
		TextView tv_new_title;
		TextView tv_new_description;
		TextView tv_new_hottime;
	}

}
