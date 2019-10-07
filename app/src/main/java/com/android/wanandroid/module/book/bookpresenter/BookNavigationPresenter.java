package com.android.wanandroid.module.book.bookpresenter;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;

import java.util.List;

public class BookNavigationPresenter extends BasePresenter<Contract.BookNavigationView, AppMode> implements Contract.BookNavigationPresenter {
   @Override
   public AppMode initModel() {
      return AppMode.getAppMode();
   }

   @Override
   public void initNavigationPresenter() {
      getModel().bookNavigation(getLifecycleProvider(),
              new BaseCallback<List<BookNavigationBean>>() {
         @Override
         public void onCallSuccessful(List<BookNavigationBean> value) {
            super.onCallSuccessful(value);
            if (mView != null)
               mView.succeed(value);
         }

         @Override
         public <M extends Throwable> void onCallFailed(M msg) {
            super.onCallFailed(msg);
            if (mView != null)
               mView.fail(msg.getMessage());
         }
      });
   }
}
