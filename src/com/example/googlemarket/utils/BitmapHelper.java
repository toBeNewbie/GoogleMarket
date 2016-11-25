package com.example.googlemarket.utils;

import android.view.View;

import com.lidroid.xutils.BitmapUtils;

public class BitmapHelper {

	static BitmapUtils mBitmapUtils;
	
	static{
		mBitmapUtils=new BitmapUtils(UIUtils.getContext());
		
	}
	
	 public static <T extends View> void display(T container, String uri) {
	        mBitmapUtils.display(container, uri);
	    }
	
}
