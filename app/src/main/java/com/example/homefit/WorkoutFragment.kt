package com.example.homefit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homefit.databinding.FragmentWorkoutBinding
import com.squareup.picasso.Picasso

class WorkoutFragment : Fragment() {
    private var binding: FragmentWorkoutBinding? = null
    private var viewModel: WorkoutViewModel? = null
    private val workoutAdapter = WorkoutAdapter(object : WorkoutAdapter.MyOnClickListener {
        override fun onClick(step: Step) {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle(step.name)
            builder.setMessage(step.description)
            val alert = builder.create()
            if (alert != null) {
                alert.show()
            }
        }

    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.recyclerView.adapter = workoutAdapter
        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        val args = WorkoutFragmentArgs.fromBundle(requireArguments()).workout
        viewModel!!.setWorkout(args)
        viewModel!!.workout.observe(viewLifecycleOwner, { workout: Workout ->
            Picasso.get().load(workout.image).fit().centerCrop().into(binding!!.image)
            binding!!.toolbar.title = workout.name.toUpperCase()
            binding!!.toolbar.subtitle = workout.duration.toString() + " minutes"
            workoutAdapter.submitList(workout.steps)
        })
        setupToolbar()
    }

    fun setupToolbar() {
        binding!!.toolbar.setNavigationOnClickListener { v: View? -> Navigation.findNavController(v!!).popBackStack() }
    }
}