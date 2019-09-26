package com.android.wanandroid.test;

public class HttpResult<T> extends com.android.httpservice.http.HttpResult<T> {

   public HttpResult(String message, int code, T data) {
      super(message, code, data);
   }

   private int errorCode;
   private String errorMsg;

   @Override
   public String getMessage() {
      return errorMsg;
   }

   @Override
   public int getCode() {
      return errorCode;
   }

}
