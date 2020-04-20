package ru.polenova.ncraftmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import androidx.recyclerview.widget.LinearLayoutManager
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import ru.polenova.ncraftmedia.Adapter.PostAdapter
import ru.polenova.ncraftmedia.dto.Post
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @io.ktor.util.KtorExperimentalAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            val list = withContext(Dispatchers.IO) {
                Api.client.get<MutableList<Post>>(Api.url)
            }
            with(content) {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = PostAdapter(list)
            }
            progressBar.visibility = GONE
        }

//        client.close()
    }


    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
