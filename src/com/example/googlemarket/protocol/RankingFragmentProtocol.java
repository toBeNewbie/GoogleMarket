package com.example.googlemarket.protocol;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RankingFragmentProtocol extends BaseProtocol<List<String>> {

	//http://localhost:8080/GooglePlayServer/hot?index=0

	

	@Override
	protected String getIndexKey() {
		// TODO Auto-generated method stub
		return "hot";
	}

	@Override
	protected List<String> parseJsonDatas(String jsonDatas) {
		// TODO Auto-generated method stub
		Gson mGson = new Gson();
		return mGson.fromJson(jsonDatas, new TypeToken<List<String>>(){}.getType());
	}



}
