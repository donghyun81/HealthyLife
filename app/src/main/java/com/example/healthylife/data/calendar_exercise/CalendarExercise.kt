package com.example.healthylife.data.calendar_exercise

import androidx.room.*
import com.example.healthylife.data.exercise.Exercise
import com.shrikanthravi.collapsiblecalendarview.data.Day
import java.util.*

@Entity
data class CalendarExercise (

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name="selected_day")
    val selectedDay:String,
    @Embedded(prefix = "exercise_")
    val exercise:Exercise,
    )