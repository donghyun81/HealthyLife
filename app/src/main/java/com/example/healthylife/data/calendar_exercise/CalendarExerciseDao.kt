package com.example.healthylife.data.calendar_exercise

import androidx.room.*
import com.example.healthylife.data.exercise.Exercise
import com.shrikanthravi.collapsiblecalendarview.data.Day
import kotlinx.coroutines.flow.Flow

@Dao
interface CalendarExerciseDao {


    @Query("select* from CalendarExercise Order By selected_day")
    fun getExercises(): Flow<List<CalendarExercise>>

    @Query("SELECT * from CalendarExercise WHERE selected_day = :selectedDay ")
    fun getExercise(selectedDay:String): Flow<List<CalendarExercise>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(calendarExercise: CalendarExercise)

    @Update
    suspend fun  update(calendarExercise: CalendarExercise)

    @Delete
    suspend fun  delete(calendarExercise: CalendarExercise)

}