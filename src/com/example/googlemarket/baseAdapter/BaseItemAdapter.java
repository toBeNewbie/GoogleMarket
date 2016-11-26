package com.example.googlemarket.baseAdapter;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.googlemarket.activity.DeailAppInfoActivity;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.viewHolder.AppItemHolder;
import com.example.googlemarket.viewHolder.BaseHolder;

public class BaseItemAdapter extends SuperBaseAdapter<AppInfo> {
	
	public BaseItemAdapter(AbsListView absListView,
			List<AppInfo> dataResouce) {
		super(absListView, dataResouce);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected BaseHolder<AppInfo> getSpecialViewHolder(int position) {
		return new AppItemHolder();
	}

	@Override
	protected void onNormalItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		Intent appInfoIntent=new Intent(UIUtils.getContext(), DeailAppInfoActivity.class);
		appInfoIntent.putExtra("packageName", mDataResouce.get(position).packageName);
		appInfoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		UIUtils.getContext().startActivity(appInfoIntent);
		super.onNormalItemClick(parent, view, position, id);
	}

	


}
