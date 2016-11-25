package com.example.googlemarket.viewHolder;

import android.view.View;

public abstract class BaseHolder<HOLDERBEANTYPE> {

	public View mViewHolder;

	private HOLDERBEANTYPE mDatas;

	public BaseHolder() {

		mViewHolder = initHolderView();
		// rootview找到一个holder，然后把它绑定到自己身上
		mViewHolder.setTag(this);

	}

	/**
	 * @des 初始化持有的视图
	 * @des 初始化视图里的孩子对象
	 * @des 必须实现，但是不知道具体实现，交给子类去实现
	 * @return
	 */
	protected abstract View initHolderView();

	/**
	 * @des 接收数据，然后数据和视图绑定
	 * @des 外部有数据、右视图，想让数据和视图绑定
	 * @param datas
	 */
	public void setDataAndRefreshHolderView(HOLDERBEANTYPE datas) {

		// 保存数据
		mDatas = datas;

		// 数据和视图绑定
		refreshHolderView(datas);

	}

	/**
	 * @des 数据和视图绑定
	 * @param datas
	 */
	protected abstract void refreshHolderView(HOLDERBEANTYPE datas);

}
