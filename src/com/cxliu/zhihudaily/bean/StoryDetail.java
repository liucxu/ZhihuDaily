
package com.cxliu.zhihudaily.bean;

import java.util.List;

/**
 * 消息内容对应的实体类
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  jqz
 * @version  [版本号, 2016-2-2]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class StoryDetail
{
    /**
     * body : HTML 格式的新闻
     */
    private String body;

    /**
     * 图片的内容提供方。为了避免被起诉非法使用图片，在显示图片时最好附上其版权信息。
     */
    private String image_source;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 获得的图片同 最新消息 获得的图片分辨率不同。这里获得的是在文章浏览界面中使用的大图。
     */
    private String image;

    /**
     * 供手机端的 WebView(UIWebView) 使用
     */
    private List<String> css;
    
    public List<String> getCss()
    {
        return css;
    }

    public void setCss( List<String> css )
    {
        this.css = css;
    }

    public String getShare_url()
    {
        return share_url;
    }

    public void setShare_url( String share_url )
    {
        this.share_url = share_url;
    }

    /**
     * 供在线查看内容与分享至 SNS 用的 URL
     */
    private String share_url;
    
    public String getBody()
    {
        return body;
    }

    public void setBody( String body )
    {
        this.body = body;
    }

    public String getImage_source()
    {
        return image_source;
    }

    public void setImage_source( String image_source )
    {
        this.image_source = image_source;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage( String image )
    {
        this.image = image;
    }
}
