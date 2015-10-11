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
 * @Description:�Զ���fragment��viewpager������
 *
 * @author ũΤ��
 * @date 2015��10��10��
 */
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList;

	/**
	 * @param fm����Ƭ������
	 * @param fragmentList����Ƭʵ������
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
