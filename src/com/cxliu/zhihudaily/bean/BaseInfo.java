package com.cxliu.zhihudaily.bean;

import java.util.List;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  jqz
 * @version  [版本号, 2016-1-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseInfo
{
private int id;
private int type;
private String ga_prefix;
private String title;
public int getId()
{
    return id;
}
public void setId( int id )
{
    this.id = id;
}
public int getType()
{
    return type;
}
public void setType( int type )
{
    this.type = type;
}
public String getGa_prefix()
{
    return ga_prefix;
}
public void setGa_prefix( String ga_prefix )
{
    this.ga_prefix = ga_prefix;
}
public String getTitle()
{
    return title;
}
public void setTitle( String title )
{
    this.title = title;
}
}
