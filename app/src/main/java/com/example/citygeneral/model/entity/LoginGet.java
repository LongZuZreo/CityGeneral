package com.example.citygeneral.model.entity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginGet {

	// "UserID":"4E10D7F7F6823846",
	// "UserName":"A6AA9F089B89F1AF",
	// "RoleName":"我愿逆飞飞扬",
	// "RoleImg":"http://img.ccoo.cn/my/img/jianzhu/13.jpg",
	// "uSiteID":"9AD62BDF9136877A"
	public static Map<String, Object> getlogin(JSONObject obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserID", obj.opt("UserID"));
		map.put("UserName", obj.opt("UserName"));
		map.put("RoleName", obj.opt("RoleName"));
		map.put("RoleImg", obj.opt("RoleImg"));
		map.put("uSiteID", obj.opt("uSiteID"));
		map.put("ouSiteID", obj.opt("ouSiteID"));
		return map;
	}
}
