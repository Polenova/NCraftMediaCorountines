package ru.polenova.ncraftmedia.Adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_card.view.*
import kotlinx.android.synthetic.main.post_card.view.imageViewComment
import kotlinx.android.synthetic.main.post_card.view.imageViewLike
import kotlinx.android.synthetic.main.post_card.view.imageViewShare
import kotlinx.android.synthetic.main.post_card.view.textViewComment
import kotlinx.android.synthetic.main.post_card.view.textViewDate
import kotlinx.android.synthetic.main.post_card.view.textViewLike
import kotlinx.android.synthetic.main.post_card.view.textViewName
import kotlinx.android.synthetic.main.post_video.view.*
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.Post

class VideoViewHolder(adapter: PostAdapter, view: View) : BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            imageViewLike.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.likeByMe = !item.likeByMe
                    adapter.notifyItemChanged(adapterPosition)
                }
            }
            imageViewShare.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT, """
                                ${item.author} (${item.created})
    
                                ${item.content}
                            """.trimIndent()
                        )
                        type = "text/plain"
                    }
                    itemView.context.startActivity(intent)
                    imageViewShare.setImageResource(R.drawable.ic_share_black_24dp)
                    //textViewShare.text = "${++post.countShare}"
                }
            }
            imageViewComment.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    imageViewComment.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                    textViewComment.text = "${++item.countComment}"
                }
            }
            imageButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(item.sourceHTTP)
                    }
                    itemView.context.startActivity(intent)
                }
            }
            ivRemove.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapter.notifyItemRemoved(adapterPosition)
                }
            }
        }
    }

    override fun bind(post: Post) {
        with(itemView) {
            textViewName.text = "${post.author}: ${post.content}"
            textViewDate.text = post.created
            if (post.likeByMe) {
                imageViewLike.setImageResource(R.drawable.ic_favorite_red_24dp)
                textViewLike.visibility = View.VISIBLE
                textViewLike.text = "${++post.countLiked}"
            } else {
                textViewLike.text = "${post.countLiked}"
                if (post.countLiked == 0) {
                    imageViewLike.setImageResource(R.drawable.ic_favorite_grey_24dp)
                    textViewLike.visibility = View.INVISIBLE
                } else {
                    textViewLike.text = "${--post.countLiked}"
                }
            }
        }
    }
}