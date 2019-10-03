package com.android.mymvp.base.Interface;

public interface IBasePresenter<T extends IBaseView> {
   /**
    * 把V依附到P层
    *
    * @param t
    */
   void attachView(T t);

   /**
    * V与P层解绑
    */
   void detachView();

}
