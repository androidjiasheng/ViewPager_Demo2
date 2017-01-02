package com.example.administrator.viewpager_demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private View top;
    private ImageCycleView mAdView;
    private ArrayList<String> mImageUrl = null;
    private String imageUrl1 = "http://ww2.sinaimg.cn/large/7a8aed7bjw1ex8h4lnq3oj20hs0qoadj.jpg";
    private String imageUrl2 = "http://ww2.sinaimg.cn/large/7a8aed7bjw1ex8h4lnq3oj20hs0qoadj.jpg";
    private String imageUrl3 = "http://ww2.sinaimg.cn/large/7a8aed7bjw1ex8h4lnq3oj20hs0qoadj.jpg";
    private String imageUrl4 = "http://ww2.sinaimg.cn/large/7a8aed7bjw1ex8h4lnq3oj20hs0qoadj.jpg";
    private ListView testlist;
    private SwipeRefreshLayout fresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        fresh = (SwipeRefreshLayout) findViewById(R.id.fresh);
        testlist = (ListView) findViewById(R.id.testlist);
        mImageUrl = new ArrayList<String>();
        mImageUrl.add(imageUrl1);
        mImageUrl.add(imageUrl2);
        mImageUrl.add(imageUrl3);
        mImageUrl.add(imageUrl4);
        top = LayoutInflater.from(this).inflate(R.layout.top_head, null);
        mAdView = (ImageCycleView) top.findViewById(R.id.adview);
        mAdView.setImageResources(mImageUrl, 1);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "test");
        }
//        mAdView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                    case MotionEvent.ACTION_MOVE:
//                        fresh.setEnabled(false);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        fresh.setEnabled(true);
//                        break;
//                }
//                return false;
//            }
//        });

        mAdView.addpagelistener(new ImageCycleView.PageListener() {
            @Override
            public void enablefresh(int state) {
                enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE);
            }
        });
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        testlist.addHeaderView(top);
        testlist.setAdapter(aa);
    }

    private void enableDisableSwipeRefresh(boolean b) {
        if (fresh != null) {
            fresh.setEnabled(b);
        }
    }


}
