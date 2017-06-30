package com.itheima.news01.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.itheima.news01.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WJQ
 */
public class MainFragment01 extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_01;
    }

    @Override
    public void initView() {
        tabLayout = (TabLayout) mRootView.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) mRootView.findViewById(R.id.view_pager);

        initViewPager();
    }

    // 显示ViewPager和TabLayout
    private void initViewPager() {
        final String[] titles = new String[] {
                "头条", "社会", "科技", "财经", "体育", "汽车"
        };

        final String[] channelIds = new String[] {
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };

        // 创建要显示的6个Fragment
        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            NewsItemFragment fragment = new NewsItemFragment();
            // 设置新闻类别id
            fragment.setNewsCategoryId(channelIds[i]);
            fragments.add(fragment);
        }

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });

        // 绑定TabLayout和ViewPager，显示TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
