package com.cxliu.zhihudaily.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxliu.zhihudaily.bean.ItemStory;
import com.cxliu.zhihudaily.view.detail.Detail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ItemStoryAdapter extends BaseAdapter
{

	private Context mContext;
	private List<ItemStory> items = new ArrayList<ItemStory>();
	private LayoutInflater mInflater;
	DisplayImageOptions options;
	ImageLoader imageLoader;

	public ItemStoryAdapter(Context context, List<ItemStory> stories)
	{
		mContext = context;
		items = new ArrayList<ItemStory>();
		if (stories != null)
		{
			items.addAll(stories);
		}
		else
		{
			items.addAll(new ArrayList<ItemStory>());
		}
		if (mInflater == null)
		{
			mInflater = LayoutInflater.from(context);
		}
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(
						com.cxliu.zhihudaily.R.drawable.ic_launcher)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnLoading(com.cxliu.zhihudaily.R.drawable.loading)
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
				.build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
	}

	@Override
	public int getCount()
	{

		return items.size();
	}

	@Override
	public Object getItem(int position)
	{

		return items.get(position);
	}

	@Override
	public long getItemId(int position)
	{

		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(
					com.cxliu.zhihudaily.R.layout.item_info, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(com.cxliu.zhihudaily.R.id.item_title);
			viewHolder.image = (ImageView) convertView
					.findViewById(com.cxliu.zhihudaily.R.id.item_image);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(items.get(position).getTitle());
		imageLoader.displayImage(items.get(position).getImages().get(0),
				viewHolder.image, options);
		convertView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(mContext, Detail.class);
				intent.putExtra("ItemId", items.get(position).getId());
				intent.putExtra( "Des", items.get(position).getTitle() );
				((Activity) mContext).overridePendingTransition(
						com.cxliu.zhihudaily.R.anim.zoomin,
						com.cxliu.zhihudaily.R.anim.zoomout);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

	private static class ViewHolder
	{
		TextView title;
		ImageView image;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param items
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void setList(List<ItemStory> itemStories)
	{
		items.clear();
		items.addAll(itemStories);
		this.notifyDataSetChanged();
	}

	/**
	 * <一句话功能简述> listview加载更多的数据<功能详细描述>
	 * 
	 * @param itemStories
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void loadMoreItems(List<ItemStory> itemStories)
	{
		items.addAll(itemStories);
		this.notifyDataSetChanged();
	}

}
