package ru.polenova.ncraftmedia.Adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_card.view.*
import kotlinx.android.synthetic.main.post_card.view.imageViewCom
import kotlinx.android.synthetic.main.post_card.view.imageViewComment
import kotlinx.android.synthetic.main.post_card.view.imageViewLike
import kotlinx.android.synthetic.main.post_card.view.imageViewShare
import kotlinx.android.synthetic.main.post_card.view.textViewComment
import kotlinx.android.synthetic.main.post_card.view.textViewLike
import kotlinx.android.synthetic.main.post_card.view.textViewName
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.Post

open class BaseViewHolder(val adapter: PostAdapter, view: View) :
    RecyclerView.ViewHolder(view) {

    init {
        this.clickViews()
    }

    open fun clickViews() {
        with(itemView) {
            imageViewLike.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.likeByMe = !item.likeByMe
                    if (item.likeByMe) {
                        ++item.countLiked
                    } else {
                        --item.countLiked
                    }
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
                    //imageViewShare.setImageResource(R.drawable.ic_share_black_24dp)
                }
            }
            imageViewComment.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    imageViewComment.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                    ++item.countComment
                }
                adapter.notifyItemChanged(adapterPosition)
            }
            imageViewLocation.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val lat = item.location!!.first
                    val lng = item.location.second
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("geo:$lat,$lng")
                    }
                    itemView.context.startActivity(intent)
                }
            }
            imageViewCom.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(item.sourceHTTP)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    open fun bind(post: Post) {
        with(itemView) {
            textViewName.text = post.author
            textViewPost.text = post.content
            textViewDate.text = post.created
            if (post.location == null) imageViewLocation.visibility =
                INVISIBLE else imageViewLocation.visibility = VISIBLE
            if (post.sourceHTTP != null) {
                imageViewCom.visibility = VISIBLE
                imageViewCom.isClickable = true
            }
            if (post.countLiked == 0) {
                imageViewLike.setImageResource(R.drawable.ic_favorite_grey_24dp)
                textViewLike.visibility = INVISIBLE
            } else {
                imageViewLike.setImageResource(R.drawable.ic_favorite_red_24dp)
                textViewLike.visibility = VISIBLE
                textViewLike.text = "${post.countLiked}"
            }
            if (post.countComment != 0) {
                imageViewComment.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                textViewComment.text = "${post.countComment}"
            }
        }
    }
}