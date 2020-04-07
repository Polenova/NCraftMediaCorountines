package ru.polenova.ncraftmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.polenova.ncraftmedia.Adapter.PostAdapter
import ru.polenova.ncraftmedia.dto.Post
import ru.polenova.ncraftmedia.dto.TypePost

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val post0 = Post(
            123,
            "Vasya Pupkov",
            "First post in our network! Hello Kotlin world!",
            "20 march 2020",
            Pair(55.75222, 37.61556)
        )

        val list = listOf(
            Post(5, "Petya", "ooo", "5.04.2020", Pair(0.0, 0.0), typePost =  TypePost.REPOST, source = post0),
            Post(5, "Vanya", "...", "5.04.2020", null, typePost =  TypePost.REPOST, source = post0),
            Post(4, "Petya", "good bye", "4.04.2020", Pair(55.75222, 37.61556), typePost =  TypePost.REPLY, source = post0),
            Post(3, "Masha", "some video", "3.04.2020", null,  TypePost.VIDEO, null, sourceHTTP = "https://www.youtube.com/watch?v=WhWc3b3KhnY"),
            Post(2, "Vanya", "мойте руки, господа", "2.04.2020", null, TypePost.COMMERCIAL, null, sourceHTTP = "https://www.kp.ru/daily/27103.4/4176710/"),
            Post(1, "Petya", "hello kotlin", "1.04.2020", null, TypePost.POST, null),
            Post(1, "Petya", "hello world", "1.04.2020", Pair(55.75222, 37.61556), TypePost.POST, null),
            Post(1, "Petya", "hello", "1.04.2020", Pair(55.75222, 37.61556), TypePost.POST, null)
        )

        with(content) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(list)
        }
    }
}
