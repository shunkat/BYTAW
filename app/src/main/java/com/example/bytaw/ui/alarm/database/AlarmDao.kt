package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarms")
    suspend fun getAll(): List<Alarms>

    @Insert
    suspend fun insert(vararg alarm: Alarms)

    @Delete
    suspend fun delete(alarm: Alarms)
}