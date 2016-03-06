package com.cxliu.zhihudaily.view.detail;

import com.cxliu.zhihudaily.base.IBasicInterface;
import com.cxliu.zhihudaily.bean.StoryDetail;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author jqz
 * @version [版本号, 2016-2-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IDetail extends IBasicInterface
{
	/**
	 * 初始化配置WebView<一句话功能简述> <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void initWebView();

	/**
	 * 根据消息的id获取消息内容<一句话功能简述> <功能详细描述>
	 * 
	 * @param storyId
	 *            [消息的id]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void requestStoryDetail(int storyId);

	/**
	 * 根据@requestStoryDetail获取的消息内容填充WebView展示消息详细内容<一句话功能简述> <功能详细描述>
	 * 
	 * @param detail
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void fillWebView(StoryDetail detail);

	/**
	 * 结束当前detail activity<一句话功能简述> <功能详细描述>
	 * 
	 * @param [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void exitActivity();

	/**
	 * 初始化footer的显示和隐藏动画
	 */
	void initAnimations();

	/**
	 * 初始化分享Dialog
	 */
	void initShareDialog();
}
