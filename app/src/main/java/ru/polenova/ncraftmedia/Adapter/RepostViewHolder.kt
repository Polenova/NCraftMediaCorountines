package ru.polenova.ncraftmedia.Adapter

import android.view.View
import kotlinx.android.synthetic.main.post_card.view.*
import ru.polenova.ncraftmedia.dto.Post

class RepostViewHolder (adapter: PostAdapter, view: View) :  BaseViewHolder (adapter, view) {

    override fun bind(post: Post) {
        super.bind(post)
        with(itemView) {
            textViewPost.text = "${post.content} \n repost: '${post.source?.content}'"
            textViewAnother.visibility = View.VISIBLE
            textViewAnother.text = "Repost of: ${post.source?.author}"
        }
    }
}