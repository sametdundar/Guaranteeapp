package com.sametdundar.guaranteeapp.roomdatabase

import javax.inject.Inject

class FormRepository @Inject constructor(private val formDao: FormDao) {

    suspend fun insertForm(form: FormData) {
        formDao.insertFormData(form)
    }

    suspend fun getAllForms(): List<FormData> {
        return formDao.getAllFormData()
    }
}
