package com.d121201059.taskmanagement.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Task")
class Task (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var subtitle: String,
    var description: String,
    var date: String,
    var priority: String,
):Parcelable