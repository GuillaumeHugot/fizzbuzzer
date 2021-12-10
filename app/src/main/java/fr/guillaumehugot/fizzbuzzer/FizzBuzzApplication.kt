package fr.guillaumehugot.fizzbuzzer

import android.app.Application
import fr.guillaumehugot.fizzbuzzer.provider.ioc.DaggerProviderComponent
import fr.guillaumehugot.fizzbuzzer.provider.ioc.ProviderComponent
import javax.inject.Inject


class FizzBuzzApplication : Application() {

    companion object {
        lateinit var instance: FizzBuzzApplication
    }

    @Inject
    lateinit var providerComponent: ProviderComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        providerComponent = DaggerProviderComponent.create()
    }
}

