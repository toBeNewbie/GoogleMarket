package com.example.googlemarket.fragment;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.googlemarket.pages.LoadingPage;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.utils.UIUtils;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-13
 * @des 基本抽取
 */
public abstract class BaseFragment extends Fragment {

	private LoadingPage mLoadingPage;

	public LoadingPage getLoadingPage() {
		// TODO Auto-generated method stub
		return mLoadingPage;
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mLoadingPage == null) {// 避免viewpager重复创建
			mLoadingPage = new LoadingPage(UIUtils.getContext()) {

				/**
				 * @des 开始加载数据了,必须实现，不知道具体实现，定义为抽象方法，交给子类具体实现
				 * 
				 * @call 外界需要触发加载数据时调用triggerLoader()方法
				 * @return 状态值
				 */
				@Override
				protected LoadResultState initData() {
					// TODO Auto-generated method stub
					return BaseFragment.this.initData();
				}

				/**
				 * @des 成功创建视图,
				 * @des 必须实现，不知道具体实现，定义为抽象方法，交给子类具体实现
				 * @call 触发加载数据，数据加载完成后，得到数据加载结果，重新刷新UI,数据加载成功了，而且成功视图还没有
				 * @return
				 */
				@Override
				protected View initSuccessView() {
					// TODO Auto-generated method stub
					return BaseFragment.this.initSuccessView();
				}

			};

		} else {// 第二次进来的时候
			ViewParent mViewParent = mLoadingPage.getParent();
			if (mViewParent != null && mViewParent instanceof ViewGroup) {

				((ViewGroup) mViewParent).removeView(mLoadingPage);
			}
		}

		return mLoadingPage;
	}


	/**
	 * @des 成功创建视图,
	 * @des BaseFragment类必须实现，不知道具体实现，定义为抽象方法，交给BaseFragment的子类具体实现
	 * @call 触发加载数据，数据加载完成后，得到数据加载结果，重新刷新UI,数据加载成功了，而且成功视图还没有
	 * @return
	 */
	protected abstract View initSuccessView();

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，
	 * 
	 * @des 不知道具体实现，定义为抽象方法，交给BaseFragment子类具体实现
	 * @des 它和LoadingPage中定义的方法同名
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return 状态值
	 */
	protected abstract LoadResultState initData();

	/**
	 * 
	 * @param result
	 *            请求回来的数据
	 * @return 不同的LoadResult状态值
	 */
	public LoadResultState checkState(Object result) {
		if (result == null) {
			return LoadResultState.EMPTY;
		}

		if (result instanceof List) {
			if (((List) result).size() == 0) {
				return LoadResultState.EMPTY;
			}
		}

		if (result instanceof Map) {
			if (((Map) result).size() == 0) {
				return LoadResultState.EMPTY;
			}
		}

		return LoadResultState.SUCCESS;
	}

	private void initListener() {
		// TODO Auto-generated method stub

	}
}
