package com.example.homefit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutViewModel : ViewModel() {
    @JvmField
    var workout = MutableLiveData<Workout>()
    fun setWorkout(workout: Workout) {
        this.workout.postValue(workout)
    }
}