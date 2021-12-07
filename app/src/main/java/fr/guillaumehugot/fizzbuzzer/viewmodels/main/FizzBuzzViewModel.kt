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

    fun isDataValid() = combine(listOf(
        isLimitValid.asFlow(),
        isFirstWordValid.asFlow(),
        isFirstPeriodValid.asFlow(),
        isSecondWordValid.asFlow(),
        isSecondPeriodValid.asFlow()
    )) { fields ->
        fields.all { isValid -> isValid }
    }

    fun getLimit() = onNewLimit.value!!
    fun getFizzBuzzers() = listOf(
        FizzBuzzer(onNewFirstPeriodValue.value!!, onNewFirstWordValue.value!!),
        FizzBuzzer(onNewSecondPeriodValue.value!!, onNewSecondWordValue.value!!)
    )
}