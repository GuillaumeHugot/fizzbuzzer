package fr.guillaumehugot.fizzbuzzer.provider

import fr.guillaumehugot.fizzbuzzer.domain.User
import io.reactivex.rxjava3.core.Observable

interface UserProvider {

    fun currentUserLimit(): Observable<Int>
    fun update(item: User)
    fun updateCurrentUserLimit(limit: Int)
}