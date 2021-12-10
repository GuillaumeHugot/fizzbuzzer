package fr.guillaumehugot.fizzbuzzer.viewmodels.main

import androidx.lifecycle.*
import fr.guillaumehugot.fizzbuzzer.FizzBuzzApplication
import fr.guillaumehugot.fizzbuzzer.domain.FizzBuzzer
import fr.guillaumehugot.fizzbuzzer.provider.UserProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class FizzBuzzViewModel : ViewModel() {

    @Inject
    lateinit var userProvider: UserProvider


    init {
        FizzBuzzApplication.instance.providerComponent.inject(this)
    }

    private val onShowResult: BehaviorSubject<Unit> = BehaviorSubject.create()

    fun newLimit(limit: Int) = userProvider.updateCurrentUserLimit(limit)

    fun onLimit(): Observable<Int> = userProvider.currentUserLimit()
        .observeOn(AndroidSchedulers.mainThread())

    fun newFirstPeriod(period: Int) = userProvider.updateCurrentUserFirstPeriod(period)

    fun newFirstWord(word: String) = userProvider.updateCurrentUserFirstWord(word)

    fun onFirstPeriod(): Observable<Int> = userProvider.currentUserFirstPeriod()
        .observeOn(AndroidSchedulers.mainThread())

    fun onFirstWord(): Observable<String> = userProvider.currentUserFirstWord()
        .observeOn(AndroidSchedulers.mainThread())

    fun newSecondPeriod(period: Int) = userProvider.updateCurrentUserSecondPeriod(period)

    fun newSecondWord(word: String) = userProvider.updateCurrentUserSecondWord(word)

    fun onSecondPeriod(): Observable<Int> = userProvider.currentUserSecondPeriod()
        .observeOn(AndroidSchedulers.mainThread())

    fun onSecondWord(): Observable<String> = userProvider.currentUserSecondWord()
        .observeOn(AndroidSchedulers.mainThread())


    fun showResult() = onShowResult.onNext(Unit)
    fun onShowResult(): Observable<Unit> = onShowResult.hide()

    /**
     * Check if the filled data is valid to process the result
     *
     * We could allow to fill only 1 word & period or even only the limit (but might be confusing),
     * depending of the goal here
     */
    fun isDataValid(): Observable<Boolean> = Observable.combineLatest(
        userProvider.currentUserLimit().map { it > 0 },
        userProvider.currentUserFirstWord().map { it.isNotEmpty() },
        userProvider.currentUserFirstPeriod().map { it > 0 },
        userProvider.currentUserSecondWord().map { it.isNotEmpty() },
        userProvider.currentUserSecondPeriod().map { it > 0 }
    ) { isLimitValid, isFirstWordValid, isFirstPeriodValid, isSecondWordValid, isSecondPeriodValid ->
        isLimitValid && isFirstWordValid && isFirstPeriodValid && isSecondWordValid && isSecondPeriodValid
    }.observeOn(AndroidSchedulers.mainThread())

    /**
     * Get the current fizzbuzzers
     */
    fun fizzBuzzers(): Observable<List<FizzBuzzer>> = userProvider.currentUserFizzBuzzers()
        .observeOn(AndroidSchedulers.mainThread())

}