package com.example.searchapplication

data class Image(
    val thumbnailUrl: String,
    val displaySitename: String,
    val datetime: String
)

data class KakaoSearchResponse(
    val documents: List<KakaoImageDocument>
)

data class KakaoImageDocument(
    val thumbnailUrl: String,
    val displaySitename: String,
    val datetime: String
)



data class KakaoImageSearchResponse(
    val meta: Meta,
    val documents: List<Document>
)

data class Meta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean
)

data class Document(
    val collection: String,
    val thumbnail_url: String,
    val image_url: String,
    val width: Int,
    val height: Int,
    val display_sitename: String,
    val doc_url: String,
    val datetime: String
)
