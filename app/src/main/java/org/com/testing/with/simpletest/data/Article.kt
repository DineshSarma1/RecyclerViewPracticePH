package org.com.testing.with.simpletest.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class Article(var imageURL: String, var title: String? = null, var content: String? = null){
    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageURL: String) {
            Glide.with(view.context).load(imageURL).into(view)
        }

    }
}