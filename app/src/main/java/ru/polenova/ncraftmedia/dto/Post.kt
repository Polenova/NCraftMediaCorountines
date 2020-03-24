package ru.polenova.ncraftmedia.dto

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val created: String,
    var countLike: Int = 0,
    var countComment: Int = 0,
    var countShare: Int = 0
)