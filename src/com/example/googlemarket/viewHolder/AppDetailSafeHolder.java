package com.example.googlemarket.viewHolder;

import java.util.List;

import android.R.integer;
import android.view.Gravity;
import android.view.View;
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

public class AppDetailSafeHolder extends BaseHolder<AppInfo>{


	@ViewInject(R.id.app_detail_safe_iv_arrow)
	ImageView mIvArrow;
	
	@ViewInject(R.id.app_detail_safe_pic_container)
	LinearLayout mPicContainer;
	
	@ViewInject(R.id.app_detail_safe_des_container)
	LinearLayout mDesContainer;
	
	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(), R.layout.item_detail_safe, null);
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
			BitmapHelper.display(ivPic, Urls.IMAGE_URL+appSafeBean.safeUrl);
			mPicContainer.addView(ivPic);
			
			LinearLayout linearLayout=new LinearLayout(UIUtils.getContext());
			linearLayout.setGravity(Gravity.CENTER_VERTICAL);
			int padding = UIUtils.dip2Px(4);
			linearLayout.setPadding(padding, padding, padding, padding);
			
			
			ImageView ivDesIcon=new ImageView(UIUtils.getContext());
			ivDesIcon.setMaxWidth(UIUtils.dip2Px(22));
			ivDesIcon.setMaxHeight(UIUtils.dip2Px(22));
			TextView tvDesNote=new TextView(UIUtils.getContext());
			
			String desColor = appSafeBean.safeDesColor;
			int desColorValue = Integer.parseInt(desColor);
			if (desColorValue==0) {
				//安全
				tvDesNote.setTextColor(UIUtils.getColor(R.color.app_detail_safe_normal));
			}else {
				//风险
				tvDesNote.setTextColor(UIUtils.getColor(R.color.app_detail_safe_warning));
			}
			
			BitmapHelper.display(ivDesIcon, Urls.IMAGE_URL+appSafeBean.safeDesUrl);
			tvDesNote.setText(appSafeBean.safeDes);
			
			linearLayout.addView(ivDesIcon);
			linearLayout.addView(tvDesNote);
			
			mDesContainer.addView(linearLayout);
			
		}
	}

}
