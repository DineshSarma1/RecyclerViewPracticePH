package org.com.testing.with.simpletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.com.testing.with.simpletest.data.Article
import org.com.testing.with.simpletest.databinding.ActivityMainBinding
import org.com.testing.with.simpletest.listeners.ArticleClickListener

class MainActivity : AppCompatActivity(), ArticleClickListener {

    private lateinit var adapter: RVCustomAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        observeArticleList()

    }

    private fun observeArticleList() {
        viewModel.data.observe(this){ articleList ->
            articleList?.let {
                adapter.updateList(it)
            }
        }
    }

    private fun initViews() {

        //initializing view model
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //initializing adapter and recycler view
        adapter = RVCustomAdapter(listener = this)
        binding.mRecyclerView.adapter = adapter
    }

    override fun onArticleItemCLicked(article: Article) {
        Toast.makeText(this, article.title + " is clicked!", Toast.LENGTH_LONG).show()
    }
}