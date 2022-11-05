package com.azat.telegramaz


import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.azat.telegramaz.activities.RegisterActivity
import com.azat.telegramaz.database.AUTH
import com.azat.telegramaz.database.initFireBase
import com.azat.telegramaz.database.initUser
import com.azat.telegramaz.databinding.ActivityMainBinding
import com.azat.telegramaz.ui.screens.main_list.MainListFragment
import com.azat.telegramaz.ui.objects.AppDrawer
import com.azat.telegramaz.ui.screens.register.EnterPhoneNumberFragment
import com.azat.telegramaz.utilits.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
   Приложение-мессенджер, клон Telegram.
*/

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer
    private var test = "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        /** Функция запускается один раз при создании activity */
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFireBase()
        initUser{
            CoroutineScope(Dispatchers.IO).launch {
                initContacts()
            }
            initFields()
            initFunc()
        }
    }

    private fun initFunc() {
        /** Функция инициализирует функциональность приложения */
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(MainListFragment(), false)
        } else {
            replaceFragment(EnterPhoneNumberFragment(), false)
        }
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer()
    }

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
            initContacts()
        }
    }
}