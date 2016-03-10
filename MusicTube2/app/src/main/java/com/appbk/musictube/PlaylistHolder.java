package com.appbk.musictube;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pham Van Thong on 3/10/2016.
 */
class PlaylistHolder extends RecyclerView.ViewHolder {
    protected ImageView imageView;
    protected TextView title, chanelTitle;
    public PlaylistHolder(View view) {
        super(view);
    this.imageView = (ImageView) view.findViewById(R.id.playlist_thumbnail);
        this.title = (TextView) view.findViewById(R.id.playlist_title);
        this.chanelTitle = (TextView) view.findViewById(R.id.playlist_chanel_title);
    }


    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getChanelTitle() {
        return chanelTitle;
    }
}