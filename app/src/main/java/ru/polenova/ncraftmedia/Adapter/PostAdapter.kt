package ru.polenova.ncraftmedia.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.Post
import ru.polenova.ncraftmedia.dto.TypePost

const val VIEW_TYPE_POST = 1
const val VIEW_TYPE_REPOST = 2
const val VIEW_TYPE_REPLY = 3
const val VIEW_TYPE_VIDEO = 4
const val VIEW_TYPE_COM = 5

fun viewTypeToPostType(viewType: Int) = when (viewType) {
    VIEW_TYPE_POST -> TypePost.POST
    VIEW_TYPE_REPOST -> TypePost.REPOST
    VIEW_TYPE_REPLY -> TypePost.REPLY
    VIEW_TYPE_VIDEO -> TypePost.VIDEO
    VIEW_TYPE_COM -> TypePost.COMMERCIAL
    else -> TODO("unknown view type")
}

class PostAdapter(val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewTypeToPostType(viewType)) {
            TypePost.POST -> PostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
            )
            TypePost.REPLY -> ReplyViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
            )
            TypePost.REPOST -> RepostViewHolder(
                this,
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_card, parent, false)
            )
            TypePost.VIDEO -> VideoViewHolder(
                this,
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_video, parent, false)
            )
            TypePost.COMMERCIAL -> CommercialViewHolder(
                this,
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.post_commercial, parent, false)
            )
        }

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = list[position].id

    override fun getItemViewType(position: Int) = when (list[position].typePost) {
        TypePost.POST -> VIEW_TYPE_POST
        TypePost.REPOST -> VIEW_TYPE_REPOST
        TypePost.REPLY -> VIEW_TYPE_REPLY
        TypePost.VIDEO -> VIEW_TYPE_VIDEO
        TypePost.COMMERCIAL -> VIEW_TYPE_COM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = list[holder.adapterPosition]
        when (post.typePost) {
            TypePost.POST -> with(holder as PostViewHolder) {
                bind(list[position])
            }
            TypePost.REPLY -> with(holder as ReplyViewHolder) {
                bind(list[position])
            }
            TypePost.REPOST -> with(holder as RepostViewHolder) {
                bind(list[position])
            }
            TypePost.VIDEO -> with(holder as VideoViewHolder) {
                bind(list[position])
            }
            TypePost.COMMERCIAL -> with(holder as CommercialViewHolder) {
                bind(list[position])
            }
        }
    }
}



