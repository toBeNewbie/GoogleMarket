package com.example.googlemarket.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.example.googlemarket.utils.BitmapHelper;
import com.example.googlemarket.utils.StringUtils;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AppDetailInfosHolder extends BaseHolder<AppInfo>{

	private TextView mTextView;

	@ViewInject(R.id.app_detail_info_iv_icon)
	ImageView mIvIcon;
	
	@ViewInject(R.id.app_detail_info_rb_star)
	RatingBar mRbStars;
	
	@ViewInject(R.id.app_detail_info_tv_name)
	TextView mTvName;
	
	@ViewInject(R.id.app_detail_info_tv_time)
	TextView mTvTime;
	
	@ViewInject(R.id.app_detail_info_tv_version)
	TextView mTvVersion;
	@ViewInject(R.id.app_detail_info_tv_downloadnum)
	TextView mTvDownloadNum;
	
	@ViewInject(R.id.app_detail_info_tv_size)
	TextView mTvSize;
	
	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(), R.layout.item_detail_info, null);
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	@Override
	protected void refreshHolderView(AppInfo datas) {
		
		String downloadNum = UIUtils.getString(R.string.detail_downloadnum, datas.downloadNum);
		String date = UIUtils.getString(R.string.detail_date, datas.date);
		String size = UIUtils.getString(R.string.detail_size, StringUtils.formatFileSize(Long.parseLong(datas.size)));
		String version = UIUtils.getString(R.string.detail_version, datas.version);
		
		mTvDownloadNum.setText(downloadNum);
		mTvTime.setText(date);
		mTvSize.setText(size);
		mTvVersion.setText(version);
		
		
		mRbStars.setRating(datas.stars);
		mIvIcon.setBackgroundResource(R.drawable.ic_default);
		String uri=Urls.IMAGE_URL+datas.iconUrl;
		BitmapHelper.display(mIvIcon, uri);
		
		
	}

}
