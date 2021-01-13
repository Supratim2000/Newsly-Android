package com.example.newsly

class NewsDataClass {

    private val author: String
    private val title: String
    private val url: String
    private val urlToImage: String

    constructor(author: String, title: String, url: String, urlToImage: String, publishedAt: String) {
        this.author = author
        this.title = title
        this.url = url
        this.urlToImage = urlToImage
    }

    public fun getAuthor(): String = this.author
    public fun getTitle(): String = this.title
    public fun getUrl(): String = this.url
    public fun getUrlToImage(): String = this.urlToImage
}