package io.aikosoft.estore.base

import androidx.lifecycle.LifecycleService
import dagger.android.AndroidInjection
import io.aikosoft.estore.utils.MyLogger

abstract class BaseService : LifecycleService(), MyLogger {

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

}