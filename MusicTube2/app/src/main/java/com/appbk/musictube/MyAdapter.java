package com.appbk.musictube;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.api.client.util.DateTime;

import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by Pham Van Thong on 3/10/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> list = Collections.emptyList();
    Context context;
    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case 1: //VIDEO
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_layout, null);
                return new VideoHolder(view);
            case 2: //PLAYLIST
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.playlist_layout, null);
                return new PlaylistHolder(view);
        }
//        //Never getting up
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case 1:
                VideoHolder videoHolder = (VideoHolder) holder;
                configVideoHolder(videoHolder, position);
                break;
            case 2:
                PlaylistHolder playlist = (PlaylistHolder) holder;
                configPlaylistHolder(playlist, position);
                break;
        }
    }

    private void configPlaylistHolder(PlaylistHolder playlist, int position) {
        Playlist pl = (Playlist) list.get(position);
        if (pl != null) {
            playlist.getTitle().setText(pl.getTitle());
            playlist.getChanelTitle().setText(pl.getChanelTitle());
           new DownloadImageTask(playlist.getImageView()).execute(pl.getLow().getUrl());
        }
    }

    private void configVideoHolder(VideoHolder videoHolder, int position) {
        Video video = (Video) list.get(position);
        if (video != null) {
            videoHolder.getChanelTitle().setText(video.getChanelTitle());
            DateTime dt = video.getPublishAt();
            videoHolder.getDate().setText(" Since: " + dt.toString());
            videoHolder.getDescription().setText(video.getDescription());
            videoHolder.getTitle().setText(video.getTitle());
            new DownloadImageTask(videoHolder.getImageView()).execute(video.getLow().getUrl());
            }
    }
    @Override
    public int getItemViewType(int position) {
        Object item = list.get(position);
        if (item instanceof Video)
            return 1;
        return 2;
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }


}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}