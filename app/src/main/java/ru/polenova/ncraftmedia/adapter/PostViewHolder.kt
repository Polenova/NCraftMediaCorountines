package ru.polenova.ncraftmedia.adapter

import android.view.View
import kotlinx.android.synthetic.main.post_card.view.*
import ru.polenova.ncraftmedia.dto.PostModel

class PostViewHolder(adapter: PostAdapter, view: View, list: MutableList<PostModel>) : BaseViewHolder(adapter, view, list){

    override fun bind(post: PostModel) {
        super.bind(post)
        with(itemView) {
            textViewPost.text = "${post.textOfPost}"
            textViewName.text = "${post.nameAuthor}"
        }
    }
}