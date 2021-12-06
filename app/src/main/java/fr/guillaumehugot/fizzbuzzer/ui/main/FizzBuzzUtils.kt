package fr.guillaumehugot.fizzbuzzer.ui.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



/**
 * @param period the word appearance periodicity. Must be != 0
 * @param word the word displayed every time the period is reached
 */
@Parcelize
data class FizzBuzzer(
    val period: Int,
    val word: String
): Parcelable

/**
 * Transform an Int to a fizzbuzz String according the rule defined by [fizzBuzzers]
 * see: https://en.wikipedia.org/wiki/Fizz_buzz
 * @param fizzBuzzers a list of word and periods that define when we should write these words
 * @return a String that return the word defined by the Fizzbuzzers whose multiple match [this] or [this] as a String otherwise
 */
fun Int.toFizzBuzzWord(fizzBuzzers: List<FizzBuzzer>) = fizzBuzzers
    .filter { it.period != 0 && (this % it.period) == 0 }
    .sortedBy { it.period }
    .map { it.word }
    .let { matchingFizzBuzzers ->
        if (matchingFizzBuzzers.isEmpty())
            this.toString()
        else
            matchingFizzBuzzers.reduce { acc, word -> acc + word }
    }