package com.example.newzify;

/**
 * Created by ASHISH on 02-Jan-18.
 */

public class NewsArticle {
    private String imageUrl;
    private String title;
    private String description;
    private String url;

    public NewsArticle(String imageUrl, String title, String description, String url) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
