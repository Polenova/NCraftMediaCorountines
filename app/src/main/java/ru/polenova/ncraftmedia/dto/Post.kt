package ru.polenova.ncraftmedia.dto

import java.security.CodeSource

enum class TypePost {
    POST, REPOST, REPLY
}

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val created: String,
    val location: Pair<Double, Double>,

    val typePost: TypePost = TypePost.POST,
    val source: Post? = null,
    var likeByMe: Boolean = false,
    var countLiked: Int = 0,
    var countComment: Int = 0,
    var countShare: Int = 0
)