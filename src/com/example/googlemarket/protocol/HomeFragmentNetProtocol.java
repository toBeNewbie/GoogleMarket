package com.example.googlemarket.protocol;

import com.example.googlemarket.bean.HomeFragmentBean;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.example.googlemarket.constant.ConstantsValues.Urls;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 
 * @author Administrator
 *@company Newbie
 *@date 2016-11-22
 *@des 主页访问数据的协议方法
 */
public class HomeFragmentNetProtocol extends BaseProtocol<HomeFragmentBean>{

	

	@Override
	protected String getIndexKey() {
		// TODO Auto-generated method stub
		return "home";
	}

	@Override
	protected HomeFragmentBean parseJsonDatas(String jsonDatas) {
		// TODO Auto-generated method stub
		Gson mGson = new Gson();

		HomeFragmentBean jsonAppInfos = mGson.fromJson(jsonDatas,
				HomeFragmentBean.class);
		
		return jsonAppInfos;
	}

	
	
}
