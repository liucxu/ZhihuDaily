package com.cxliu.zhihudaily.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;

/**
 * WebView处理工具类
 * 
 * @author liucxu
 * 
 */
public class WebViewUtils
{
	/**
	 * WebView初始化方法
	 * 
	 * @param mContext
	 * @param webview
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public static void initWebView(Context mContext, WebView webview)
	{
		webview.getSettings().setJavaScriptEnabled(true);
		// webview.addJavascriptInterface(new JavascriptInterface(this),
		// "imagelistner");
	}
}
