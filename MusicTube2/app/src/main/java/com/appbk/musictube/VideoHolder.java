package com.appbk.musictube;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pham Van Thong on 3/10/2016.
 */
public class VideoHolder extends RecyclerView.ViewHolder {
    protected ImageView imageView;
    protected TextView title, date, chanelTitle, description;
    public VideoHolder(View view) {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.video_thumbnail);
        this.title = (TextView) view.findViewById(R.id.video_title);
        date = (TextView) view.findViewById(R.id.video_chanel_date);
        description = (TextView) view.findViewById(R.id.video_description);
        chanelTitle = (TextView) view.findViewById(R.id.video_chanel_title);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getChanelTitle() {
        return chanelTitle;
    }

    public TextView getDescription() {
        return description;
    }
}
