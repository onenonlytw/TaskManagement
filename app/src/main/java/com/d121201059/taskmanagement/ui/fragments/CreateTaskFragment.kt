package com.d121201059.taskmanagement.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.d121201059.taskmanagement.Model.Task
import com.d121201059.taskmanagement.R
import com.d121201059.taskmanagement.ViewModel.TaskViewModel
import com.d121201059.taskmanagement.databinding.FragmentCreateTaskBinding
import java.util.*

class CreateTaskFragment : Fragment() {

    lateinit var binding: FragmentCreateTaskBinding
    var priority: String = "1"
    val viewModel : TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCreateTaskBinding.inflate(layoutInflater, container, false)

        binding.btnSaveTask.setOnClickListener {
            createTask(it)
        }

        binding.pBlue.setOnClickListener {
            priority = "1"
            binding.pBlue.setImageResource(R.drawable.ic_done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pGreen.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(R.drawable.ic_done)
            binding.pBlue.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.ic_done)
            binding.pBlue.setImageResource(0)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "4"
            binding.pRed.setImageResource(R.drawable.ic_done)
            binding.pBlue.setImageResource(0)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        return binding.root
    }

    private fun createTask(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubtitle.text.toString()
        val description = binding.editDescription.text.toString()

        val d = Date()
        val taskDate: CharSequence = DateFormat.format("MMMM d, yyy", d.time)

        val data = Task(
            null,
            title = title,
            subtitle = subTitle,
            description = description,
            date = taskDate.toString(),
            priority
        )
        viewModel.addTask(data)

        Toast.makeText(requireActivity(), "Task Created Successfully!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createTaskFragment_to_homeFragment)
    }
}