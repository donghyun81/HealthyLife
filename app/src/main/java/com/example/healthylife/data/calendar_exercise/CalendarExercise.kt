package com.example.healthylife.data.calendar_exercise

import androidx.room.*
import com.example.healthylife.data.exercise.Exercise
import java.util.*

@Entity
data class CalendarExercise (
    @PrimaryKey @ColumnInfo(name="selected_day")
    val selectedDay:String,
    @ColumnInfo(name="exercise_timer")
    val exerciseTimer:Int,
    @Embedded
    val exercise:Exercise,
    )