// Generated code from Butter Knife. Do not modify!
package com.cxliu.zhihudaily.view;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class Welcome$$ViewInjector {
  public static void inject(Finder finder, final com.cxliu.zhihudaily.view.Welcome target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361800, "field 'errorView'");
    target.errorView = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131361799, "field 'welcomeImage'");
    target.welcomeImage = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131361802, "field 'welcomeAuthor'");
    target.welcomeAuthor = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131361801, "field 'welcomeTitle'");
    target.welcomeTitle = (android.widget.TextView) view;
  }

  public static void reset(com.cxliu.zhihudaily.view.Welcome target) {
    target.errorView = null;
    target.welcomeImage = null;
    target.welcomeAuthor = null;
    target.welcomeTitle = null;
  }
}
