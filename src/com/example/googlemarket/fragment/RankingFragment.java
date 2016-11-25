package com.example.googlemarket.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.googlemarket.R;
import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.protocol.RankingFragmentProtocol;
import com.example.googlemarket.utils.UIUtils;
import com.example.googlemarket.view.FlowLayout;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-13
 * @des 主页
 */
public class RankingFragment extends BaseFragment {

	private List<String> mRankingDatas;

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return 状态值
	 */
	@Override
	protected LoadResultState initData() {

		RankingFragmentProtocol mRankingFragmentProtocol = new RankingFragmentProtocol();
		try {
			mRankingDatas = mRankingFragmentProtocol.loadDataFromNet(0);
			return checkState(mRankingDatas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return LoadResultState.ERROR;
		}

	}

	@Override
	protected View initSuccessView() {

		ScrollView mScrollView = new ScrollView(UIUtils.getContext());

		FlowLayout mFlowLayout = new FlowLayout(UIUtils.getContext());
		for (String rankingString : mRankingDatas) {
			TextView mTextView = new TextView(UIUtils.getContext());

			//设置文本圆角
			mTextView.setBackgroundResource(R.drawable.shape_ranking_tv);

			/*-----------add begin 动态产生背景色------------*/
			GradientDrawable mNormalGradientDrawable = new GradientDrawable();
			mNormalGradientDrawable.setCornerRadius(10);
			Random mRandom = new Random();
			int alpha = 255;
			int red = mRandom.nextInt(170) + 30;
			int green = mRandom.nextInt(170) + 30;
			int blue = mRandom.nextInt(170) + 30;
			int argb = Color.argb(alpha, red, green, blue);
			mNormalGradientDrawable.setColor(argb);
			
			//按下的状态
			GradientDrawable pressGradientDrawable=new GradientDrawable();
			pressGradientDrawable.setCornerRadius(10);
			pressGradientDrawable.setColor(Color.GRAY);
			
			
			StateListDrawable mStateListDrawable=new StateListDrawable();
			mStateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressGradientDrawable);
			mStateListDrawable.addState(new int[]{}, mNormalGradientDrawable);
			mTextView.setBackground(mStateListDrawable);
			/*-----------add end------------*/

			/*-----------add begin  设置文本布局------------*/
			
			mTextView.setGravity(Gravity.CENTER);
			mTextView.setTextColor(Color.WHITE);
			int padding = UIUtils.dip2Px(6);
			mTextView.setPadding(padding, padding, padding, padding);
			mTextView.setText(rankingString);
			/*-----------add end------------*/

			mTextView.setClickable(true);
			mFlowLayout.addView(mTextView);
		}

		mScrollView.addView(mFlowLayout);

		return mScrollView;
	}

}
