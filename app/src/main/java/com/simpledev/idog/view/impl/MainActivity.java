package com.simpledev.idog.view.impl;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.simpledev.idog.App;
import com.simpledev.idog.R;
import com.simpledev.idog.util.database.RxPreferenceHelper;
import com.simpledev.idog.view.MainView;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.MainPresenter;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.injection.MainViewModule;
import com.simpledev.idog.injection.DaggerMainViewComponent;
import com.simpledev.idog.view.adapter.MainViewPagerAdapter;
import com.simpledev.idog.view.custom.CustomTabLayout;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView, TabLayout.OnTabSelectedListener {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    @BindView(R.id.tab_layout)
    CustomTabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    PowerMenu mPowerMenu;

    @BindView(R.id.imv_main_menu)
    ImageView mMainMenu;

    @Inject
    RxPreferenceHelper mPreferenceHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.d("On main activity create");

        ButterKnife.bind(this);

        setupTabLayout();

        createMenu();

        setUpGestureListener();
    }

    private void setUpGestureListener() {

    }

    private void setupTabLayout() {
        AlbumFragment albumFragment = AlbumFragment.getInstance();
        MainViewPagerAdapter viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());

        RandomFragment randomFragment = RandomFragment.getInstance();

        viewPagerAdapter.setFragment(randomFragment, "Random");
        viewPagerAdapter.setFragment(albumFragment, "Albums");
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(this);
    }


    @OnClick(R.id.imv_main_menu)
    public void openMainMenu() {
        if(!mPowerMenu.isShowing()) {
            mPowerMenu.showAsDropDown(mMainMenu);
        }
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Timber.i("ON Tab selected");
        changeTabTextColor(tab, true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        changeTabTextColor(tab, false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void changeTabTextColor(TabLayout.Tab tab, boolean isSelected) {
        TextView txtTitle = tab.getCustomView().findViewById(R.id.tabTitle);
        if(isSelected) {
            txtTitle.setTextColor(getResources().getColor(R.color.tabActiveColor));
        }else {
            txtTitle.setTextColor(getResources().getColor(R.color.tabInActiveColor));
        }
    }

    private void createMenu() {
        Context context = App.getAppContext();

        mPowerMenu = new PowerMenu.Builder(context)
                .addItem(new PowerMenuItem("Journals", false))
                .addItem(new PowerMenuItem("Travel", false))
                .setAnimation(MenuAnimation.FADE)
                .setMenuRadius(10f)
                .setMenuShadow(10f)
                .setTextColor(context.getResources().getColor(R.color.tabActiveColor))
                .setSelectedTextColor(Color.WHITE)
                .setMenuColor(Color.WHITE)
                .setSelectedMenuColor(context.getResources().getColor(R.color.colorPrimary))
                .setShowBackground(false)
                .setOnMenuItemClickListener(null)
                .build();
    }

    @Override
    public void onBackPressed() {
         showCloseAppPopup();
    }

}
