package com.example.healthylife.data.calendar_exercise

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthylife.data.exercise.ExerciseRoomDatabase

@Database(entities = [CalendarExercise::class], version = 1, exportSchema = false)
abstract class CalendarExerciseRoomDataBase:RoomDatabase(){
    abstract fun calendarExerciseDao():CalendarExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: CalendarExerciseRoomDataBase? = null

        fun getDatabase(context: Context): CalendarExerciseRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalendarExerciseRoomDataBase::class.java,
                    "calendar_exercise_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance

            }
        }
    }
}