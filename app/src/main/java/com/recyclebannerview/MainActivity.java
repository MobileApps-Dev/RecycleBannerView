package com.recyclebannerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpagerTop, viewPagerBackground;
    public static final int ADAPTER_TYPE_TOP = 1;
    public static final int ADAPTER_TYPE_BOTTOM = 2;
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_TRANSITION_IMAGE = "image";
    int currentPage = 0;



    private int[] listItems = {R.mipmap.ch, R.mipmap.li, R.mipmap.images, R.mipmap.tt, R.mipmap.dd, R.mipmap.ed,
            R.mipmap.ss}; //, R.mipmap.img6, R.mipmap.img7, R.mipmap.img8, R.mipmap.img9, R.mipmap.img10};

    //private int lastPageIndex = listItems.length - 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewpagerTop = (ViewPager) findViewById(R.id.viewpagerTop);
        //   viewPagerBackground = (ViewPager) findViewById(R.id.viewPagerbackground);


        viewpagerTop.setClipChildren(false);
        viewpagerTop.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.pager_margin));
        viewpagerTop.setOffscreenPageLimit(3);
        viewpagerTop.setPageTransformer(false, new CarouselEffectTransformer(this)); // Set transformer


//** Auto start of viewpager **//
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == listItems.length) {
                    currentPage = 0;
                }
                viewpagerTop.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000); // image swipe after 5 second


        // Set Top ViewPager Adapter
        MyPagerAdapter adapter = new MyPagerAdapter(this, listItems, ADAPTER_TYPE_TOP);
        viewpagerTop.setAdapter(adapter);

        // Set Background ViewPager Adapter
        MyPagerAdapter adapterBackground = new MyPagerAdapter(this, listItems, ADAPTER_TYPE_BOTTOM);
        // viewPagerBackground.setAdapter(adapterBackground);


        viewpagerTop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int index = 0;

            @Override
            public void onPageSelected(int position) {
                index = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //    int width = viewPagerBackground.getWidth();
                //  viewPagerBackground.scrollTo((int) (width * position + width * positionOffset), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    //  viewPagerBackground.setCurrentItem(index);
//                }

//                if (currentPage == 0)
//                    viewpagerTop.setCurrentItem(lastPageIndex - 1, false);
//
//                if (currentPage == lastPageIndex)
//                    viewpagerTop.setCurrentItem(1, false);

            }
        });


    }
}
