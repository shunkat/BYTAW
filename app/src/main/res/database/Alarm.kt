package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarms(
    @PrimaryKey(autoGenerate = true) val id: String,
    @ColumnInfo val hour: Int,
    @ColumnInfo val minute: Int,
    @ColumnInfo val schedules: Array<Int>?,
    @ColumnInfo val isWork: Boolean
)