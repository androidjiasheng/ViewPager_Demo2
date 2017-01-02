package com.example.administrator.viewpager_demo;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 * 作者：wu
 */
public class ImageCycleView extends LinearLayout{
    private Context mContext;
    private final ViewPager mAdViewPager;
    private final ViewGroup mGroup;
    private Handler mHandler = new Handler();
    /**
     * 图片滚动当前的下标
     */
    private boolean isStop;
    /**
     * 滚动图片指示视图列表
     */
    private ImageView[] mImageViews = null;
    /**
     * 游标是圆形还是长条，要是设置为0是长条，要是1就是圆形 默认是圆形
     */
    public int style=1;
    private ImageView mImageView;
    private List<ImageView> mImageViewList = new ArrayList<>();
    private ImageCycleAdapter mAdapter;

    public ImageCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.ad_cycle_view,this);
        mAdViewPager = (ViewPager) findViewById(R.id.adv_pager);
        mAdViewPager.addOnPageChangeListener(new GuildPageChangeListener());
//        滚动图片右下指示器
        mGroup = (ViewGroup) findViewById(R.id.viewGroup);
    }

    /**
     * @param imageUrlList     图片的列表
     * @param style            下标的形状
     */
    public void setImageResources(ArrayList<String> imageUrlList,int style){
        this.style = style;
//
        mGroup.removeAllViews();
//        图片广告的数量
        final int imageCount = imageUrlList.size();
//      图片的资源
        mImageViewList = setImageList(imageUrlList);
        mImageViews = new ImageView[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageView = new ImageView(mContext);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin=30;
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setLayoutParams(params);
            mImageViews[i] = mImageView;
            if (i == 0) {
                if(style==1)
                    mImageViews[i].setBackgroundResource(R.drawable.banner_dian_focus);
                else
                    mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_focus);
            } else {
                if(style==1)
                    mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
                else
                    mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_blur);
            }
            mGroup.addView(mImageViews[i]);
        }
        mAdapter = new ImageCycleAdapter();
        mAdViewPager.setAdapter(mAdapter);
        mAdViewPager.setCurrentItem(1000/2);
        startImage();
    }


    private List<ImageView> setImageList(List<String> mimageurl){
        List<ImageView> viewlist = new ArrayList<>();
        for (int i = 0; i < mimageurl.size(); i++) {
            ImageView view = new ImageView(mContext);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
            Glide.with(mContext).load(mimageurl.get(i)).into(view);
            viewlist.add(view);
        }
        return viewlist;
    }

//    触摸停止计时器  抬起启动计时器
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_UP){
            startImage();
        }else {
            pauseImage();
        }
        return super.dispatchTouchEvent(ev);
    }

//  暂停轮播
    public void pauseImage(){
        StopTimerTask();
    }

//   开始轮播
    public void startImage(){
        StrartTimerTask();
    }

    private void StrartTimerTask(){
        StopTimerTask();
        // 图片滚动
        mHandler.postDelayed(mImageTimerTask, 3000);
    }

    private void StopTimerTask(){
        isStop=true;
        mHandler.removeCallbacks(mImageTimerTask);
    }

    private Runnable mImageTimerTask = new Runnable() {
        @Override
        public void run() {
            if(mImageView!=null){
                mAdViewPager.setCurrentItem(mAdViewPager.getCurrentItem() + 1);
                if(!isStop){
                    mHandler.postDelayed(mImageTimerTask,3000);
                }
            }
        }
    };

    private class GuildPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position%mImageViews.length;
            if(style==1)
                mImageViews[position].setBackgroundResource(R.drawable.banner_dian_focus);
            else
                mImageViews[position].setBackgroundResource(R.drawable.cicle_banner_dian_focus);
            for (int i = 0; i < mImageViews.length; i++) {
                if (position != i) {
                    if(style==1)
                        mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
                    else
                        mImageViews[i].setBackgroundResource(R.drawable.cicle_banner_dian_blur);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mListener.enablefresh(state);
            if (state == ViewPager.SCROLL_STATE_IDLE)
                startImage();
        }
    }

    private PageListener mListener = null;
    public interface PageListener{
        void enablefresh(int state);
    }
    public void addpagelistener(PageListener listener){
        this.mListener = listener;
    }


    private class ImageCycleAdapter extends PagerAdapter{

        @Override
        public int getCount() {
//            return mImageViewList.size();
//            这里将其设置为最大就是无限轮播滑动的要点
            return 1000;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position%mImageViews.length));
            return mImageViewList.get(position%mImageViews.length);
        }
    }
}
