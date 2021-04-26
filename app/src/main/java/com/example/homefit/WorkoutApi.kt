package com.example.homefit

import retrofit2.http.GET
import rx.Observable

interface WorkoutApi {
    @get:GET("/list")
    val workouts: Observable<List<Workout>>
}