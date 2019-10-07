package com.android.wanandroid.module.project.presenter;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.BasePresenter;
import com.android.wanandroid.AppMode;
import com.android.wanandroid.Contract.ProjectContract;
import com.android.wanandroid.Contract.ProjectContract.ProjectItemView;
import com.android.wanandroid.module.project.entity.ProjectItemData;

public class ProjectItemPresenter extends BasePresenter<ProjectItemView, AppMode>
        implements ProjectContract.ProjectItemPresenter {
   @Override
   public AppMode initModel() {
      return AppMode.getAppMode();
   }

   @Override
   public void getProjectItemData(int page, int id) {
      getModel().getProjectItemDate(getLifecycleProvider(), page, id,
              new BaseCallback<ProjectItemData>() {
                 @Override
                 public void onCallSuccessful(ProjectItemData value) {
                    if (mView != null) {
                       mView.onSuccessful(value);
                    }
                 }
              });
   }
}
