package com.nong.news.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class CustomPagerAdapter extends PagerAdapter {
	View viewPagerItems[];

	public CustomPagerAdapter(View viewPagerItems[]) {
		super();
		this.viewPagerItems = viewPagerItems;

	}

	@Override
	public int getCount() {

		return viewPagerItems.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0==arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(viewPagerItems[position]);
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(viewPagerItems[position]);
		return viewPagerItems[position];
	}

}
