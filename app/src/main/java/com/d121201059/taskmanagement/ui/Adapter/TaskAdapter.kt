package com.d121201059.taskmanagement.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.d121201059.taskmanagement.Model.Task
import com.d121201059.taskmanagement.R
import com.d121201059.taskmanagement.databinding.ItemTaskBinding
import com.d121201059.taskmanagement.ui.fragments.HomeFragment
import com.d121201059.taskmanagement.ui.fragments.HomeFragmentDirections

class TaskAdapter(val requireContext: Context, var taskList: List<Task>) : RecyclerView.Adapter<TaskAdapter.taskViewHolder>() {

    fun filtering(newFilteredList: ArrayList<Task>) {
        taskList = newFilteredList
        notifyDataSetChanged()
    }

    class taskViewHolder( val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
       return taskViewHolder(
           ItemTaskBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val data = taskList[position]
        holder.binding.taskTitle.text = data.title
        holder.binding.taskSubTitle.text = data.subtitle
        holder.binding.taskDate.text = data.date

        when (data.priority) {
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.blue_dot)
            }
            "2" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "3" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "4" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditTaskFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = taskList.size

}