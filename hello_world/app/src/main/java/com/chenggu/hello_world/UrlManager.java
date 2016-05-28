package com.chenggu.hello_world;

import android.net.Uri;

/**
 * Created by chenggu on 5/28/16.
 */
public class UrlManager {

    private static final String TAG = UrlManager.class.getSimpleName();

    public static final String API_KEY = "6350b336ef6558adf0e613c560ed11b7";
    public static final String PREF_SEARCH_QUERY = "searchQuery";

    public static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    public static final String METHOD_GETRECENT = "flickr.photos.getRecent";
    public static final String METHOD_SEARCH = "flickr.photos.search";

    public static volatile UrlManager instance = null;
    private UrlManager() {

    }

    // 多线程单例模式
    public static UrlManager getInstance() {
        if(instance == null) {
            synchronized (UrlManager.class) {
                // TODO: 需要再检查一次null么？
                if(instance == null) {
                    instance = new UrlManager();
                }
            }
        }
        return instance;
    }

    public static String getItemUrl(String query, int page) {
        String url;
        if(query != null) {
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_SEARCH)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("text", query)
                    .appendQueryParameter("page", String.valueOf(page))
                    .build().toString();
        } else {
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_GETRECENT)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("page", String.valueOf(page))
                    .build().toString();
        }
        return url;
    }


}
