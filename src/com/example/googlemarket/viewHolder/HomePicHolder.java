package com.example.googlemarket.viewHolder;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.googlemarket.R;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.example.googlemarket.utils.BitmapHelper;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.view.InnerViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HomePicHolder extends BaseHolder<List<String>> {

	@ViewInject(R.id.item_home_picture_pager)
	InnerViewPager mViewPager;

	@ViewInject(R.id.item_home_picture_container_indicator)
	LinearLayout mLinearLayout;

	private List<String> mDatas;

	@Override
	protected View initHolderView() {

		View rootView = View.inflate(UIUtils.getContext(),
				R.layout.item_home_pictures, null);

		ViewUtils.inject(this, rootView);

		return rootView;
	}

	@Override
	protected void refreshHolderView(List<String> datas) {

		mDatas = datas;

		mViewPager.setAdapter(new HomePicAdapter());

		/**
		 * 给轮播图，加上点
		 */
		for (int i = 0; i < mDatas.size(); i++) {
			ImageView mIvPointers = new ImageView(UIUtils.getContext());
			mIvPointers.setImageResource(R.drawable.indicator_normal);
			if (i == 0) {
				mIvPointers.setImageResource(R.drawable.indicator_selected);
			}

			int width = UIUtils.dip2Px(6);
			int height = UIUtils.dip2Px(6);

			LayoutParams layoutParams = new LinearLayout.LayoutParams(width,
					height);
			layoutParams.leftMargin = UIUtils.dip2Px(6);
			layoutParams.bottomMargin = UIUtils.dip2Px(6);

			mLinearLayout.addView(mIvPointers, layoutParams);
		}

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub

				position = position % mDatas.size();

				for (int i = 0; i < mDatas.size(); i++) {
					ImageView mImageView = (ImageView) mLinearLayout
							.getChildAt(i);

					mImageView.setImageResource(R.drawable.indicator_normal);
				}

				ImageView mImageView = (ImageView) mLinearLayout
						.getChildAt(position);
				mImageView.setImageResource(R.drawable.indicator_selected);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});

		/*-----------add begin 无限轮播------------*/
		
		int diff = Integer.MAX_VALUE / 2 % mDatas.size();
		
		mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - diff);
		/*-----------add end------------*/

		
		/*-----------add begin 自动轮播------------*/
		
		final AutoScrollTask mAutoScrollTask = new AutoScrollTask();

		mAutoScrollTask.startPlay();

		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				
				case MotionEvent.ACTION_DOWN:
					mAutoScrollTask.stopPlay();
					break;
				case MotionEvent.ACTION_MOVE:

					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					mAutoScrollTask.startPlay();
					break;

				default:
					break;
				}
				return false;
			}
		});

		/*-----------add end------------*/
	}

	class AutoScrollTask implements Runnable {

		// 开始自动轮播
		public void startPlay() {
			UIUtils.getMainThreadHandler().postDelayed(this, 2000);
		}

		// 停止自动轮播
		public void stopPlay() {
			UIUtils.getMainThreadHandler().removeCallbacks(this);
		}

		@Override
		public void run() {
			
			int mCurrentItem = mViewPager.getCurrentItem();
			
			mCurrentItem++;
			
			mViewPager.setCurrentItem(mCurrentItem);

			startPlay();
		}

	}

	class HomePicAdapter extends PagerAdapter {

		@Override
		public int getCount() {

			return Integer.MAX_VALUE;// 实现无限轮滑
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			// 实现无限轮滑
			position = position % mDatas.size();

			ImageView mImageView = new ImageView(UIUtils.getContext());
			mImageView.setScaleType(ScaleType.FIT_XY);

			String urlDatas = Urls.IMAGE_URL + mDatas.get(position);
			BitmapHelper.display(mImageView, urlDatas);

			container.addView(mImageView);

			return mImageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

	}

}
