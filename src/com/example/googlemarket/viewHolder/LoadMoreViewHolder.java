package com.example.googlemarket.viewHolder;

import com.example.googlemarket.R;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.widget.LinearLayout;

public class LoadMoreViewHolder extends BaseHolder<Integer> {

	@ViewInject(R.id.item_loadmore_container_loading)
	LinearLayout			mContainerLoading;

	@ViewInject(R.id.item_loadmore_container_retry)
	LinearLayout			mContainerRetry;

	
	/**
	 loadmore视图有几种显示情况
		1.显示加载
		2.显示重试
		3.都不显示(没有更多)
	 */
	public static final int	LOADMORE_LOADING	= 0;
	public static final int	LOADMORE_RETRY		= 1;
	public static final int	LOADMORE_NONE		= 2;
	
	
	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(), R.layout.item_load_more, null);
		
		//注解，获得子组件
		ViewUtils.inject(this, rootView);
		
		return rootView;
	}

	@Override
	protected void refreshHolderView(Integer curState) {
		// 隐藏所有
		mContainerLoading.setVisibility(8);
		mContainerRetry.setVisibility(8);
		
		switch (curState) {
		case LOADMORE_LOADING:// 显示加载视图
			mContainerLoading.setVisibility(0);
			break;
		case LOADMORE_RETRY:// 显示重试视图
			mContainerRetry.setVisibility(0);
			break;
		case LOADMORE_NONE:// 啥也不显示

			break;

		default:
			break;
		}

	}
	
	//// hoder中数据的作用-->决定视图的展示

}
