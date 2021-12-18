package com.example.healthylife.data.calendar_exercise

import androidx.room.Entity
import com.example.healthylife.data.exercise.Exercise
import java.util.*

@Entity
data class CalendarExercise (
    val selectedDay:String,
    val exercise:Exercise,
    val timer:Timer,
        )