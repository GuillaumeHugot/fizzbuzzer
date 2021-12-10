package fr.guillaumehugot.fizzbuzzer.provider.ioc

import dagger.Component
import fr.guillaumehugot.fizzbuzzer.FizzBuzzApplication
import fr.guillaumehugot.fizzbuzzer.database.ioc.DatabaseModule
import fr.guillaumehugot.fizzbuzzer.viewmodels.main.FizzBuzzViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ProviderModule::class, DatabaseModule::class])
interface ProviderComponent {

    fun inject(fizzBuzzViewModel: FizzBuzzViewModel)

    fun inject(fizzBuzzApplication: FizzBuzzApplication)
}