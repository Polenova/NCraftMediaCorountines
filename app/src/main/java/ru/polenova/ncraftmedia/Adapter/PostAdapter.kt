package ru.polenova.ncraftmedia.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_card.view.*
import ru.polenova.ncraftmedia.R
import ru.polenova.ncraftmedia.dto.Post

class PostAdapter(val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return PostViewHolder(this, view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as PostViewHolder) {
            bind(list[position])
        }
    }

    class PostViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {
        init {
            with(itemView) {

                imageViewLike.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        val item = adapter.list[adapterPosition]
                        item.likeByMe = !item.likeByMe
                        adapter.notifyItemChanged(adapterPosition)
                    }
                }
                imageViewShare.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        val item = adapter.list[adapterPosition]
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(
                                Intent.EXTRA_TEXT, """
                                ${item.author} (${item.created})
    
                                ${item.content}
                            """.trimIndent()
                            )
                            type = "text/plain"
                        }
                        itemView.context.startActivity(intent)
                        imageViewShare.setImageResource(R.drawable.ic_share_black_24dp)
                        //textViewShare.text = "${++post.countShare}"
                    }
                }
                imageViewComment.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        val item = adapter.list[adapterPosition]
                        imageViewComment.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                        textViewComment.text = "${++item.countComment}"
                    }
                }
                imageViewLocation.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        val item = adapter.list[adapterPosition]
                        val lat = item.location.first
                        val lng = item.location.second
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("geo:$lat,$lng")
                        }
                            itemView.context.startActivity(intent)
                    }
                }
            }
        }


        fun bind(post: Post) {
            with(itemView) {
                textViewName.text = post.author
                textViewPost.text = post.content
                textViewDate.text = post.created
                if (post.likeByMe) {
                    imageViewLike.setImageResource(R.drawable.ic_favorite_red_24dp)
                    textViewLike.text = "1"
                    textViewLike.visibility = View.VISIBLE
                } else {
                    imageViewLike.setImageResource(R.drawable.ic_favorite_grey_24dp)
                    textViewLike.text = "0"
                    textViewLike.visibility = View.INVISIBLE
                }
            }
        }
    }
}


