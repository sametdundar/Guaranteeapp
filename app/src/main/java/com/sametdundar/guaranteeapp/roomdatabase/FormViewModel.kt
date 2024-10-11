package com.sametdundar.guaranteeapp.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FormViewModel(private val dao: FormDao) : ViewModel() {

    fun saveFormData(name: String, email: String, phoneNumber: String, address: String, imageUris: List<String>) {
        viewModelScope.launch {
            val formData = FormData.from(name, email, phoneNumber, address, imageUris)
            dao.insertFormData(formData)
        }
    }
}
