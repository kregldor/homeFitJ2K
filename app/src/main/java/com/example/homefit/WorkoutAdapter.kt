package com.example.homefit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homefit.WorkoutAdapter.StepViewHolder
import com.example.homefit.databinding.StepViewBinding
import com.squareup.picasso.Picasso

class WorkoutAdapter internal constructor(private val listener: MyOnClickListener) : ListAdapter<Step, StepViewHolder>(StepItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val binding = StepViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class StepViewHolder internal constructor(var binding: StepViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(step: Step?) {
            binding.stepDescription.text = step!!.name
            binding.sets.text = step.sets.toString() + "x"
            binding.reps.text = step.reps.toString()
            Picasso.get().load(step.image).fit().centerCrop().into(binding.stepImg)
            itemView.setOnClickListener { v: View? -> listener.onClick(step) }
        }
    }

    internal interface MyOnClickListener {
        fun onClick(step: Step)
    }
}

internal class StepItemDiffCallback : DiffUtil.ItemCallback<Step>() {
    override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem == newItem
    }
}