package com.example.googlemarket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.googlemarket.R;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.pages.LoadingPage;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.DetailActivityProtocol;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.viewHolder.AppDetailBottomHolder;
import com.example.googlemarket.viewHolder.AppDetailDesHolder;
import com.example.googlemarket.viewHolder.AppDetailInfosHolder;
import com.example.googlemarket.viewHolder.AppDetailPicHolder;
import com.example.googlemarket.viewHolder.AppDetailSafeHolder;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class DeailAppInfoActivity extends Activity {
	private String mPackageName;
	private LoadingPage mLoadingPage;
	private AppInfo mAppInfos;

	
	@ViewInject(R.id.detail_fl_bottom)
	FrameLayout mContinerBottom;
	
	@ViewInject(R.id.detail_fl_des)
	FrameLayout mContinerDes;
	
	@ViewInject(R.id.detail_fl_info)
	FrameLayout mContinerInfos;
	
	@ViewInject(R.id.detail_fl_pic)
	FrameLayout mContinerPic;

	@ViewInject(R.id.detail_fl_safe)
	FrameLayout mContinerSafe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		init();

		initView();

		initData();

		initEvent();
		super.onCreate(savedInstanceState);
	}

	private void init() {
		
		mPackageName = getIntent().getStringExtra("packageName");

	}

	private void initView() {
		mLoadingPage = new LoadingPage(UIUtils.getContext()) {

			/**
			 * @des 开始加载数据了,在子线程中执行，外界调用了triggerLoader()方法
			 * @call 外界需要触发加载数据时调用此方法
			 * @return 状态值
			 */
			@Override
			protected LoadResultState initData() {
				// TODO Auto-generated method stub
				return DeailAppInfoActivity.this.loadToData();
			}

			/**
			 * @des 成功创建视图,外界调用了triggerLoader()方法
			 * @call 触发加载数据，数据加载完成后，得到数据加载结果，重新刷新UI,数据加载成功了，而且成功视图还没有
			 * @return
			 */
			@Override
			protected View initSuccessView() {
				// TODO Auto-generated method stub
				return DeailAppInfoActivity.this.initSuccessView();
			}

		};

		setContentView(mLoadingPage);

	}

	protected View initSuccessView() {
		View rootView = View.inflate(UIUtils.getContext(), R.layout.item_detail, null);
		ViewUtils.inject(this, rootView);
		
		//应用信息
		AppDetailInfosHolder mDetailInfosHolder=new AppDetailInfosHolder();
		mContinerInfos.addView(mDetailInfosHolder.mViewHolder);
		mDetailInfosHolder.setDataAndRefreshHolderView(mAppInfos);
		
		//应用安全
		AppDetailSafeHolder mSafeHolder=new AppDetailSafeHolder();
		mContinerSafe.addView(mSafeHolder.mViewHolder);
		mSafeHolder.setDataAndRefreshHolderView(mAppInfos);
		
		//应用描述
		AppDetailDesHolder mDetailDesHolder=new AppDetailDesHolder();
		mContinerDes.addView(mDetailDesHolder.mViewHolder);
		mDetailDesHolder.setDataAndRefreshHolderView(mAppInfos);
		
		//应用截图
		AppDetailPicHolder mDetailPicHolder=new AppDetailPicHolder();
		mContinerPic.addView(mDetailPicHolder.mViewHolder);
		mDetailPicHolder.setDataAndRefreshHolderView(mAppInfos);
		
		//下载
		AppDetailBottomHolder mDetailBottomHolder=new AppDetailBottomHolder();
		mContinerBottom.addView(mDetailBottomHolder.mViewHolder);
		mDetailBottomHolder.setDataAndRefreshHolderView(mAppInfos);
		
		ViewUtils.inject(this, rootView);
		return rootView;
	}

	protected LoadResultState loadToData() {
	
		DetailActivityProtocol detailActivityProtocol = new DetailActivityProtocol(
				mPackageName);
		try {
			mAppInfos = detailActivityProtocol.loadDataFromNet(0);

			if (mAppInfos != null) {
				return LoadResultState.SUCCESS;
			} else {
				return LoadResultState.EMPTY;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;
		}
	}

	private void initData() {
		// 触发加载数据
		mLoadingPage.triggerLoader();
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		
	}

}
