/**
 * 显示大图需要的Presenter
 */
package com.cxliu.zhihudaily.presenter;

import com.cxliu.zhihudaily.base.BasicPresenter;
import com.cxliu.zhihudaily.view.bigimage.IBigImage;

/**
 * @author liucxu 显示大图需要的Presenter
 */
public class BigImagePresenter extends BasicPresenter
{
	private static IBigImage mView;
	private static BigImagePresenter presenter;

	private BigImagePresenter(IBigImage view)
	{
		mView = view;
	}

	/**
	 * @param view
	 * @return
	 */
	public static BigImagePresenter getInstance(IBigImage view)
	{
		if (presenter == null)
		{
			presenter = new BigImagePresenter(view);
		}
		return presenter;
	}

	public static void ResetPresenter()
	{
		presenter = null;
		mView = null;
	}
	
	/**
	 * 初始化ImageLoader<一句话功能简述>
	 * <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void initImageLoader(){
	    mView.initImageLoader();
	}
	
	/**
	 * 初始化显示大图<一句话功能简述>
	 * <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void initView(){
	    mView.initView();
	}
}
