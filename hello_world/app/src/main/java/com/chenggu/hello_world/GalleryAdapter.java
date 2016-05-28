package com.chenggu.hello_world;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by chenggu on 5/28/16.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context mContext; // 代表一个运行时的环境
    private List<GalleryItem> mList;

    public GalleryAdapter(Context mContext, List<GalleryItem> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    /*
     * 以一个缓存的形式把这个viewHolder存下来，已经load过的imageView就不用再次生成了
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.gallery_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 把一个xml装载到一个view上
        // 把adapter_gallery load 到v这个view的instance当中
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gallery,
                parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GalleryItem item = mList.get(position);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Glide.with(mContext)
                .load(item.getUrl())
                .thumbnail(0.5f)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<GalleryItem> newList) {
        mList.addAll(newList);
    }

    public void clear() {
        mList.clear();
    }

}
