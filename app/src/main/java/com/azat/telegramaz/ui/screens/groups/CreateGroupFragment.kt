package com.azat.telegramaz.ui.screens.groups

import androidx.recyclerview.widget.RecyclerView
import com.azat.telegramaz.R
import com.azat.telegramaz.models.CommonModel
import com.azat.telegramaz.ui.screens.base.BaseFragment
import com.azat.telegramaz.utilits.APP_ACTIVITY
import com.azat.telegramaz.utilits.hideKeyboard
import com.azat.telegramaz.utilits.showToast
import kotlinx.android.synthetic.main.fragment_create_group.*

class CreateGroupFragment(private var listContacts: List<CommonModel>) :
    BaseFragment(R.layout.fragment_create_group) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = getString(R.string.create_group)
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        initRecyclerView()
        create_group_btn_complete.setOnClickListener { showToast("Click here, Azat") }
        create_group_input_name.requestFocus()
    }

    private fun initRecyclerView() {
        mRecyclerView = create_group_recycler_view
        mAdapter = AddContactsAdapter()
        mRecyclerView.adapter = mAdapter
        listContacts.forEach {
            mAdapter.updateListItems(it)
        }
    }
}