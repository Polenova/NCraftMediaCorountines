package ru.polenova.ncraftmedia.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_card.view.*
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.PostModel

class AdViewHolder(
    adapter: PostAdapter,
    view: View,
    list: MutableList<PostModel>): PostViewHolder(adapter, view, list) {

    override fun bind(post: PostModel) {
        super.bind(post)
        with(view) {
            textViewPost.text = post.textOfPost
        }
    }


}