package fr.guillaumehugot.fizzbuzzer.database.ioc

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import fr.guillaumehugot.fizzbuzzer.FizzBuzzApplication
import fr.guillaumehugot.fizzbuzzer.database.UserDAO
import fr.guillaumehugot.fizzbuzzer.database.impl.UserDAOImpl
import fr.guillaumehugot.fizzbuzzer.sqldelight.FizzBuzzDatabase
import javax.inject.Singleton

@Module
object DatabaseModule {

    const val TAG = "DatabaseModule"

    @Provides
    @Singleton
    fun driver(context: Context): SqlDriver = AndroidSqliteDriver(
        FizzBuzzDatabase.Schema,
        context,
        "fizz_buzz.db",
        callback = object : AndroidSqliteDriver.Callback(FizzBuzzDatabase.Schema) {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                db.execSQL("PRAGMA foreign_keys=ON;")
            }
        }
    )

    @Provides
    @Singleton
    fun database(driver: SqlDriver): FizzBuzzDatabase = FizzBuzzDatabase.invoke(driver)


    @Provides
    fun userDao(userDAOImpl: UserDAOImpl): UserDAO = userDAOImpl

}