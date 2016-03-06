package com.cxliu.zhihudaily.presenter;

import com.cxliu.zhihudaily.Const.Consts;
import com.cxliu.zhihudaily.base.BasicPresenter;
import com.cxliu.zhihudaily.bean.WelcomePageInfo;
import com.cxliu.zhihudaily.view.IWelcomeView;
import com.google.gson.Gson;

public class WelcomePresenter extends BasicPresenter
{
	private IWelcomeView mView;
	private static WelcomePresenter presenter;

	private WelcomePresenter(IWelcomeView view)
	{
		mView = view;
	}

	/**
	 * 
	 * @param view
	 * @return
	 */
	public static WelcomePresenter getInstance(IWelcomeView view)
	{
		if (presenter == null)
		{
			presenter = new WelcomePresenter(view);
		}
		return presenter;
	}

	/**
	 * 获取欢迎页面图片的信息
	 * 
	 * @param url
	 */
	public void getWelcomePicInfo(String url)
	{
		exeApi(url, Consts.API_WELCOME_INFO);
	}

	@Override
	public void onSuccess(int code, String t)
	{
		mView.onSuccess(code, t);
		int api_code = code;
		Gson gson = new Gson();
		switch (api_code)
		{
			case Consts.API_WELCOME_INFO:
				WelcomePageInfo welcomePageInfo = gson.fromJson(t,
						WelcomePageInfo.class);
				mView.showWelcomeImage(welcomePageInfo.getImg());
				mView.showAuthorInfo(welcomePageInfo.getText());
				mView.onSuccess(api_code, t);
				break;
			default:
				break;
		}
	}

	@Override
	public void onFailure(int code, String t)
	{
		mView.onFailure(code, t);
	}
}
