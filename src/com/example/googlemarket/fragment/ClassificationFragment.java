package com.example.googlemarket.fragment;

import java.util.List;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.googlemarket.baseAdapter.SuperBaseAdapter;
import com.example.googlemarket.bean.ClassificationInfoBean;
import com.example.googlemarket.factory.ListViewFactory;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.ClassificationProtocol;
import com.example.googlemarket.viewHolder.BaseHolder;
import com.example.googlemarket.viewHolder.ClassificationNormalViewHolder;
import com.example.googlemarket.viewHolder.ClassificationTitleViewHolder;

/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-13
 *@des 主页
 */
public class ClassificationFragment extends BaseFragment {

	private List<ClassificationInfoBean> mLoadDatas;

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return  状态值
	 */
	@Override
	protected LoadResultState initData() {
		ClassificationProtocol mProtocol = new ClassificationProtocol();
		try {
			//没有请求到数据
			mLoadDatas = mProtocol.loadDataFromNet(0);
			
			return checkState(mLoadDatas);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;
		}
		
	}

	@Override
	protected View initSuccessView() {
		
		ListView listView = ListViewFactory.createListView();
		listView.setAdapter(new ClassificationAdapter(listView, mLoadDatas));
		return listView;
	}
	
	class ClassificationAdapter extends SuperBaseAdapter<ClassificationInfoBean>{

		public ClassificationAdapter(AbsListView absListView,
				List<ClassificationInfoBean> dataResouce) {
			super(absListView, dataResouce);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected BaseHolder<ClassificationInfoBean> getSpecialViewHolder(
				int position) {
			// TODO Auto-generated method stub
			ClassificationInfoBean infoBean = mLoadDatas.get(position);
			if (infoBean.ifTitle) {
				//是标题
				return new ClassificationTitleViewHolder();
			}else {
				//不是标题
				return new ClassificationNormalViewHolder();
			}
			
		}

		@Override
		protected boolean hasLoadMoreDatas() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return super.getViewTypeCount()+1;//默认是2，+1等于3
		}
		
		@Override
		protected int getNormalState(int position) {
			ClassificationInfoBean infoBean = mLoadDatas.get(position);
			if (infoBean.ifTitle) {
				return 1;
			}else {
				return 2;
			}
		}
		
	}
}
