package com.android.wanandroid;

import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
import com.android.wanandroid.module.book.bookbeans.SystemDetailsBean;
import com.android.wanandroid.module.home.HomeBannerBean;
import com.android.wanandroid.module.home.HomeBean;
import com.android.wanandroid.module.project.entity.ProjectItemData;
import com.android.wanandroid.module.project.entity.ProjectList;
import com.android.wanandroid.module.wechat.entity.WechatBean;
import com.android.wanandroid.module.wechat.entity.WechatListBean;
import com.android.wanandroid.test.HttpResult;
import com.android.wanandroid.test.entity.Test;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HttpService {

   @GET("article/listproject/{page}/json")
   Observable<HttpResult<Test>> getTest(@Path("page") int page);

   @GET("navi/json")
   Observable<HttpResult<List<BookNavigationBean>>> getBookNavigationList();

   //公众号
   @GET("wxarticle/chapters/json")
   Observable<HttpResult<List<WechatBean>>> getWechatList();

   //公众号page页
   @GET("wxarticle/list/{id}/{page}/json")
   Observable<HttpResult<WechatListBean>> getWechatLists(@Path("id") int id,
                                                         @Path("page") int page);

   @GET("tree/json")
   Observable<HttpResult<List<BookSystemBean>>> getBookSystemList();

   //项目tab列表
   @GET("project/tree/json")
   Observable<HttpResult<List<ProjectList>>> getProjectList();

   //项目item内容
   @GET("project/list/{page}/json?")
   Observable<HttpResult<ProjectItemData>> getProjectItemData(@Path("page") int page,
                                                              @Query("cid") int id);

   @GET("article/list/{page}/json")
   Observable<HttpResult<HomeBean>> getHomeList(@Path("page") int page);

   @GET("banner/json")
   Observable<HttpResult<List<HomeBannerBean>>> getHomeBannerList();


    //系统详情
    @GET("article/list/{page}/json?")
    Observable<HttpResult<SystemDetailsBean>> getSystemDetails(@Path("page") int page, @Query("cid") int id);
}
