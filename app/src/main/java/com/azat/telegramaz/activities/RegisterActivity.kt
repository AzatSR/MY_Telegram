package com.azat.telegramaz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azat.telegramaz.R
import com.azat.telegramaz.databinding.ActivityRegisterBinding
import com.azat.telegramaz.ui.screens.register.EnterPhoneNumberFragment
import com.azat.telegramaz.database.initFireBase
import com.azat.telegramaz.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFireBase()
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}