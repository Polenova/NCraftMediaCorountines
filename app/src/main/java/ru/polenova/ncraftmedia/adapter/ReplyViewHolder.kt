package ru.polenova.ncraftmedia.adapter

import android.view.View
import kotlinx.android.synthetic.main.post_card.view.*
import ru.polenova.ncraftmedia.dto.Post

class ReplyViewHolder (adapter: PostAdapter, view: View, list: MutableList<Post>) : BaseViewHolder(adapter, view, list) {

    override fun bind(post: Post) {
        super.bind(post)
        with(itemView) {
            textViewAnother.visibility = View.VISIBLE
            textViewAnother.text = "Reply to: ${post.source?.author} ${post.source?.content}"
        }
    }
}