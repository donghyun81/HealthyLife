package com.example.healthylife.ui.check

import androidx.lifecycle.*
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.data.exercise.ExerciseDao
import kotlinx.coroutines.launch

class CheckViewModel(private val exerciseDao: ExerciseDao): ViewModel() {

    val allExercises:LiveData<List<Exercise>> = exerciseDao.getExercises().asLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text



    private fun getNewExerciseEntry(
                                 exercise:String,
                                 set: String,
                                 weight:String,
                                 count: String): Exercise {
        return Exercise(
            exercise=exercise,
            set=set.toInt(),
            weight = weight.toInt(),
            count = count.toInt()
        )
    }

    fun addNewExercise(exercise:String,
                       set: String,
                       weight:String,
                       count: String) {
        val newExercise = getNewExerciseEntry(exercise, set, weight, count)
        insertItem(newExercise)
    }

    private fun insertItem(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.insert(exercise)
        }
    }




    private fun getUpdatedExerciseEntry(
        id:Int,
        exercise:String,
        set: String,
        weight:String,
        count: String
    ): Exercise {
        return Exercise(
            id=id,
            exercise=exercise,
            set=set.toInt(),
            weight = weight.toInt(),
            count = count.toInt()
        )
    }


    fun updateExercise(
        id:Int,
        exercise:String,
        set: String,
        weight:String,
        count: String
    ) {
        val updateExercise= getUpdatedExerciseEntry(id,exercise,set,weight,count)
        updateExercise(updateExercise)
    }

    private fun updateExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.update(exercise)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            exerciseDao.delete(exercise)
        }
    }
    fun isEntryVaild(exercise:String,set:String,weight: String,count: String):Boolean {
        if(exercise.isBlank()||set.isBlank()||weight.isBlank()||count.isBlank()) {
            return false
        }
        return true
    }

    fun retrieveExercise(id:Int):LiveData<Exercise> {
        return exerciseDao.getExercise(id).asLiveData()

    }
}





class CheckViewModelFactory(private val exerciseDao: ExerciseDao) :ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(CheckViewModel::class.java)) {

            return CheckViewModel(exerciseDao) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }
}

