package com.example.healthylife.ui.home

import androidx.lifecycle.*
import com.example.healthylife.data.calendar_exercise.CalendarExercise
import com.example.healthylife.data.calendar_exercise.CalendarExerciseDao

class HomeViewModel(private val calendarExerciseDao: CalendarExerciseDao) : ViewModel() {
    val allExercise:LiveData<List<CalendarExercise>> = calendarExerciseDao.getExercises().asLiveData()

    fun getCalendarExercise(selectedDay:String):LiveData<CalendarExercise>{
        return calendarExerciseDao.getExercise(selectedDay).asLiveData()
    }




}

class HomeViewModelFactory(private val calendarExerciseDao: CalendarExerciseDao):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(calendarExerciseDao) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }

}