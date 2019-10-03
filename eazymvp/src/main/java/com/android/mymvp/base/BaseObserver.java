package com.android.mymvp.base;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {

   private WeakReference<BaseCallback<T>> callback;
   private Disposable mDisposable;

   public BaseObserver(BaseCallback<T> callback) {
      this.callback = new WeakReference<>(callback);
   }

   @Override
   public void onSubscribe(Disposable d) {
      mDisposable = d;
   }

   @Override
   public void onNext(T t) {
      if (callback != null) {
         if (getCallback() != null) {
            getCallback().onCallSuccessful(t);
         }
      }
   }

   @Override
   public void onError(Throwable e) {
      if (callback != null) {
         getCallback().onCallFailed(e);
      }
   }

   @Override
   public void onComplete() {
      if (mDisposable != null) {
         mDisposable.dispose();
         mDisposable = null;
      }
      if (callback != null) {
         callback.clear();
         callback = null;
      }
   }

   public final BaseCallback<T> getCallback() {
      return callback.get();
   }
}
