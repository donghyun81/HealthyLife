package com.example.healthylife.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Exercise::class],version = 1,exportSchema = false)
abstract class ExerciseRoomDatabase: RoomDatabase() {
    abstract fun exerciseDao():ExerciseDao

    companion object{
        @Volatile
        private var INSTANCE:ExerciseRoomDatabase?=null

        fun getDatabase(context: Context) :ExerciseRoomDatabase{
            return INSTANCE ?: synchronized(this ){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseRoomDatabase::class.java,
                    "exercise_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}