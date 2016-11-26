package com.example.googlemarket.protocol;


import java.util.HashMap;
import java.util.Map;

import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.google.gson.Gson;

public class DetailActivityProtocol extends BaseProtocol<AppInfo>{

	
	private String mPackageName;

	public DetailActivityProtocol(String packageName) {
		super();
		mPackageName = packageName;
	}

	@Override
	protected String getIndexKey() {
		// TODO Auto-generated method stub
		return "detail";
	}

	@Override
	protected AppInfo parseJsonDatas(String jsonDatas) {
		// TODO Auto-generated method stub
		Gson mGson = new Gson();
		return mGson.fromJson(jsonDatas, AppInfo.class);
	}

	@Override
	public Map<String, String> getExtraParams() {
		Map<String, String> extraParams = new HashMap<String, String>();
		extraParams.put("packageName", mPackageName);
		return extraParams;
	}
	
}
