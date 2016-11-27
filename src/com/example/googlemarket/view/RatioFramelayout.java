package com.example.googlemarket.view;

import com.example.googlemarket.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RatioFramelayout extends FrameLayout {

	//已知宽度，动态计算高度
	public static final int RELATIVE_WIDTH = 0;
	
	//已知高度，动态计算宽度
	public static final int RELATIVE_HEIGTH = 1;
	
	public int relative = RELATIVE_WIDTH;//默认为宽度已知，动态计算高度
	
	private float mPicRatio=0.0f;
	
	public RatioFramelayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioFramelayout);
		
		mPicRatio = mTypedArray.getFloat(R.styleable.RatioFramelayout_pic_ratio, 0.0f);

		relative = mTypedArray.getInt(R.styleable.RatioFramelayout_relative, RELATIVE_WIDTH);
	}

	public void setRelative(int relative) {
		this.relative = relative;
	}

	public void setPicRatio(float picRatio) {
		mPicRatio = picRatio;
	}

	public RatioFramelayout(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
		
		if (modeWidth == MeasureSpec.EXACTLY && relative == RELATIVE_WIDTH) {//宽度已知情况
			
			//获取自身的高度
			int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
			int parentHeight = (int) (parentWidth/mPicRatio+0.5);
			
			//子控件的宽高
			int childWidth = parentWidth-getPaddingLeft()-getPaddingRight();
			int childHeight = parentHeight-getPaddingBottom()-getPaddingTop();
			
			//保存测量结果
			setMeasuredDimension(parentWidth, parentHeight);
			
			int childWidthMeasureSpec=MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
			int childHeightMeasureSpec=MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
			
			//告知子控件测绘自己，请求子控件测绘自己
			measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
			
			
		}else if (modeHeight == MeasureSpec.EXACTLY && relative == RELATIVE_HEIGTH) {
		
			int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
			int parentWidth = (int) (parentHeight*mPicRatio+.5f);
			
			int childWidth = parentWidth-getPaddingLeft()-getPaddingRight();
			int childHeight = parentHeight-getPaddingBottom()-getPaddingTop();
			
			setMeasuredDimension(parentWidth, parentHeight);
			
			int childWidthMeasureSpec=MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
			int childHeightMeasureSpec=MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
			
			measureChildren(childWidthMeasureSpec, childHeightMeasureSpec);
			
		}else{
			
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
		
		
	}

}
