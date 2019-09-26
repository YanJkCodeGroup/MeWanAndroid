package com.android.wanandroid;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.Interface.IBasePresenter;
import com.android.mymvp.base.Interface.IBaseView;
import com.android.mymvp.base.Interface.IBaseViewback;
import com.android.wanandroid.test.entity.Test;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface Contract {
   interface TestRequest {
      interface testPresenter extends IBasePresenter<testView> {
         void getTest(int page);
      }

      interface testView extends IBaseView<testPresenter>, IBaseViewback<Test> {
      }
   }

   interface AppModeImpl {
      void getTest(LifecycleProvider provider, int page,
                   BaseCallback<Test> callback);
   }
}
