package com.example.googlemarket.protocol;

import java.util.List;

import com.example.googlemarket.bean.ProjectBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProjectProtocol extends BaseProtocol<List<ProjectBean>> {

	@Override
	protected List<ProjectBean> parseJsonDatas(String jsonDatas) {
		Gson mGson=new Gson();
		return mGson.fromJson(jsonDatas, new TypeToken<List<ProjectBean>>(){}.getType());
	}

	@Override
	protected String getIndexKey() {
		// TODO Auto-generated method stub
		return "subject";
	}

	

}
