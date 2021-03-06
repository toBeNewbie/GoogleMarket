package com.example.googlemarket.protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


import com.example.googlemarket.application.BaseApplication;
import com.example.googlemarket.constant.ConstantsValues;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.example.googlemarket.utils.FileUtils;
import com.example.googlemarket.utils.IOUtils;
import com.example.googlemarket.utils.LogUtils;
import com.example.googlemarket.utils.UIUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 
 * @author Administrator
 * @param <T>
 * @company Newbie
 * @date 2016-11-22
 * @des 网络请求数据的基本抽取
 */
public abstract class BaseProtocol<T> {


	public T loadDataFromNet(int index) throws Exception {

		T t = getDataFromMemory(index);

		
		if (t != null) {
			
				LogUtils.s("####从内存中获取数据" + getIndexKey() + "." + index);
			
			return t;
		}

		t = getDatasFromNative(index);

		if (t != null) {
			LogUtils.s("####从本地获取数据" + getCacheFile(index).getAbsolutePath());
			return t;
		}

		return getDatasFromNet(index);
	}

	/**
	 * @des 从内存中获取数据
	 * 
	 * @param index
	 * @return
	 */
	private T getDataFromMemory(int index) {

		BaseApplication mBaseApplication = (BaseApplication) UIUtils
				.getContext();
		Map<String, String> mCacheMap = mBaseApplication.getCacheMap();
		
		/*-----------add begin item界面跳转保持唯一性------------*/
		Map<String, String> extraParams = getExtraParams();
		String Key;

		if (extraParams != null) {
			Key = getIndexKey() + "." + extraParams.get("packageName");
			
		}else {
			
			Key = getIndexKey() + "." + index;
		}
		/*-----------add end------------*/
		
		String jsonDataString = mCacheMap.get(Key);
		return parseJsonDatas(jsonDataString);
	}

	/**
	 * @des 从本地加载数据
	 * @param index
	 * @return
	 */
	private T getDatasFromNative(int index) {

		File cacheFile = getCacheFile(index);

		if (cacheFile.exists()) {
			BufferedReader mBufferedReader = null;
			try {
				// 如果缓存文件存在
				// 读取插入时间
				mBufferedReader = new BufferedReader(new FileReader(cacheFile));
				// 读取第一行
				long insertTime = Long.parseLong(mBufferedReader.readLine());

				if (System.currentTimeMillis() - insertTime < ConstantsValues.NATIVIE_DATAS_TIMEOUT) {
					// 读取有效的缓存数据
					String cacheJsonStr = mBufferedReader.readLine();
					

					/*-----------add begin 保存到内存中------------*/
					Map<String, String> extraParams = getExtraParams();
					BaseApplication mBaseApplication = (BaseApplication) UIUtils
							.getContext();
					Map<String, String> mCacheMap = mBaseApplication.getCacheMap();
					String key;
					if (getExtraParams()!=null) {
						key = getIndexKey()+"."+ extraParams.get("packageName");
					}else {
						key = getIndexKey()+"."+index;
					}
					mCacheMap.put(key, cacheJsonStr);
					/*-----------add end------------*/
					
					T jsonDatas = parseJsonDatas(cacheJsonStr);
					return jsonDatas;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				IOUtils.close(mBufferedReader);
			}

		}

		return null;
	}

	/**
	 * @des 得到缓存的文件
	 * @param index
	 * @return
	 */
	public File getCacheFile(int index) {
		// 文件存在哪里
		String dir = FileUtils.getDir("json");


		/*-----------add begin item界面跳转保持唯一性------------*/
		Map<String, String> extraParams = getExtraParams();
		String fileName;
		if (extraParams != null) {
			fileName = getIndexKey() + "." + extraParams.get("packageName");
			
		}else {
			
			// 文件名需要保证索引唯一性
			fileName = getIndexKey() + "." + index;
		}
		/*-----------add end------------*/

		File cacheFile = new File(dir, fileName);
		return cacheFile;
	}

	/**
	 * @des 从网络获取数据
	 * @param index
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public T getDatasFromNet(int index) throws HttpException, IOException {

		HttpUtils mHttpUtils = new HttpUtils();
		String url = Urls.BASE_URL + getIndexKey();
		RequestParams params = new RequestParams();
		
		/*-----------add begin保证listview跳转唯一性------------*/
		
		Map<String, String> extraParams = getExtraParams();
		if (extraParams != null) {
			for (Map.Entry<String, String> info: extraParams.entrySet()) {
				String key = info.getKey();//key:packageName
				String value = info.getValue();
				
				params.addQueryStringParameter(key, value);
			}
		}else {
			
			params.addQueryStringParameter("index", index + "");
		}
		/*-----------add end------------*/
		
		
		ResponseStream responseStream = mHttpUtils.sendSync(HttpMethod.GET,
				url, params);

		String jsonDatas = responseStream.readString();

		/*-----------add begin 保存到内存中------------*/
		BaseApplication mBaseApplication = (BaseApplication) UIUtils
				.getContext();
		Map<String, String> mCacheMap = mBaseApplication.getCacheMap();
		String key;
		if (getExtraParams()!=null) {
			key = getIndexKey()+"." +extraParams.get("packageName");
		}else {
			key = getIndexKey()+"."+index;
		}
		mCacheMap.put(key, jsonDatas);
		/*-----------add end------------*/

		/*-----------add begin 缓存数据写到文件中------------*/
		File cacheFile = getCacheFile(index);
		BufferedWriter mBufferedWriter = null;
		try {
			mBufferedWriter = new BufferedWriter(new FileWriter(cacheFile));
			// 在第一行写入插入数据时间
			mBufferedWriter.write(System.currentTimeMillis() + "");
			mBufferedWriter.newLine();// 换行
			mBufferedWriter.write(jsonDatas);// 写入到文件中
		} finally {
			// TODO Auto-generated catch block
			IOUtils.close(mBufferedWriter);
		}

		/*-----------add end------------*/

		T t = parseJsonDatas(jsonDatas);
		return t;
	}

	/**
	 * @des 获取额外参数
	 * @des 子类选择性复写此方法，传递额外的参数
	 * @call 传递额外的数据访问参数
	 * @return
	 */
	protected Map<String, String> getExtraParams() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @des 具体的json数据解析过程
	 * @des 必须实现，但不知道具体实现，定义为抽象方法，交给具体子类去实现
	 * @call 解析网络获取的json数据
	 * @param jsonDatas
	 * @return
	 */
	protected abstract T parseJsonDatas(String jsonDatas);

	/**
	 * @des 返回协议的关键字
	 * @des 必须实现，但不知道具体实现，定义为抽象方法，交给具体子类去实现
	 * @call BaseProtocol 作为基类，调用loadDataFromNet()方法
	 * @return
	 */
	protected abstract String getIndexKey();
}
