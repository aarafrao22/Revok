package com.revok.project.database

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.revok.project.models.StringModel

@Dao
interface DAO {
    @Insert
    suspend fun insertData(data: ModelClass)

    @Delete
    suspend fun removeData(data: ModelClass)

    @Query("SELECT * FROM database")
    fun getAllData(): MutableLiveData<MutableList<StringModel>?>?
    // ... other methods
}
