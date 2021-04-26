package com.example.homefit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homefit.databinding.FragmentWorkoutTypesBinding
import java.util.*

class WorkoutTypesFragment : Fragment() {
    var binding: FragmentWorkoutTypesBinding? = null
    private var viewModel: WorkoutTypesViewModel? = null
    private val workoutTypesAdapter = WorkoutTypesAdapter(object: WorkoutTypesAdapter.MyOnClickListener{
        override fun onClick(workout: Workout) {
            val action: NavDirections = WorkoutTypesFragmentDirections.actionWorkoutTypesToWorkout(workout)
            if (view != null) {
                Navigation.findNavController(requireView()).navigate(action)
            }
        }

    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWorkoutTypesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(WorkoutTypesViewModel::class.java)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.init()
        binding!!.recyclerView.adapter = workoutTypesAdapter
        binding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel!!.errorMessage.observe(viewLifecycleOwner, { message: String? -> binding!!.errorMessage.text = message })
        viewModel!!.selectedWorkout.observe(viewLifecycleOwner, { workouts: List<Workout?> -> workoutTypesAdapter.submitList(workouts) })
        val listPopupWindow = ListPopupWindow(requireContext(), null, R.attr.listPopupWindowStyle)
        val items = Arrays.asList(Type.UPPER_BODY.label, Type.FULL_BODY.label, Type.LOWER_BODY.label)
        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(requireContext(), R.layout.list_popup_window_item, items as List<Any?>)
        listPopupWindow.anchorView = binding!!.listPopupButton
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.setOnItemClickListener { parent, view, position, id ->
            viewModel!!.filterWorkouts(items[position])
            listPopupWindow.dismiss()
        }
        binding!!.listPopupButton.setOnClickListener { v: View? -> listPopupWindow.show() }
        setupToolbar()
    }

    fun setupToolbar() {
        binding!!.myToolbar.setNavigationOnClickListener { v: View? -> Navigation.findNavController(v!!).popBackStack() }
    }
}