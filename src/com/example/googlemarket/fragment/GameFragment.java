package com.example.googlemarket.fragment;

import java.util.List;
import java.util.Random;

import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.googlemarket.baseAdapter.BaseItemAdapter;
import com.example.googlemarket.baseAdapter.SuperBaseAdapter;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.factory.ListViewFactory;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.GamePortocol;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.viewHolder.AppItemHolder;
import com.example.googlemarket.viewHolder.BaseHolder;


/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-13
 *@des 主页
 */
public class GameFragment extends BaseFragment {

	private GamePortocol mGamePortocol;
	private List<AppInfo> mGameAppInfos;

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return  状态值
	 */
	@Override
	protected LoadResultState initData() {
		SystemClock.sleep(2000);
		
		mGamePortocol = new GamePortocol();
		try {
			mGameAppInfos = mGamePortocol.loadDataFromNet(0);
			return checkState(mGameAppInfos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;// 数据加载之后的状态,应该有几种
		}
		
	}

	@Override
	protected View initSuccessView() {
		
		ListView mGameListView = ListViewFactory.createListView();
		mGameListView.setAdapter(new GameAdapter(mGameListView, mGameAppInfos));
		return mGameListView;
	}
	
	class GameAdapter extends BaseItemAdapter{

		public GameAdapter(AbsListView absListView, List<AppInfo> dataResouce) {
			super(absListView, dataResouce);
			// TODO Auto-generated constructor stub
		}

	
		@Override
		protected List<AppInfo> onLoadMore() throws Exception {
			// TODO Auto-generated method stub
			return mGamePortocol.loadDataFromNet(mGameAppInfos.size());
		}
	}
}
