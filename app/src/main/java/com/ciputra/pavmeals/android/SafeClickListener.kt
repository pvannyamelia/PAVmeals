package com.ciputra.pavmeals.android

import android.os.SystemClock
import android.view.View

class SafeClickListener(
        private var defaultInterval: Int = 5000,
        private val onSafeClick: (View) -> Unit) : View.OnClickListener {
            private var lastTimeClicked: Long = 0
    override fun onClick(p0: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval){
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(p0)
    }
}