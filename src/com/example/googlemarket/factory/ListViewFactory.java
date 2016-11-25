package com.example.googlemarket.factory;

import com.example.googlemarket.utils.UIUtils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ListView;

/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-22
 *@des 产生listview的视图
 */
public class ListViewFactory {

	public static ListView createListView(){
		ListView mListView = new ListView(UIUtils.getContext());
		// 常规的设置
		mListView.setCacheColorHint(Color.TRANSPARENT);
		
		mListView.setFastScrollEnabled(true);
		
		//背景选择器为透明色
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		return mListView;
	}
}
