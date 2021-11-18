package com.example.petproject

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("app:imageUrl")
fun loadImage(image: ImageView, url: String){
    image.load(url)
}