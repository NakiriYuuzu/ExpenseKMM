package net.yuuzu.expanseapp.core.local

expect class ShareStorage {
    fun putString(key: String, value: String)
    fun getString(key: String): String?
}