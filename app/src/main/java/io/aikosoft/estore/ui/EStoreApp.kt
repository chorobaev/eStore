package io.aikosoft.estore.ui

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.aikosoft.estore.di.components.DaggerApplicationComponent

class EStoreApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        component.inject(this)
        return component
    }

    companion object {
        private var instance: EStoreApp? = null

        fun getInstance(): EStoreApp = instance!!
    }
}