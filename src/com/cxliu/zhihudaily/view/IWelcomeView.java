package com.cxliu.zhihudaily.view;

import com.cxliu.zhihudaily.base.IBasicInterface;

/**
 * 欢迎页面对应的View接口
 * 
 * @author liucxu
 * 
 */
public interface IWelcomeView extends IBasicInterface
{

	/**
	 * 初始化欢迎页面的信息
	 */
	void initWelcomePageInfo(String welcomeRequestUrl);

	/**
	 * 显示欢迎页面
	 * 
	 * @param url
	 */
	void showWelcomeImage(String url);

	/**
	 * 设置显示图片作者信息
	 * 
	 * @param author
	 */
	void showAuthorInfo(String author);
}
