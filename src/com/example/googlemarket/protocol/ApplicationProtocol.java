package com.example.googlemarket.protocol;

import java.util.List;
import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApplicationProtocol extends BaseProtocol<List<AppInfo>> {

	
	@Override
	protected String getIndexKey() {

		return "app";

	}

	
	@Override
	protected List<AppInfo> parseJsonDatas(String jsonDatas) {

		// json解析分为两种1.bean解析。2.泛型解析
		Gson mGson = new Gson();

		return mGson.fromJson(jsonDatas, new TypeToken<List<AppInfo>>() {
		}.getType());
	}

}
