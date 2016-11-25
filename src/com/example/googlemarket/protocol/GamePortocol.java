package com.example.googlemarket.protocol;

import java.util.List;

import com.example.googlemarket.bean.HomeFragmentBean.AppInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GamePortocol extends BaseProtocol<List<AppInfo>> {

	@Override
	protected List<AppInfo> parseJsonDatas(String jsonDatas) {
		Gson mGson = new Gson();
		return mGson.fromJson(jsonDatas, new TypeToken<List<AppInfo>>(){}.getType());
	}

	@Override
	protected String getIndexKey() {
		// TODO Auto-generated method stub
		return "game";
	}

	
}
