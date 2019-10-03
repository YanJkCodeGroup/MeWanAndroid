package com.android.mymvp.base.Interface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.mymvp.base.BaseFragment;

public interface IbaseFragmentAddManeage {
   /**
    * 添加显示fragment
    *
    * @param manager fragment管理器
    * @param fClass  需要添加的fragment
    * @param groupID 显示这个fragment的容器
    * @param bundle  设置fragment初始数据,可null
    * @return 返回添加成功的fragment
    */
   BaseFragment addFragment(FragmentManager manager, Class<? extends BaseFragment> fClass,
                            int groupID, Bundle bundle);

   /**
    * 隐藏所有fragment
    *
    * @param manager
    * @param transaction
    * @param fragment
    */
   void hideAllFragment(FragmentManager manager, FragmentTransaction transaction,
                        Fragment fragment);
}
