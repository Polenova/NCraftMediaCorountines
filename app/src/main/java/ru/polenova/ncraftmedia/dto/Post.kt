package ru.polenova.ncraftmedia.dto

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val created: String,
    val location: Pair<Double, Double>,
    var likeByMe: Boolean = false,
    var countLiked: Int = 0,
    var countComment: Int = 0,
    var countShare: Int = 0
)