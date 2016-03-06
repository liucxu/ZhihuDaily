package com.cxliu.zhihudaily.base;

public interface IBasicInterface
{
	/**
	 * 初始化界面上的一些控件
	 */
	void initView();

	/**
	 * 显示圆形Dialog
	 */
	void showDialog();

	/**
	 * 隐藏圆形的Dialog
	 */
	void hideDialog();

	void onSuccess(int code, String t);

	void onFailure(int code, String t);
}
