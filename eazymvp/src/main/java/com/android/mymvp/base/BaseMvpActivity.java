package com.android.mymvp.base;

import com.android.mymvp.base.Interface.IBasePresenter;
import com.android.mymvp.base.Interface.IBaseView;

public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView<P> {
   protected P mPresenter;

   @Override
   protected final void initMvp() {
      mPresenter = createPresenter();
      if (mPresenter != null) {
         mPresenter.attachView(this);
      }
   }


   @Override
   protected void onDestroy() {
      super.onDestroy();
      if (mPresenter != null) {
         mPresenter.detachView();
         mPresenter = null;
      }
   }

}
