package com.sametdundar.guaranteeapp.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "form_data")
data class FormData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val imageUris: String // JSON formatında URI'leri tutacağız
) {
//    // URI listesini dönüştürmek için yardımcı fonksiyonlar
//    fun getImageUris(): List<String> {
//        val listType = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(imageUris, listType) ?: emptyList()
//    }

    companion object {
        fun from(name: String, email: String, phoneNumber: String, address: String, imageUris: List<String>): FormData {
            val imageUrisJson = Gson().toJson(imageUris) // URI'leri JSON string'e çeviriyoruz
            return FormData(name = name, email = email, phoneNumber = phoneNumber, address = address, imageUris = imageUrisJson)
        }
    }
}
