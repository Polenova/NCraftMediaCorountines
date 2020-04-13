package ru.polenova.ncraftmedia.Adapter

import android.view.View
import kotlinx.android.synthetic.main.post_card.view.*
import ru.polenova.ncraftmedia.dto.Post

class ReplyViewHolder (adapter: PostAdapter, view: View) : BaseViewHolder(adapter, view) {

    override fun bind(post: Post) {
        super.bind(post)
        with(itemView) {
            textViewAnother.visibility = View.VISIBLE
            textViewAnother.text = "Reply to: ${post.source?.author} ${post.source?.content}"
        }
    }
}