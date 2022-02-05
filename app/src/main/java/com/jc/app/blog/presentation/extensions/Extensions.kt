package com.jc.app.blog.presentation.extensions

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.jc.app.blog.R
import com.jc.app.blog.presentation.utils.SafeOnClickListener

fun Activity.showToast(message: Any?) {
    val textToShow = when (message) {
        is Int -> getString(message)
        is String -> message
        else -> {
            ""
        }
    }
    Toast.makeText(this, textToShow, Toast.LENGTH_LONG).show()
}

fun View.setSafeOnClickListener(safeTime: Long = 1000, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeOnClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

@BindingAdapter("app:isFavorite")
fun setFavoriteSource(
    view: ImageView,
    isFavorite: Boolean?
) {
    isFavorite?.let {
        if (it) {
            view.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            view.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}
