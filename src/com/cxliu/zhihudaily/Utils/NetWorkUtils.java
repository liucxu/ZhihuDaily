/**
 * 网络工具类
 */
package com.cxliu.zhihudaily.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author liucxu
 * 
 */
public class NetWorkUtils
{
	/**
	 * 判断是否联网
	 * 
	 * @return true 联网 false 没有网络
	 */
	public static boolean connectNetWork(Context mContext)
	{
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null)
		{
			return false;
		}
		NetworkInfo[] netInfos = cm.getAllNetworkInfo();
		if (netInfos == null)
		{
			return false;
		}
		for (NetworkInfo networkInfo : netInfos)
		{
			if (networkInfo.isConnected())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断wifi是否连接 为后期的wifi链接状态下加载大图 3G移动网络情况下不加载图片，这个版本还未加入
	 * 
	 * @param mContext
	 * @return
	 */
	public boolean isWifiConnected(Context mContext)
	{
		if (!connectNetWork(mContext))
		{
			return false;
		}
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo == null)
		{
			return false;
		}
		if (netInfo.getType() == ConnectivityManager.TYPE_WIFI)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 判断3G是否连接 为后期的wifi链接状态下加载大图 3G移动网络情况下不加载图片，这个版本还未加入
	 * 
	 * @param mContext
	 * @return
	 */
	public boolean is3GConnected(Context mContext)
	{
		if (!connectNetWork(mContext))
		{
			return false;
		}
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo == null)
		{
			return false;
		}
		if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
