package com.example.googlemarket.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-17
 * @des 线程池的代理，帮ThreadPoolExecutor做一些事情
 * @des 提交任务、执行任务、移除任务
 */
public class ThreadPoolExecutorProxy {

	ThreadPoolExecutor mExecutor;
	
	private int mMCorePoolSize;
	private int mMMaximumPoolSize;
	private long mMKeepAliveTime;
	

	public ThreadPoolExecutorProxy(int mCorePoolSize, int mMaximumPoolSize,
			long mKeepAliveTime) {
		super();
		mMCorePoolSize = mCorePoolSize;
		mMMaximumPoolSize = mMaximumPoolSize;
		mMKeepAliveTime = mKeepAliveTime;
	}
	private void initThreadPoolExecutor() {
		if (mExecutor==null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
			
			synchronized (ThreadPoolExecutorProxy.class) {
				
				if (mExecutor==null || mExecutor.isShutdown() || mExecutor.isTerminated()){
					
					TimeUnit mTimeUnit=TimeUnit.MILLISECONDS;
					
					
					BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
					
					
					ThreadFactory threadFactory = Executors.defaultThreadFactory();
					
					RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
					
					mExecutor = new ThreadPoolExecutor(mMCorePoolSize, 
							mMMaximumPoolSize, 
							mMKeepAliveTime, 
							mTimeUnit, 
							workQueue, 
							threadFactory, 
							handler);
			
				}
			}
			
			
		}

	}
	/**
	 * 提交任务
	 */
	public Future<?> submitTask(Runnable task) {

		initThreadPoolExecutor();

		return mExecutor.submit(task);
	}

	/**
	 * 执行任务
	 */
	public void executeTask(Runnable task) {
		
		initThreadPoolExecutor();
		
		mExecutor.execute(task);
	}

	/**
	 * 移除任务
	 */
	public void removeTask(Runnable task) {
		
		initThreadPoolExecutor();
		
		mExecutor.remove(task);
	}

	
}
