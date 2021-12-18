package com.example.healthylife.data

import android.app.Application
import com.example.healthylife.data.calendar_exercise.CalendarExerciseRoomDataBase
import com.example.healthylife.data.exercise.ExerciseRoomDatabase

class HealthyLifeApplication:Application() {
    val databaseExercise: ExerciseRoomDatabase by lazy{ ExerciseRoomDatabase.getDatabase(this) }
    val databaseCalendar: CalendarExerciseRoomDataBase by lazy{CalendarExerciseRoomDataBase.getDatabase(this)}
}