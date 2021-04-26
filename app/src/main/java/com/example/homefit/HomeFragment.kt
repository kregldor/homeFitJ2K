package com.example.homefit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.homefit.WorkoutRepositoryImpl
import com.example.homefit.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    var repo: WorkoutRepository = WorkoutRepositoryImpl()
    var allWorkout = MutableLiveData<List<Workout>>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.workoutButton.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_home_to_workout_types) }

    }
}