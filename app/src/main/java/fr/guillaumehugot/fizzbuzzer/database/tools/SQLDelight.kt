package fr.guillaumehugot.fizzbuzzer.database.tools

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.rx3.asObservable
import com.squareup.sqldelight.runtime.rx3.mapToList
import com.squareup.sqldelight.runtime.rx3.mapToOne

/** When listening for a list of rows */
fun <T : Any> Query<T>.asObservableList() = asObservable().mapToList()
    .distinctUntilChanged()

/** When listening for a single non null result */
fun <T : Any> Query<T>.asObservableElement() = asObservable().mapToOne()
    .distinctUntilChanged()
