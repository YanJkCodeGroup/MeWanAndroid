package com.android.wanandroid;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BaseModel;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
import com.android.wanandroid.module.home.HomeBannerBean;
import com.android.wanandroid.module.home.HomeBean;
import com.android.wanandroid.module.project.entity.ProjectItemData;
import com.android.wanandroid.module.project.entity.ProjectList;
import com.android.wanandroid.module.wechat.WechatBean;
import com.android.wanandroid.module.wechat.WechatListBean;
import com.android.wanandroid.test.entity.Test;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class AppMode extends BaseModel<HttpService> implements Contract.AppModeImpl {
   private static volatile AppMode sAppMode;

   private AppMode() {
   }

   @Override
   protected boolean isOpenHttpService() {
      return true;
   }

   @Override
   protected Class getHttpServiceClass() {
      return HttpService.class;
   }

   @Override
   protected String getBaseUrl() {
      return HttpConfig.BASE_URL;
   }

   public static AppMode getAppMode() {
      if (sAppMode == null) {
         synchronized (AppMode.class) {
            if (sAppMode == null) {
               sAppMode = new AppMode();
            }
         }
      }
      return sAppMode;
   }

   @Override
   public void getTest(LifecycleProvider provider, int page, BaseCallback<Test> callback) {
      observer(provider, getHttpService().getTest(page), callback);
   }

   @Override
   public void bookNavigation(LifecycleProvider provider,
                              BaseCallback<List<BookNavigationBean>> callback) {
      observer(provider, getHttpService().getBookNavigationList(), callback);
   }

   @Override
   public void wechat(LifecycleProvider provider, BaseCallback<List<WechatBean>> callback) {
      observer(provider, getHttpService().getWechatList(), callback);
   }

   @Override
   public void wechatList(LifecycleProvider provider, int id, int page,
                          BaseCallback<WechatListBean> callback) {
      observer(provider, getHttpService().getWechatLists(id, page), callback);
   }

   @Override
   public void getProjectTabList(LifecycleProvider provider,
                                 BaseCallback<List<ProjectList>> callback) {
      observer(provider, getHttpService().getProjectList(), callback);
   }

   @Override
   public void getProjectItemDate(LifecycleProvider provider, int page, int id,
                                  BaseCallback<ProjectItemData> callback) {
      observer(provider, getHttpService().getProjectItemData(page, id), callback);
   }


   @Override
   public void bookSystem(LifecycleProvider provider, BaseCallback<List<BookSystemBean>> callback) {
      observer(provider, getHttpService().getBookSystemList(), callback);
   }

   @Override
   public void getHome(LifecycleProvider provider, BaseCallback<List<HomeBean>> callback) {
      observer(provider, getHttpService().getHomeList(), callback);
   }

   @Override
   public void getHomeBanner(LifecycleProvider provider,
                             BaseCallback<List<HomeBannerBean>> callback) {
      observer(provider, getHttpService().getHomeBannerList(), callback);
   }

}
