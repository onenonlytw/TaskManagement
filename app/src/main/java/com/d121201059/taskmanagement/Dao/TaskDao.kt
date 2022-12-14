package com.d121201059.taskmanagement.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.d121201059.taskmanagement.Model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getTask(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE priority=4")
    fun getPentingMendesakTask(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE priority=3")
    fun getTidakPentingMendesakTask(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE priority=2")
    fun getPentingTidakMendesakTask(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE priority=1")
    fun getTidakPentingTidakMendesakTask(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Query("DELETE FROM Task WHERE id=:id")
    fun deleteTask(id: Int)

    @Update
    fun updateTask(task: Task)
}