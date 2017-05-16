package com.example.citygeneral.utils;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.security.MessageDigest;

public class ThreadTools {
	public static void threadMethod(final PublicUtils utils, Context context) {
		final String path;
		if (utils.isLogin()) {
			path = "http://"
					+ utils.getUserInfo().getSiteSqURL()
					+ "/Common/SsoSetUser/?action=login&username="
					+ utils.getUserName()
					+ "&siteid="
					+ utils.getCityId()
					+ "&md5="
					+ encodeByMD5("login" + utils.getUserName()
							+ utils.getCityId() + "ccoocity");
		} else {
			return;
		}

		WebView wv = new WebView(context);
		wv.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(path);
				return true;
			}
		});
		System.out.println("!!!!!" + path);
		// new Thread() {
		// public void run() {
		// try {
		// System.out.println("登录请求");
		// DefaultHttpClient client = new DefaultHttpClient();
		// // String newname = URLEncoder.encode(name);
		// // String newpassword = URLEncoder.encode(password);
		//
		// HttpGet httpGet = new HttpGet(path);
		// HttpResponse response = null;
		//
		// response = client.execute(httpGet);
		//
		// int code = response.getStatusLine().getStatusCode();
		// if (code == 200) {
		// System.out.println("成功");
		// }
		// } catch (Exception e1) {
		// System.out.println(33);
		// e1.printStackTrace();
		// }
		// }
		// }.start();

	}

	/** 对字符串进行MD5加密 */
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串
	 *
	 * @param
	 * @return 十六进制字符串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
