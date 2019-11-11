package com.android.rahul.movies.components


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

// ImageURL databinding for AppCompatImageView
@BindingAdapter("imageURL")
fun AppCompatImageView.imageUrl(path: String?) {
    Glide.with(this.context).load(path).into(this)
}