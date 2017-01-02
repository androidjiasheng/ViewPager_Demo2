package com.example.administrator.viewpager_demo;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by jiasheng on 2016/12/29.
 * email 898478073@qq.com
 * Description: ViewPager_Demo2
 */

public class CustomViewPageView implements ViewPager.PageTransformer {

    private static final float ROT_MAX = 20.0f;
    private float mRot;
//    position的可能性的值有，其实从官方示例的注释就能看出：
//
//            [-Infinity,-1)
//
//    已经看不到了
//
//
//            (1,+Infinity]已经看不到了
//
//
//                    [-1, 1]
//
//             重点看[-1, 1]这个区间， 其他两个的View都已经看不到了~~
//
//
//                     假设现在ViewPager在A页现在滑出B页，则:
//
//                     A页的position变化就是(0, -1]
//
//             B页的position变化就是[1, 0]

    @Override
    public void transformPage(View page, float position) {
//        左边看不见得到页面
        Log.e("what",position+"");
        if(position<-1){
           page.setRotation(0);
        }else if(position <= 1){//从右往左滑的
//          可见范围的左半场
            if(position < 0){
                mRot = (ROT_MAX * position);
                page.setPivotX(page.getMeasuredWidth()*0.5f);
                page.setPivotY(page.getMeasuredHeight());
                page.setRotation(mRot);
            }else {
                mRot = (ROT_MAX * position);
//                设置View的旋转点
                page.setPivotX(page.getMeasuredWidth()*0.5f);
                page.setPivotY(page.getMeasuredHeight());
                page.setRotation(mRot);
            }
        }else {
            page.setRotation(0);
        }
    }
}
