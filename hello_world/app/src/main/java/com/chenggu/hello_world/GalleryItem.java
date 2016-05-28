package com.chenggu.hello_world;

/**
 * Created by chenggu on 5/28/16.
 */
public class GalleryItem {

    private String id;
    private String secret;
    private String server;
    private String farm;

    public GalleryItem(String id, String secret, String server, String farm) {
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return "http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" +secret + ".jpg";
    }
}
