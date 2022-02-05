package com.jc.app.blog.presentation.utils

import android.os.SystemClock
import android.view.View

const val MILLIS_TO_SAFE_CLICK: Long = 1000

class SafeOnClickListener(val interval: Long = MILLIS_TO_SAFE_CLICK, val listener: (View) -> Unit) : View.OnClickListener {
    private var lastTimeCLicked: Long = 0
    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeCLicked < interval) {
            return
        }
        lastTimeCLicked = SystemClock.elapsedRealtime()
        view?.let {
            listener(it)
        }
    }
}
