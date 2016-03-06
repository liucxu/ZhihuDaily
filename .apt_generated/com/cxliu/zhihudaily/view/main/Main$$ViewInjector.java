// Generated code from Butter Knife. Do not modify!
package com.cxliu.zhihudaily.view.main;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class Main$$ViewInjector {
  public static void inject(Finder finder, final com.cxliu.zhihudaily.view.main.Main target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361798, "field 'mListView'");
    target.mListView = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131361797, "field 'mPullRefreshView'");
    target.mPullRefreshView = (com.ab.view.pullview.AbPullToRefreshView) view;
  }

  public static void reset(com.cxliu.zhihudaily.view.main.Main target) {
    target.mListView = null;
    target.mPullRefreshView = null;
  }
}
