package com.example.homefit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homefit.WorkoutTypesAdapter.WorkoutViewHolder
import com.example.homefit.databinding.WorkoutViewBinding
import com.squareup.picasso.Picasso

class WorkoutTypesAdapter internal constructor(private val listener: MyOnClickListener) : ListAdapter<Workout, WorkoutViewHolder>(CharacterItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val binding = WorkoutViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class WorkoutViewHolder internal constructor(var binding: WorkoutViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(workout: Workout?) {
            binding.workoutName.text = workout!!.name.toUpperCase()
            binding.workoutLevel.text = workout.level.label
            binding.workoutDuration.text = workout.duration.toString() + " min"
            Picasso.get().load(workout.image).fit().centerCrop().into(binding.workoutImg)
            itemView.setOnClickListener { v: View? -> listener.onClick(workout) }
        }
    }

    internal interface MyOnClickListener {
        fun onClick(workout: Workout)
    }
}

internal class CharacterItemDiffCallback : DiffUtil.ItemCallback<Workout>() {
    override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem.equals(newItem)
    }
}