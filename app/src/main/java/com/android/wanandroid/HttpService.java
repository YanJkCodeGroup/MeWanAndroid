package com.android.wanandroid;

import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.wechat.DataBean;

import com.android.wanandroid.module.wechat.WechatListBean;
import com.android.wanandroid.test.HttpResult;
import com.android.wanandroid.test.entity.Test;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HttpService {

    @GET("article/listproject/{page}/json")
    Observable<HttpResult<Test>> getTest(@Path("page") int page);

    @GET("https://www.wanandroid.com/navi/json")
    Observable<HttpResult<List<BookNavigationBean>>> getBookNavigationList();

    //公众号
    @GET("https://wanandroid.com/wxarticle/chapters/json")
    Observable<HttpResult<List<DataBean>>> getWechatList();

    //公众号page页
    @GET("https://wanandroid.com/wxarticle/list/{id}/{page}/json")
    Observable<HttpResult<WechatListBean>> getWechatLists(@Path("id") int id, @Path("page") int page);

}
