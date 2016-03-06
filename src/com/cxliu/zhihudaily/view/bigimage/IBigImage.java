package com.cxliu.zhihudaily.view.bigimage;

import com.cxliu.zhihudaily.base.IBasicInterface;

/**
 * 显示大图的Activity对应的接口
 * 
 * @author liucxu
 * 
 */
public interface IBigImage extends IBasicInterface
{
	/**
	 * 根据url加载大图
	 * 
	 * @param url
	 *            图片url
	 */
	void loadImage(String url);

	/**
	 * 保存图片到手机内存或者是内存卡
	 */
	void saveImage();

	/**
	 * 初始化选择Dialog
	 */
	void initSelectDialog();

	/**
	 * 弹出选择保存还是取消的Dialog
	 */
	void showSelectDialog();

	/**
	 * 结束当前BigImage activity<一句话功能简述> <功能详细描述>
	 * 
	 * @param [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void exitActivity();
	
	/**
	 * 初始化ImageLoader<一句话功能简述>
	 * <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void initImageLoader();
	
	/**
	 * 隐藏Dialog,取消按钮<一句话功能简述>
	 * <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void cancelDialog();
}
