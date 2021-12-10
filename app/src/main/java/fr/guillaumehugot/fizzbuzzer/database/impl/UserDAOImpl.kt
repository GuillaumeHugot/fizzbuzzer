package fr.guillaumehugot.fizzbuzzer.database.impl

import fr.guillaumehugot.fizzbuzzer.database.UserDAO
import fr.guillaumehugot.fizzbuzzer.database.tools.asObservableElement
import fr.guillaumehugot.fizzbuzzer.domain.FizzBuzzer
import fr.guillaumehugot.fizzbuzzer.sqldelight.FizzBuzzDatabase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserDAOImpl @Inject constructor(database: FizzBuzzDatabase) : UserDAO {

    companion object {
        private val FizzBuzzersFromDb: (Int, String, Int, String) -> List<FizzBuzzer> = { firstPeriod, firstWord, secondPeriod, secondWord ->
            listOf(FizzBuzzer(firstPeriod, firstWord), FizzBuzzer(secondPeriod, secondWord))
        }
    }

    private val queries = database.dbUserQueries

    override fun updateCurrentUserLimit(limit: Int) = queries.updateCurrentUserLimit(limit)

    override fun currentUserLimit(): Observable<Int> = queries.currentUserLimit().asObservableElement()

    override fun updateCurrentUserFirstPeriod(firstPeriod: Int) = queries.updateCurrentUserFirstPeriod(firstPeriod)

    override fun currentUserFirstPeriod(): Observable<Int> = queries.currentUserFirstPeriod().asObservableElement()

    override fun updateCurrentUserFirstWord(firstWord: String) = queries.updateCurrentUserFirstWord(firstWord)

    override fun currentUserFirstWord(): Observable<String> = queries.currentUserFirstWord().asObservableElement()

    override fun updateCurrentUserSecondPeriod(secondPeriod: Int) = queries.updateCurrentUserSecondPeriod(secondPeriod)

    override fun currentUserSecondPeriod(): Observable<Int> = queries.currentUserSecondPeriod().asObservableElement()

    override fun updateCurrentUserSecondWord(secondWord: String) = queries.updateCurrentUserSecondWord(secondWord)

    override fun currentUserSecondWord(): Observable<String> = queries.currentUserSecondWord().asObservableElement()

    override fun currentUserFizzBuzzers(): Observable<List<FizzBuzzer>> = queries.currentUserFizzBuzzers(FizzBuzzersFromDb).asObservableElement()
}