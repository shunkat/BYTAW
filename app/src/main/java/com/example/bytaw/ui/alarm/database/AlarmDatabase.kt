package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kotlin.coroutines.CoroutineContext

@Database(entities = [Alarms::class], version = 1, exportSchema = false)
abstract class AlarmDatabase: RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

    companion object {
        private const val dbName = "alarm.db"
        private var instance: AlarmDatabase ?= null

        fun getInstance(context: Context): AlarmDatabase {
            if (instance == null) {
                instance = databaseBuilder(context, AlarmDatabase::class.java, dbName).fallbackToDestructiveMigration().build()
            }
            return requireNotNull(instance)
        }
    }
}