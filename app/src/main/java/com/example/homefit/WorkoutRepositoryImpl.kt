package com.example.homefit

import rx.Observable

class WorkoutRepositoryImpl : WorkoutRepository {
    var workoutApi = RetrofitClientInstance().retrofitInstance.create(WorkoutApi::class.java)
    override val workouts: Observable<List<Workout>>
        get() = workoutApi.workouts
}