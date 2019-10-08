package com.android.wanandroid.module.mine;


import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.mymvp.base.BaseFragment;
import com.android.mymvp.base.util.AppUtils;
import com.android.wanandroid.R;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


   @BindView(R.id.mine_navigation)
   NavigationView mineNavigation;

   public MineFragment() {
   }

   @Override
   public int initLayout() {
      return R.layout.fragment_mine;
   }

   @Override
   protected void initView() {
      View headerView = mineNavigation.getHeaderView(0);
      headerView.setPadding(headerView.getPaddingLeft(),
              AppUtils.getStateBar2(getContext()),
              headerView.getPaddingRight(),
              headerView.getPaddingBottom());


   }

   @Override
   public void onResume() {
      //todo 判断用户是否登录如果登录就加载用户信息 到页面显示
      View headerView = mineNavigation.getHeaderView(0);
      ImageView userIcon = headerView.findViewById(R.id.navigation_header_user_icon);//用户头像
      TextView userPetName = headerView.findViewById(R.id.navigation_header_pet_name);//用户昵称
      TextView userId = headerView.findViewById(R.id.navigation_header_user_id);//用户id
      TextView userGrade = headerView.findViewById(R.id.navigation_header_user_grade);//用户等级
      TextView userRanking = headerView.findViewById(R.id.navigation_header_user_ranking);//用户排名
      super.onResume();
   }

   @Override
   protected void initListener() {

      mineNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
               case R.id.mine_my_integral://我的积分
                  showHint("我的积分");
                  break;
               case R.id.mine_my_collect://我的收藏
                  showHint("我的收藏");
                  break;
               case R.id.mine_read_later://稍后阅读
                  showHint("稍后阅读");
                  break;
               case R.id.mine_github://开源项目
                  showHint("开源项目");
                  break;
               case R.id.mine_about://关于作者
                  showHint("关于作者");
                  break;
               case R.id.mine_settings://系统设置
                  showHint("系统设置");
                  break;
            }
            return true;
         }
      });
   }
}
