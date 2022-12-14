package com.d121201059.taskmanagement.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.d121201059.taskmanagement.Database.TaskDatabase
import com.d121201059.taskmanagement.Model.Task
import com.d121201059.taskmanagement.Repository.TaskRepository

class TaskViewModel(application: Application): AndroidViewModel(application) {
    val repository: TaskRepository

    init {
        val  dao = TaskDatabase.getDatabaseInstance(application).myTaskDao()
        repository = TaskRepository(dao)
    }

    fun addTask(task: Task) {
        repository.insertTask(task)
    }

    fun getTask(): LiveData<List<Task>> = repository.getAllTask()

    fun getTidakPentingTidakMendesak(): LiveData<List<Task>> = repository.getTidakPentingTidakMendesak()

    fun getPentingTidakMendesak(): LiveData<List<Task>> = repository.getPentingTidakMendesak()

    fun getTidakPentingMendesak(): LiveData<List<Task>> = repository.getTidakPentingMendesak()

    fun getPentingMendesak(): LiveData<List<Task>> = repository.getPentingMendesak()

    fun deleteTask(id: Int) {
        repository.deleteTask(id)
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
    }
}