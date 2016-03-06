package com.cxliu.zhihudaily.app;

import android.app.Application;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author jqz
 * @version [版本号, 2016-2-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class App extends Application
{
	public App()
	{
		super();
		// 暂时只支持微信的朋友圈和好友分享
		// PlatformConfig.setWeixin( "wx00c714cfa3c77e4c",
		// "5a1bf23ffb6548938eadd31553a5f4bd" );
		// UMWXHandler wxHandler = new UMWXHandler(this, "wx00c714cfa3c77e4c",
		// "5a1bf23ffb6548938eadd31553a5f4bd");
	}

	{
		// 微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d

		// PlatformConfig.setQQZone("100424468",
		// "c7394704798a158208a74ab60104f0ba");
	}
}
