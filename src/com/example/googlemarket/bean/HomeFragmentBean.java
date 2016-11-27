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
		
		
		public String author		;//黑马程序员
		public String date			;//2015-06-10
		public String downloadNum	;//40万+
		public String version		;//1.1.0605.0
		
		public List<AppSafeBean> safe			;//Array
		
		public class AppSafeBean{
			public String safeDes		;//		已通过安智市场安全检测，请放心使用
			public String safeDesColor	;//	0
			public String safeDesUrl	;//		app/com.itheima.www/safeDesUrl0.jpg
			public String safeUrl		;//		app/com.itheima.www/safeIcon0.jpg
		}
		
		public List<String> screen		;//Array

		@Override
		public String toString() {
			return "AppInfo [des=" + des + ", downloadUrl=" + downloadUrl
					+ ", iconUrl=" + iconUrl + ", id=" + id + ", name=" + name
					+ ", packageName=" + packageName + ", size=" + size
					+ ", stars=" + stars + ", author=" + author + ", date="
					+ date + ", downloadNum=" + downloadNum + ", version="
					+ version + ", safe=" + safe + ", screen=" + screen + "]";
		}
		
		
		
	}

	public List<String> picture;
}
