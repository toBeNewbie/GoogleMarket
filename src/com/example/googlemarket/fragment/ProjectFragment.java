package com.example.googlemarket.fragment;

import java.util.List;

import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.googlemarket.baseAdapter.SuperBaseAdapter;
import com.example.googlemarket.bean.ProjectBean;
import com.example.googlemarket.factory.ListViewFactory;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.ProjectProtocol;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.viewHolder.BaseHolder;
import com.example.googlemarket.viewHolder.ProjectHolder;



/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-13
 *@des 主页
 */
public class ProjectFragment extends BaseFragment {

	private List<ProjectBean> mProjectDatas;

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return  状态值
	 */
	@Override
	protected LoadResultState initData() {
		ProjectProtocol mProjectProtocol=new ProjectProtocol();
		try {
			mProjectDatas = mProjectProtocol.loadDataFromNet(0);
			return checkState(mProjectDatas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;
		}
		
		
	}

	@Override
	protected View initSuccessView() {
		
		ListView mListView = ListViewFactory.createListView();
		mListView.setAdapter(new ProjectAdapter(mListView, mProjectDatas));
		return mListView;
	}
	
	
	class ProjectAdapter extends SuperBaseAdapter<ProjectBean>{

		public ProjectAdapter(AbsListView absListView,
				List<ProjectBean> dataResouce) {
			super(absListView, dataResouce);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected BaseHolder<ProjectBean> getSpecialViewHolder(int position) {
			// TODO Auto-generated method stub
			return new ProjectHolder();
		}
		
		
		@Override
		protected boolean hasLoadMoreDatas() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
}
