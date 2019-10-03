package com.android.mymvp.base;

import com.android.mymvp.base.Interface.IBasePresenter;
import com.android.mymvp.base.Interface.IBaseView;

public abstract class BaseMvpFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView<P> {
   protected P mPresenter;

   @Override
   protected final void initMvp() {
      mPresenter = createPresenter();
      if (mPresenter != null) {
         mPresenter.attachView(this);
      }
   }

   @Override
   public final void onDestroyView() {
      super.onDestroyView();
      if (mPresenter != null) {
         mPresenter.detachView();
         mPresenter = null;
      }
   }
}
