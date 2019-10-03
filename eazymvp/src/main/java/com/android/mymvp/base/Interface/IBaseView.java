package com.android.mymvp.base.Interface;


public interface IBaseView<T extends IBasePresenter> {
   T createPresenter();
}
