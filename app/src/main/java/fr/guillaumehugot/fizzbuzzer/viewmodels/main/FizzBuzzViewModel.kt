package fr.guillaumehugot.fizzbuzzer.viewmodels.main

import androidx.lifecycle.*
import fr.guillaumehugot.fizzbuzzer.ui.main.FizzBuzzer
import kotlinx.coroutines.flow.combine

class FizzBuzzViewModel : ViewModel() {

    val onNewLimit = MutableLiveData<Int?>()
    val isLimitValid
        get() = onNewLimit.map { it != null && it > 0 }

    val onNewFirstWordValue = MutableLiveData<String>()
    val isFirstWordValid
        get() = onNewFirstWordValue.map { it.isNotEmpty() }

    val onNewFirstPeriodValue = MutableLiveData<Int?>()
    val isFirstPeriodValid
        get() = onNewFirstPeriodValue.map { it != null && it > 0 }

    val onNewSecondWordValue = MutableLiveData<String>()
    val isSecondWordValid
        get() = onNewSecondWordValue.map { it.isNotEmpty() }

    val onNewSecondPeriodValue = MutableLiveData<Int?>()
    val isSecondPeriodValid
        get() = onNewSecondPeriodValue.map { it != null && it > 0 }

    val onShowResult = MutableLiveData<Unit>()

    /**
     * Check if the filled data is valid to process the result
     *
     * We could allow to fill only 1 word & period or even only the limit (but might be confusing),
     * depending of the goal here
     */
    fun isDataValid() = combine(listOf(
        isLimitValid.asFlow(),
        isFirstWordValid.asFlow(),
        isFirstPeriodValid.asFlow(),
        isSecondWordValid.asFlow(),
        isSecondPeriodValid.asFlow()
    )) { fields ->
        fields.all { isValid -> isValid }
    }

    /**
     * Get the current limit
     * @throws IllegalStateException if it hasn't been set yet
     */
    fun getLimit() = if (isLimitValid.value != true) throw IllegalStateException("Limit isn't set yet") else onNewLimit.value!!

    /**
     * Get the current fizzbuzzers
     */
    fun getFizzBuzzers(): List<FizzBuzzer> {
        val fizzBuzzers = mutableListOf<FizzBuzzer>()
        if (onNewFirstPeriodValue.value != null && onNewFirstWordValue.value != null)
            fizzBuzzers.add(FizzBuzzer(onNewFirstPeriodValue.value!!, onNewFirstWordValue.value!!))
        if (onNewSecondPeriodValue.value != null && onNewSecondWordValue.value != null)
            FizzBuzzer(onNewSecondPeriodValue.value!!, onNewSecondWordValue.value!!)
        return fizzBuzzers.toList()
    }
}