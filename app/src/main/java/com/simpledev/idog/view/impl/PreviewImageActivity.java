package com.simpledev.idog.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.simpledev.idog.R;
import com.simpledev.idog.util.Commons;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.view.PreviewImageView;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.PreviewImagePresenter;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.injection.PreviewImageViewModule;
import com.simpledev.idog.injection.DaggerPreviewImageViewComponent;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class PreviewImageActivity extends BaseActivity<PreviewImagePresenter, PreviewImageView> implements PreviewImageView {
    @Inject
    PresenterFactory<PreviewImagePresenter> mPresenterFactory;

    @BindView(R.id.imv_preview)
    ZoomageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_image);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra(Constants.BundleKey.IMAGE_URL);


        if (!Commons.isNullOrEmpty(url)){
            Glide.with(this)
                    .load(url)
                    .into(mImageView);
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
     }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerPreviewImageViewComponent.builder()
                .appComponent(parentComponent)
                .previewImageViewModule(new PreviewImageViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<PreviewImagePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
