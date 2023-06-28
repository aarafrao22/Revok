package com.revok.project.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "database")
data class ModelClass(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String
)
