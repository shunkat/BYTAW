package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarms")
    fun getAll(): LiveData<List<Alarms>>

    @Insert
    suspend fun insert(vararg alarm: Alarms)

    @Delete
    suspend fun delete(alarm: Alarms)
}