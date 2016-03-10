package com.appbk.musictube;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

/**
 * Created by Pham Van Thong on 3/10/2016.
 */
public class Playlist {
    String playlistID;
    String title;
    String chanelTitle;
    Thumbnail low, medium, hight;

    public Playlist(SearchResult searchResult) {
        playlistID = searchResult.getId().getPlaylistId();
        title = searchResult.getSnippet().getTitle();
        chanelTitle = searchResult.getSnippet().getChannelTitle();
        low = searchResult.getSnippet().getThumbnails().getDefault();
        medium = searchResult.getSnippet().getThumbnails().getMedium();
        hight = searchResult.getSnippet().getThumbnails().getHigh();
    }

    public String getPlaylistID() {
        return playlistID;
    }

    public String getTitle() {
        return title;
    }

    public String getChanelTitle() {
        return chanelTitle;
    }

    public Thumbnail getLow() {
        return low;
    }

    public Thumbnail getHight() {
        return hight;
    }

    public Thumbnail getMedium() {
        return medium;
    }
}
