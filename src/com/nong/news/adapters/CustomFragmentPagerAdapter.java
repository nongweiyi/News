/**
 * 
 */
package com.nong.news.adapters;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * @Name: CustomFragmentPagerAdapter
 * @Description:自定义fragment与viewpager适配器
 *
 * @author 农韦依
 * @date 2015年10月10日
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList;

	/**
	 * @param fm：碎片管理器
	 * @param fragmentList：碎片实例集合
	 */
	public CustomFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
		super(fragmentManager);
		this.fragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int arg0) {

		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {

		return fragmentList.size();
	}
	@Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
