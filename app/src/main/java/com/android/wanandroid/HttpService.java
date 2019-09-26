package com.android.wanandroid;

import com.android.wanandroid.test.HttpResult;
import com.android.wanandroid.test.entity.Test;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HttpService {

   @GET("article/listproject/{page}/json")
   Observable<HttpResult<Test>> getTest(@Path("page") int page);
}
