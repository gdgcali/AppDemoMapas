package com.demo.encuestahotel.fragments;

import com.demo.encuestahotel.R;
import com.demo.encuestahotel.data.CustomPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

@TargetApi(14)
public class MainFragment extends Fragment implements ViewPager.OnPageChangeListener, ActionBar.TabListener {

	private ViewPager viewPager;
	private CustomPagerAdapter adapter;	
	
	@Override
	public View onCreateView(LayoutInflater infalter, ViewGroup group,
			Bundle svaedInstanceState) {
		View view = infalter.inflate(R.layout.fragment_main, null);
		viewPager = (ViewPager)view.findViewById(R.id.pager);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);		
		
		adapter = new CustomPagerAdapter(getActivity().getSupportFragmentManager());		
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		
		ActionBar bar = getActivity().getActionBar();
		bar.removeAllTabs();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.addTab(bar.newTab().setText("Lista").setTabListener(this));
		bar.addTab(bar.newTab().setText("Grid").setTabListener(this));
		bar.addTab(bar.newTab().setText("Mapa").setTabListener(this));
	}	
	

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	@Override	
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		Log.e("Debug", String.valueOf(arg0));
		getActivity().getActionBar().setSelectedNavigationItem(arg0);
		
	}

}
