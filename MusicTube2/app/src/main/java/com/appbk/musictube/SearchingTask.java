package com.appbk.musictube;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pham Van Thong on 3/10/2016.
 */
public class SearchingTask extends AsyncTask<String, Void, List<SearchResult>> {
    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
    private static final String API_KEY = "AIzaSyDdioZ_546vaRaJbLkzN-bPh_0WuqYAfBg";
    private RecyclerView.Adapter adapter;

    public SearchingTask(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    private static final YouTube youTube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {

        public void initialize(HttpRequest request) throws IOException {
        }
    }).setApplicationName("youtube-cmdline-search-sample").build();


    protected List<SearchResult> doInBackground(String... query) {
        try {
            YouTube.Search.List search = youTube.search().list("id,snippet");
            search.setKey(API_KEY);
            search.setQ(query[0]);
            search.setType("video,playlist");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            SearchListResponse searchResponse = search.execute();

            return searchResponse.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPreExcute() {
        //Do Job in backround thread

    }

    protected void onPostExecute(List<SearchResult> searchResults) {
        Iterator<SearchResult> iteratorSearchResults = searchResults.iterator();
        List<Object> list = new ArrayList<Object>();
        if (searchResults != null) {
            if (!iteratorSearchResults.hasNext()) {
                //NO RESULT
                Log.e("Server result", " There aren't any results for your query.");
            }

            while (iteratorSearchResults.hasNext()) {

                Object tmp = null;
                SearchResult item = iteratorSearchResults.next();
                ResourceId rId = item.getId();

                // Confirm that the result represents a video. Otherwise, the
                // item will not contain a video ID.
                if (rId.getKind().equals("youtube#playlist")) {
                    tmp = new Playlist(item);
                }
                else if (rId.getKind().equals("youtube#video")) {
                    tmp = new Video(item);
                }
                if (tmp != null)
                    list.add(tmp);
                
            }
        }

        ((MyAdapter)adapter).setList(list);
    }


    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singlePlaylist = iteratorSearchResults.next();
            ResourceId rId = singlePlaylist.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#playlist")) {
                Thumbnail thumbnail = singlePlaylist.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getPlaylistId());
                System.out.println(" Title: " + singlePlaylist.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
}