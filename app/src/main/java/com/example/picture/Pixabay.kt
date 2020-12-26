package com.example.picture

data class Pixabay (
    val totalHits: Int,
    val hits:Array<PhotoItem>,
    val total:Int,
)

data class PhotoItem(
    val webformatURL: String,
    val id:Int,
    val largeImageURL: String,
)