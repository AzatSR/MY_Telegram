package com.azat.telegramaz.utilits

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AppValueEventListener(val onSucess: (DataSnapshot) -> Unit): ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        onSucess(snapshot)
    }

    override fun onCancelled(error: DatabaseError) {

    }

}