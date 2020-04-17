package io.aikosoft.estore.utils

import android.util.Log

interface MyLogger {

    fun log(msg: String) {
        Log.d("Mylog${this.javaClass.simpleName}", msg)
    }
}