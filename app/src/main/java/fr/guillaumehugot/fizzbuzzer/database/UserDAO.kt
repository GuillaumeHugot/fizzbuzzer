package fr.guillaumehugot.fizzbuzzer.database

import fr.guillaumehugot.fizzbuzzer.domain.FizzBuzzer
import io.reactivex.rxjava3.core.Observable

interface UserDAO {
    fun currentUserLimit(): Observable<Int>
    fun updateCurrentUserLimit(limit: Int)
    fun updateCurrentUserFirstPeriod(firstPeriod: Int)
    fun currentUserFirstPeriod(): Observable<Int>
    fun updateCurrentUserFirstWord(firstWord: String)
    fun currentUserFirstWord(): Observable<String>
    fun updateCurrentUserSecondPeriod(secondPeriod: Int)
    fun currentUserSecondPeriod(): Observable<Int>
    fun updateCurrentUserSecondWord(secondWord: String)
    fun currentUserSecondWord(): Observable<String>
    fun currentUserFizzBuzzers(): Observable<List<FizzBuzzer>>
}