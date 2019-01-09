package com.atlabs.medusalib.SharedPreferences

import android.content.Context
import android.util.Log

fun getAppState(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("appState", false)
}

fun setAppState(context: Context, value: Boolean) {
    val sharedPreferences =  context.getSharedPreferences("data", Context.MODE_PRIVATE)
    sharedPreferences.edit()
        .putBoolean("appState", value)
        .apply()
}