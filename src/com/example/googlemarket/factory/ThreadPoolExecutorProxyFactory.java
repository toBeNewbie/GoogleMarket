package com.example.googlemarket.factory;

import com.example.googlemarket.manager.ThreadPoolExecutorProxy;

/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-17
 *@des 创建不同的线程池代理
 */
public class ThreadPoolExecutorProxyFactory {

	/**
	 * 普通的线程池代理
	 */
	static ThreadPoolExecutorProxy mNormalThreadPoolExecutorProxy;
	
	/**
	 * 下载的线程池代理
	 */
	static ThreadPoolExecutorProxy mDownloadThreadPoolExecutorProxy;
	
	/**
	 * 得到普通的线程池代理
	 */
	public static ThreadPoolExecutorProxy getNormalThreadPoolExecutorProxy(){
		if (mNormalThreadPoolExecutorProxy==null) {
			
			synchronized (ThreadPoolExecutorProxyFactory.class) {
			
				if (mNormalThreadPoolExecutorProxy==null) {
				
					mNormalThreadPoolExecutorProxy=new ThreadPoolExecutorProxy(5, 5, 3000);
				
				}
			}
		}
		
		return mNormalThreadPoolExecutorProxy;
	}
	/**
	 * 得到下载的线程池代理
	 */
	public static ThreadPoolExecutorProxy getDownloadThreadPoolExecutorProxy(){
		if (mDownloadThreadPoolExecutorProxy==null) {
			
			synchronized (ThreadPoolExecutorProxyFactory.class) {
				
				if (mDownloadThreadPoolExecutorProxy==null) {
					
					mDownloadThreadPoolExecutorProxy=new ThreadPoolExecutorProxy(3, 5, 3000);
					
				}
			}
		}
		
		return mDownloadThreadPoolExecutorProxy;
	}
	
	
	
	
}
