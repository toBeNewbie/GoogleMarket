package com.example.googlemarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-13
 *@des 所有fragment的基类   常规抽取
 */
public abstract class BaseFragmentCommand extends Fragment {
	
	/**
	 * 封装基类的好处：
	 * 			1、只关心自己定义的方法，不需要关心生命周期定义的方法
	 * 			2、还可以定义哪些方法是“必须实现”，哪些方法是“选择性实现”
	 * 			3、放置公有的属性和方法
	 * @param savedInstanceState
	 */
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		init();
		super.onCreate(savedInstanceState);
	}
	

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		return initView();
	}
	

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		initData();		
		
		initListener();
		
	}

	
	
	/**
	 * @des 初始化
	 * @call 子类可以选择性实现此方法
	 */
	protected void initListener() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @des 初始化
	 * @call 子类可以选择性实现此方法
	 */
	protected void initData() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @des 初始化
	 * @call 子类可以选择性实现此方法
	 */
	protected void init() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 *@desc  初始化视图
	 *  
	 *  
	 *@call  必须实现，基类不知道具体实现，交给子类具体实现
	 * @return
	 */
	protected abstract View initView();
}
