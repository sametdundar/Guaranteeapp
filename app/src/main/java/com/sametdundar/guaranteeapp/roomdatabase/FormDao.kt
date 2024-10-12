package com.sametdundar.guaranteeapp.roomdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FormDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormData(formData: FormData)

    @Query("SELECT * FROM form_data")
    fun getAllFormData(): LiveData<List<FormData>>
}
