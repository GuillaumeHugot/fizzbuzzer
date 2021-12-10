package fr.guillaumehugot.fizzbuzzer.provider.ioc

import dagger.Module
import dagger.Provides
import fr.guillaumehugot.fizzbuzzer.provider.UserProvider
import fr.guillaumehugot.fizzbuzzer.provider.impl.UserProviderImpl

@Module
object ProviderModule {

    @Provides
    fun userProvider(userProviderImpl: UserProviderImpl): UserProvider = userProviderImpl
}


