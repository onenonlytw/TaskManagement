package com.d121201059.taskmanagement.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.d121201059.taskmanagement.Dao.TaskDao
import com.d121201059.taskmanagement.Model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun myTaskDao(): TaskDao

    companion object {
        var INSTANCE: TaskDatabase?=null

        fun getDatabaseInstance(context: Context): TaskDatabase {
            val  tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context,
                        TaskDatabase::class.java,
                        "Task"
                    ).allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}