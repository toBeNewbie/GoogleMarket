package com.example.googlemarket.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.example.googlemarket.fly_view_sets.ShakeListener;
import com.example.googlemarket.fly_view_sets.ShakeListener.OnShakeListener;
import com.example.googlemarket.fly_view_sets.StellarMap;
import com.example.googlemarket.fly_view_sets.StellarMap.Adapter;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.RecommenFragmentProtocol;
import com.example.googlemarket.utils.UIUtils;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-13
 * @des 主页
 */
public class RecommentFragment extends BaseFragment {

	private List<String> mRecommentDatas;
	private RecommentAdapter mRecommentAdapter;
	private ShakeListener mShakeListener;

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return 状态值
	 */
	@Override
	protected LoadResultState initData() {
		RecommenFragmentProtocol mRecommenFragmentProtocol = new RecommenFragmentProtocol();
		try {
			mRecommentDatas = mRecommenFragmentProtocol.loadDataFromNet(0);
			return checkState(mRecommentDatas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;
		}
	}

	@Override
	protected View initSuccessView() {

		final StellarMap mStellarMap = new StellarMap(UIUtils.getContext());
		mRecommentAdapter = new RecommentAdapter();
		mStellarMap.setAdapter(mRecommentAdapter);
		//设置拆分规则
		mStellarMap.setRegularity(15, 20);
		//设置首页选中
		mStellarMap.setGroup(0, true);
		
		mShakeListener = new ShakeListener(UIUtils.getContext());
		mShakeListener.setOnShakeListener(new OnShakeListener() {
			
			@Override
			public void onShake() {
				int currentGroup = mStellarMap.getCurrentGroup();
				if (currentGroup == mRecommentAdapter.getGroupCount()-1) {
					//end group page
					currentGroup=0;
				}else {
					currentGroup++;
				}
				
				//switch page group
				mStellarMap.setGroup(currentGroup, true);
			}
		});
		/*-----------add end------------*/
		
		return mStellarMap;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		if (mShakeListener != null) {
			mShakeListener.pause();
		}
		super.onPause();
	}
	
	@Override
	public void onResume() {
	
		// TODO Auto-generated method stub
		if (mShakeListener!= null) {
			mShakeListener.resume();
		}
		super.onResume();
	}
	
	class RecommentAdapter implements Adapter {

		private static final int PAGE_PER_COUNTS = 15;

		@Override
		public int getGroupCount() {
			if (mRecommentDatas.size() % PAGE_PER_COUNTS != 0) {// 有余数
				return mRecommentDatas.size() / PAGE_PER_COUNTS+1;
			}else {
				
				return mRecommentDatas.size() / PAGE_PER_COUNTS;
			}
		}

		@Override
		public int getCount(int group) {
			if (group == getGroupCount() - 1) {// 最后一页
				if (mRecommentDatas.size() % PAGE_PER_COUNTS != 0) {// 有余数
					return mRecommentDatas.size() % PAGE_PER_COUNTS;
				}
			}
			return PAGE_PER_COUNTS;
		}

		@Override
		public View getView(int group, int position, View convertView) {
			TextView mTextView=new TextView(UIUtils.getContext());
			int location = group*PAGE_PER_COUNTS + position;
			mTextView.setText(mRecommentDatas.get(location));
			
			int padding = UIUtils.dip2Px(5);
			mTextView.setPadding(padding, padding, padding, padding);
			/*-----------add begin 随机设置字体大小------------*/
			
			Random mRandom = new Random();
			mTextView.setTextSize(mRandom.nextInt(6)+16);
			
			/*-----------add end------------*/
			
			/*-----------add begin 随机颜色产生------------*/
			
			int alpha=255;
			int red = mRandom.nextInt(190)+30;
			int green= mRandom.nextInt(190)+30;
			int blue= mRandom.nextInt(190)+30;
			int color = Color.argb(alpha, red, green, blue);
			mTextView.setTextColor(color);
			/*-----------add end------------*/
					
			return mTextView;
		}

		@Override
		public int getNextGroupOnPan(int group, float degree) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
