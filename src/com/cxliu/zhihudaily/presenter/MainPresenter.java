package com.cxliu.zhihudaily.presenter;

import com.cxliu.zhihudaily.Const.Consts;
import com.cxliu.zhihudaily.URL.Urls;
import com.cxliu.zhihudaily.Utils.DateUtils;
import com.cxliu.zhihudaily.base.BasicPresenter;
import com.cxliu.zhihudaily.bean.ItemInfo;
import com.cxliu.zhihudaily.view.main.IMainView;
import com.google.gson.Gson;

public class MainPresenter extends BasicPresenter
{
	private IMainView mView;
	private static MainPresenter presenter;
	// 为了加载更多的时候会用到
	private static int PAGE = 0;

	private MainPresenter(IMainView view)
	{
		mView = view;
	}

	/**
	 * 
	 * @param view
	 * @return
	 */
	public static MainPresenter getInstance(IMainView view)
	{
		if (presenter == null)
		{
			presenter = new MainPresenter(view);
		}
		return presenter;
	}

	/**
	 * 页面获取今天最新消息包括界面顶部 ViewPager 滚动显示的显示内容接口 <功能详细描述>
	 * 
	 * @param url
	 *            http://news-at.zhihu.com/api/4/news/latest
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void getMainInfo(String url)
	{
		mView.showDialog();
		exeApi(url, Consts.API_ITEM_LAST_INFO);
	}

	/**
	 * 为了在下拉刷新，每刷新一次就加载一次前一天的数据, 利用 PAGE 计数URL:
	 * http://news.at.zhihu.com/api/4/news/before/20131119 若果需要查询 11 月 18
	 * 日的消息，before 后的数字应为 20131119 知乎日报的生日为 2013 年 5 月 19 日，若 before 后数字小于
	 * 20130520 ，只会接收到空消息
	 * 
	 * @param url
	 *            http://news.at.zhihu.com/api/4/news/before/
	 */
	public void loadMoreItem()
	{
		mView.showDialog();
		String newUrl = Urls.LOAD_MORE_ITEM_URL
				+ DateUtils.formateDateByPage(PAGE);
		PAGE = PAGE + 1;
		exeApi(newUrl, Consts.API_LOAD_MORE_ITEM);
	}

	@Override
	public void onSuccess(int code, String result)
	{
		int api_code = code;
		Gson gson = new Gson();
		mView.hideDialog();
		switch (api_code)
		{
			case Consts.API_ITEM_LAST_INFO:
				PAGE = 0;
				ItemInfo itemInfo = gson.fromJson(result, ItemInfo.class);
				mView.initViewPager(itemInfo.getTop_stories());
				mView.initListViewAdapter(itemInfo.getStories());
				break;
			case Consts.API_LOAD_MORE_ITEM:
				ItemInfo itemInfoMore = gson.fromJson(result, ItemInfo.class);
				mView.loadMoreItem(itemInfoMore.getStories());
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
