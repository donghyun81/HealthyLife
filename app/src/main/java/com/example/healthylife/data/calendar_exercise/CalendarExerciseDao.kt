package com.example.healthylife.data.calendar_exercise

import androidx.room.*

@Dao
interface CalendarExerciseDao {



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(calendarExercise: CalendarExercise)

    @Update
    suspend fun  update(calendarExercise: CalendarExercise)

    @Delete
    suspend fun  delete(calendarExercise: CalendarExercise)

}