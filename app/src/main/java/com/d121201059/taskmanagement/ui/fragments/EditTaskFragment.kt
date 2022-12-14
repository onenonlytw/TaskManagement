package com.d121201059.taskmanagement.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.d121201059.taskmanagement.Model.Task
import com.d121201059.taskmanagement.R
import com.d121201059.taskmanagement.ViewModel.TaskViewModel
import com.d121201059.taskmanagement.databinding.FragmentEditTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditTaskFragment : Fragment() {

    val oldtask by navArgs<EditTaskFragmentArgs>()

    lateinit var binding: FragmentEditTaskBinding
    var priority: String = "1"
    val viewModel : TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditTaskBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.editTitle.setText(oldtask.data.title)
        binding.editSubtitle.setText(oldtask.data.subtitle)
        binding.editDescription.setText(oldtask.data.description)

        when (oldtask.data.priority) {
            "1" -> {
                priority = "1"
                binding.pBlue.setImageResource(R.drawable.ic_done)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pGreen.setImageResource(R.drawable.ic_done)
                binding.pBlue.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pYellow.setImageResource(R.drawable.ic_done)
                binding.pBlue.setImageResource(0)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "4" -> {
                priority = "4"
                binding.pRed.setImageResource(R.drawable.ic_done)
                binding.pBlue.setImageResource(0)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
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

        binding.btnEditSaveTask.setOnClickListener {
            updateTask(it)
        }

        return binding.root
    }

    private fun updateTask(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubtitle.text.toString()
        val description = binding.editDescription.text.toString()

        val d = Date()
        val taskDate: CharSequence = DateFormat.format("MMMM d, yyy", d.time)

        val data = Task(
            oldtask.data.id,
            title = title,
            subtitle = subTitle,
            description = description,
            date = taskDate.toString(),
            priority
        )

        Log.e("@@@@@", "updateTask: Title : $title SubTitle : $subTitle Description : $description")
        viewModel.updateTask(data)

        Toast.makeText(requireActivity(), "Task Updated Successfully!", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editTaskFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete) {

            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener {
                viewModel.deleteTask(oldtask.data.id!!)
                bottomSheet.dismiss()
            }

            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}