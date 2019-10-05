package com.android.wanandroid;

import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
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

    @GET("https://www.wanandroid.com/tree/json")
    Observable<HttpResult<List<BookSystemBean>>> getBookSystemList();

}
