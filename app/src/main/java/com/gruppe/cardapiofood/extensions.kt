package com.gruppe.cardapiofood

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.afollestad.materialdialogs.MaterialDialog
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

fun showDialogError(context: Context,title : String, message : String){
    MaterialDialog
        .Builder(context)
        .title(title)
        .content(message)
        .iconRes(R.drawable.error)
        .positiveText("OK").show()
}
