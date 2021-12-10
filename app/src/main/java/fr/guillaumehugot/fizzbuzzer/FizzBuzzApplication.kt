package fr.guillaumehugot.fizzbuzzer

import android.app.Application
import fr.guillaumehugot.fizzbuzzer.provider.ioc.DaggerProviderComponent
import fr.guillaumehugot.fizzbuzzer.provider.ioc.ProviderComponent


class FizzBuzzApplication : Application() {

    companion object {
        lateinit var instance: FizzBuzzApplication
    }

    lateinit var providerComponent: ProviderComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        providerComponent = DaggerProviderComponent.builder().context(this).build()
    }
}

