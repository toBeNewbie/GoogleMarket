package com.example.googlemarket.viewHolder;

import java.util.List;

import android.animation.ObjectAnimator;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo.AppSafeBean;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.example.googlemarket.utils.BitmapHelper;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class AppDetailSafeHolder extends BaseHolder<AppInfo> implements
		OnClickListener {

	@ViewInject(R.id.app_detail_safe_iv_arrow)
	ImageView mIvArrow;

	@ViewInject(R.id.app_detail_safe_pic_container)
	LinearLayout mPicContainer;

	@ViewInject(R.id.app_detail_safe_des_container)
	LinearLayout mDesContainer;

	boolean ifOpen = true;// 默认打开

	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(),
				R.layout.item_detail_safe, null);
		rootView.setOnClickListener(this);
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	@Override
	protected void refreshHolderView(AppInfo datas) {

		List<AppSafeBean> safesBeans = datas.safe;
		for (int i = 0; i < safesBeans.size(); i++) {

			AppSafeBean appSafeBean = safesBeans.get(i);
			ImageView ivPic = new ImageView(UIUtils.getContext());
			ivPic.setMaxHeight(UIUtils.dip2Px(22));
			ivPic.setMaxWidth(UIUtils.dip2Px(44));
			BitmapHelper.display(ivPic, Urls.IMAGE_URL + appSafeBean.safeUrl);
			mPicContainer.addView(ivPic);

			LinearLayout linearLayout = new LinearLayout(UIUtils.getContext());
			linearLayout.setGravity(Gravity.CENTER_VERTICAL);
			int padding = UIUtils.dip2Px(4);
			linearLayout.setPadding(padding, padding, padding, padding);

			ImageView ivDesIcon = new ImageView(UIUtils.getContext());
			ivDesIcon.setMaxWidth(UIUtils.dip2Px(22));
			ivDesIcon.setMaxHeight(UIUtils.dip2Px(22));
			TextView tvDesNote = new TextView(UIUtils.getContext());

			String desColor = appSafeBean.safeDesColor;
			int desColorValue = Integer.parseInt(desColor);
			if (desColorValue == 0) {
				// 安全
				tvDesNote.setTextColor(UIUtils
						.getColor(R.color.app_detail_safe_normal));
			} else {
				// 风险
				tvDesNote.setTextColor(UIUtils
						.getColor(R.color.app_detail_safe_warning));
			}

			BitmapHelper.display(ivDesIcon, Urls.IMAGE_URL
					+ appSafeBean.safeDesUrl);
			tvDesNote.setText(appSafeBean.safeDes);

			linearLayout.addView(ivDesIcon);
			linearLayout.addView(tvDesNote);

			mDesContainer.addView(linearLayout);

		}
		
		//初次进入，无滑动动画
		triggerAnimator(false);
	}

	@Override
	public void onClick(View v) {
		triggerAnimator(true);
	}

	/**
	 * @des  触发加载动画
	 * @call safe模块点击的时候
	 * @param ifAnimator
	 */
	public void triggerAnimator(boolean ifAnimator) {
		if (ifOpen) {// 打开------>>>折叠

			mDesContainer.measure(0, 0);
			int measuredHeight = mDesContainer.getMeasuredHeight();
			int start = measuredHeight;
			int end = 0;
			
			if (ifAnimator) {//进入有动画
				doAnimator(start, end);
				
			}else {//初次进入无动画
				LayoutParams params = mDesContainer.getLayoutParams();
				params.height=0;
				mDesContainer.setLayoutParams(params);
			}

		} else {// 折叠------>>>打开
			mDesContainer.measure(0, 0);
			int measuredHeight = mDesContainer.getMeasuredHeight();
			int end = measuredHeight;
			int start = 0;

			if (ifAnimator) {//进入有动画
				doAnimator(start, end);
				
			}else {//初次进入无动画
				LayoutParams params = mDesContainer.getLayoutParams();
				params.height=0;
				mDesContainer.setLayoutParams(params);
			}
		}

		ifOpen = !ifOpen;
	}

	/**
	 * @des 开始属性动画
	 * @call 安全模块滑动
	 * @param start
	 * 
	 * @param end
	 */
	public void doAnimator(int start, int end) {
		ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
		valueAnimator.start();

		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				// TODO Auto-generated method stub
				int animatedValue = (Integer) animator.getAnimatedValue();
				LayoutParams layoutParams = mDesContainer.getLayoutParams();
				layoutParams.height = animatedValue;
				// 更新layoutParams
				mDesContainer.setLayoutParams(layoutParams);
			}

		});

		// add 箭头旋转动画
		if (ifOpen) {
			ObjectAnimator.ofFloat(mIvArrow,"Rotation" , 0,180).start();
		}else {
			ObjectAnimator.ofFloat(mIvArrow, "Rotation", 180,0).start();
		}
	}

}
