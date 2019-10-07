package com.android.wanandroid.module.project.presenter;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract.ProjectContract;
import com.android.wanandroid.Contract.ProjectContract.ProjectView;
import com.android.wanandroid.module.project.entity.ProjectList;

import java.util.List;

public class ProjectPresenter extends BasePresenter<ProjectView,
        AppMode> implements ProjectContract.ProjectPresenter {
   @Override
   public AppMode initModel() {
      return AppMode.getAppMode();
   }

   @Override
   public void getProjectTabList() {
      getModel().getProjectTabList(getLifecycleProvider(), new BaseCallback<List<ProjectList>>() {
         @Override
         public void onCallSuccessful(List<ProjectList> value) {
            if (mView != null) {
               mView.onSuccessful(value);
            }
         }
      });
   }
}
