package fr.guillaumehugot.fizzbuzzer.provider.impl

import fr.guillaumehugot.fizzbuzzer.database.UserDAO
import fr.guillaumehugot.fizzbuzzer.domain.FizzBuzzer
import fr.guillaumehugot.fizzbuzzer.provider.UserProvider
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProviderImpl @Inject constructor(
    private val dao: UserDAO
) : UserProvider {

    companion object {
        const val TAG = "UserProviderImpl"
    }


    override fun currentUserLimit(): Observable<Int> = dao.currentUserLimit()

    override fun updateCurrentUserLimit(limit: Int) = dao.updateCurrentUserLimit(limit)

    override fun updateCurrentUserFirstPeriod(firstPeriod: Int) = dao.updateCurrentUserFirstPeriod(firstPeriod)

    override fun currentUserFirstPeriod(): Observable<Int> = dao.currentUserFirstPeriod()

    override fun updateCurrentUserFirstWord(firstWord: String) = dao.updateCurrentUserFirstWord(firstWord)

    override fun currentUserFirstWord(): Observable<String> = dao.currentUserFirstWord()

    override fun updateCurrentUserSecondPeriod(secondPeriod: Int) = dao.updateCurrentUserSecondPeriod(secondPeriod)

    override fun currentUserSecondPeriod(): Observable<Int> = dao.currentUserSecondPeriod()

    override fun updateCurrentUserSecondWord(secondWord: String) = dao.updateCurrentUserSecondWord(secondWord)

    override fun currentUserSecondWord(): Observable<String> = dao.currentUserSecondWord()

    override fun currentUserFizzBuzzers(): Observable<List<FizzBuzzer>> = dao.currentUserFizzBuzzers()
}