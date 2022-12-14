package com.d121201059.taskmanagement.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.d121201059.taskmanagement.Model.Task
import com.d121201059.taskmanagement.R
import com.d121201059.taskmanagement.ViewModel.TaskViewModel
import com.d121201059.taskmanagement.databinding.FragmentHomeBinding
import com.d121201059.taskmanagement.ui.Adapter.TaskAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: TaskViewModel by viewModels()
    var oldMyTask = arrayListOf<Task>()
    lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvAllTask.layoutManager = staggeredGridLayoutManager

        viewModel.getTask().observe(viewLifecycleOwner, { taskList ->
            oldMyTask =
                taskList as ArrayList<Task> /* = java.util.ArrayList<com.d121201059.taskmanagement.Model.Task> */
            adapter = TaskAdapter(requireContext(), taskList)
            binding.rcvAllTask.adapter = adapter
        })

        binding.filterPentingMendesak.setOnClickListener {
            viewModel.getPentingMendesak().observe(viewLifecycleOwner, { taskList ->
                oldMyTask = taskList as ArrayList<Task>
                adapter = TaskAdapter(requireContext(), taskList)
                binding.rcvAllTask.adapter = adapter
            })
        }

        binding.allTask.setOnClickListener {
            viewModel.getTask().observe(viewLifecycleOwner, { taskList ->
                oldMyTask = taskList as ArrayList<Task>
                adapter = TaskAdapter(requireContext(), taskList)
                binding.rcvAllTask.adapter = adapter
            })
        }

        binding.filterPentingTidakMendesak.setOnClickListener {
            viewModel.getPentingTidakMendesak().observe(viewLifecycleOwner, { taskList ->
                oldMyTask = taskList as ArrayList<Task>
                adapter = TaskAdapter(requireContext(), taskList)
                binding.rcvAllTask.adapter = adapter
            })
        }

        binding.filterTidakPentingMendesak.setOnClickListener {
            viewModel.getTidakPentingMendesak().observe(viewLifecycleOwner, { taskList ->
                oldMyTask = taskList as ArrayList<Task>
                adapter = TaskAdapter(requireContext(), taskList)
                binding.rcvAllTask.adapter = adapter
            })
        }

        binding.filterTidakPentingTidakMendesak.setOnClickListener {
            viewModel.getTidakPentingTidakMendesak().observe(viewLifecycleOwner, { taskList ->
                oldMyTask = taskList as ArrayList<Task>
                adapter = TaskAdapter(requireContext(), taskList)
                binding.rcvAllTask.adapter = adapter
            })
        }

        binding.btnAddTask.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createTaskFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView

        searchView.queryHint = "Enter Task Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                TaskFilterin(p0)
                return true
            }
        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun TaskFilterin(p0: String?) {

        val newFilteredList = arrayListOf<Task>()

        for (i in oldMyTask) {
            if (i.title.contains(p0!!) || i.subtitle.contains(p0!!)) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)

    }
}