package com.example.googlemarket.viewHolder;

import java.util.List;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.example.googlemarket.utils.BitmapHelper;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.view.RatioFramelayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AppDetailPicHolder extends BaseHolder<AppInfo>{


	@ViewInject(R.id.app_detail_pic_iv_container)
	LinearLayout mIvPicContainer;
	
	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(), R.layout.item_detail_pic, null);
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	@Override
	protected void refreshHolderView(AppInfo datas) {
		List<String> screen = datas.screen;
		for (int i = 0; i < screen.size(); i++) {
			String picUrl = screen.get(i);
			
			/*-----------add begin 进行屏幕适配------------*/
			
			RatioFramelayout mRatiolayout=new RatioFramelayout(UIUtils.getContext());
			mRatiolayout.setPicRatio((float)150 / 250);
			mRatiolayout.setRelative(RatioFramelayout.RELATIVE_WIDTH);
			
			/*-----------add end------------*/
			
			ImageView mImageView = new ImageView(UIUtils.getContext());
			String uri=Urls.IMAGE_URL+picUrl;
			BitmapHelper.display(mImageView, uri);
			//添加到自定义layout中
			mRatiolayout.addView(mImageView);

			//得到屏幕的宽度
			int screenWidth = UIUtils.getResources().getDisplayMetrics().widthPixels-UIUtils.dip2Px(22);
			
			
			int width=screenWidth / 3;
			int height=LayoutParams.WRAP_CONTENT;
			LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
			
			if (i!=0) {
				layoutParams.leftMargin=UIUtils.dip2Px(5);
			}
			
			
			mIvPicContainer.addView(mRatiolayout,layoutParams);
		}
	}

}
