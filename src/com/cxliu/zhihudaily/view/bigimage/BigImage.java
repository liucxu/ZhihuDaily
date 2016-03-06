package com.cxliu.zhihudaily.view.bigimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.ab.activity.AbActivity;
import com.ab.util.AbDialogUtil;
import com.cxliu.zhihudaily.R;
import com.cxliu.zhihudaily.Utils.ImageUtils;
import com.cxliu.zhihudaily.presenter.BigImagePresenter;
import com.cxliu.zhihudaily.view.detail.Detail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * <一句话功能简述> 详细页面点击图片通过JS调用显示大图的Activity <功能详细描述>
 * 
 * @author jqz
 * @version [版本号, 2016-2-18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BigImage extends AbActivity implements IBigImage
{
	private String imageUrl;
	private BigImagePresenter presenter;
	@InjectView(R.id.bigimage)
	ImageView bigImage;
	@InjectView(R.id.more)
	Button more;
	DisplayImageOptions options;
	ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_big_image);
		this.getTitleBar().setVisibility(View.GONE);
		ButterKnife.inject(this);
		Intent intent = getIntent();
		imageUrl = intent.getStringExtra("image");
		presenter = BigImagePresenter.getInstance(this);
		presenter.initImageLoader();
		presenter.initView();
	}

	@Override
	public void initView()
	{
		imageLoader.displayImage(imageUrl, bigImage, options);
	}

	@Override
	public void showDialog()
	{

	}

	@Override
	public void hideDialog()
	{

	}

	@Override
	public void onSuccess(int code, String t)
	{

	}

	@Override
	public void onFailure(int code, String t)
	{

	}

	@Override
	public void loadImage(String url)
	{

	}

	@Override
	public void saveImage()
	{

	}

	@Override
	public void showSelectDialog()
	{

	}

	@Override
	public void initSelectDialog()
	{

	}

	@OnClick({ R.id.more, R.id.bigimage })
	void calledByClick(View view)
	{
		int id = view.getId();
		switch (id)
		{
			case R.id.more:
				View mView = mInflater.inflate(R.layout.dialog_select, null);
				TextView save = (TextView) mView
						.findViewById(R.id.choice_one_text);
				TextView cancel = (TextView) mView
						.findViewById(R.id.choice_two_text);
				save.setOnClickListener(new View.OnClickListener()
				{

					@Override
					public void onClick(View v)
					{
						ImageUtils.saveDrawable(((BitmapDrawable) bigImage
								.getDrawable()).getBitmap(), BigImage.this);
						AbDialogUtil.removeDialog(BigImage.this);
					}
				});
				cancel.setOnClickListener(new View.OnClickListener()
				{

					@Override
					public void onClick(View v)
					{
						AbDialogUtil.removeDialog(BigImage.this);
					}
				});
				AbDialogUtil.showAlertDialog(mView);
				break;
			case R.id.bigimage:
				exitActivity();
				break;
			default:
				break;
		}
	}

	@Override
	public void exitActivity()
	{
		this.finish();
		Intent intent = new Intent(BigImage.this, Detail.class);
		startActivity(intent);
		overridePendingTransition(com.cxliu.zhihudaily.R.anim.zoomin,
				com.cxliu.zhihudaily.R.anim.zoomout);
		BigImagePresenter.ResetPresenter();
		imageLoader = null;
		options = null;
	}

	@Override
	public void initImageLoader()
	{
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(
						com.cxliu.zhihudaily.R.drawable.ic_launcher)
				// 设置图片Uri为空或是错误的时候显示的图片
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
				.build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));

	}

	@Override
	public void cancelDialog()
	{
		AbDialogUtil.removeDialog(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			exitActivity();
		}
		return true;
	}
}
