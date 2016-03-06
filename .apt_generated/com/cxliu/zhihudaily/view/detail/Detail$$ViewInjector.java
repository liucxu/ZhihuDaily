// Generated code from Butter Knife. Do not modify!
package com.cxliu.zhihudaily.view.detail;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class Detail$$ViewInjector {
  public static void inject(Finder finder, final com.cxliu.zhihudaily.view.detail.Detail target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361812, "field 'back' and method 'clickButton'");
    target.back = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.clickButton(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361813, "field 'share' and method 'clickButton'");
    target.share = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.clickButton(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361811, "field 'footer'");
    target.footer = (android.widget.RelativeLayout) view;
    view = finder.findRequiredView(source, 2131361796, "field 'mWebView'");
    target.mWebView = (android.webkit.WebView) view;
    view = finder.findRequiredView(source, 2131361795, "field 'weixinCircle' and method 'share'");
    target.weixinCircle = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.share(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361794, "field 'weixinFriend' and method 'share'");
    target.weixinFriend = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.share(p0);
        }
      });
  }

  public static void reset(com.cxliu.zhihudaily.view.detail.Detail target) {
    target.back = null;
    target.share = null;
    target.footer = null;
    target.mWebView = null;
    target.weixinCircle = null;
    target.weixinFriend = null;
  }
}
