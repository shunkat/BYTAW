package database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarms")
    fun getAll(): LiveData<List<Alarms>>

    @Update
    suspend fun update(vararg alarm: Alarms)

    @Insert
    suspend fun insert(vararg alarm: Alarms)

    @Delete
    suspend fun delete(alarm: Alarms)
}