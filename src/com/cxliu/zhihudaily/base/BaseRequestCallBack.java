package com.cxliu.zhihudaily.base;

import net.tsz.afinal.http.AjaxCallBack;

public abstract class BaseRequestCallBack extends AjaxCallBack<String>
{

	@Override
	public AjaxCallBack<String> progress(boolean progress, int rate)
	{
		return super.progress(progress, rate);
	}

	@Override
	public void onLoading(long count, long current)
	{
	}

	@Override
	public void onSuccess(String t)
	{
		onResponseSuccess(t);
	}

	@Override
	public void onFailure(Throwable t, int errorNo, String strMsg)
	{
		onResponseFailure(strMsg);
	}

	public abstract void onResponseSuccess(String t);

	public abstract void onResponseFailure(String error);
}
