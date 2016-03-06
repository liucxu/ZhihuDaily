package com.cxliu.zhihudaily.view.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ab.activity.AbActivity;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.pullview.AbPullToRefreshView.OnFooterLoadListener;
import com.ab.view.pullview.AbPullToRefreshView.OnHeaderRefreshListener;
import com.ab.view.sliding.AbSlidingPlayView;
import com.ab.view.sliding.AbSlidingPlayView.AbOnItemClickListener;
import com.cxliu.zhihudaily.R;
import com.cxliu.zhihudaily.URL.Urls;
import com.cxliu.zhihudaily.adapter.ItemStoryAdapter;
import com.cxliu.zhihudaily.bean.ItemStory;
import com.cxliu.zhihudaily.bean.TopStory;
import com.cxliu.zhihudaily.presenter.MainPresenter;
import com.cxliu.zhihudaily.view.detail.Detail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class Main extends AbActivity implements IMainView,
		OnHeaderRefreshListener, OnFooterLoadListener, AbOnItemClickListener
{

	@InjectView(R.id.mPullRefreshView)
	AbPullToRefreshView mPullRefreshView;

	@InjectView(R.id.mListView)
	ListView mListView;

	private MainPresenter presenter;

	ItemStoryAdapter listViewAdapter;

	private List<ItemStory> stories = new ArrayList<ItemStory>();

	private AbSlidingPlayView mSlidingPlayView = null;

	private DisplayImageOptions options;

	private ImageLoader imageLoader;

	private List<TopStory> topStories = new ArrayList<TopStory>();

	// 两次点击的退出实现
	int flag = 0;

	private Handler exitHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			flag = 0;
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_main);
		this.getTitleBar().setVisibility(View.GONE);
		ButterKnife.inject(this);
		// 设置监听器
		mPullRefreshView.setOnHeaderRefreshListener(this);
		mPullRefreshView.setOnFooterLoadListener(this);
		initData();
		initImageLoader();
	}

	@Override
	public void initView()
	{
	}

	@Override
	public void showDialog()
	{
		AbDialogUtil.showProgressDialog(this, 0, "请等待");
	}

	@Override
	public void hideDialog()
	{
		AbDialogUtil.removeDialog(this);
	}

	@Override
	public void onSuccess(int code, String t)
	{
	}

	@Override
	public void onFailure(int code, String t)
	{
		hideDialog();
		AbToastUtil.showToast(this, R.string.error_toast);
	}

	@Override
	public void onFooterLoad(AbPullToRefreshView view)
	{
		presenter.loadMoreItem();
	}

	@Override
	public void onHeaderRefresh(AbPullToRefreshView view)
	{
		cleanViewPagerAdapter();
		presenter.getMainInfo(Urls.ITEM_INFO_URL);
	}

	/**
	 * 初始化
	 */
	private void initData()
	{
	}

	@Override
	public void initListViewAdapter(List<ItemStory> items)
	{
		mPullRefreshView.onHeaderRefreshFinish();
		if (items == null)
		{
			items = new ArrayList<ItemStory>();
		}
		stories.addAll(items);
		listViewAdapter = new ItemStoryAdapter(this, stories);
		mListView.setAdapter(listViewAdapter);
		listViewAdapter.notifyDataSetChanged();
	}

	@Override
	public void showListView()
	{

	}

	@Override
	public void cleanListViewAdapter()
	{

	}

	@Override
	public void initViewPager(List<TopStory> topItems)
	{
		if (topStories != null && topStories.size() > 0)
		{
			return;
		}
		mSlidingPlayView = new AbSlidingPlayView(this);
		if (topItems == null)
		{
			topItems = new ArrayList<TopStory>();
		}
		topStories.clear();
		topStories.addAll(topItems);
		for (TopStory topStory : topStories)
		{
			final View mPlayView = mInflater.inflate(R.layout.viewpager_item,
					null);
			ImageView mPlayImage = (ImageView) mPlayView
					.findViewById(R.id.view_pager_img);
			TextView mPlayText = (TextView) mPlayView
					.findViewById(R.id.view_pager_des);
			mPlayText.setText(topStory.getTitle());
			imageLoader.displayImage(topStory.getImage(), mPlayImage, options);
			mSlidingPlayView.addView(mPlayView);
		}
		mSlidingPlayView.setNavHorizontalGravity(Gravity.RIGHT);
		mSlidingPlayView.startPlay();
		mSlidingPlayView.setOnItemClickListener(this);
		// 设置高度
		mSlidingPlayView.setLayoutParams(new AbsListView.LayoutParams(
				LayoutParams.FILL_PARENT, 350));
		mListView.addHeaderView(mSlidingPlayView);
		// 解决冲突问题
		mSlidingPlayView.setParentListView(mListView);
	}

	@Override
	public void cleanViewPagerAdapter()
	{
		if (stories == null)
		{
			stories = new ArrayList<ItemStory>();
		}
		stories.clear();
		listViewAdapter.setList(stories);
		listViewAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		if (stories.size() == 0)
		{
			if (presenter == null)
			{
				presenter = MainPresenter.getInstance(this);
			}
			presenter.getMainInfo(Urls.ITEM_INFO_URL);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (flag == 1)
			{
				this.finish();
				System.exit(1);
			}
			else
			{
				flag = 1;
				AbToastUtil.showToast(this, "再次点击返回按键退出应用");
				exitHandler.sendEmptyMessageDelayed(0x01, 1500);
			}
			return true;
		}
		return false;
	}

	@Override
	public void loadMoreItem(List<ItemStory> itemStories)
	{
		if (itemStories == null)
		{
			itemStories = new ArrayList<ItemStory>();
		}
		int position = stories.size();
		stories.addAll(itemStories);
		listViewAdapter.setList(stories);
		listViewAdapter.notifyDataSetChanged();
		mPullRefreshView.onFooterLoadFinish();
		mListView.setSelection(position - 1);
	}

	@Override
	public void initImageLoader()
	{
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_launcher)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnLoading(com.cxliu.zhihudaily.R.drawable.loading)
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
				.build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
	}

	/**
	 * 点击viewpager跳转到detail画面
	 */
	@Override
	public void onClick(int position)
	{
		Intent intent = new Intent(this, Detail.class);
		intent.putExtra("ItemId", topStories.get(position).getId());
		overridePendingTransition(com.cxliu.zhihudaily.R.anim.zoomin,
				com.cxliu.zhihudaily.R.anim.zoomout);
		startActivity(intent);
	}
}
