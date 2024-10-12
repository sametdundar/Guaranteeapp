package com.sametdundar.guaranteeapp.roomdatabase

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sametdundar.guaranteeapp.MyApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private var repository: FormRepository, private val application: MyApplication) : ViewModel() {


    val allUsers: LiveData<List<FormData>>

    init {
        val userDao = AppDatabase.getDatabase(application).formDao()
        repository = FormRepository(userDao)
        allUsers = repository.allUsers
    }

    fun saveFormData(name: String, email: String, phoneNumber: String, address: String, imageUris: List<String>) {

        val formData = FormData.from(name, email, phoneNumber, address, imageUris)

        viewModelScope.launch {
            repository.insertForm(formData)
        }
    }
}
