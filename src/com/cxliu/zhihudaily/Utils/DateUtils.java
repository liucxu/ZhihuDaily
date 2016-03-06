package com.cxliu.zhihudaily.Utils;

import java.util.Date;

import com.ab.util.AbStrUtil;

/**
 * 
 * @author liucxu
 * 
 */
public class DateUtils
{
	/**
	 * 为了实现下拉刷新功能，每次下拉的时候的日期格式为“20131119” 这个函数实现了根据page数得到相应的字符串
	 * 
	 * @param page
	 * @return
	 */
	public static String formateDateByPage(int page)
	{
		Date now = new Date();
		if (page < 0)
		{
			now.setDate(now.getDate() + 1);
		}
		else
		{
			now.setDate(now.getDate() - page);
		}
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append(now.getYear() + 1900)
				.append(AbStrUtil.strFormat2(now.getMonth() + 1 + ""))
				.append(AbStrUtil.strFormat2(now.getDate() + ""));
		return sbBuilder.toString();
	}
}
