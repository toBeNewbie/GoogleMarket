package com.example.googlemarket.viewHolder;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.example.googlemarket.utils.BitmapHelper;
import com.example.googlemarket.utils.StringUtils;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-17
 * @des 提供视图
 * @des 提供接收数据
 * @des 内部让数据和视图绑定
 */
public class AppItemHolder extends BaseHolder<AppInfo> {

	@ViewInject(R.id.item_appinfo_iv_icon)
	ImageView mIvIcon;

	@ViewInject(R.id.item_appinfo_rb_stars)
	RatingBar mRbStars;

	@ViewInject(R.id.item_appinfo_tv_des)
	TextView mTvDes;

	@ViewInject(R.id.item_appinfo_tv_size)
	TextView mTvSize;

	@ViewInject(R.id.item_appinfo_tv_title)
	TextView mTvTitle;

	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(),
				R.layout.item_home_info, null);

		// 注解，找子组件
		ViewUtils.inject(this, rootView);

		return rootView;
	}

	@Override
	protected void refreshHolderView(AppInfo datas) {
 		mTvDes.setText(datas.des);
		mRbStars.setRating(datas.stars);
		mTvSize.setText(StringUtils.formatFileSize(Long.parseLong(datas.size)));
		mTvTitle.setText(datas.name);

		mIvIcon.setImageResource(R.drawable.ic_default);
		// http://localhost:8080/GooglePlayServer/image?name=app/com.itheima.www/icon.jpg

		String ivIconUrl = Urls.IMAGE_URL + datas.iconUrl;
		// BitmapUtils mBitmapUtils=new BitmapUtils(UIUtils.getContext());
		// mBitmapUtils.display(mIvIcon, ivIconUrl);
		BitmapHelper.display(mIvIcon, ivIconUrl);

	}

}
