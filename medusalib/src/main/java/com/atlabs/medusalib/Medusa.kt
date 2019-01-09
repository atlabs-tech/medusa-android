package com.atlabs.medusalib

import android.content.Context
import android.util.Log
import com.atlabs.medusalib.Firebase.iniFirestore
import com.atlabs.medusalib.Network.isNetworkAvailable
import com.atlabs.medusalib.SharedPreferences.getAppState


class Medusa(private val context: Context) {

    fun start() {
        if (isNetworkAvailable(context)) {
            iniFirestore(context)
        } else {
            if (getAppState(context)) {
                throw RuntimeException("Crashed by Medusa")
            }

        }
    }


}