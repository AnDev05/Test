package com.simpledev.idog.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.simpledev.idog.App;
import com.simpledev.idog.R;
import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.util.Commons;
import com.simpledev.idog.view.helper.OnItemClickListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumRvAdapter extends RecyclerView.Adapter<AlbumRvAdapter.ViewHolder> {

    private List<Breed> mListBreed = new ArrayList<>();

    private OnItemClickListener mItemClickListener;
    private Context mContext;

    public AlbumRvAdapter() {
        this.mContext = App.getAppContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_album_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Breed breed = mListBreed.get(position);
        viewHolder.tvAlbumName.setText(breed.getBreedName());
        viewHolder.tvImageNumber.setText(breed.getNumberOfImage() > 0 ? String.valueOf(breed.getNumberOfImage()) : "");

        String url = "https://images.dog.ceo/breeds/komondor/n02105505_2069.jpg";

        if(!Commons.isNullOrEmpty(breed.getImageUrl())) {
            url = breed.getImageUrl();
        }

        viewHolder.prgPlaceHolder.setVisibility(View.VISIBLE);

        Glide.with(mContext)
                .load(url)
                .apply(new RequestOptions()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .thumbnail(0.5f)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        viewHolder.prgPlaceHolder.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        viewHolder.prgPlaceHolder.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(viewHolder.imvThumb);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(breed);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListBreed.size();
    }

    public void addBreeds(List<Breed> mListBreeds) {
        this.mListBreed = mListBreeds;
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.with(mContext).clear(holder.itemView);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;

        @BindView(R.id.imv_thumb_album)
        ImageView imvThumb;

        @BindView(R.id.tv_image_number)
        TextView tvImageNumber;

        @BindView(R.id.tv_album_name)
        TextView tvAlbumName;

        @BindView(R.id.prg_place_holder)
        AVLoadingIndicatorView prgPlaceHolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
