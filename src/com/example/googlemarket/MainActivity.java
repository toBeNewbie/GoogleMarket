package com.example.googlemarket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.astuetz.PagerSlidingTabStripExtends;
import com.example.googlemarket.factory.FragmentFactory;
import com.example.googlemarket.fragment.BaseFragment;
import com.example.googlemarket.pages.LoadingPage;
import com.example.googlemarket.utils.LogUtils;
import com.example.googlemarket.utils.UIUtils;

public class MainActivity extends FragmentActivity {

	private PagerSlidingTabStripExtends mPsts_main;
	private ViewPager mVp_itemPager;
	private MyFragmentPagerAdapter mPagerAdapter;
	private String[] mMainTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);

		
		initView();
		
		initData();
		
		initListener();
		
		initEvent();
	}

	
	
	private void initEvent() {
		// TODO Auto-generated method stub
		
	}

	private void initListener() {
		// TODO Auto-generated method stub
		mPsts_main.setOnPageChangeListener(mPageChangeListener);
		
		mVp_itemPager.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				
				mPageChangeListener.onPageSelected(0);
				
				mVp_itemPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
			}
		});
	}

	
	MyOnPageChangeListener mPageChangeListener=new MyOnPageChangeListener();
	
	class MyOnPageChangeListener implements OnPageChangeListener{
		
		@Override
		public void onPageSelected(int position) {
			// 开始触发加载数据(loadingPager)
			//此时的fragment肯定是已经创建过的fragment
			BaseFragment baseFragment = FragmentFactory.createFragment(position);
			if (baseFragment!=null) {
				LoadingPage loadingPage = baseFragment.getLoadingPage();
				if (loadingPage!=null) {
					
					loadingPage.triggerLoader();
				}
			}
		}
		
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	class MyFragmentPagerAdapter extends FragmentPagerAdapter{

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment = FragmentFactory.createFragment(position);
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			
			if (mMainTitles!=null) {
				
				return mMainTitles.length;
			}else {
				
				return 0;
			}
		}
		
		/**
		 * 需要覆盖pagerAdapter中定义的getPagerTitles方法
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return mMainTitles[position];
		}
		
	}
	
	class MyFragmentPagerStateAdapter extends FragmentStatePagerAdapter{

		public MyFragmentPagerStateAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment = FragmentFactory.createFragment(position);
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mMainTitles.length;
		}
		
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return mMainTitles[position];
		}
		
	}
	
	private void initData() {
		//初始化数据集(dataset)//数据源(datasource)
		
		mMainTitles = UIUtils.getStringArr(R.array.main_titles);
		
//		MyFragmentPagerAdapter mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		
		MyFragmentPagerStateAdapter mFragmentPagerStateAdapter = new MyFragmentPagerStateAdapter(getSupportFragmentManager());
		
		mVp_itemPager.setAdapter(mFragmentPagerStateAdapter);
	
		
		
		mPsts_main.setViewPager(mVp_itemPager);
	}

	private void initView() {
		// TODO Auto-generated method stub
		
		setContentView(R.layout.activity_main);
		
		mPsts_main = (PagerSlidingTabStripExtends) findViewById(R.id.psts_tab_slidign_main);
		
		mVp_itemPager = (ViewPager) findViewById(R.id.vp_main_activity);
	}

	
}
