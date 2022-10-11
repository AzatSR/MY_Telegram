package com.azat.telegramaz.ui.fragments

import androidx.fragment.app.Fragment
import com.azat.telegramaz.R
import com.azat.telegramaz.utilits.APP_ACTIVITY

class ChatsFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Чаты"
    }
}