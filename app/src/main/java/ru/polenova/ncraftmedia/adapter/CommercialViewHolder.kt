package ru.polenova.ncraftmedia.adapter

import android.view.View
import kotlinx.android.synthetic.main.post_card.view.*
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.PostModel

class CommercialViewHolder(adapter: PostAdapter, view: View, list: MutableList<PostModel>) : BaseViewHolder(adapter, view, list) {

    override fun bind(post: PostModel) {
        super.bind(post)
        with(itemView) {
            imageViewCom.setImageResource(R.mipmap.lifestyle_00535)
        }
    }
}