package com.example.homefit

import android.annotation.SuppressLint
import java.io.Serializable
import java.util.*

class Step(val name: String, val sets: Int, val reps: Int, val image: String, description: String?) : Serializable {
    var description: String? = null
    @SuppressLint("NewApi")
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Step) return false
        val step = o
        return sets == step.sets && reps == step.reps && name == step.name && image == step.image &&
                description == step.description
    }

    @SuppressLint("NewApi")
    override fun hashCode(): Int {
        return Objects.hash(name, sets, reps, image, description)
    }

    override fun toString(): String {
        return "Step{" +
                "name='" + name + '\'' +
                ", sets=" + sets +
                ", reps=" + reps +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}'
    }

    init {
        if (description != null) {
            this.description = description
        }
    }
}