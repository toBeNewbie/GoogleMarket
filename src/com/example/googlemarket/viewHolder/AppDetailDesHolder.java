package com.example.googlemarket.viewHolder;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

public class AppDetailDesHolder extends BaseHolder<AppInfo> implements
		OnClickListener {

	@ViewInject(R.id.app_detail_des_tv_des)
	TextView mTvDes;

	@ViewInject(R.id.app_detail_des_iv_arrow)
	ImageView mIvArrow;

	@ViewInject(R.id.app_detail_des_tv_author)
	TextView mTvAuthor;

	private boolean ifOpen = true;

	private int mTvMeasureHeight;

	private AppInfo mAppInfoDatas;

	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(),
				R.layout.item_detail_des, null);
		rootView.setOnClickListener(this);
		ViewUtils.inject(this, rootView);

		return rootView;
	}

	@Override
	protected void refreshHolderView(AppInfo datas) {

		mAppInfoDatas = datas;

		mTvAuthor.setText(datas.author);
		mTvDes.setText(datas.des);

		mTvDes.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						mTvMeasureHeight = mTvDes.getMeasuredHeight();
						mTvDes.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						
						ifStartAnimator(false);
					}
				});
	}

	@Override
	public void onClick(View v) {
		ifStartAnimator(true);
	}

	public void ifStartAnimator(boolean ifStartAnimator) {
		if (ifOpen) {// open----->>>close

			int start = mTvMeasureHeight;

			int end = getShortHeight(5, mAppInfoDatas.des);

			if (ifStartAnimator) {

				doStartAnimator(start, end);
			} else {
				mTvDes.setHeight(end);
			}

		} else {// close----->open
			int end = mTvMeasureHeight;

			int start = getShortHeight(5, mAppInfoDatas.des);

			if (ifStartAnimator) {

				doStartAnimator(start, end);
			} else {
				mTvDes.setHeight(end);
			}
		}

		ifOpen = !ifOpen;
	}

	public void doStartAnimator(int start, int end) {
		
		ObjectAnimator animator = ObjectAnimator.ofInt(mTvDes, "height", start,
				end);

		animator.start();

		//监听动画的执行过程
		animator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animator) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animator) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator animator) {
				//找到scrollView,进行滚动
				ViewParent parent = mTvDes.getParent();
				while (true) {

					parent = parent.getParent();
					if (parent==null) {
						break;
					}
					
					if (parent instanceof ScrollView) {
						//scrollView滑动到底部
						((ScrollView) parent).fullScroll(View.FOCUS_DOWN);
						break;
					}
				}
			}
			
			@Override
			public void onAnimationCancel(Animator animator) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//箭头翻转
		if (ifOpen) {

			ObjectAnimator.ofFloat(mIvArrow, "rotation", 180, 0).start();
		} else {
			ObjectAnimator.ofFloat(mIvArrow, "rotation", 0, 180).start();
		}
	}

	/**
	 * @des 测绘文本高度
	 * @param i
	 * @param des
	 * @return
	 */
	private int getShortHeight(int i, String des) {

		TextView temTextView = new TextView(UIUtils.getContext());
		temTextView.setText(des);

		temTextView.setLines(5);// 需要设置行高，才能拿到具体测量值
		temTextView.measure(0, 0);

		int measureHeight = temTextView.getMeasuredHeight();

		return measureHeight;
	}

}
