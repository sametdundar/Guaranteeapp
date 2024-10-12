package com.sametdundar.guaranteeapp.roomdatabase

import androidx.lifecycle.LiveData
import javax.inject.Inject

class FormRepository @Inject constructor(private val formDao: FormDao) {

    suspend fun insertForm(form: FormData) {
        formDao.insertFormData(form)
    }

    val allUsers: LiveData<List<FormData>> = formDao.getAllFormData()

}
