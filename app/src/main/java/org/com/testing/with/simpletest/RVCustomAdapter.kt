package org.com.testing.with.simpletest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.com.testing.with.simpletest.data.Article
import org.com.testing.with.simpletest.databinding.CardViewItemBinding
import org.com.testing.with.simpletest.listeners.ArticleClickListener

/**
 * TODO: Implement the Adapter that will populate a RecyclerView using the information generated in ViewModel
 * */

class RVCustomAdapter(
    private val articleList: MutableList<Article> = mutableListOf(),
    val listener: ArticleClickListener
) : RecyclerView.Adapter<RVCustomAdapter.ArticleViewHolder>(){

    inner class ArticleViewHolder(private val binding: CardViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.listener = listener
        }
    }

    fun updateList(newList: List<Article>) {
        val result = DiffUtil.calculateDiff(DiffUtilImpl(articleList, newList))
        articleList.clear()
        articleList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = CardViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.apply {
            bind(articleList[position])
        }
    }

    override fun getItemCount(): Int = articleList.size

    inner class DiffUtilImpl(private val oldList: List<Article>, private val newList: List<Article>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int= oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }

}