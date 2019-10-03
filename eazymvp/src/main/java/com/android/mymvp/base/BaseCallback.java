package com.android.mymvp.base;

import com.android.mymvp.base.Interface.IBaseViewback;

public abstract class BaseCallback<T> {
   private IBaseViewback mView;

   public BaseCallback(IBaseViewback view) {
      mView = view;
   }

   public BaseCallback() {
   }

   public void onCallSuccessful(T value) {
      if (mView != null) {
         mView.onSuccessful(value);
         mView = null;
      }
   }

   public <M extends Throwable> void onCallFailed(M msg) {
      if (mView != null) {
         mView.onFailed(msg.getMessage());
         mView = null;
      }
   }
}
