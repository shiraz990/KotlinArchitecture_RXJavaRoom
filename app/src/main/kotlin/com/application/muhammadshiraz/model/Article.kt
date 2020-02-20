package com.application.muhammadshiraz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import java.io.Serializable


@Entity
data class Article(
        @field:PrimaryKey
        var id: Long =0L,
        var title: String="",
        var dateTime: String="",
        var subject: String="",
        var image: String="",
        var description: String=""
)


data class ArticleItem(
         var id: Long =0L,
        var title: String="",
        var dateTime: String="",
        var subject: String="",
        var image: String="",
        var description: String=""
):Serializable
data class ArticleResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)

data class Result(
    val `abstract`: String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: Any,
    val des_facet: List<String>,
    val eta_id: Int,
    val geo_facet: List<String>,
    val id: Long,
    val media: List<Media>,
    val nytdsection: String,
    val org_facet: List<Any>,
    val per_facet: List<Any>,
    val published_date: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
)

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    val `media-metadata`: List<MediaMetadata>,
    val subtype: String,
    val type: String
)

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
)