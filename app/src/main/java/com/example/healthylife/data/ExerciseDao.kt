package com.example.healthylife.data

import androidx.room.*

import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDao {

    @Query("select* from Exercise Order By exercise")
    fun getExercises(): Flow<List<Exercise>>

    @Query("SELECT * from Exercise WHERE id = :id")
    fun getExercise(id: Int): Flow<Exercise>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)

    @Update
    suspend fun update(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)


}