package com.simpledev.idog.view.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.simpledev.idog.App;
import com.simpledev.idog.R;

public class CustomTabLayout extends TabLayout {

    private Typeface mTypeFace;

    public CustomTabLayout(Context context) {
        super(context);
        initTypeFace();
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeFace();
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace();
    }

    private void initTypeFace() {
        //mTypeFace = Typeface.createFromAsset(getContext().getAssets(),"fonts/AmazingDayEveryday.ttf");
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);

        boolean isFirstTab = true;

        if(viewPager != null) {
            this.removeAllTabs();

            PagerAdapter adapter = viewPager.getAdapter();

            if(adapter == null) {
                return;
            }

            for (int i = 0, count = adapter.getCount(); i < count; i++) {

                LayoutInflater layoutInflater = (LayoutInflater)App.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.custom_tab, null, false);

                TextView txtTitle = view.findViewById(R.id.tabTitle);
                txtTitle.setText(adapter.getPageTitle(i));

                if(isFirstTab) {
                    txtTitle.setTextColor(getResources().getColor(R.color.tabActiveColor));
                }

                isFirstTab = false;

                Tab tab = this.newTab();
                tab.setCustomView(view);
                this.addTab(tab);
            }
        }
    }
}
