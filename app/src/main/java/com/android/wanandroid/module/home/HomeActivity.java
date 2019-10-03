package com.android.wanandroid.module.home;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.mymvp.base.BaseActivity;
import com.android.wanandroid.R;
import com.android.wanandroid.module.adapter.HomeViewPagerAdapter;
import com.android.wanandroid.module.book.BookFragment;
import com.android.wanandroid.module.mine.MineFragment;
import com.android.wanandroid.module.project.ProjectFragment;
import com.android.wanandroid.module.wechat.WechatFragment;
import com.android.wanandroid.utils.TabLayoutUtil;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

   @BindView(R.id.home_vp)
   ViewPager homeVp;
   @BindView(R.id.home_tab)
   TabLayout homeTab;
   @BindView(R.id.home_navigation)
   NavigationView homeNavigation;
   @BindView(R.id.home_drawer)
   DrawerLayout homeDrawer;
   private ArrayList<Fragment> fragments;
   private int[] homeIcon = {
           R.drawable.tab_home_selector,
           R.drawable.tab_book_selector,
           R.drawable.tab_wechat_selector,
           R.drawable.tab_project_selector,
           R.drawable.tab_mine_selector};
   private String[] homeTitle = {"首页", "体系", "公众号", "项目", "我的"};

   @Override
   public int initLayout() {
      return R.layout.activity_home;
   }

   @Override
   protected void initView() {
      initTab();
   }


   private void initTabData() {
      fragments = new ArrayList<>();
      fragments.add(new HomeFragment());
      fragments.add(new BookFragment());
      fragments.add(new WechatFragment());
      fragments.add(new ProjectFragment());
      fragments.add(new MineFragment());
   }

   private void initTab() {
      initTabData();
      HomeViewPagerAdapter homeViewPagerAdapter =
              new HomeViewPagerAdapter(getSupportFragmentManager(), fragments);
      homeVp.setAdapter(homeViewPagerAdapter);
      homeTab.setupWithViewPager(homeVp);
      for (int i = 0 ; i < fragments.size() ; i++) {
         homeTab.getTabAt(i).setCustomView(TabLayoutUtil.getTabView(homeIcon[i], homeTitle[i],
                 R.layout.tab_navi_home));
      }
   }


}
