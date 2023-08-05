package net.yuuzu.expanseapp.core.local

import android.content.Context

actual class ShareStorage(
    private val context: Context
) {
    private val sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    actual fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    actual fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}