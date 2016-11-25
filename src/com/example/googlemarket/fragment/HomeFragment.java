package com.example.googlemarket.fragment;

import java.io.IOException;
import java.util.List;

import android.os.SystemClock;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.googlemarket.baseAdapter.SuperBaseAdapter;
import com.example.googlemarket.bean.HomeFragmentBean;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.factory.ListViewFactory;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.HomeFragmentNetProtocol;
import com.example.googlemarket.utils.LogUtils;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.viewHolder.AppItemHolder;
import com.example.googlemarket.viewHolder.BaseHolder;
import com.example.googlemarket.viewHolder.HomePicHolder;
import com.lidroid.xutils.exception.HttpException;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-13
 * @des 主页
 */
public class HomeFragment extends BaseFragment {

	private List<AppInfo> mAppInfos;

	private List<String> mPictures;

	private HomeFragmentNetProtocol mHomeFragmentNetProtocol;

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return 状态值
	 */
	@Override
	protected LoadResultState initData() {

		try {

			mHomeFragmentNetProtocol = new HomeFragmentNetProtocol();

			HomeFragmentBean jsonAppInfos = mHomeFragmentNetProtocol
					.loadDataFromNet(0);

			LoadResultState resultState = checkState(jsonAppInfos);

			if (resultState != LoadResultState.SUCCESS) {// jsonAppInfos==null，有问题
				return resultState;
			}

			resultState = checkState(jsonAppInfos.list);

			if (resultState != LoadResultState.SUCCESS) {// jsonAppInfos.list有问题===》》jsonAppInfos.list.size()==0
				return resultState;
			}

			// 数据正常获取，赋值存储
			mAppInfos = jsonAppInfos.list;
			mPictures = jsonAppInfos.picture;

			return resultState;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;
		}
		

	}

	class HomeFragmentAdapter extends SuperBaseAdapter<AppInfo> {

		public HomeFragmentAdapter(AbsListView absListView,
				List<AppInfo> dataResouce) {
			super(absListView, dataResouce);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected BaseHolder<AppInfo> getSpecialViewHolder() {
			return new AppItemHolder();
		}

		@Override
		protected void onNormalItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			Toast.makeText(UIUtils.getContext(),
					mAppInfos.get(position).packageName, 0).show();

			super.onNormalItemClick(parent, view, position, id);
		}

		@Override
		protected List<AppInfo> onLoadMore() throws Exception {

			SystemClock.sleep(2000);

			return performLoadMore();

		}

		/**
		 * @des 加载更多数据
		 * @return
		 * @throws HttpException
		 * @throws IOException
		 * @throws Exception
		 */
		public List<AppInfo> performLoadMore() throws HttpException,
				IOException, Exception {

			HomeFragmentBean jsonAppInfos = mHomeFragmentNetProtocol
					.loadDataFromNet(mAppInfos.size());

			if (jsonAppInfos != null) {

				return jsonAppInfos.list;
			}

			return super.onLoadMore();
		}

	}

	@Override
	protected View initSuccessView() {

		ListView mListView = ListViewFactory.createListView();

		/*-----------add begin------------*/

		HomePicHolder homePictureHolder = new HomePicHolder();
		mListView.addHeaderView(homePictureHolder.mViewHolder);
		homePictureHolder.setDataAndRefreshHolderView(mPictures);

		/*-----------add end------------*/

		HomeFragmentAdapter mHomeFragmentAdapter = new HomeFragmentAdapter(
				mListView, mAppInfos);

		mListView.setAdapter(mHomeFragmentAdapter);

		return mListView;

	}

}
