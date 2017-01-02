package com.example.administrator.viewpager_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiasheng on 2016/12/28.
 * email 898478073@qq.com
 * Description: ViewPager_Demo2
 */

public class Demo extends FragmentActivity {
    private android.support.v4.view.ViewPager advpager;
    private android.widget.LinearLayout viewGroup;
    private android.widget.TextView viewGroup2;
    private android.widget.RelativeLayout adrl;

//    初始数组
    private List<View> mList = new ArrayList<>();
//   实现循环的数组
    private List<View> mList3 = new ArrayList<>();
    private List<Fragment> mList2 = new ArrayList<>();
    private android.widget.Button del;
    private int Localposition;
    //  原始的

    private PagerAdapter adapter = new PagerAdapter() {
        //            getCount():返回要滑动的VIew的个数
        @Override
        public int getCount() {
//            return mList3.size();
            return 1000;
        }

        //            该函数用来判断instantiateItem(ViewGroup, int)函数所返回来的Key
//            与一个页面视图是否是代表的同一个视图(即它俩是否是对应的，
//            对应的表示同一个View)
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //          instantiateItem()：做了两件事，
//          第一：将当前视图添加到container中，
//          第二：返回当前View
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            container.addView(mList.get(position % mList.size()));
//            return mList.get(position % mList.size());
            container.addView(mList3.get(position));
            return mList3.get(position);
        }

        //            destroyItem（）：从当前container中删除指定位置（position）的View;
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mList.get(position % mList.size()));
            container.removeView(mList3.get(position));
        }

//        @Override
//        public int getItemPosition(Object object) {
//            return POSITION_NONE;
//        }
    };

    //    关于Fragment的
    private FragmentPagerAdapter mFragmentPagerAdapter;

    private FragmentStatePagerAdapter mFragmentStatePagerAdapter;
    private int mI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_cycle_view);

        this.del = (Button) findViewById(R.id.del);
        this.adrl = (RelativeLayout) findViewById(R.id.ad_rl);
        this.viewGroup2 = (TextView) findViewById(R.id.viewGroup2);
        this.viewGroup = (LinearLayout) findViewById(R.id.viewGroup);
        this.advpager = (ViewPager) findViewById(R.id.adv_pager);


        View view1 = LayoutInflater.from(this).inflate(R.layout.view1, null, false);
        View view2 = LayoutInflater.from(this).inflate(R.layout.view2, null, false);
        View view3 = LayoutInflater.from(this).inflate(R.layout.view3, null, false);
        View view4 = LayoutInflater.from(this).inflate(R.layout.view4, null, false);
        View view5 = LayoutInflater.from(this).inflate(R.layout.view5, null, false);
        View view6 = LayoutInflater.from(this).inflate(R.layout.view1, null, false);
        View view7 = LayoutInflater.from(this).inflate(R.layout.view5, null, false);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        mList.add(view4);
        mList.add(view5);


        mList3.addAll(mList);
        mList3.add(0,view7);
        mList3.add(view6);

//        // 设置缓存页面，当前页面的相邻N各页面都会被缓存   N在这里就是1
//        advpager.setOffscreenPageLimit(1);
        Log.e("LayoutId"," "+R.layout.view1+" "+R.layout.view2+" "+R.layout.view3+" "+R.layout.view4+" "+R.layout.view5);
        Bundle bundle1 = new Bundle();
        bundle1.putInt("layout", R.layout.view1);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("layout", R.layout.view2);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("layout", R.layout.view3);
        Bundle bundle4 = new Bundle();
        bundle4.putInt("layout", R.layout.view4);
        Bundle bundle5 = new Bundle();
//        bundle5.putInt("layout", 5);
        bundle5.putInt("layout", R.layout.view5);
        DemoFragment demo1 = new DemoFragment();
        demo1.setArguments(bundle1);
        DemoFragment demo2 = new DemoFragment();
        demo2.setArguments(bundle2);
        DemoFragment demo3 = new DemoFragment();
        demo3.setArguments(bundle3);
        DemoFragment demo4 = new DemoFragment();
        demo4.setArguments(bundle4);
        DemoFragment demo5 = new DemoFragment();
        demo5.setArguments(bundle5);

        mList2.add(demo1);
        mList2.add(demo2);
        mList2.add(demo3);
        mList2.add(demo4);
        mList2.add(demo5);

        init();


        advpager.setAdapter(adapter);
//        advpager.setAdapter(mFragmentPagerAdapter);
//        advpager.setAdapter(mFragmentStatePagerAdapter);
//        advpager.setCurrentItem(500);
        advpager.setCurrentItem(1);
//        设置动画
//        advpager.setPageTransformer(true,new DepthPageTransformer());
//        advpager.setPageTransformer(true,new ZoomOutPageTransformer());
//        advpager.setPageTransformer(true,new CustomViewPageView());

        advpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("whatfuck",positionOffset+"  "+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Localposition = position;
//                动画效果略差 所以选择设置最大值   向恶势力低头
                if (mList.size() > 1) { //多于1，才会循环跳转
                    if (position < 1) { //首位之前，跳转到末尾（N）
                        position = mList.size();
                        advpager.setCurrentItem(position, false);
                    } else if (position > mList.size()) { //末位之后，跳转到首位（1）.
                        advpager.setCurrentItem(1, false);//false:不显示跳转过程的动画
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        del.setVisibility(View.VISIBLE);
        mI = 0;
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(Localposition);
//                Log.e("showme", "now size()" + mList.size());
                adapter.notifyDataSetChanged();
//                mList2.remove(Localposition);
//                Log.e("showme", "now size()" + mList2.size());
//                mFragmentPagerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList2.get(position);
            }

            @Override
            public int getCount() {
                return mList2.size();
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                DemoFragment fragment = (DemoFragment) super.instantiateItem(container, position);
                return fragment;
            }

            @Override
            public int getItemPosition(Object object) {
                return PagerAdapter.POSITION_NONE;
            }
        };
        mFragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList2.get(position);
            }

            @Override
            public int getCount() {
                return mList2.size();
            }
        };
    }
}
