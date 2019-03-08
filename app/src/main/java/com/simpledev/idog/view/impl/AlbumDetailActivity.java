package com.simpledev.idog.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.simpledev.idog.R;
import com.simpledev.idog.injection.DaggerAlbumDetailViewComponent;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.view.AlbumDetailView;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.AlbumDetailPresenter;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.injection.AlbumDetailViewModule;
import com.simpledev.idog.view.adapter.AlbumDetailRvAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class AlbumDetailActivity extends BaseActivity<AlbumDetailPresenter, AlbumDetailView> implements AlbumDetailView {
    @Inject
    PresenterFactory<AlbumDetailPresenter> mPresenterFactory;

    private Bundle mBundle;

    private AlbumDetailRvAdapter mRvAdapter;

    private List<String> mListImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        mBundle = getIntent().getExtras();

        initRecycleView();
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(mBundle.getString(Constants.BundleKey.NAME_BREED, getString(R.string.app_name)));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initRecycleView() {
        RecyclerView mRecycleView = findViewById(R.id.rv_images);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setHasFixedSize(true);
        mRvAdapter = new AlbumDetailRvAdapter(this);
        mListImages = new ArrayList<>();
        mRvAdapter.setImageList(mListImages);
        mRecycleView.setAdapter(mRvAdapter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Timber.d("On post created");
        if(mPresenter != null) {
            mPresenter.getAllImagesOfBreed(mBundle.getString(Constants.BundleKey.BASE_BREED), mBundle.getString(Constants.BundleKey.SUB_BREED));
        }
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerAlbumDetailViewComponent.builder()
                .appComponent(parentComponent)
                .albumDetailViewModule(new AlbumDetailViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<AlbumDetailPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void updateBreedDetail(List<String> listBreedImages) {
        mListImages.clear();
        mListImages.addAll(listBreedImages);
        mRvAdapter.notifyDataSetChanged();
    }
}
