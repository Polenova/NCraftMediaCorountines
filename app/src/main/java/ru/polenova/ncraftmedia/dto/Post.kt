package ru.polenova.ncraftmedia.dto

enum class TypePost {
    POST, REPOST, REPLY, VIDEO, COMMERCIAL
}

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val created: String,
    val location: Pair<Double, Double>? = null,

    var typePost: TypePost = TypePost.POST,
    val source: Post? = null,
    val sourceHTTP: String? = null,

    var likeByMe: Boolean = false,
    var countLiked: Int = 0,
    var countComment: Int = 0,
    var countShare: Int = 0
)