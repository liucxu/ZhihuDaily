package com.cxliu.zhihudaily.view.main;

import java.util.List;

import com.cxliu.zhihudaily.base.IBasicInterface;
import com.cxliu.zhihudaily.bean.ItemStory;
import com.cxliu.zhihudaily.bean.TopStory;

/**
 * News展示页面对应的View接口
 * 
 * @author liucxu
 */
public interface IMainView extends IBasicInterface
{
	/**
	 * 初始化Adapter<一句话功能简述> <功能详细描述>
	 * 
	 * @param stories
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void initListViewAdapter(List<ItemStory> stories);

	/**
	 * 清空ListView Adapter<一句话功能简述> <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void cleanListViewAdapter();

	/**
	 * 初始化ViewPager<一句话功能简述> <功能详细描述>
	 * 
	 * @param topStories
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void initViewPager(List<TopStory> topStories);

	/**
	 * 清空ViewPager Adapter<一句话功能简述> <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void cleanViewPagerAdapter();

	void showListView();

	/**
	 * 下拉刷新的时候加载更多的数据<一句话功能简述> <功能详细描述>
	 * 
	 * @param stories
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void loadMoreItem(List<ItemStory> stories);

	void initImageLoader();
}
