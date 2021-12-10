package fr.guillaumehugot.fizzbuzzer.provider.impl

import fr.guillaumehugot.fizzbuzzer.database.UserDAO
import fr.guillaumehugot.fizzbuzzer.domain.User
import fr.guillaumehugot.fizzbuzzer.provider.UserProvider
import fr.guillaumehugot.fizzbuzzer.sqldelight.FizzBuzzDatabase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProviderImpl @Inject constructor(
    private val database: FizzBuzzDatabase,
    private val dao: UserDAO,
    private val UserDAO: UserDAO
) : UserProvider {

    companion object {
        const val TAG = "UserProviderImpl"
    }


//    override fun list(): Observable<List<User>> = dao.list()

}