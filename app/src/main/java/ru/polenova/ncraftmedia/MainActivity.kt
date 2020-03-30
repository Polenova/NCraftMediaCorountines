package ru.polenova.ncraftmedia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.polenova.ncraftmedia.dto.Post

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val post = Post(
            123,
            "Vasya Pupkov",
            "First post in our network! Hello Kotlin world!",
            "20 march 2020",
            Pair(55.75222, 37.61556)
        )
        textViewName.text = post.author
        textViewPost.text = post.content
        textViewDate.text = post.created
        val lat = post.location.first
        val lng = post.location.second
        imageViewLike.setOnClickListener {
            post.likeByMe = !post.likeByMe
            imageViewLike.setImageResource(
                if (post.likeByMe) R.drawable.ic_favorite_red_24dp
                else R.drawable.ic_favorite_grey_24dp
            )
            textViewLike.text = if (post.likeByMe) "1" else ""
        }
        imageViewComment.setOnClickListener {
            imageViewComment.setImageResource(R.drawable.ic_mode_comment_black_24dp)
            textViewComment.text = "${++post.countComment}"
        }
        imageViewShare.setOnClickListener {

            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, """ 
                    ${post.author} (${post.created})
                    ${post.content} 
                    """.trimIndent()
                )
                type = "text/plain"
            }
            startActivity(intent)
            imageViewShare.setImageResource(R.drawable.ic_share_black_24dp)
            textViewShare.text = "${++post.countShare}"
        }
        imageViewLocation.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:$lat,$lng")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}
