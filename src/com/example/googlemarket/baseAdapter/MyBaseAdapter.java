package com.example.googlemarket.baseAdapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<ITEMBEANTYPE> extends BaseAdapter {

	private List<ITEMBEANTYPE> mDataResouce=new ArrayList<ITEMBEANTYPE>();
	
	
	
	public MyBaseAdapter(List<ITEMBEANTYPE> dataResouce) {
		super();
		mDataResouce = dataResouce;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDataResouce.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDataResouce.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if (mDataResouce!=null) {
			return mDataResouce.size();
		}else {
			
			return 0;
		}
	}

	

}
