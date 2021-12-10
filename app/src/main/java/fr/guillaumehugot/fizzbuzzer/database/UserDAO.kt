package fr.guillaumehugot.fizzbuzzer.database

import fr.guillaumehugot.fizzbuzzer.domain.User
import io.reactivex.rxjava3.core.Observable

interface UserDAO {
    fun currentUserLimit(): Observable<Int>
    fun update(item: User)
    fun updateCurrentUserLimit(limit: Int)
}