// Generated code from Butter Knife. Do not modify!
package com.cxliu.zhihudaily.view.bigimage;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class BigImage$$ViewInjector {
  public static void inject(Finder finder, final com.cxliu.zhihudaily.view.bigimage.BigImage target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361793, "field 'more' and method 'calledByClick'");
    target.more = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.calledByClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131361792, "field 'bigImage' and method 'calledByClick'");
    target.bigImage = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.calledByClick(p0);
        }
      });
  }

  public static void reset(com.cxliu.zhihudaily.view.bigimage.BigImage target) {
    target.more = null;
    target.bigImage = null;
  }
}
