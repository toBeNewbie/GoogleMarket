package com.example.googlemarket.protocol;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RecommenFragmentProtocol extends BaseProtocol<List<String>>{

	@Override
	protected List<String> parseJsonDatas(String jsonDatas) {
		Gson mGson = new Gson();
		return mGson.fromJson(jsonDatas, new TypeToken<List<String>>(){}.getType());
		
	}

	@Override
	protected String getIndexKey() {
		// TODO Auto-generated method stub
		return "recommend";
	}

}
