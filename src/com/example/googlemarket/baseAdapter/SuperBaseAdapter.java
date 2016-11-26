package com.example.googlemarket.baseAdapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.example.googlemarket.factory.ThreadPoolExecutorProxyFactory;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.viewHolder.BaseHolder;
import com.example.googlemarket.viewHolder.LoadMoreViewHolder;

public abstract class SuperBaseAdapter<ITEMBEANTYPE> extends BaseAdapter implements OnItemClickListener{

	private static final int VIEW_LOADING_MORE = 0;
	private static final int VIEW_NORMRL = 1;
	private List<ITEMBEANTYPE> mDataResouce = new ArrayList<ITEMBEANTYPE>();
	private LoadMoreViewHolder mLoadMoreViewHolder;
	private LoadMoreTask mLoadMoreTask;
	private AbsListView mAbsListView;

	public SuperBaseAdapter(AbsListView absListView,List<ITEMBEANTYPE> dataResouce) {
		
		mAbsListView = absListView;
		
		mAbsListView.setOnItemClickListener(this);
		
		mDataResouce = dataResouce;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mDataResouce != null) {

			// +1添加加载更多view
			return mDataResouce.size() + 1;
		}

		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDataResouce.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/**
		 * 系统返回convertView之前，首先会判断即将显示的itemView的viewType类型
		 * 
		 */

		// 决定根视图
		BaseHolder<ITEMBEANTYPE> mBaseHolder = null;

		if (convertView == null) {

			if (getItemViewType(position) == VIEW_LOADING_MORE) {
				// 加载更多的view
				mBaseHolder = (BaseHolder<ITEMBEANTYPE>) getLoadMoreViewHolder();

			} else {
				// 普通的listview条目
				mBaseHolder = getSpecialViewHolder(position);
			}

		} else {

			mBaseHolder = (BaseHolder) convertView.getTag();

		}

		/*-----------add begin  数据和视图的绑定------------*/
		if (getItemViewType(position) == VIEW_LOADING_MORE) {
			// 显示加载更多视图，加载更多数据
			if (hasLoadMoreDatas()) {

				// TODO 去加载更多的数据
				triggerLoadMoreData();

			} else {

				// 没有更多数据，显示没有加载更多的视图
				mLoadMoreViewHolder
						.setDataAndRefreshHolderView(LoadMoreViewHolder.LOADMORE_NONE);

			}

		} else {// 普通的视图

			ITEMBEANTYPE data = mDataResouce.get(position);

			// 接收数据，绑定数据
			mBaseHolder.setDataAndRefreshHolderView(data);
		}

		/*-----------add end------------*/

		return mBaseHolder.mViewHolder;
	}

	/**
	 * @des 触发加载更多数据
	 * @call 滑到底，而且有加载更多数据
	 */
	private void triggerLoadMoreData() {
		if (mLoadMoreTask==null) {//没有加载更多
			
			// 初始化mLoadMoreHolder视图
			int state = LoadMoreViewHolder.LOADMORE_LOADING;
			mLoadMoreViewHolder.setDataAndRefreshHolderView(state);
			
			mLoadMoreTask = new LoadMoreTask();
			ThreadPoolExecutorProxyFactory.getNormalThreadPoolExecutorProxy().executeTask(
					mLoadMoreTask);
		}

	}

	
	class LoadMoreTask implements Runnable{

		private static final int PAGERSIZE = 20;

		@Override
		public void run() {
			// 真正的在子线程中加载更多数据
			/*-----------add begin 定义两个决定UI的变量------------*/
			int state;
			List<ITEMBEANTYPE> loadMoreData = null;
			/*-----------add end------------*/
			try {
				loadMoreData = onLoadMore();
				
				//根据loadMoreData决定state
				if (loadMoreData == null) {
					//没有加载更多数据
					state = LoadMoreViewHolder.LOADMORE_NONE;
				}
				
				
				if (loadMoreData.size() < PAGERSIZE) {
					//没有加载更多数据
					state=LoadMoreViewHolder.LOADMORE_NONE;
				}else {
					//可能有加载更多数据
					state = LoadMoreViewHolder.LOADMORE_LOADING;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				state = LoadMoreViewHolder.LOADMORE_RETRY;
			}
			
			
			
			/*-----------add begin 定义临时变量------------*/
			final int temState = state;
			final List<ITEMBEANTYPE> tempLoadMoreData = loadMoreData;
			/*-----------add end------------*/
			
			/*-----------add begin 根据当前的数据刷新UI------------*/
			UIUtils.performSecurityTask(new Runnable() {
				
				@Override
				public void run() {
					// 刷新UI
					//1.listview--->更新数据集-->notifyDataSetChange方法
					if (tempLoadMoreData != null) {
						mDataResouce.addAll(tempLoadMoreData);
						notifyDataSetChanged();//刷新操作
					}
					
					
					// 2.刷新mLoadMoreHolder(希望完成加载更多之后返回一个 "int值")
					mLoadMoreViewHolder.setDataAndRefreshHolderView(temState);
					
					
				}
			});
			/*---------- -add end------------*/
			
			// 置空当前的loadMoreTask
			mLoadMoreTask = null;
			
		}
		
	}
	
	/**
	 * @des 决定是否有加载更多,默认是加载更多，子类可以覆盖此方法，修改返回值，决定子类是否有加载更多
	 * @call 滑到底的时候
	 * @return
	 */
	protected boolean hasLoadMoreDatas() {
		// 默认是有加载更多数据
		return true;
	}

	
	/**
	 * @des 真正在子线程中加载更多数据
	 * @des 默认返回null，但是子类可以选择性地复写该方法，放具体加载更多之后数据
	 * @return
	 */
	protected List<ITEMBEANTYPE> onLoadMore() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	private LoadMoreViewHolder getLoadMoreViewHolder() {
		if (mLoadMoreViewHolder == null) {
			mLoadMoreViewHolder = new LoadMoreViewHolder();
		}
		return mLoadMoreViewHolder;
	}

	/*-----------add begin  listView子组件的ViewType------------*/
	@Override
	public int getItemViewType(int position) {
		if (position == getCount() - 1) {

			// listview滑到底啦
			return VIEW_LOADING_MORE;

		} else {
			return getNormalState(position);

		}
	}



	/**
	 * @des 当listview布局item数目多于两个的时候可复写此方法，加载更多视图类型
	 * @call 默认返回1，得到普通的viewType
	 * @param position
	 * @return
	 */
	protected int getNormalState(int position) {
		// TODO Auto-generated method stub
		return VIEW_NORMRL;
	}

	@Override
	public int getViewTypeCount() {
		// the adapter always returns two types 返回两种view类型
		return super.getViewTypeCount() + 1;
	}

	/*-----------add end------------*/

	/**
	 * @param  
	 * @des 返回一个baseHolder对象的子类
	 * @des 必须实现，但不知道具体实现，定义为抽象方法，交给子类去实现
	 * @call 来到getView()方法内&&convertView==null
	 * @return
	 */
	protected abstract BaseHolder<ITEMBEANTYPE> getSpecialViewHolder(int position);

	
	/*-----------add begin  处理item的点击事件------------*/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		if (getItemViewType(position) == VIEW_LOADING_MORE) {
			//重新加载更多数据视图
			//触发加载更多数据
			triggerLoadMoreData();
		}else {
			//普通视图
			onNormalItemClick(parent, view, position, id);
		}
	}
	
	/**
	 * @des 普通条目的点击事件
	 * @des 子类可以覆盖此方法，处理普通条目点击事件
	 * @call 用户点击了普通条目的时候
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	protected void onNormalItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}
	/*-----------add end------------*/
	
}
