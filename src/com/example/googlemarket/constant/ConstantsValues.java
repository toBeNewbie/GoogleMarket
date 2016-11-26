package com.example.googlemarket.constant;

import android.support.v4.view.ViewPager;

import com.example.googlemarket.utils.LogUtils;


/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-12
 *@des 封装常量的接口
 */
public interface ConstantsValues {


	/**
	 * 打印所有日志
	 */
	public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;
	public static final long NATIVIE_DATAS_TIMEOUT = 5*60*1000;

	/**
	 * http://169.254.253.65:8080/GooglePlayServer/category?index=0
	 * 网络请求的基本IP地址
	 */
	public static final class Urls{
		//image/home01.jpg"
		public static final String BASE_URL="http://169.254.253.65:8080/GooglePlayServer/";
		
		public static final String IMAGE_URL="http://169.254.253.65:8080/GooglePlayServer/image?name=";
		
	}
	
}
