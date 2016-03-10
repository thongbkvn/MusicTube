package com.appbk.musictube;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

/**
 * Created by Pham Van Thong on 3/10/2016.
 */
public class Video {
    private String videoID;
    private String title;
    private String chanelTitle;
    private String description;
    private DateTime publishAt;
    private Thumbnail low,medium,hight;
    public Video(SearchResult searchResult) {
        videoID = searchResult.getId().getVideoId();
        title = searchResult.getSnippet().getTitle();
        chanelTitle = searchResult.getSnippet().getChannelTitle();
        description = searchResult.getSnippet().getDescription();
        low = searchResult.getSnippet().getThumbnails().getDefault();
        medium = searchResult.getSnippet().getThumbnails().getMedium();
        hight = searchResult.getSnippet().getThumbnails().getHigh();
        publishAt = searchResult.getSnippet().getPublishedAt();
    }

    public String getVideoID() {
        return videoID;
    }

    public String getTitle() {
        return title;
    }

    public String getChanelTitle() {
        return chanelTitle;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getPublishAt() {
        return publishAt;
    }

    public Thumbnail getLow() {
        return low;
    }

    public Thumbnail getMedium() {
        return medium;
    }

    public Thumbnail getHight() {
        return hight;
    }
}
