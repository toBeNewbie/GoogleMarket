package com.example.googlemarket.viewHolder;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.ProjectBean;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.example.googlemarket.utils.BitmapHelper;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProjectHolder extends BaseHolder<ProjectBean> {

	@ViewInject(R.id.item_subject_iv_icon)
	ImageView mIvIcon;
	
	@ViewInject(R.id.item_subject_tv_title)
	TextView mTvTitle;
	
	@Override
	protected View initHolderView() {
		View rootView = View.inflate(UIUtils.getContext(), R.layout.item_project_view, null);
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	@Override
	protected void refreshHolderView(ProjectBean datas) {
		mTvTitle.setText(datas.des);
		String projectIconUrl = Urls.IMAGE_URL+datas.url;
		BitmapHelper.display(mIvIcon, projectIconUrl);
	}

}
