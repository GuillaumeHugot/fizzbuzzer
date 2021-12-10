package fr.guillaumehugot.fizzbuzzer

import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector

import dagger.android.DispatchingAndroidInjector

import javax.inject.Inject

import dagger.android.HasAndroidInjector





class FizzBuzzApplication : Application(), HasAndroidInjector {

    companion object {
        lateinit var instance: FizzBuzzApplication
    }

    @Inject
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        DaggerProvierComponent.create()
            .inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector!!
    }
}

