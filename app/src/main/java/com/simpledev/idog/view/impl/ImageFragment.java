package com.simpledev.idog.view.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jsibbold.zoomage.ZoomageView;
import com.simpledev.idog.App;
import com.simpledev.idog.R;
import com.wang.avi.AVLoadingIndicatorView;


public class ImageFragment extends Fragment {


    private static ImageFragment mImageFragment;

    private ZoomageView mImageView;
    private AVLoadingIndicatorView mLoadingView;

    private String mImageUrl;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance() {
        if(mImageFragment == null) {
            mImageFragment = new ImageFragment();
        }
        return mImageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        mImageView = view.findViewById(R.id.imv_preview);
        mLoadingView = view.findViewById(R.id.prg_place_holder);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadImage();
    }

    private void loadImage() {
        Glide.with(App.getAppContext())
                .load(mImageUrl)
                .apply(new RequestOptions()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .thumbnail(0.5f)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        mLoadingView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        mLoadingView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mImageView);
    }


    public void setImageUrl(String url) {
        mImageUrl = url;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
