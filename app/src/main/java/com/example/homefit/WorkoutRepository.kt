package com.example.homefit

import rx.Observable

interface WorkoutRepository {
    val workouts: Observable<List<Workout>>
}