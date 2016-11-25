package com.example.googlemarket.bean;

import java.util.List;

/**
 * 
 * @author Administrator
 * @company Newbie
 * @date 2016-11-18
 * @des 封装主页的json数据
 */
public class HomeFragmentBean {
	public List<AppInfo> list;

	public class AppInfo {
		public String des; // 产品介绍：google市场app测试。
		public String downloadUrl; // app/com.itheima.www/com.itheima.www
		public String iconUrl; // app/com.itheima.www/icon.jpg
		public String id; // 1525489
		public String name; // 黑马程序员
		public String packageName; // com.itheima.www
		public String size; // 91767
		public float stars; // 5
	}

	public List<String> picture;
}
