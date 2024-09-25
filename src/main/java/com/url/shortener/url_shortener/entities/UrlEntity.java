package com.url.shortener.url_shortener.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "links")
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String urlShortener;

    public UrlEntity() {
    }

    public UrlEntity(Long id, String url, String urlShortener) {
        this.id = id;
        this.url = url;
        this.urlShortener = urlShortener;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlShortener() {
        return urlShortener;
    }

    public void setUrlShortener(String urlShortener) {
        this.urlShortener = urlShortener;
    }

}
