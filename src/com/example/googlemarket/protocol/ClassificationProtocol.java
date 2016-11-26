package com.example.googlemarket.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.googlemarket.bean.ClassificationInfoBean;

public class ClassificationProtocol extends BaseProtocol<List<ClassificationInfoBean>>{

	@Override
	protected List<ClassificationInfoBean> parseJsonDatas(String jsonDatas) {
		List<ClassificationInfoBean> infoBeans = new ArrayList<ClassificationInfoBean>();
		try {
			JSONArray rootArray = new JSONArray(jsonDatas);
			for (int i = 0; i < rootArray.length(); i++) {
				JSONObject itemObject = rootArray.getJSONObject(i);
				String title = itemObject.getString("title");
				
				ClassificationInfoBean infoBean = new ClassificationInfoBean();
				infoBean.title=title;
				infoBean.ifTitle=true;
				infoBeans.add(infoBean);
				
				JSONArray infosArray = itemObject.getJSONArray("infos");
				for (int j = 0; j < infosArray.length(); j++) {
					JSONObject itemObject2 = infosArray.getJSONObject(j);
					ClassificationInfoBean infoBean2 = new ClassificationInfoBean();
					infoBean2.name1= itemObject2.getString("name1");
					infoBean2.name2= itemObject2.getString("name2");
					infoBean2.name3= itemObject2.getString("name3");
					infoBean2.url1= itemObject2.getString("url1");
					infoBean2.url2= itemObject2.getString("url2");
					infoBean2.url3= itemObject2.getString("url3");
					
					infoBeans.add(infoBean2);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infoBeans;
	}

	@Override
	protected String getIndexKey() {
		// TODO Auto-generated method stub
		return "category";
	}

}
