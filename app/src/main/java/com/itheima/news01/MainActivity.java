package com.itheima.news01;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;

import com.itheima.news01.fragment.MainFragment01;
import com.itheima.news01.fragment.MainFragment02;
import com.itheima.news01.fragment.MainFragment03;
import com.itheima.news01.fragment.MainFragment04;
import com.itheima.news01.fragment.MainFragment05;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 要在setContentView之前调用，否则报错
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        initViewPager();
        initNavigationView();
        initToolBar();
        initActionBarDrawerToggle();
    }

    // ActionBarDrawerToggle: 用来绑定ActionBar（ToolBar）和DrawerLayout的一个控件
    private ActionBarDrawerToggle toggle;

    private void initActionBarDrawerToggle() {
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();     // 同步drawerLayout和toolbar的状态
    }

    //================Toolbar右上角弹出菜单(begin)=======================

    // 初始化标题栏
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // 使用Toolbar代替ActionBar
        setSupportActionBar(toolbar);

        toolbar.setTitle("广交院实训");

        // 显示标题栏左上角的返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // toolbar.setNavigationIcon(R.drawable.biz_video_play);
        // toolbar.setLogo(R.mipmap.ic_launcher);
        // toolbar.setSubtitle("实训");
        // toolbar.setTitleTextColor(Color.RED);
        // toolbar.setSubtitleTextColor(Color.GREEN);
    }

    // Toolbar标题栏右侧点击菜单:创建
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // res/memu/main_option.xml
        // 定义可点击的菜单项
        getMenuInflater().inflate(R.menu.main_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // Toolbar标题栏右侧点击菜单：点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_02) {
            showToast("menu_02");
        }
        return super.onOptionsItemSelected(item);
    }

    //================Toolbar右上角弹出菜单(end)=======================


    private void initNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        // 设置NavigationView菜单项点击监听
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.menu_01) {
                    showToast("menu_01");
                    drawerLayout.closeDrawers();    // 关闭侧滑菜单
                    return true;
                }

                return false;
            }
        });
    }

    /** 显示ViewPager */
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment01());
        fragments.add(new MainFragment02());
        fragments.add(new MainFragment03());
        fragments.add(new MainFragment04());
        fragments.add(new MainFragment05());

        // ListView： BaseAdapter
        // ViewPager: FragmentPagerAdapter

        // 设置适配器，显示ViewPager
        // getSupportFragmentManager()是FragmentActivity的方法,需要继承FragmentActivity
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override // 要显示哪个Fragment
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override // 有多少个子界面
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    public void initListener() {
        // 1. 点击单选时，ViewPager显示对应的子界面
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override // 选中的单选(RadioButton)的id
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId) {
                    case R.id.rb_01:            // 新闻
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:            // 视听
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:            // 阅读
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:            // 发现
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:            // 设置
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        // 2. 当ViewPager子界面发生改变时，选中并高亮对应的单选按钮
        // viewPager.addOnPageChangeListener();
        // viewPager.removeOnPageChangeListener();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override   // 子界面发生改变时,调用此方法
            public void onPageSelected(int position) {
                switch(position) {
                    case 0:            // 新闻
                        radioGroup.check(R.id.rb_01);
                        break;
                    case 1:            // 视听
                        radioGroup.check(R.id.rb_02);
                        break;
                    case 2:            // 阅读
                        radioGroup.check(R.id.rb_03);
                        break;
                    case 3:            // 发现
                        radioGroup.check(R.id.rb_04);
                        break;
                    case 4:            // 设置
                        radioGroup.check(R.id.rb_05);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源
        JCVideoPlayer.releaseAllVideos();
    }
}
