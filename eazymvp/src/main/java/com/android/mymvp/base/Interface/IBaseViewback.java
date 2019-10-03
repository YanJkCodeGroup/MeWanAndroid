package com.android.mymvp.base.Interface;

public interface IBaseViewback<T> {

   void onSuccessful(T t);

    void onFailed(String msg);
}
