
package com.cxliu.zhihudaily.bean;

import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author jqz
 * @version [版本号, 2016-1-31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ItemInfo
{
    private String date;
    private List<ItemStory> stories;
    private List<TopStory> top_stories;

    public String getDate()
    {
        return date;
    }

    public void setDate( String date )
    {
        this.date = date;
    }

    public List<ItemStory> getStories()
    {
        return stories;
    }

    public void setStories( List<ItemStory> stories )
    {
        this.stories = stories;
    }

    public List<TopStory> getTop_stories()
    {
        return top_stories;
    }

    public void setTop_stories( List<TopStory> top_stories )
    {
        this.top_stories = top_stories;
    }

}
