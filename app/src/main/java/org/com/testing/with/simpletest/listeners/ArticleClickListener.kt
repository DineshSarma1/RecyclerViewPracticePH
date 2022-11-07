package org.com.testing.with.simpletest.listeners

import org.com.testing.with.simpletest.data.Article

interface ArticleClickListener {
    fun onArticleItemCLicked(article: Article)
}