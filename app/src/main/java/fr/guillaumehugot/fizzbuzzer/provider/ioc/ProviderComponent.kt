package fr.guillaumehugot.fizzbuzzer.provider.ioc

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import fr.guillaumehugot.fizzbuzzer.FizzBuzzApplication
import fr.guillaumehugot.fizzbuzzer.database.ioc.DatabaseModule
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ProviderModule::class, DatabaseModule::class])
interface ProviderComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ProviderComponent
    }

    fun inject(fizzBuzzViewModel: FizzBuzzViewModel)
}