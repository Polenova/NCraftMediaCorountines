package ru.polenova.ncraftmedia.Adapter

import android.view.View
import kotlinx.android.synthetic.main.post_card.view.imageViewCom
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.Post

class VideoViewHolder(adapter: PostAdapter, view: View) : BaseViewHolder(adapter, view) {

    override fun bind(post: Post) {
        super.bind(post)
        with(itemView) {
            imageViewCom.setImageResource(R.mipmap.youtube_offline_videos)
        }
    }
}