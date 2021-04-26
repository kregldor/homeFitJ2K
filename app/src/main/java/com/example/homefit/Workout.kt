package com.example.homefit

import android.annotation.SuppressLint
import java.io.Serializable
import java.util.*

class Workout(val name: String, val duration: Int, val level: Level, val type: Type, val description: String, val trainer: String?, val image: String, val steps: List<Step>?) : Serializable {
    @SuppressLint("NewApi")
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Workout) return false
        val workout = o
        return duration == workout.duration && name == workout.name && level == workout.level && type == workout.type && description == workout.description &&
                trainer == workout.trainer && image == workout.image &&
                steps == workout.steps
    }

    @SuppressLint("NewApi")
    override fun hashCode(): Int {
        return Objects.hash(name, duration, level, type, description, trainer, image, steps)
    }

    override fun toString(): String {
        return "Workout{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", level=" + level +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", trainer='" + trainer + '\'' +
                ", image='" + image + '\'' +
                ", steps=" + steps +
                '}'
    }
}

enum class Level(val label: String) {


    BEGINNER("beginner"), ADVANCED("advanced");
}

enum class Type(val label: String) {
    UPPER_BODY("upper body"), LOWER_BODY("lower body"), FULL_BODY("full body");

    companion object {
        @JvmStatic
        fun fromString(text: String): Type? {
            for (b in values()) {
                if (b.label == text) {
                    return b
                }
            }
            return null
        }
    }
}