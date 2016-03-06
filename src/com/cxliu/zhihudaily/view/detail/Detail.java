package com.cxliu.zhihudaily.view.detail;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.ab.activity.AbActivity;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.cxliu.zhihudaily.R;
import com.cxliu.zhihudaily.Const.Consts;
import com.cxliu.zhihudaily.bean.StoryDetail;
import com.cxliu.zhihudaily.presenter.DetailPresenter;
import com.cxliu.zhihudaily.view.bigimage.BigImage;
import com.cxliu.zhihudaily.view.main.Main;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

public class Detail extends AbActivity implements IDetail, OnTouchListener
{

	@InjectView(R.id.share_weixin_friend)
	Button weixinFriend;
	@InjectView(R.id.share_weixin_friend_circle)
	Button weixinCircle;
	private int id;
	@InjectView(R.id.webview)
	WebView mWebView;
	@InjectView(R.id.footer)
	RelativeLayout footer;
	@InjectView(R.id.back)
	ImageView back;
	@InjectView(R.id.share)
	ImageView share;
	private DetailPresenter presenter;
	// 分享简介
	private String des = "";
	// 手指向右滑动时的最小距离
	private static final int XDISTANCE_MIN = 150;
	// 记录手指按下时的横坐标。
	private float yDown;
	// 记录手指移动时的横坐标。
	private float yMove;
	// 两次点击的退出实现
	int flag = 0;
	// U盟分享图片
	UMImage image = new UMImage(this, R.drawable.ic_launcher);
	// Umeng 的详细画面对应的storyDetail
	StoryDetail storyDetail;
	final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");
	Animation showAnimation;
	Animation hideAnimation;
	LayoutInflater mInflater;
	View dialogView;
	ImageView weixinImageView;
	ImageView weixinCircleImageView;
	Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_item_detail);
		this.getTitleBar().setVisibility(View.GONE);
		ButterKnife.inject(this);
		Intent intent = getIntent();
		id = intent.getIntExtra("ItemId", -1);
		des = intent.getStringExtra("Des");
		image = new UMImage(this, R.drawable.ic_launcher);
		mWebView.setOnTouchListener(new OnTouchListener()
		{

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					yDown = event.getY();
				}
				else if (event.getAction() == MotionEvent.ACTION_MOVE)
				{
					yMove = event.getY() - yDown;
					if (yMove > 100)
					{
						// 显示
						footer.setVisibility(View.VISIBLE);
						footer.setAnimation(showAnimation);
					}
					else if (yMove < -100)
					{
						// 隐藏
						footer.setVisibility(View.GONE);
						footer.setAnimation(hideAnimation);
					}
					Log.i("onTouch", "onTouch-->" + yMove);
				}
				return false;
			}
		});
	}

	protected void onResume()
	{
		super.onResume();
		presenter = DetailPresenter.getInstance(this);
		presenter.getDetailInfo(id);
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
		hideDialog();
	}

	@Override
	public void onFailure(int code, String t)
	{
		hideDialog();
		AbToastUtil.showToast(this, R.string.error_toast);
	}

	@Override
	public void requestStoryDetail(int storyId)
	{

	}

	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	@Override
	public void fillWebView(StoryDetail detail)
	{
		storyDetail = detail;
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(new JSInterface(this), "imagelistner");
		StringBuffer body = new StringBuffer();
		body.append("<html  lang=\"zh-cn\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		body.append("<link rel=\"stylesheet\" type=\"text/css\" href=");
		body.append("\"");
		body.append(detail.getCss().get(0));
		body.append("\"");
		body.append("></link>");
		body.append("<script type=\"text/javascript\">");
		// 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
		body.append("function setClick(){");
		body.append("var objs = document.getElementsByTagName(\"img\"); ");
		body.append("for(var i=0;i<objs.length;i++)  {");
		body.append("objs[i].onclick=function(){");
		body.append("window.imagelistner.openImage(this.src); };}");
		body.append("}");
		body.append("</script>");
		body.append("</head>");
		body.append(detail.getBody());
		body.append("</html>");
		String htmlString = body.toString();
		int index = htmlString.indexOf("img-place-holder\">");
		if (index != -1)
		{
			String img = "<img height=\"200\" width=\"400\" src=\"";
			img = img + detail.getImage() + "\" />";
			htmlString = htmlString.replaceAll("img-place-holder\">",
					"img-place-holder\">" + img);
		}
		if (mWebView == null)
		{
			mWebView = (WebView) findViewById(R.id.webview);
		}
		mWebView.setWebViewClient(new MyWebViewClient());
		mWebView.setWebChromeClient(new MyWebChromeClient());
		mWebView.loadDataWithBaseURL("", htmlString, "text/html", "UTF-8", "");
	}

	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	@Override
	public void initWebView()
	{
		WebSettings ws = mWebView.getSettings();
		ws.setDefaultTextEncodingName("UTF-8"); // 设置文本编码
		ws.setJavaScriptEnabled(true);// JS可用
		ws.setAppCacheEnabled(false);
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			exitActivity();
			return true;
		}
		return false;
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mWebView.destroy();
	}

	@Override
	public void exitActivity()
	{
		this.finish();
		Intent intent = new Intent(Detail.this, Main.class);
		startActivity(intent);
		overridePendingTransition(com.cxliu.zhihudaily.R.anim.zoomin,
				com.cxliu.zhihudaily.R.anim.zoomout);
		DetailPresenter.ResetPresenter();
	}

	// 注入js函数监听
	private void addImageClickListner()
	{
		// 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
		mWebView.loadUrl("javascript:setClick()");
	}

	// js通信接口
	public class JSInterface
	{

		private Context mContext;

		public JSInterface(Context context)
		{
			this.mContext = context;
		}

		@JavascriptInterface
		public void openImage(String img)
		{
			Intent intent = new Intent();
			intent.putExtra("image", img);
			intent.setClass(Detail.this, BigImage.class);
			Detail.this.startActivity(intent);
		}
	}

	private class MyWebViewClient extends WebViewClient
	{
		@Override
		public void onPageFinished(WebView view, String url)
		{
			super.onPageFinished(view, url);
		}
	}

	private class MyWebChromeClient extends WebChromeClient
	{
		@Override
		public void onProgressChanged(WebView view, int newProgress)
		{
			super.onProgressChanged(view, newProgress);
			if (newProgress == 100)
			{
				addImageClickListner();
			}
		}
	}

	@OnClick({ R.id.share_weixin_friend, R.id.share_weixin_friend_circle })
	void share(View v)
	{
		int id = v.getId();
		switch (id)
		{

			case R.id.share_weixin_friend:
				// 支持微信
				shareWeiXinFreinds();
				break;
			case R.id.share_weixin_friend_circle:
				// 支持微信朋友圈
				shareToCircle();
				break;
			default:
				break;
		}
	}

	@SuppressWarnings("deprecation")
	void shareToCircle()
	{
		UMWXHandler wxCircleHandler = new UMWXHandler(this,
				"wx00c714cfa3c77e4c", Consts.WEIXIN_SECRET);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
		// 设置微信朋友圈分享内容
		CircleShareContent circleMedia = new CircleShareContent();
		// 设置朋友圈title
		circleMedia.setTitle(storyDetail.getTitle());
		circleMedia.setShareContent(des);
		circleMedia.setShareImage(image);
		circleMedia.setTargetUrl(storyDetail.getShare_url());
		mController.setShareMedia(circleMedia);
		mController.postShare(this, SHARE_MEDIA.WEIXIN_CIRCLE, null);
	}

	@SuppressWarnings("deprecation")
	void shareWeiXinFreinds()
	{
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(this, Consts.APP_ID,
				Consts.WEIXIN_SECRET);
		wxHandler.addToSocialSDK();
		// 设置微信好友分享内容
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		// 设置分享文字
		weixinContent.setShareContent(des);
		// 设置title
		weixinContent.setTitle(storyDetail.getTitle());
		// 设置分享内容跳转URL
		weixinContent.setTargetUrl(storyDetail.getShare_url());
		// 设置分享图片
		weixinContent.setShareImage(image);
		mController.setShareMedia(weixinContent);
		mController.postShare(this, SHARE_MEDIA.WEIXIN, null);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return false;
	}

	@Override
	public void initAnimations()
	{
		showAnimation = AnimationUtils.loadAnimation(this, R.anim.footer_show);
		hideAnimation = AnimationUtils.loadAnimation(this, R.anim.footrt_hide);
	}

	@OnClick({ R.id.back, R.id.share })
	void clickButton(View view)
	{
		int id = view.getId();
		switch (id)
		{
			case R.id.back:
				exitActivity();
				break;
			case R.id.share:
				initShareDialog();
				dialog.show();
				break;
			default:
				break;
		}
	}

	@Override
	public void initShareDialog()
	{
		mInflater = LayoutInflater.from(this);
		dialogView = mInflater.inflate(R.layout.dialog_share, null);
		weixinImageView = (ImageView) dialogView.findViewById(R.id.weixin);
		weixinCircleImageView = (ImageView) dialogView
				.findViewById(R.id.weixin_circle);
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(dialogView);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.BOTTOM);
		dialogWindow.setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		dialogWindow.setAttributes(lp);
		OnClickListener listener = new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				int id = v.getId();
				switch (id)
				{
					case R.id.weixin:
						shareWeiXinFreinds();
						dialog.dismiss();
						break;
					case R.id.weixin_circle:
						shareToCircle();
						dialog.dismiss();
						break;
					default:
						break;
				}
			}
		};
		weixinImageView.setOnClickListener(listener);
		weixinCircleImageView.setOnClickListener(listener);
	}
}
