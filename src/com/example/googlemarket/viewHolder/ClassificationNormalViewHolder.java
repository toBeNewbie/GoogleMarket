package com.example.googlemarket.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.ClassificationInfoBean;
import com.example.googlemarket.utils.BitmapHelper;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ClassificationNormalViewHolder extends BaseHolder<ClassificationInfoBean>{

	@ViewInject(R.id.item_category_icon_1)
	ImageView mIvIcon1;
	@ViewInject(R.id.item_category_icon_2)
	ImageView mIvIcon2;
	@ViewInject(R.id.item_category_icon_3)
	ImageView mIvIcon3;
	
	@ViewInject(R.id.item_category_item_1)
	LinearLayout mLinearLayout1;
	@ViewInject(R.id.item_category_item_2)
	LinearLayout mLinearLayout2;
	@ViewInject(R.id.item_category_item_3)
	LinearLayout mLinearLayout3;
	
	@ViewInject(R.id.item_category_name_1)
	TextView mTvName1;
	@ViewInject(R.id.item_category_name_2)
	TextView mTvName2;
	@ViewInject(R.id.item_category_name_3)
	TextView mTvName3;
	

	@Override
	protected View initHolderView() {
		
		View rootView = View.inflate(UIUtils.getContext(), R.layout.classification_item, null);
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	@Override
	protected void refreshHolderView(ClassificationInfoBean datas) {
		
		setDatas(mTvName1,mIvIcon1,datas.name1,datas.url1);
		setDatas(mTvName2,mIvIcon2,datas.name2,datas.url2);
		setDatas(mTvName3,mIvIcon3,datas.name3,datas.url3);
	}

	private void setDatas(TextView tvName, ImageView ivIcon,  final String name, String url) {
		if (!TextUtils.isEmpty(name) && TextUtils.isEmpty(url)) {
			
			tvName.setText(name);
			ivIcon.setImageResource(R.drawable.ic_default);
			BitmapHelper.display(ivIcon, url);
			ViewGroup viewParent = (ViewGroup) tvName.getParent();
			viewParent.setVisibility(View.VISIBLE);
			
			viewParent.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(UIUtils.getContext(), name,1).show();
				}
			});
		
		}else {
			
			ViewGroup viewParent = (ViewGroup) tvName.getParent();
			viewParent.setVisibility(View.INVISIBLE);
		
		}
	}

}
