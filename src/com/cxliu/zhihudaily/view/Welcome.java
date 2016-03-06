package com.cxliu.zhihudaily.view;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.bitmap.core.BitmapDisplayConfig;
import net.tsz.afinal.bitmap.core.BitmapDisplayConfig.AnimationType;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
import com.cxliu.zhihudaily.R;
import com.cxliu.zhihudaily.URL.Urls;
import com.cxliu.zhihudaily.Utils.DisplayUtils;
import com.cxliu.zhihudaily.presenter.WelcomePresenter;
import com.cxliu.zhihudaily.view.main.Main;

public class Welcome extends AbActivity implements IWelcomeView
{

	private WelcomePresenter presenter;

	@InjectView(R.id.welcome_image)
	ImageView welcomeImage;

	@InjectView(R.id.welcome_title)
	TextView welcomeTitle;

	@InjectView(R.id.welcome_author)
	TextView welcomeAuthor;

	@InjectView(R.id.welcome_image_error)
	ImageView errorView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_welcome);
		this.getTitleBar().setVisibility(View.GONE);
		ButterKnife.inject(this);
		initData();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
	public void initWelcomePageInfo(String welcomeRequestUrl)
	{
	}

	@Override
	public void onSuccess(int code, String t)
	{
		errorView.setVisibility(View.GONE);
		welcomeImage.setVisibility(View.VISIBLE);
	}

	@Override
	public void onFailure(int code, String t)
	{
		AbToastUtil.showToast(this, R.string.error_toast);
		welcomeImage.setVisibility(View.GONE);
		errorView.setVisibility(View.VISIBLE);
		errorView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				initData();
			}
		});
	}

	private void initData()
	{
		if (presenter == null)
		{
			presenter = WelcomePresenter.getInstance(this);
		}
		// 如果没有联网，需要显示没有联网的图标，并且在点击的时候判断是否联网，如果联网了就去做presenter.getWelcomePicInfo
		// 获取启动画面图片
		// if (NetWorkUtils.connectNetWork(this))
		// {
		presenter.getWelcomePicInfo(Urls.WELCOME_INFO_URL
				+ DisplayUtils.GetDisplayMetrics(this));
		// }
	}

	@Override
	public void showWelcomeImage(String url)
	{
		showImage(url);
	}

	private void showImage(final String url)
	{
		final FinalBitmap finalBitMap = FinalBitmap.create(this);
		BitmapDisplayConfig config = new BitmapDisplayConfig();
		config.setAnimationType(AnimationType.userDefined);
		Animation animation = AnimationUtils.loadAnimation(this,
				R.anim.welcome_fade_in_scale);
		animation.setFillAfter(true);
		animation.setDuration(4000);
		animation.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				Intent intent = new Intent(Welcome.this, Main.class);
				Welcome.this.startActivity(intent);
				Welcome.this.finish();
				overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}
		});
		config.setAnimation(animation);
		finalBitMap.display(welcomeImage, url, config);
	}

	@Override
	public void showAuthorInfo(String author)
	{
		Typeface type = Typeface.createFromAsset(getAssets(),
				"fonts/DroidSerif-Italic.ttf");
		welcomeAuthor.setTypeface(type);
		welcomeAuthor.setText(String.format("@%s", author));
	}

	@Override
	public void initView()
	{
		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/DroidSerif-BoldItalic.ttf");
		welcomeTitle.setTypeface(typeface);
		welcomeTitle.setText(R.string.welcome_page_title);
	}
}
