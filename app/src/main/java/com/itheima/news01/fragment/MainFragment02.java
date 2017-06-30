package com.itheima.news01.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.itheima.news01.R;
import com.itheima.news01.adapter.VideoAdapter;
import com.itheima.news01.base.URLManager;
import com.itheima.news01.bean.VideoEntity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * @author WJQ
 */
public class MainFragment02 extends BaseFragment {

    private RecyclerView recyclerView;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_02;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        getVideoDatas();
    }

    /**
     * 获取服务器视频列表数据
     *
     */
    private void getVideoDatas() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, URLManager.VideoURL,
                new RequestCallBack<String>() {

            @Override   // 请求成功
            public void onSuccess(ResponseInfo<String> responseInfo) {
                // （1）服务器返回的json数据
                String json = responseInfo.result;
                System.out.println("---------json: " + json);

                // （2）解析json数据
                // 替换json字符串中的新闻类别id
                json = json.replace("V9LG4B3A0", "result");

                Gson gson = new Gson();         // 使用到反射  fastjson
                VideoEntity entity = gson.fromJson(json, VideoEntity.class);
                // 列表显示的数据
                List<VideoEntity.ResultBean> listDatas = entity.getResult();

                // int count = listDatas.size();
                // System.out.println("--------解析：" + count + " 个视频");

                // （3）显示列表(RecyclerView: 列表，网格，瀑布流)
                // RecyclerView: 列表数据，列表项布局， 适配器
                showRecyclerView(listDatas);
            }

            @Override   // 请求失败
            public void onFailure(HttpException error, String msg) {
                System.out.println("---------error: " + error);
            }
        });
    }

    /** 显示列表RecyclerView */
    private void showRecyclerView(List<VideoEntity.ResultBean> listDatas) {
        // 设置列表布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VideoAdapter videoAdapter = new VideoAdapter(getContext(), listDatas);
        recyclerView.setAdapter(videoAdapter);

//        ListView listView = null;
//        listView.setOnItemClickListener();
    }
}
