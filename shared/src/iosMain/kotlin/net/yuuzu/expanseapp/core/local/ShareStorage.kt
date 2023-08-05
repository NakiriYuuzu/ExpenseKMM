package net.yuuzu.expanseapp.core.local

import platform.Foundation.NSUserDefaults

actual class ShareStorage {
    private val userDefaults = NSUserDefaults.standardUserDefaults()

    actual fun getString(key: String): String? = userDefaults.stringForKey(key)

    actual fun putString(key: String, value: String) {
        userDefaults.setObject(value, key)
    }
}