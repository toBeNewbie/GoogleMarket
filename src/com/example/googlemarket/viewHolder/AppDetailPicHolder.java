package com.example.googlemarket.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.utils.UIUtils;

public class AppDetailPicHolder extends BaseHolder<AppInfo>{

	private TextView mTextView;

	@Override
	protected View initHolderView() {
		mTextView = new TextView(UIUtils.getContext());
		return mTextView;
	}

	@Override
	protected void refreshHolderView(AppInfo datas) {
		mTextView.setText(this.getClass().getSimpleName());
		
	}

}
