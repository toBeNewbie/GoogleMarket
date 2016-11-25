package com.example.googlemarket.pages;

import com.example.googlemarket.R;
import com.example.googlemarket.factory.ThreadPoolExecutorProxyFactory;
import com.example.googlemarket.utils.UIUtils;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public abstract class LoadingPage extends FrameLayout {

	private View mErrorView;
	private View mEmptyView;
	private View mLoadingView;
	private View mSuccessView;
	
	public static final int STATE_LOADING=0;
	public static final int STATE_ERROR=1;
	public static final int STATE_EMPTY=2;
	public static final int STATE_SUCCESS=3;
	public static final int STATE_NONE=4;//默认
	
	public int				 mCurrentState=STATE_NONE;//初始状态为加载数据

	public LoadingPage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initCommonPages();
	}

	public void initCommonPages() {
		
		//加载错误的界面
		mErrorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
		this.addView(mErrorView);
		//没有数据的空界面
		mEmptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
		this.addView(mEmptyView);
		//加载数据的界面
		mLoadingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
		this.addView(mLoadingView);
		
		//根据状态显示界面
		refreshUiState();
	}

	
	/**
	 * @des 根据当前的状态显示不同的UI
	 * @call 1、LoadingPager()初始化的时候
	 * @call 2、触发加载数据之前，状态被重置
	 * @call 3、触发加载数据，数据加载完成后，得到数据加载结果，重新刷新UI
	 */
	private void refreshUiState() {
		//控制空视图的显示和隐藏
		mEmptyView.setVisibility((mCurrentState==STATE_EMPTY)?0:8);
		
		//控制错误视图的显示与隐藏
		mErrorView.setVisibility((mCurrentState==STATE_ERROR)?0:8);
		
		mErrorView.findViewById(R.id.error_btn_retry).setOnClickListener(new OnClickListener() {
			//点击重新加载数据
			@Override
			public void onClick(View v) {
				
				triggerLoader();
				
			}
		});
		
		//控制加载视图的显示和隐藏
		mLoadingView.setVisibility((mCurrentState==STATE_LOADING)||(mCurrentState==STATE_NONE)?0:8);
		
		//数据加载成功了，而且成功视图还没有
		if (mCurrentState==STATE_SUCCESS && mSuccessView==null) {
			mSuccessView = initSuccessView();
			this.addView(mSuccessView);
		}
		
		if (mSuccessView!=null) {
			//控制加载成功视图的显示和隐藏
			mSuccessView.setVisibility((mCurrentState==STATE_SUCCESS)?0:8);
		}
	}
	
	
	/**
	 * @desc 触发异步加载数据
	 * 
	 * @call 外界需要触发加载数据时调用此方法
	 */
	public void triggerLoader(){
		//没有成功&&没有加载数据
		if (mCurrentState!=STATE_SUCCESS && mCurrentState!=STATE_LOADING) {
			//重置加载数据
			mCurrentState=STATE_LOADING;
			refreshUiState();
			
			//异步加载数据
//			new Thread(new LoadDatasTask()).start();
//			ThreadPoolExecutorProxyFactory.createNormalThreadPoolExecutorProxy().executeTask(new loadDatasTask());
			ThreadPoolExecutorProxyFactory.getNormalThreadPoolExecutorProxy().executeTask(new LoadDatasTask());
		}
	}

	class LoadDatasTask implements Runnable{

		@Override
		public void run() {
			// 正在开始加载数据
			//加载数据完成返回一个临死状态
			LoadResultState tempState = initData();
			
			//赋值临时状态给当前状态
			mCurrentState=tempState.state;
			
			UIUtils.performSecurityTask(new Runnable() {
				
				@Override
				public void run() {
					//刷新UI
					refreshUiState();
					
				}
			});
		}
		
	}

	/**
	 * @des 开始加载数据了,在子线程中执行
	 * @des 必须实现，不知道具体实现，定义为抽象方法，交给子类具体实现
	 * 
	 * @call 外界需要触发加载数据时调用此方法
	 * @return  状态值
	 */
	protected abstract LoadResultState initData();
	
	/**
	 * @des 成功创建视图,
	 * @des 必须实现，不知道具体实现，定义为抽象方法，交给子类具体实现
	 * @call 触发加载数据，数据加载完成后，得到数据加载结果，重新刷新UI,数据加载成功了，而且成功视图还没有
	 * @return
	 */
	protected abstract View initSuccessView();


	public enum LoadResultState{
		
		SUCCESS(STATE_SUCCESS),ERROR(STATE_ERROR),EMPTY(STATE_EMPTY);
		
		int state;
		
		private LoadResultState(int state){
			
			this.state=state;
		}
	}
	
		
}
