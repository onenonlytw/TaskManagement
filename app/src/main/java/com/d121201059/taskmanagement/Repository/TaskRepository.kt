package com.d121201059.taskmanagement.Repository

import androidx.lifecycle.LiveData
import com.d121201059.taskmanagement.Dao.TaskDao
import com.d121201059.taskmanagement.Model.Task

class TaskRepository (val  dao: TaskDao) {

    fun getAllTask(): LiveData<List<Task>> = dao.getTask()

    fun getTidakPentingTidakMendesak(): LiveData<List<Task>> = dao.getTidakPentingTidakMendesakTask()

    fun getPentingTidakMendesak(): LiveData<List<Task>> = dao.getPentingTidakMendesakTask()

    fun getTidakPentingMendesak(): LiveData<List<Task>> = dao.getTidakPentingMendesakTask()

    fun getPentingMendesak(): LiveData<List<Task>> = dao.getPentingMendesakTask()

    fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    fun deleteTask(id: Int) {
        dao.deleteTask(id)
    }

    fun updateTask(task: Task) {
        dao.updateTask(task)
    }

}