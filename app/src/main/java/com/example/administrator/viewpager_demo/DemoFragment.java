package com.example.administrator.viewpager_demo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jiasheng on 2016/12/29.
 * email 898478073@qq.com
 * Description: ViewPager_Demo2
 */

public class DemoFragment extends Fragment {
//    layout的Id
    private int layoutId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        layoutId = getArguments().getInt("layout");
        Log.e("showme","onAttach+fragment"+layoutId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("showme","onCreateView+fragment"+layoutId);
        View v = LayoutInflater.from(container.getContext()).inflate(layoutId,container,false);
        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("showme","onDestroyView"+layoutId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("showme","On destory");
    }
}
