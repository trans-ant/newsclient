package com.itheima.news01.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.itheima.news01.NewsDetailActivity;
import com.itheima.news01.R;
import com.itheima.news01.adapter.NewsAdapter;
import com.itheima.news01.base.URLManager;
import com.itheima.news01.bean.NewsEntity;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * 显示一个类别下的新闻
 *
 * @author WJQ
 */
public class NewsItemFragment extends BaseFragment {

    private TextView textView;
    private ListView listView;

    /** 新闻类别id */
    private String newsCategoryId;
    private NewsAdapter newsAdapter;
    private SpringView springView;
    private View headerView;

    private List<NewsEntity.ResultBean> listDatas;

    /** 设置新闻类别id */
    public void setNewsCategoryId(String newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news_item;
    }

    @Override
    public void initView() {
        textView = (TextView) mRootView.findViewById(R.id.tv_01);

        listView = (ListView) mRootView.findViewById(R.id.list_view);
        newsAdapter = new NewsAdapter(null, getContext());
        listView.setAdapter(newsAdapter);

        textView.setText("类别id：" + newsCategoryId);

        initSpringView();
    }

    // 显示下拉刷新和加载更多的控件
    private void initSpringView() {
        springView = (SpringView) mRootView.findViewById(R.id.spring_view);

        // 设置SpringView头部和尾部

//        DefaultHeader (com.liaoinstan.springview.container)
//        AcFunHeader (com.liaoinstan.springview.container)
//        RotationHeader (com.liaoinstan.springview.container)
//        AliHeader (com.liaoinstan.springview.container)
//        MeituanHeader (com.liaoinstan.springview.container)

//        springView.setHeader(new AcFunHeader(getContext(), R.drawable.ad_new_version1_img1));
        springView.setHeader(new MeituanHeader(getContext()));
        springView.setFooter(new DefaultFooter(getContext()));

        // springView.setType(SpringView.Type.OVERLAP);
        springView.setType(SpringView.Type.FOLLOW);

        // 设置监听器
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {       // 下拉，刷新第一页数据
                // showToast("下拉");

                // 请求服务器第一页数据,然后刷新
                getNewsDatas(true);
            }

            @Override
            public void onLoadmore() {      // 上拉，加载下一页数据
                // showToast("上拉");

                // 请求服务器下一页数据
                getNewsDatas(false);
            }
        });
    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override  // parent: 指ListView
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = position;
                // 列表有添加头部,才需要减1
                if (listView.getHeaderViewsCount() > 0) {
                    // 列表添加了头部后，第一条新闻数据的索引是从1开始，所以要减1
                    index = index - 1;
                }

                // 点击列表项，跳转到新闻详情界面

                // 用户点击的那条新闻数据
                // 方式1：
                NewsEntity.ResultBean news = listDatas.get(index);
                // 方式2：
                // NewsEntity.ResultBean news = (NewsEntity.ResultBean)
                //         parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("news", news);    // 新闻数据传到新闻详情界面
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        // 获取服务器新闻数据
        getNewsDatas(true);
    }

    /** 要加载第几页数据 */
    private int pageNo = 1;

    /**
     * 获取服务器新闻数据
     *
     * @param refresh true表示下拉刷新，false表示加载下一页数据
     */
    private void getNewsDatas(final boolean refresh) {
        if (refresh)  // 如果是下拉刷新
            pageNo = 1;

        String newsUrl = URLManager.getUrl(newsCategoryId, pageNo);

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, newsUrl, new RequestCallBack<String>() {
            @Override   // 请求成功
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // （1）服务器返回的json数据
                String json = responseInfo.result;
                System.out.println("---------json: " + json);

                // （2）解析json数据
                // 替换json字符串中的新闻类别id
                json = json.replace(newsCategoryId, "result");
                Gson gson = new Gson();         // 使用到反射  fastjson
                NewsEntity newsEntity = gson.fromJson(json, NewsEntity.class);
                int count = newsEntity.getResult().size();
                System.out.println("--------解析：" + count + " 条新闻");
                // 列表显示的数据集合
                listDatas = newsEntity.getResult();

                // （3）显示列表(数据，列表项布局，适配器BaseAdapter)
                if (refresh) {  // 下拉刷新
                    showListView(listDatas);
                } else {        // 上拉加载下一页数据
                    newsAdapter.appendDatas(listDatas);
                }
                pageNo++;       // 页码自增1

                //  隐藏SpringView的下拉和上拉显示
                springView.onFinishFreshAndLoad();
            }

            @Override   // 请求失败
            public void onFailure(HttpException error, String msg) {
                System.out.println("---------error: " + error);
            }
        });
    }

    // 显示列表
    private void showListView(List<NewsEntity.ResultBean> listDatas) {
        // （1）显示轮播图

        // 如果列表已经添加了头部布局，则先移除
        if (listView.getHeaderViewsCount() > 0) {
            listView.removeHeaderView(headerView);
        }

        // 第一条新闻
        NewsEntity.ResultBean firstNews = listDatas.get(0);
        // 有轮播图
        if (firstNews.getAds() != null && firstNews.getAds().size() > 0) {
            headerView = LayoutInflater.from(getContext()).inflate(R.layout
                    .list_view_header, listView, false);

            // 移除第一条数据（也就是轮播图数据），不显示在列表项中
            listDatas.remove(0);

            // 查找轮播图控件
            SliderLayout sliderLayout = (SliderLayout)
                    headerView.findViewById(R.id.slider_layout);
            // 准备轮播图要显示的数据
            List<NewsEntity.ResultBean.AdsBean> ads = firstNews.getAds();
            // 添加轮播图子界面
            for (int i = 0; i < ads.size(); i++) {
                NewsEntity.ResultBean.AdsBean bean = ads.get(i);

                // 一个TextSliderView表示一个子界面
                TextSliderView  textSliderView = new TextSliderView(getContext());
                textSliderView.description(bean.getTitle())  // 显示标题
                              .image(bean.getImgsrc());      // 显示图片

                sliderLayout.addSlider(textSliderView);      // 添加一个子界面
            }

            // 添加到轮播图到列表的头部
            listView.addHeaderView(headerView);

        } else {
            // 没有轮播图的情况
        }

        // （2）显示列表
        newsAdapter.setDatas(listDatas);    // 重置列表的数据，刷新列表显示
    }
}















