package com.example.autoimageslider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private SliderAdapter mAdapter;
    private ArrayList<SliderItem> mList=new ArrayList<>();
    private Handler sliderHandler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2=(ViewPager2) findViewById(R.id.viewPagerImageSlider);

        SliderItem item1=new SliderItem(R.drawable.first);
        SliderItem item2=new SliderItem(R.drawable.second);
        SliderItem item3=new SliderItem(R.drawable.third);
        SliderItem item4=new SliderItem(R.drawable.fourth);
        SliderItem item5=new SliderItem(R.drawable.fifth);
        SliderItem item6=new SliderItem(R.drawable.seven);

        mList.add(item1);
        mList.add(item2);
        mList.add(item3);
        mList.add(item4);
        mList.add(item5);
        mList.add(item6);

        mAdapter=new SliderAdapter(mList,viewPager2);
        viewPager2.setAdapter(mAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {

                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);

            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });

    }

    private Runnable sliderRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable,3000);
    }
}
