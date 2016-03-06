package com.cxliu.zhihudaily.Utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class DisplayUtils
{
	/**
	 * 启动界面图像获取的时候，需要填写需要获取的图片图片分辨率，但是分辨率是320*432 480*728 720*1184 1080*1776 中之一
	 * 
	 * @return
	 */
	public static String GetDisplayMetrics(Activity context)
	{
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;
		if (width == 320)
		{
			return "320*432";
		}
		else if (width == 480)
		{
			return "480*728";
		}
		else if (width == 720)
		{
			return "720*1184";
		}
		else if (width == 1080)
		{
			return "1080*1776";
		}
		return "1080*1776";
	}
}
