package com.example.googlemarket.fragment;

import java.util.Random;

import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.googlemarket.pages.LoadingPage.LoadResultState;
import com.example.googlemarket.utils.UIUtils;

/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-13
 *@des 主页
 */
public class ClassificationFragment extends BaseFragment {

	/**
	 * @des 开始加载数据了,BaseFragment必须实现，在子线程中执行
	 * 
	 * @call 外界需要触发加载数据时调用triggerLoader()方法
	 * @return  状态值
	 */
	@Override
	protected LoadResultState initData() {
		SystemClock.sleep(2000);
		// 成功
		// 失败(空,错误)
		// public static final int STATE_EMPTY = 1; // 空
		// public static final int STATE_ERROR = 2; // 错误
		// public static final int STATE_SUCCESS = 3; // 成功

		// 随机返回3种状态
		LoadResultState[] loadedResultArr = { LoadResultState.SUCCESS, LoadResultState.EMPTY, LoadResultState.ERROR };

		Random random = new Random();
		int index = random.nextInt(loadedResultArr.length);// 0 1 2

		return loadedResultArr[index];// 数据加载之后的状态,应该有几种
		
	}

	@Override
	protected View initSuccessView() {
		
		TextView tv = new TextView(UIUtils.getContext());
		tv.setText(this.getClass().getSimpleName());
		tv.setGravity(Gravity.CENTER);
		return tv;
	}
	
	
}
