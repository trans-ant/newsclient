package com.itheima.news01;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.itheima.news01.bean.NewsEntity;

/**
 * 新闻详情界面
 *
 * @author WJQ
 */
public class NewsDetailActivity extends BaseActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initView() {
        progressBar = (ProgressBar) findViewById(R.id.pb_01);
        // progressBar.setMax(100);        // max默认值为100

        // 显示左上角标题栏的返回按钮(前提：Activity需要使用有标题栏的主题)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWebView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // android.R.id.home: 系统定义的id，也就是标题栏左上角的返回按钮
        if (item.getItemId() == android.R.id.home) {
            finish();       // 退出当前Activity界面,返回到首页
        }

        return super.onOptionsItemSelected(item);
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.web_view);

        // 支持javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);

        // 禁止使用外部的浏览器打开网页
        webView.setWebViewClient(new WebViewClient(){
            @Override  // 网页加载完成
            public void onPageFinished(WebView view, String url) {
                System.out.println("------onPageFinished");
                super.onPageFinished(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override // 网页加载进度改变时调用
            // newProgress: 取值为0到100
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {   // 网页加载完成
                    progressBar.setVisibility(View.GONE);
                } else {    // 刷新ProgressBar的进度
                    progressBar.setProgress(newProgress);
                }

                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        // 上一个界面传过来的新闻数据
        NewsEntity.ResultBean newsBean = (NewsEntity.ResultBean)
                getIntent().getSerializableExtra("news");

        String newUrl = newsBean.getUrl();
        System.out.println("--------newUrl: " + newUrl);
        webView.loadUrl(newUrl);        // 显示新闻网页数据
    }
}
