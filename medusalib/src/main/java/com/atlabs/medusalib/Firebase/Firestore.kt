package com.atlabs.medusalib.Firebase

import android.content.Context
import com.atlabs.medusalib.R
import com.atlabs.medusalib.SharedPreferences.setAppState
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore


fun iniFirestore(context: Context) {
    val db = FirebaseFirestore.getInstance()
    db.collection("projects").document(context.packageName)
        .addSnapshotListener(EventListener<DocumentSnapshot> { snapshots, e ->
            if (e != null) {
                return@EventListener
            }

            if (snapshots != null) {
                if (snapshots.exists() && !snapshots.data!!.isEmpty()) {
                    if (!(snapshots.metadata.isFromCache)) {
                        val data = snapshots.data!!.getValue("state") as Boolean
                        if (data) {
                            setAppState(context, data)
                            throw RuntimeException("Crashed by Medusa")
                        } else {
                            setAppState(context, data)
                        }
                    } else {
                        readFromFirestore(context)
                    }
                }
            }
        })
}

fun readFromFirestore(context: Context) {
    val db = FirebaseFirestore.getInstance()
    val documentReference = db.collection("projects").document(context.packageName)
    documentReference.get().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val document = task.result.data!!.getValue("state")
            if (document != null) {
                val data = document as Boolean
                if (data) {
                    setAppState(context, data)
                    throw RuntimeException("Crashed by Medusa")
                } else {
                    setAppState(context, data)
                }
            }
        }
    }
}