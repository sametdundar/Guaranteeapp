package com.sametdundar.guaranteeapp.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> getParcelableData(arguments: Bundle?, key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        arguments?.getParcelable(key)
    }
}
