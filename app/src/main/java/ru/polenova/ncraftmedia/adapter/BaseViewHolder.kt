package ru.polenova.ncraftmedia.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_create_post.*
import kotlinx.android.synthetic.main.post_card.view.*
import kotlinx.android.synthetic.main.post_card.view.imageViewLike
import kotlinx.android.synthetic.main.post_card.view.textViewLike
import kotlinx.android.synthetic.main.post_card.view.textViewName
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.PostModel

open class BaseViewHolder(private val adapter: PostAdapter, val view: View, var list: MutableList<PostModel>
) : RecyclerView.ViewHolder(view) {

    init {
        this.clickButtonListener()
    }

    open fun bind(post: PostModel) {
        with(view) {
            textViewDate.text = post.dateOfPost
            textViewPost.text = post.textOfPost
            textViewName.text = post.nameAuthor
            textViewLike.text = post.likesCount.toString()
            textViewRepost.text = post.repostsCount.toString()
            fillCount(textViewLike, post.likesCount)
            fillCount(textViewRepost, post.repostsCount)
            when {
                post.likeActionPerforming -> {
                    imageViewLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
                post.isLikedByUser -> {
                    imageViewLike.setImageResource(R.drawable.ic_favorite_red_24dp)
                }
                else -> {
                    imageViewLike.setImageResource(R.drawable.ic_favorite_grey_24dp)
                }
            }
        }
    }

    private fun clickButtonListener() {
        with(view) {
            imageViewLike.setOnClickListener {
                it as ImageButton
                val currentPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = list[currentPosition]
                    if (item.likeActionPerforming) {
                        Toast.makeText(
                            context,
                            R.string.like_is_performing,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        adapter.likeBtnClickListener?.onLikeBtnClicked(item, currentPosition)
                    }
                }
            }
            imageViewRepost.setOnClickListener {
                it as ImageButton
                val currentPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = list[currentPosition]
                    if (item.isRepostedByUser) {
                        Toast.makeText(
                            context,
                            R.string.cannot_repost_2_time,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        showDialog(context) {content ->
                            adapter.repostBtnClickListener?.onRepostBtnClicked(
                                item,
                                currentPosition, content
                            )
                        }
                    }
                }
            }

            imageRemove.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    list.removeAt(adapterPosition)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

}

fun showDialog(context: Context, createBtnClicked: (content: String) -> Unit) {
    val dialog = AlertDialog.Builder(context)
        .setView(R.layout.activity_create_post)
        .show()
    dialog.createButton.setOnClickListener {
        createBtnClicked(dialog.enterContentEditText.text.toString())
        dialog.dismiss()
    }
}

fun fillCount(view: TextView, count: Int) {
    if (count == 0) {
        view.isEnabled = false
    } else {
        view.isEnabled = true
        view.text = count.toString()
    }
}

