package com.example.homefit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homefit.NetworkState.HttpErrors.*
import com.example.homefit.Type.Companion.fromString
import retrofit2.HttpException
import rx.schedulers.Schedulers
import java.util.*

class WorkoutTypesViewModel : ViewModel() {
    var workoutRepository: WorkoutRepository = WorkoutRepositoryImpl()
    var allWorkout = MutableLiveData<List<Workout>>()
    var selectedWorkout = MutableLiveData<List<Workout>>()
    var errorMessage = MutableLiveData<String>()
    fun init() {
//        workoutRepository.getWorkouts().subscribeOn(Schedulers.io()).subscribe(value ->
//                allWorkout.postValue(value)
//        );
//
//        workoutRepository.getWorkouts().subscribeOn(Schedulers.io()).subscribe(value ->
//                selectedWorkout.postValue(value)
//        );
        workouts
    }

    private val workouts: Unit
        private get() {
            val result = workoutRepository.workouts
            workoutRepository
                    .workouts
                    .doOnError { error: Throwable -> handleError(error) }
                    .subscribeOn(Schedulers.io()).subscribe { value ->
                        if (value[0] is Workout) {
                            allWorkout.postValue(value)
                            selectedWorkout.postValue(value)
                        }
                    }
        }

    private fun handleError(error: Throwable) {
        val networkState: NetworkState
        if (error is HttpException) {
            val message = error.message()
            when (error.code()) {
                403 -> {
                    networkState = ResourceForbidden(message)
                    handleMessage(networkState)
                }
                404 -> {
                    networkState = ResourceNotFound(message)
                    handleMessage(networkState)
                }
                500 -> {
                    networkState = InternalServerError(message)
                    handleMessage(networkState)
                }
                502 -> {
                    networkState = BadGateWay(message)
                    handleMessage(networkState)
                }
                301 -> {
                    networkState = ResourceRemoved(message)
                    handleMessage(networkState)
                }
                302 -> {
                    networkState = RemovedResourceFound(message)
                    handleMessage(networkState)
                }
                else -> {
                    networkState = NetworkState.Error("Network error, try it again")
                    handleMessage(networkState)
                }
            }
        }
    }

    private fun handleMessage(networkState: NetworkState) {
        errorMessage.postValue(networkState.getMessage())
    }

    fun filterWorkouts(selected: String?) {
        val selectedType = fromString(selected!!)
        filter(selectedType)
    }

    private fun filter(type: Type?) {
        val temp: MutableList<Workout> = ArrayList()
        for (w in allWorkout.value!!) {
            if (w.type === type) {
                temp.add(w)
            }
        }
        selectedWorkout.postValue(temp)
    }
}