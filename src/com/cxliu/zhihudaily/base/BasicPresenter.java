package com.cxliu.zhihudaily.base;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

public class BasicPresenter
{
	public void exeApi(String url, int api_code)
	{
		FinalHttp finalHttp = new FinalHttp();
		finalHttp.addHeader("Accept-Charset", "UTF-8");
		finalHttp.configCharset("UTF-8");
		finalHttp.configRequestExecutionRetryCount(3);
		finalHttp.configTimeout(5000);// 超时时间
		finalHttp.get(url, getCallBack(api_code));
	}

	private AjaxCallBack<String> getCallBack(final int api_code)
	{
		return new AjaxCallBack<String>()
		{

			@Override
			public void onStart()
			{
			}

			@Override
			public void onSuccess(String t)
			{
				BasicPresenter.this.onSuccess(api_code, t);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg)
			{
				BasicPresenter.this.onFailure(api_code, strMsg);
			}

		};
	}

	/**
	 * 
	 * @param code
	 * @param t
	 */
	public void onSuccess(int code, String t)
	{

	}

	/**
	 * 
	 * @param code
	 * @param t
	 */
	public void onFailure(int code, String t)
	{

	}
}
