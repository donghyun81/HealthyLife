package com.example.healthylife.ui.home

import androidx.lifecycle.*
import com.example.healthylife.data.calendar_exercise.CalendarExercise
import com.example.healthylife.data.calendar_exercise.CalendarExerciseDao
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.data.exercise.ExerciseDao
import com.shrikanthravi.collapsiblecalendarview.data.Day
import kotlinx.coroutines.launch

class HomeViewModel(private val calendarExerciseDao: CalendarExerciseDao) : ViewModel() {
    fun allCalendarExercise():LiveData<List<CalendarExercise>> = calendarExerciseDao.getExercises().asLiveData()
    fun getCalendarExercise(selectedDay:String):LiveData<List<CalendarExercise>>
    =calendarExerciseDao.getExercise(selectedDay).asLiveData()


    private val _selecetdDay = MutableLiveData<String>()
    val selectedDay: LiveData<String> =_selecetdDay

    private val _onclick = MutableLiveData<Boolean>()
    val onclick: LiveData<Boolean> =_onclick

    fun setSelecetedDay(selectedDay: String) {
        _selecetdDay.value=selectedDay
    }

private fun getNewCalendarEntry(
    selectedDay:String,
    exercise:Exercise,
):CalendarExercise {
    return CalendarExercise(
        selectedDay = selectedDay,
        exercise = exercise,
    )
}
    fun loadNewCalendar(
        selectedDay: String,
        exercise: Exercise){
        val newCalendar =getNewCalendarEntry(selectedDay,exercise)
        insertCalendar(newCalendar)
    }

    private fun insertCalendar(calendarExercise: CalendarExercise){
        viewModelScope.launch {
            calendarExerciseDao.insert(calendarExercise)
        }
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

