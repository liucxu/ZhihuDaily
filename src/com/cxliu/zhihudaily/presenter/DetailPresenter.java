package com.cxliu.zhihudaily.presenter;

import com.cxliu.zhihudaily.Const.Consts;
import com.cxliu.zhihudaily.URL.Urls;
import com.cxliu.zhihudaily.base.BasicPresenter;
import com.cxliu.zhihudaily.bean.StoryDetail;
import com.cxliu.zhihudaily.view.detail.IDetail;
import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class DetailPresenter extends BasicPresenter
{
	private static IDetail mView;
	private static DetailPresenter presenter;
	// 为了加载更多的时候会用到
	private static int PAGE = 0;
	private static final String appid="";
	private static IWXAPI wxApi;
	private DetailPresenter(IDetail view)
	{
		mView = view;
	}

	/**
	 * @param view
	 * @return
	 */
	public static DetailPresenter getInstance(IDetail view)
	{
		if (presenter == null)
		{
			presenter = new DetailPresenter(view);
		}
		return presenter;
	}

	public void getDetailInfo(int id)
	{
		mView.showDialog();
		exeApi(Urls.ITEM_INFO_DETAIL_URL + id, Consts.API_ITEM_DETAIL_INFO);
	}

	@Override
	public void onSuccess(int code, String content)
	{
		int api_code = code;
		mView.onSuccess(api_code, content);
		switch (api_code)
		{
			case Consts.API_ITEM_DETAIL_INFO:
				StoryDetail detail = (new Gson()).fromJson(content,
						StoryDetail.class);
				mView.fillWebView(detail);
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

	public static void ResetPresenter()
	{
		presenter = null;
		mView = null;
	}
}
