package com.simpledev.idog.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simpledev.idog.App;
import com.simpledev.idog.R;
import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.view.AlbumView;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.AlbumPresenter;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.injection.AlbumViewModule;
import com.simpledev.idog.injection.DaggerAlbumViewComponent;
import com.simpledev.idog.view.adapter.AlbumRvAdapter;
import com.simpledev.idog.view.helper.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public final class AlbumFragment extends BaseFragment<AlbumPresenter, AlbumView> implements AlbumView, OnItemClickListener {
    @Inject
    PresenterFactory<AlbumPresenter> mPresenterFactory;

    @BindView(R.id.rv_album)
    RecyclerView mRVAlbum;

    private static AlbumFragment mAlbumFragment;

    private AlbumRvAdapter mAdapter;
    private List<Breed> mAlbumList;

    public AlbumFragment() {
    }

    public static AlbumFragment getInstance() {
        if(mAlbumFragment == null) {
            mAlbumFragment = new AlbumFragment();
        }
        return mAlbumFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("On create");

        mAdapter = new AlbumRvAdapter();
        mAlbumList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.d("On view created");
        initRecycleView();
    }

    private void initRecycleView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(App.getAppContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRVAlbum.getContext(),
                layoutManager.getOrientation());

        mRVAlbum.addItemDecoration(dividerItemDecoration);
        mRVAlbum.setLayoutManager(layoutManager);
        mAdapter.addBreeds(mAlbumList);
        mAdapter.setItemClickListener(this);
        mRVAlbum.setAdapter(mAdapter);
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerAlbumViewComponent.builder()
                .appComponent(parentComponent)
                .albumViewModule(new AlbumViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<AlbumPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void updateAlbum(List<Breed> breedList) {
        mAlbumList.clear();
        mAlbumList.addAll(breedList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Breed breed) {
        Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
        intent.putExtra(Constants.BundleKey.BREED, breed);
        startActivity(intent);
    }
}
