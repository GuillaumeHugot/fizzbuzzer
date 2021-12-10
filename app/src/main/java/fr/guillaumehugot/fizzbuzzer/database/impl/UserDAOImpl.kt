package fr.guillaumehugot.fizzbuzzer.database.impl

import fr.guillaumehugot.fizzbuzzer.database.UserDAO
import fr.guillaumehugot.fizzbuzzer.database.tools.asObservableElement
import fr.guillaumehugot.fizzbuzzer.domain.User
import fr.guillaumehugot.fizzbuzzer.sqldelight.FizzBuzzDatabase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserDAOImpl @Inject constructor(database: FizzBuzzDatabase) : UserDAO {

    companion object {
        private val UserFromDb: (String, Int) -> User = { id, limit ->
            User(id, limit)
        }
    }

    private val queries = database.dbUserQueries

//    override fun insert(item: User) = queries.insert(
//        DbUser(
//            item.id,
//            item.limit?.toLong()
//        )
//    )


    override fun update(item: User) = queries.update(item.limit, item.id)

    override fun updateCurrentUserLimit(limit: Int) = queries.updateCurrentUserLimit(limit)

    override fun currentUserLimit(): Observable<Int> = queries.currentUserLimit().asObservableElement()
}