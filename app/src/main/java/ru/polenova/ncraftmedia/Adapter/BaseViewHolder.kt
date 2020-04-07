package ru.polenova.ncraftmedia.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.polenova.ncraftmedia.dto.Post

abstract class BaseViewHolder(val adapter: PostAdapter, view: View) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(post: Post)
}