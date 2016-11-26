package com.example.googlemarket.viewHolder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.googlemarket.bean.ClassificationInfoBean;
import com.example.googlemarket.utils.UIUtils;

public class ClassificationTitleViewHolder extends BaseHolder<ClassificationInfoBean>{

	private TextView mTextView;

	@Override
	protected View initHolderView() {
		
		mTextView = new TextView(UIUtils.getContext());
		
		int padding = UIUtils.dip2Px(5);
		mTextView.setPadding(padding, padding, padding, padding);
		mTextView.setBackgroundColor(Color.WHITE);
		return mTextView;
	}

	@Override
	protected void refreshHolderView(ClassificationInfoBean datas) {
		// TODO Auto-generated method stub
		mTextView.setText(datas.title);
	}

}
