package com.example.googlemarket.fragment;

import java.util.List;
import android.os.SystemClock;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.example.googlemarket.baseAdapter.SuperBaseAdapter;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.factory.ListViewFactory;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.ApplicationProtocol;
import com.example.googlemarket.viewHolder.BaseHolder;
import com.example.googlemarket.viewHolder.AppItemHolder;
import com.google.gson.JsonElement;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-13
 * @des 主页
 * 
 */
public class ApplicationFragment extends BaseFragment {

	private List<AppInfo> mAppInfos;
	private ApplicationProtocol mApplicationProtocol;

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return  状态值
	 */

	
	
	@Override
	protected LoadResultState initData() {
		
		SystemClock.sleep(2000);
		
		mApplicationProtocol = new ApplicationProtocol();
		
		try {
			mAppInfos = mApplicationProtocol.loadDataFromNet(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;// 数据加载之后的状态,应该有几种
		}
		
		return checkState(mAppInfos);
		
	}

	@Override
	protected View initSuccessView() {
		
		ListView mListView = ListViewFactory.createListView();
		
		mListView.setAdapter(new applicationAdapter(mListView, mAppInfos));
		
		return mListView;
		
	}
		
	
	class applicationAdapter extends SuperBaseAdapter<AppInfo>{
		
		public applicationAdapter(AbsListView absListView,
				List<AppInfo> dataResouce) {
		
			super(absListView, dataResouce);
		
		}
		
		@Override
		protected BaseHolder<AppInfo> getSpecialViewHolder(int position) {
			// TODO Auto-generated method stub
			return new AppItemHolder();
		}
		
		@Override
		protected List<AppInfo> onLoadMore() throws Exception {
			
			List<AppInfo> datasFromNet = mApplicationProtocol.getDatasFromNet(mAppInfos.size());
			
			return datasFromNet;
		}
		
	}
		
}
