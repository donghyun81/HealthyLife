package com.example.healthylife.data

import android.app.Application
import com.example.healthylife.data.ExerciseRoomDatabase

class ExerciseApplication:Application() {
    val database: ExerciseRoomDatabase by lazy{ ExerciseRoomDatabase.getDatabase(this) }
}