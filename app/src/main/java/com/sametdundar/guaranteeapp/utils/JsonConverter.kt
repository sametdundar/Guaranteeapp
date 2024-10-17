package com.sametdundar.guaranteeapp.utils

import android.content.Context
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream

object JsonConverter {
    val gson = Gson()

    // Nesneyi JSON formatına dönüştür
    fun <T> toJson(data: T): String {
        return gson.toJson(data)
    }

    // JSON'dan nesne oluştur
    inline fun <reified T> fromJson(json: String): T {
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }

    // JSON'dan liste oluştur
    inline fun <reified T> fromJsonList(json: String): List<T> {
        return gson.fromJson(json, object : TypeToken<List<T>>() {}.type)
    }

    fun copyUriToInternalStorage(context: Context, uri: Uri): Uri? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            val file = File(context.getExternalFilesDir(null), "image_${System.currentTimeMillis()}.jpg")
            val outputStream = FileOutputStream(file)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            return Uri.fromFile(file) // Kalıcı URI döndürülüyor
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}

