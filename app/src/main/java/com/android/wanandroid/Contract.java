package com.android.wanandroid;

import com.android.mymvp.base.BaseCallback;
import com.android.mymvp.base.Interface.IBasePresenter;
import com.android.mymvp.base.Interface.IBaseView;
import com.android.mymvp.base.Interface.IBaseViewback;
import com.android.wanandroid.module.book.bookbeans.BookNavigationBean;
import com.android.wanandroid.module.book.bookbeans.BookSystemBean;
import com.android.wanandroid.module.book.bookbeans.SystemDetailsBean;
import com.android.wanandroid.module.home.HomeBannerBean;
import com.android.wanandroid.module.home.HomeBean;
import com.android.wanandroid.module.home.HomeTopBean;
import com.android.wanandroid.module.project.entity.ProjectItemData;
import com.android.wanandroid.module.project.entity.ProjectList;
import com.android.wanandroid.module.wechat.WechatBean;
import com.android.wanandroid.module.wechat.WechatListBean;
import com.android.wanandroid.test.entity.Test;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface Contract {
    interface TestRequest {
        interface TestPresenter extends IBasePresenter<TestView> {
            void getTest(int page);
        }

        interface TestView extends IBaseView<TestPresenter>, IBaseViewback<Test> {
        }
    }

    interface AppModeImpl {
        void getTest(LifecycleProvider provider, int page, BaseCallback<Test> callback);

        void bookNavigation(LifecycleProvider provider,
                            BaseCallback<List<BookNavigationBean>> callback);

        void bookSystem(LifecycleProvider provider, BaseCallback<List<BookSystemBean>> callback);

        //首页文章
        void getHome(LifecycleProvider provider, BaseCallback<HomeBean> callback, int page);

        //首页Banner
        void getHomeBanner(LifecycleProvider provider, BaseCallback<List<HomeBannerBean>> callback);

        //首页置顶
        void getHomeTop(LifecycleProvider provider,BaseCallback<List<HomeTopBean>> callback);

        //公众号
        void wechat(LifecycleProvider provider, BaseCallback<List<WechatBean>> callback);

        //公众号page页
        void wechatList(LifecycleProvider provider, int id, int page,
                        BaseCallback<WechatListBean> callback);

        //项目的列表
        void getProjectTabList(LifecycleProvider provider,
                               BaseCallback<List<ProjectList>> callback);

        //项目列表的内容
        void getProjectItemDate(LifecycleProvider provider, int page, int id,
                                BaseCallback<ProjectItemData> callback);

        //系统详情
        void systemDetails(LifecycleProvider provider, int page, int id, BaseCallback<SystemDetailsBean> callback);


    }

    //导航(book Navigation)
    interface BookNavigationPresenter extends IBasePresenter<BookNavigationView> {
        void initNavigationPresenter();
    }

    interface BookNavigationView extends IBaseView<BookNavigationPresenter> {
        void succeed(List<BookNavigationBean> beanList);

        void fail(String error);
    }

    //体系(book system)
    interface BookSystemPresenter extends IBasePresenter<BookSystemView> {
        void initSystemPresenter();
    }

    interface BookSystemView extends IBaseView<BookSystemPresenter> {
        void succeed(List<BookSystemBean> sysList);

        void fail(String error);
    }


    //公众号
    interface WechatPresenter extends IBasePresenter<WechatView> {
        void initwechatPresenter();
    }

    interface WechatView extends IBaseView<WechatPresenter> {
        void succeed(List<WechatBean> wechatList);

        void fail(String error);
    }

    //公众号page页
    interface WechatlistPresenter extends IBasePresenter<WechatlistView> {
        void initwechatlistPresenter(int id, int page);
    }

    interface WechatlistView extends IBaseView<WechatlistPresenter> {
        void succeed(WechatListBean wechatList);

        void fail(String error);
    }

    //首页(home)
    interface HomePresenter extends IBasePresenter<HomeView> {
        //文章
        void initHomePresenter(int page);

        //Banner
        void initHomeBannerPresenter();

        //置顶
        void initHomeTopPresenter();
    }

    interface HomeView extends IBaseView<HomePresenter> {
        void homeBeanSucceed(HomeBean homeBean);

        void homeFail(String error);


        void homeBannerSucceed(List<HomeBannerBean> homeBannerList);

        void homeBannerFail(String error);


        void HomeTopSucceed(List<HomeTopBean> homeTopList);

        void homeTopFail(String error);
    }


    interface ProjectContract {
        interface ProjectPresenter extends IBasePresenter<ProjectView> {
            void getProjectTabList();
        }

        interface ProjectView extends IBaseView<ProjectPresenter>,
                IBaseViewback<List<ProjectList>> {
        }

        interface ProjectItemPresenter extends IBasePresenter<ProjectItemView> {
            void getProjectItemData(int page, int id);
        }

        interface ProjectItemView extends IBaseView<ProjectItemPresenter>,
                IBaseViewback<ProjectItemData> {
        }

    }

    interface SystemDetailsPresenter extends IBasePresenter<SystemDetailsView> {
        void initSystemDetailsPresenter(int page, int id);
    }

    interface SystemDetailsView extends IBaseView<SystemDetailsPresenter> {
        void succeed(SystemDetailsBean systemDetails);

        void fail(String error);
    }


}
