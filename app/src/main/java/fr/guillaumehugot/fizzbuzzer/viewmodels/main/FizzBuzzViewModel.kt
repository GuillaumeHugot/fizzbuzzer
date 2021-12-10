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


    private val onNewLimit: BehaviorSubject<Int> = BehaviorSubject.create()

    private val onNewFirstWordValue: BehaviorSubject<String> = BehaviorSubject.create()

    private val onNewFirstPeriodValue: BehaviorSubject<Int> = BehaviorSubject.create()

    private val onNewSecondWordValue: BehaviorSubject<String> = BehaviorSubject.create()

    private val onNewSecondPeriodValue: BehaviorSubject<Int> = BehaviorSubject.create()

    private val onShowResult: BehaviorSubject<Unit> = BehaviorSubject.create()

    fun newLimit(limit: Int) = onNewLimit.onNext(limit)
    fun onLimit(): Observable<Int> = onNewLimit.hide()

    fun newFirstPeriod(period: Int) = onNewFirstPeriodValue.onNext(period)

    fun newFirstWord(word: String) = onNewFirstWordValue.onNext(word)

    fun newSecondPeriod(period: Int) = onNewSecondPeriodValue.onNext(period)

    fun newSecondWord(word: String) = onNewSecondWordValue.onNext(word)


    fun showResult() = onShowResult.onNext(Unit)
    fun onShowResult(): Observable<Unit> = onShowResult.hide()

    /**
     * Check if the filled data is valid to process the result
     *
     * We could allow to fill only 1 word & period or even only the limit (but might be confusing),
     * depending of the goal here
     */
    fun isDataValid(): Observable<Boolean> = Observable.combineLatest(
        onNewLimit.map { it > 0 },
        onNewFirstWordValue.map { it.isNotEmpty() },
        onNewFirstPeriodValue.map { it > 0 },
        onNewSecondWordValue.map { it.isNotEmpty() },
        onNewSecondPeriodValue.map { it > 0 }
    ) { isLimitValid, isFirstWordValid, isFirstPeriodValid, isSecondWordValid, isSecondPeriodValid ->
        isLimitValid && isFirstWordValid && isFirstPeriodValid && isSecondWordValid && isSecondPeriodValid
    }.observeOn(AndroidSchedulers.mainThread())

    /**
     * Get the current fizzbuzzers
     */
    fun fizzBuzzers(): Observable<List<FizzBuzzer>> = Observable.combineLatest(
        onNewFirstPeriodValue,
        onNewFirstWordValue,
        onNewSecondPeriodValue,
        onNewSecondWordValue
    ) { firstPeriod, firstWord, secondPeriod, secondWord ->
        listOf(FizzBuzzer(firstPeriod, firstWord), FizzBuzzer(secondPeriod, secondWord))
    }.observeOn(AndroidSchedulers.mainThread())

}