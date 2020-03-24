package ru.polenova.ncraftmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.polenova.ncraftmedia.dto.Post

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val post = Post(123, "Vasya Pupkov", "First post in our network!\nHello Kotlin world!", "20 march 2020")
        textViewName.text = post.author
        textViewPost.text = post.content
        textViewDate.text = post.created
        imageViewLike.setOnClickListener {
            imageViewLike.setImageResource(R.drawable.ic_favorite_red_24dp)
            textViewLike.text = "${++post.countLike}"
        }
        imageViewComment.setOnClickListener {
            imageViewComment.setImageResource(R.drawable.ic_mode_comment_black_24dp)
            textViewComment.text = "${++post.countComment}"
        }
        imageViewShare.setOnClickListener {
            imageViewShare.setImageResource(R.drawable.ic_share_black_24dp)
            textViewShare.text = "${++post.countShare}"
        }
    }
}
