package com.example.googlemarket.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class InnerViewPager extends ViewPager {

	private float mDownX;
	private float mDownY;


	public InnerViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public InnerViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = ev.getRawX();
			mDownY = ev.getRawY();
			break;
		
		case MotionEvent.ACTION_MOVE:
			float moveX = ev.getRawX();
			float moveY = ev.getRawY();
			
			int dx = (int) (moveX-mDownX+.5f);
			int dy = (int) (moveY - mDownY + .5f);
			
			if (Math.abs(dx)>Math.abs(dy)) {
				//横向滑动
				getParent().requestDisallowInterceptTouchEvent(true);
			}else {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
			
			
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			
			break;
			
		

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

}
