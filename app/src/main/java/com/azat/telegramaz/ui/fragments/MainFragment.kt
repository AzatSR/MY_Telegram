package com.azat.telegramaz.ui.fragments

import androidx.fragment.app.Fragment
import com.azat.telegramaz.R
import com.azat.telegramaz.utilits.APP_ACTIVITY
import com.azat.telegramaz.utilits.hideKeyboard

class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Телеграм"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }
}