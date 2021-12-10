package fr.guillaumehugot.fizzbuzzer.provider.ioc

import dagger.Binds
import fr.guillaumehugot.fizzbuzzer.provider.*
import fr.guillaumehugot.fizzbuzzer.provider.impl.*
import dagger.Module
import dagger.Provides

@Module
object ProviderModule {

    @Binds
    fun userProvider(userProviderImpl: UserProviderImpl): UserProvider = userProviderImpl
}


