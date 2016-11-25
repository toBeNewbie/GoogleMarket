package com.example.googlemarket.factory;

import java.util.HashMap;
import java.util.Map;

import com.example.googlemarket.fragment.ApplicationFragment;
import com.example.googlemarket.fragment.BaseFragment;
import com.example.googlemarket.fragment.ClassificationFragment;
import com.example.googlemarket.fragment.GameFragment;
import com.example.googlemarket.fragment.HomeFragment;
import com.example.googlemarket.fragment.ProjectFragment;
import com.example.googlemarket.fragment.RankingFragment;
import com.example.googlemarket.fragment.RecommentFragment;

import android.R.integer;
import android.support.v4.app.Fragment;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-13
 * @des 生成创建fragment的类
 * 
 * 
 *      <string-array name="main_titles"> 
 *      <item>首页</item> 
 *      <item>应用</item>
 *      <item>游戏</item> 
 *      <item>专题</item> 
 *      <item>推荐</item> 
 *      <item>分类</item>
 *      <item>排行</item> 
 *      </string-array>
 *      
 *      
 */
public class FragmentFactory {
	
	public static final int FRAGMENT_HOME=0;
	public static final int FRAGMENT_APPLICATION=1;
	public static final int FRAGMENT_GAME=2;
	public static final int FRAGMENT_PROJECT=3;
	public static final int FRAGMENT_RECOMMEND=4;
	public static final int FRAGMENT_CLASSIFICATION=5;
	private static final int FRAGMENT_RANKING=6;
	
	public static final Map<Integer,BaseFragment> mCacheFragmentsMap=new HashMap<Integer, BaseFragment>();
	
	public static BaseFragment createFragment(int index){
		
		BaseFragment fragment=null;
		
		/**
		 * 判断集合中是否存在
		 */
		if (mCacheFragmentsMap.containsKey(index)) {
			fragment = mCacheFragmentsMap.get(index);
			return fragment;
		}
		switch (index) {
		
		case FRAGMENT_HOME://首页
			fragment = new HomeFragment();
			break;
		case FRAGMENT_APPLICATION://应用
			fragment = new ApplicationFragment();			
			break;
		case FRAGMENT_GAME://游戏
			fragment = new GameFragment();
			break;
		case FRAGMENT_PROJECT://专题
			fragment = new ProjectFragment();
			break;
		case FRAGMENT_RANKING://排行
			fragment = new RankingFragment();
			break;
		case FRAGMENT_CLASSIFICATION://分类
			fragment = new ClassificationFragment();
			break;
		case FRAGMENT_RECOMMEND://推荐
			fragment = new RecommentFragment();
			break;

		default:
			break;
		}
		
		//保存到集合中
		mCacheFragmentsMap.put(index, fragment);
		return fragment;
	}
	
}
