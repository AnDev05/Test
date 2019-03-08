package com.simpledev.idog.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.simpledev.idog.R;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.view.impl.PreviewImageActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

public class AlbumDetailRvAdapter extends RecyclerView.Adapter<AlbumDetailRvAdapter.ItemViewHolder> {

    private List<String> mListImages;

    private Context mContext;


    public AlbumDetailRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setImageList(List<String> mListImages) {
        this.mListImages = mListImages;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_album_detail_layout, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int position) {
        final String url = mListImages.get(position);
        final int index = position;
        itemViewHolder.prgPlaceHolder.setVisibility(View.VISIBLE);

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
                        itemViewHolder.prgPlaceHolder.setVisibility(View.GONE);
                        removeItem(index);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        itemViewHolder.prgPlaceHolder.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(itemViewHolder.mImageView);

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PreviewImageActivity.class);
                intent.putExtra(Constants.BundleKey.IMAGE_URL, url);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onViewRecycled(@NonNull ItemViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.with(mContext).clear(holder.mImageView);
    }

    public void removeItem(int index) {
        mListImages.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mListImages.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        View itemView;
        AVLoadingIndicatorView prgPlaceHolder;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_photo);
            prgPlaceHolder = itemView.findViewById(R.id.prg_place_holder);
            this.itemView = itemView;
        }
    }
}
