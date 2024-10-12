package com.sametdundar.guaranteeapp.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val repository: FormRepository) : ViewModel() {

    fun saveFormData(name: String, email: String, phoneNumber: String, address: String, imageUris: List<String>) {

        val formData = FormData.from(name, email, phoneNumber, address, imageUris)

        viewModelScope.launch {
            repository.insertForm(formData)
        }
    }
}
