package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarms(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val hour: Int,
    @ColumnInfo val minute: Int,
    @ColumnInfo val isRepeatable: Boolean,
    @ColumnInfo val isSundayAlarm: Boolean,
    @ColumnInfo val isMondayAlarm: Boolean,
    @ColumnInfo val isTuesdayAlarm: Boolean,
    @ColumnInfo val isWednesdayAlarm: Boolean,
    @ColumnInfo val isThursdayAlarm: Boolean,
    @ColumnInfo val isFridayAlarm: Boolean,
    @ColumnInfo val isSaturdayAlarm: Boolean,
    @ColumnInfo val isWork: Boolean
)