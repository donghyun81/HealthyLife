package com.example.healthylife.data.exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name="exercise")
    val exercise:String,
    @ColumnInfo(name="set")
    val set:Int,
    @ColumnInfo(name="weight")
    val weight:Int,
    @ColumnInfo(name="count")
    val count:Int
) {
}