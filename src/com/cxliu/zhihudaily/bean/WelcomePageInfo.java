package com.cxliu.zhihudaily.bean;

/**
 * 欢迎页面的响应信息Bean
 * 
 * @author liucxu
 * 
 */
public class WelcomePageInfo
{
	/**
	 * 供显示的图片版权信息
	 */
	private String text;
	/**
	 * 图像的 URL
	 */
	private String img;

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	@Override
	public String toString()
	{
		return "text-->" + text + " " + "img-->" + img;
	}
}
