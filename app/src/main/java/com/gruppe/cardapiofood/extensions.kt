package com.gruppe.cardapiofood

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.squareup.picasso.Picasso

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, {
        it?.let(observer)
    })
}

fun ImageView.load(url: String?){
    url?.let {
        Picasso.get().load(it).into(this)
    }
}